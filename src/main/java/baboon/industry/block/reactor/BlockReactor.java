package baboon.industry.block.reactor;

import baboon.industry.block.reactor.entity.TileEntityReactorChamber;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;

public class BlockReactor extends BlockTileEntity {

    public BlockReactor(String key, int id, Material material) {
        super(key, id, material);
    }

    @Override
    protected TileEntity getNewBlockEntity() {
        return new TileEntityReactorChamber();
    }
}
