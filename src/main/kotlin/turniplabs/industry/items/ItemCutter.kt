package turniplabs.industry.items

import net.minecraft.core.item.Item

class ItemCutter(id: Int) : Item(id) {
    init {
        maxDamage = 128
        maxStackSize = 1
    }
}