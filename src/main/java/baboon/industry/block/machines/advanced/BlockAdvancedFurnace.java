package baboon.industry.block.machines.advanced;

import baboon.industry.Industry2;
import baboon.industry.block.machines.advanced.entity.TileEntityAdvancedFurnace;
import net.minecraft.core.block.BlockTileEntityRotatable;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.helper.Sides;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import sunsetsatellite.energyapi.interfaces.mixins.IEntityPlayer;
import turniplabs.halplibe.helper.TextureHelper;

public class BlockAdvancedFurnace extends BlockTileEntityRotatable {
    private boolean keepInventory = false;
    private final String MOD_ID = Industry2.MOD_ID;
    private final int[][] machineTexture = new int[][]{
            TextureHelper.getOrCreateBlockTexture(MOD_ID, "advanced_furnace.png"),
            TextureHelper.getOrCreateBlockTexture(MOD_ID, "advanced_furnace_on.png"),
            TextureHelper.getOrCreateBlockTexture(MOD_ID, "machine_casing_advanced.png")
    };

    public BlockAdvancedFurnace(String key, int id, Material material) {
        super(key, id, material);
        setupInstance(this);
    }

    @Override
    protected TileEntity getNewBlockEntity() {
        return new TileEntityAdvancedFurnace();
    }

    @Override
    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        if (!world.isClientSide) {
            TileEntityAdvancedFurnace tileEntity = (TileEntityAdvancedFurnace) world.getBlockTileEntity(x, y, z);

            if (tileEntity == null)
                return false;
            ((IEntityPlayer) player).displayGuiScreen_energyapi(tileEntity);
        }
        return true;
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

        TileEntityAdvancedFurnace tileEntity = ((TileEntityAdvancedFurnace) blockAccess.getBlockTileEntity(x, y, z));
        int metadata = blockAccess.getBlockMetadata(x, y, z);
        int index = Sides.orientationLookUpHorizontal[6 * metadata + side.getId()];
        if (index != 2)
            atlasIndices[index] = texCoordToIndex(machineTexture[2][0], machineTexture[2][1]);

        if (index == 2) {
            if (tileEntity.active)
                atlasIndices[index] = texCoordToIndex(machineTexture[1][0], machineTexture[1][1]);
            else
                atlasIndices[index] = texCoordToIndex(machineTexture[0][0], machineTexture[0][1]);
        }

        return atlasIndices[index];
    }

    // Static Methods

    private static BlockAdvancedFurnace instance = null;

    private static void setupInstance(BlockAdvancedFurnace machine) {
        instance = machine;
    }

    private static BlockAdvancedFurnace getInstance() {
        if (instance == null)
            throw new NullPointerException("Instance of BlockAdvancedFurnace hasn't been setup!");
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