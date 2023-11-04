package baboon.industry.block.generator;

import baboon.industry.Industry2;
import baboon.industry.block.generator.entity.TileEntityGeneratorGeothermal;
import net.minecraft.core.block.BlockTileEntityRotatable;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.helper.Sides;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import sunsetsatellite.energyapi.interfaces.mixins.IEntityPlayer;
import turniplabs.halplibe.helper.TextureHelper;

public class BlockGeneratorGeothermal extends BlockTileEntityRotatable {
    private boolean keepInventory = false;
    private final String MOD_ID = Industry2.MOD_ID;
    private final int[][] machineTexture = new int[][]{
            TextureHelper.getOrCreateBlockTexture(MOD_ID, "generator_geothermal.png"),
            TextureHelper.getOrCreateBlockTexture(MOD_ID, "machine_casing_basic.png")
    };

    public BlockGeneratorGeothermal(String key, int id, Material material) {
        super(key, id, material);
        setupInstance(this);
    }

    @Override
    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        if (!world.isClientSide) {
            TileEntityGeneratorGeothermal tileEntity = (TileEntityGeneratorGeothermal) world.getBlockTileEntity(x, y, z);

            if (tileEntity == null)
                return false;
            ((IEntityPlayer) player).displayGuiScreen_energyapi(tileEntity);
        }
        return true;
    }

    @Override
    protected TileEntity getNewBlockEntity() {
        return new TileEntityGeneratorGeothermal();
    }

    @Override
    public int getBlockTexture(WorldSource blockAccess, int x, int y, int z, Side side) {
                /*
        0 = bottom
        1 = top
        2 = north
        3 = south
        4 = west
        5 = east
        */

        int metadata = blockAccess.getBlockMetadata(x, y, z);
        int index = Sides.orientationLookUpHorizontal[6 * metadata + side.getId()];
        if (index != 2)
            atlasIndices[index] = texCoordToIndex(machineTexture[1][0], machineTexture[1][1]);

        if (index == 2)
            atlasIndices[index] = texCoordToIndex(machineTexture[0][0], machineTexture[0][1]);

        return atlasIndices[index];
    }

    private void dropContents(World world, int x, int y, int z) {
        TileEntityGeneratorGeothermal tileEntity = (TileEntityGeneratorGeothermal) world.getBlockTileEntity(x, y, z);
        if (tileEntity == null)
            System.out.println("Can't drop inventory at X: " + x + " Y: " + y + " Z: " + z + " because TileEntity is null");
        else {
            for (int i = 0; i < tileEntity.getSizeInventory(); ++i) {
                ItemStack itemStack = tileEntity.getStackInSlot(i);
                if (itemStack != null) {
                    EntityItem item = world.dropItem(x, y, z, itemStack);
                    item.xd *= 0.5;
                    item.yd *= 0.5;
                    item.zd *= 0.5;
                    item.delayBeforeCanPickup = 0;
                }
            }
        }
    }

    @Override
    public void onBlockRemoval(World world, int x, int y, int z) {
        dropContents(world, x, y, z);
        super.onBlockRemoval(world, x, y, z);
    }

    // Static methods
    private static BlockGeneratorGeothermal instance = null;

    private static void setupInstance(BlockGeneratorGeothermal machine) {
        instance = machine;
    }

    private static BlockGeneratorGeothermal getInstance() {
        if (instance == null)
            throw new NullPointerException("Instance of BlockGeneratorGeothermal hasn't been setup!");
        return instance;
    }

    public static void updateBlockState(boolean active, World world, int x, int y, int z) {
        int metadata = world.getBlockMetadata(x, y, z);
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        if (tileEntity == null)
            world.setBlockWithNotify(x, y, z, 0);
        else {
            getInstance().keepInventory = true;
            if (active)
                world.setBlockMetadataWithNotify(x, y, z, 1);
            if (!active)
                world.setBlockMetadataWithNotify(x, y, z, 0);

            getInstance().keepInventory = false;
            world.setBlockMetadataWithNotify(x, y, z, metadata);
            tileEntity.validate();
            world.setBlockTileEntity(x, y, z, tileEntity);
        }
    }
}
