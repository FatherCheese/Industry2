package turniplabs.industry.blocks

import net.minecraft.core.block.BlockSaplingBase
import net.minecraft.core.world.World
import net.minecraft.core.world.generate.feature.WorldFeature
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTree
import turniplabs.industry.Industry2
import java.util.*

class BlockSaplingRubber(key: String?, id: Int) : BlockSaplingBase(key, id) {

    override fun growTree(world: World, x: Int, y: Int, z: Int, random: Random?) {
        world.setBlock(x, y, z, 0)
        val tree: Any = WorldFeatureTree(Industry2.rubberLeaves.id, Industry2.rubberLog.id, 6)
        if (!(tree as WorldFeature).generate(world, random, x, y, z)) {
            world.setBlock(x, y, z, id)
        }
    }
}