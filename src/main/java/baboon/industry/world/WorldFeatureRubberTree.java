package baboon.industry.world;

import baboon.industry.block.IndustryBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTree;

import java.util.Random;

public class WorldFeatureRubberTree extends WorldFeatureTree {

    public WorldFeatureRubberTree(int leavesID, int heightMod) {
        super(leavesID, IndustryBlocks.logRubberWood.id, heightMod);
    }

    public boolean generate(World world, Random random, int x, int y, int z) {
        int randHeight = random.nextInt(3) + this.heightMod;
        boolean flag = true;
        if (y >= 1 && y + randHeight + 1 <= world.getHeightBlocks()) {
            for(int treeY = y; treeY <= y + 1 + randHeight; ++treeY) {
                byte byte0 = 1;
                if (treeY == y)
                    byte0 = 0;

                if (treeY >= y + 1 + randHeight - 2)
                    byte0 = 2;

                for(int treeX = x - byte0; treeX <= x + byte0 && flag; ++treeX)
                    for (int treeZ = z - byte0; treeZ <= z + byte0 && flag; ++treeZ)
                        if (treeY >= 0 && treeY < world.getHeightBlocks()) {
                            int j3 = world.getBlockId(treeX, treeY, treeZ);
                            if (j3 != 0 && j3 != this.leavesID)
                                flag = false;
                        } else
                            flag = false;
            }

            if (!flag)
                return false;
            else {
                int idBelow = world.getBlockId(x, y - 1, z);
                if (Block.hasTag(idBelow, BlockTags.GROWS_TREES) && y < world.getHeightBlocks() - randHeight - 1) {
                    onTreeGrown(world, x, y, z);

                    for(int k1 = y - 3 + randHeight; k1 <= y + randHeight; ++k1) {
                        int j2 = k1 - (y + randHeight);
                        int i3 = 1 - j2 / 2;

                        for(int k3 = x - i3; k3 <= x + i3; ++k3) {
                            int l3 = k3 - x;

                            for(int i4 = z - i3; i4 <= z + i3; ++i4) {
                                int j4 = i4 - z;
                                if ((Math.abs(l3) != i3 || Math.abs(j4) != i3 || random.nextInt(2) != 0 && j2 != 0)
                                        && !Block.opaqueCubeLookup[world.getBlockId(k3, k1, i4)]) {
                                    this.placeLeaves(world, k3, k1, i4, random);
                                }
                            }
                        }
                    }

                    for(int l1 = 0; l1 < randHeight; ++l1) {
                        int id = world.getBlockId(x, y + l1, z);
                        if (id == 0 || this.isLeaf(id)) {
                            if (random.nextInt(5) == 0) {
                                world.setBlockWithNotify(x, y + l1, z, IndustryBlocks.logRubberWoodResinFull.id);
                            }
                            else
                                world.setBlockWithNotify(x, y + l1, z, this.logID);
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}
