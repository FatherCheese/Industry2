package baboon.industry.block.storage;

import baboon.industry.block.storage.entity.TileEntityBatboxEHV;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;

public class BlockBatboxEHV extends BlockBatbox {
    public BlockBatboxEHV(String key, int id, Material material) {
        super(key, id, material);
    }
    @Override
    protected TileEntity getNewBlockEntity() {
        return new TileEntityBatboxEHV();
    }
}
