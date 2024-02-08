package baboon.industry.block;

import baboon.industry.world.WorldFeatureRubberTree;
import net.minecraft.core.block.BlockSaplingBase;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;

import java.util.Random;

public class BlockSaplingRubberwood extends BlockSaplingBase {

    public BlockSaplingRubberwood(String key, int id) {
        super(key, id);
    }

    public void growTree(World world, int i, int j, int k, Random random) {
        world.setBlock(i, j, k, 0);

        WorldFeature tree = new WorldFeatureRubberTree(I2Blocks.leavesRubberWood.id,4 + random.nextInt(3 - 1) + 1);
        if (!tree.generate(world, random, i, j, k)) {
            world.setBlock(i, j, k, this.id);
        }
    }
}
