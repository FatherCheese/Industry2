package turniplabs.industry.recipes

import net.minecraft.core.block.Block
import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemStack
import turniplabs.industry.blocks.IndustryBlocks
import turniplabs.industry.items.IndustryItems

object RecipesExtractor {
    private val recipeList = HashMap<Any?, Any?>()

    init {
        addRecipe(IndustryBlocks.rubberLog, 1)
        addRecipe(IndustryBlocks.rubberSapling, 1)
        addRecipe(IndustryItems.resin, 3)
    }

    fun addRecipe(itemInput: Item, outputCount: Int) {
        recipeList[itemInput.id] = ItemStack(IndustryItems.rubber, outputCount)
    }

    fun addRecipe(blockInput: Block, outputCount: Int) {
        recipeList[blockInput.id] = ItemStack(IndustryItems.rubber, outputCount)
    }

    fun getResult(i: Int): ItemStack? {
        return recipeList[i] as ItemStack?
    }

    fun getRecipeList(): HashMap<Any?, Any?> {
        return recipeList
    }
}