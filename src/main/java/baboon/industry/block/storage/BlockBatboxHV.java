package baboon.industry.block.storage;

import baboon.industry.block.storage.entity.TileEntityBatboxHV;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;

public class BlockBatboxHV extends BlockBatbox {
    public BlockBatboxHV(String key, int id, Material material) {
        super(key, id, material);
    }

    @Override
    protected TileEntity getNewBlockEntity() {
        return new TileEntityBatboxHV();
    }
}
