package turniplabs.industry.recipes.fuels

import net.minecraft.core.block.Block
import net.minecraft.core.item.Item

object AdvancedRedstoneFuel {
    private val redstoneList = HashMap<Any?, Any?>()

    init {
        addRedstoneFuel(Item.dustRedstone.id, 128)
        addRedstoneFuel(Block.blockRedstone.id, 1152)
    }

    fun addRedstoneFuel(input: Int, yield: Int) {
        redstoneList[input] = yield
    }

    fun getYield(i: Int): Int? {
        return redstoneList[i] as Int?
    }

    fun getRedstoneFuel(i: Int): Int? {
        return if (redstoneList[i] == null) 0 else redstoneList[i] as Int?
    }

    fun getRedstoneFuelList(): HashMap<Any?, Any?> {
        return redstoneList
    }
}