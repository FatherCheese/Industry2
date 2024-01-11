package baboon.industry.block.storage;

import baboon.industry.block.storage.entity.TileEntityBatboxMV;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;

public class BlockBatboxMV extends BlockBatbox {

    public BlockBatboxMV(String key, int id, Material material) {
        super(key, id, material);
    }

    @Override
    protected TileEntity getNewBlockEntity() {
        return new TileEntityBatboxMV();
    }
}
