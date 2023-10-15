package turniplabs.industry.recipes

import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemStack
import turniplabs.industry.items.IndustryItems

object RecipesCutter {
    private val recipeList = HashMap<Any?, Any?>()

    init {
        addRecipe(IndustryItems.copperIngot.id, ItemStack(IndustryItems.itemCopperCable, 4))
        addRecipe(IndustryItems.tinIngot.id, ItemStack(IndustryItems.itemTinCable, 4))
        addRecipe(Item.ingotGold.id, ItemStack(IndustryItems.itemGoldCable, 4))
        addRecipe(Item.ingotSteel.id, ItemStack(IndustryItems.itemSteelCable, 4))
    }

    fun addRecipe(input: Int, output: ItemStack) {
        recipeList[input] = output
    }

    fun getResult(i: Int): ItemStack? {
        return recipeList[i] as ItemStack?
    }

    fun getRecipeList(): HashMap<Any?, Any?> {
        return recipeList
    }
}