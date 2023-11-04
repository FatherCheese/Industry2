package baboon.industry.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLeavesBase;
import net.minecraft.core.block.material.Material;

public class BlockLeavesRubberwood extends BlockLeavesBase {

    public BlockLeavesRubberwood(String key, int id, Material material, boolean flag) {
        super(key, id, material, flag);
    }

    @Override
    protected Block getSapling() {
        return IndustryBlocks.saplingRubberWood;
    }
}
