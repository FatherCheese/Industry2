package baboon.industry.block.reactor;

import baboon.industry.block.reactor.entity.TileEntityReactorRTG;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;

public class BlockReactorRTG extends BlockTileEntity {
    public BlockReactorRTG(String key, int id) {
        super(key, id, Material.metal);
    }

    @Override
    protected TileEntity getNewBlockEntity() {
        return new TileEntityReactorRTG();
    }
}
