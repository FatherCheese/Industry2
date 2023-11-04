package baboon.industry.block;

import net.minecraft.core.block.BlockSaplingBase;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTree;

import java.util.Random;

public class BlockSaplingRubberwood extends BlockSaplingBase {

    public BlockSaplingRubberwood(String key, int id) {
        super(key, id);
    }

    @Override
    public void growTree(World world, int x, int y, int z, Random random) {
        new WorldFeatureTree(IndustryBlocks.leavesRubberWood.id, IndustryBlocks.leavesRubberWood.id, 7).generate(world, random, x, y, z);
    }
}
