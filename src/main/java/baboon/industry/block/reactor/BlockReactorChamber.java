package baboon.industry.block.reactor;

import baboon.industry.block.reactor.entity.TileEntityReactorNew;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import sunsetsatellite.catalyst.Catalyst;

public class BlockReactorChamber extends Block {

    public BlockReactorChamber(String key, int id, Material material) {
        super(key, id, material);
    }

    @Override
    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        if (!world.isClientSide) {
            Side[] sides = new Side[]{Side.NORTH, Side.SOUTH, Side.EAST, Side.WEST, Side.BOTTOM, Side.TOP};
            for (Side side : sides) {
                int reactorX = x + side.getOffsetX();
                int reactorY = y + side.getOffsetY();
                int reactorZ = z + side.getOffsetZ();
                TileEntity tileEntity = world.getBlockTileEntity(reactorX, reactorY, reactorZ);

                if (!(tileEntity instanceof TileEntityReactorNew) || !((TileEntityReactorNew) tileEntity).isAssembled())
                    continue;
                Catalyst.displayGui(player, tileEntity, ((TileEntityReactorNew) tileEntity).getInvName());
                break;
            }
        }
        return true;
    }
}
