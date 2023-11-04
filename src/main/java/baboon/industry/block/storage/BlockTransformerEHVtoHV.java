package baboon.industry.block.storage;

import baboon.industry.block.storage.entity.TileEntityTransformerEHVtoHV;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;

public class BlockTransformerEHVtoHV extends BlockTileEntity {

    public BlockTransformerEHVtoHV(String key, int id, Material material) {
        super(key, id, material);
    }

    @Override
    protected TileEntity getNewBlockEntity() {
        return new TileEntityTransformerEHVtoHV();
    }
}
