package baboon.industry.block.reactor;

import baboon.industry.block.reactor.entity.TileEntityReactor;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;
import sunsetsatellite.energyapi.interfaces.mixins.IEntityPlayer;

public class BlockReactorChamber extends Block {

    public BlockReactorChamber(String key, int id, Material material) {
        super(key, id, material);
    }

    @Override
    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        if (!world.isClientSide) {
            TileEntityReactor tileEntity = (TileEntityReactor) world.getBlockTileEntity(x + 1, y, z);
            if (tileEntity == null)
                return false;

            ((IEntityPlayer) player).displayGuiScreen_energyapi(tileEntity);
        }
        return true;
    }
}
