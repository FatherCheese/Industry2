package turniplabs.industry.recipes.fuels

import net.minecraft.core.item.Item
import turniplabs.industry.items.IndustryItems

object GeothermalFuel {
    private val fuelList = HashMap<Any?, Any?>()

    init {
        addFuel(Item.bucketLava.id, 2000)
        addFuel(IndustryItems.cellLava.id, 2000)
        addFuel(Item.nethercoal.id, 500)
    }

    fun addFuel(input: Int, time: Int) {
        fuelList[input] = time
    }

    fun getFuelResult(i: Int): Int? {
        return if (fuelList[i] == null) 0 else fuelList[i] as Int?
    }

    fun getFuelList(): HashMap<Any?, Any?> {
        return fuelList
    }
}