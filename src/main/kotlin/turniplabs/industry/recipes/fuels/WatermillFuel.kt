package turniplabs.industry.recipes.fuels

import net.minecraft.core.item.Item
import turniplabs.industry.items.IndustryItems

object WatermillFuel {
    private val fuelList = HashMap<Any?, Any?>()

    init {
        addFuel(Item.bucketWater.id, 1000)
        addFuel(IndustryItems.cellWater.id, 1000)
    }

    fun addFuel(input: Int, yield: Int) {
        fuelList[input] = yield
    }

    fun getResult(i: Int): Int? {
        return if (fuelList[i] == null) 0 else fuelList[i] as Int?
    }

    fun getFuelList(): HashMap<Any?, Any?> {
        return fuelList
    }
}