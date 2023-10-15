package turniplabs.industry.recipes

import net.minecraft.core.item.ItemStack
import turniplabs.industry.blocks.IndustryBlocks
import turniplabs.industry.items.IndustryItems

object RecipesExtractor {
    private val recipeList = HashMap<Any?, Any?>()

    init {
        addRecipe(IndustryBlocks.rubberLog.id, ItemStack(IndustryItems.rubber, 1))
        addRecipe(IndustryBlocks.rubberSapling.id, ItemStack(IndustryItems.rubber, 1))
        addRecipe(IndustryItems.resin.id, ItemStack(IndustryItems.rubber, 3))
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