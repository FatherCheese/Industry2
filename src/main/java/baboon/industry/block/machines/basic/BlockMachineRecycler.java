package baboon.industry.block.machines.basic;

import baboon.industry.Industry2;
import baboon.industry.block.machines.basic.entity.TileEntityMachineMacerator;
import baboon.industry.block.machines.basic.entity.TileEntityMachineRecycler;
import net.minecraft.core.block.BlockTileEntityRotatable;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;
import sunsetsatellite.energyapi.interfaces.mixins.IEntityPlayer;

public class BlockMachineRecycler extends BlockTileEntityRotatable {
    private boolean keepInventory = false;
    private final String MOD_ID = Industry2.MOD_ID;

    public BlockMachineRecycler(String key, int id, Material material) {
        super(key, id, material);
    }

    @Override
    protected TileEntity getNewBlockEntity() {
        return new TileEntityMachineRecycler();
    }

    @Override
    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        if (!world.isClientSide) {
            TileEntityMachineRecycler tileEntity = (TileEntityMachineRecycler) world.getBlockTileEntity(x, y, z);

            if (tileEntity == null)
                return false;
            ((IEntityPlayer) player).displayGuiScreen_energyapi(tileEntity);
        }
        return true;
    }
}
