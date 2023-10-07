package turniplabs.industry.recipes

import net.minecraft.core.item.ItemStack
import turniplabs.industry.Industry2

object RecipesExtractor {
    private val recipeList = HashMap<Any?, Any?>()

    init {
        addRecipe(Industry2.rubberLog.id, ItemStack(Industry2.rubber, 1))
        addRecipe(Industry2.rubberSapling.id, ItemStack(Industry2.rubber, 1))
        addRecipe(Industry2.resin.id, ItemStack(Industry2.rubber, 3))
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