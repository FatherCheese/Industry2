package turniplabs.industry.recipes

import net.minecraft.core.block.Block
import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemStack
import turniplabs.industry.blocks.IndustryBlocks
import turniplabs.industry.items.IndustryItems

object RecipesCutter {
    private val recipeList = HashMap<Any?, Any?>()

    init {
        addRecipe(IndustryItems.copperIngot, IndustryItems.itemCopperCable, 4)
        addRecipe(IndustryItems.tinIngot, IndustryItems.itemTinCable, 4)
        addRecipe(Item.ingotGold, IndustryItems.itemGoldCable, 4)
        addRecipe(Item.ingotSteel, IndustryItems.itemSteelCable, 4)

        addRecipe(IndustryBlocks.copperBlock, IndustryItems.itemCopperCable, 36)
        addRecipe(IndustryBlocks.tinBlock, IndustryItems.itemTinCable, 36)
        addRecipe(Block.blockGold, IndustryItems.itemGoldCable, 36)
        addRecipe(Block.blockSteel, IndustryItems.itemSteelCable, 36)

        addRecipe(Block.wool, Item.string, 4)
    }

    fun addRecipe(itemInput: Item, outputItem: Item, outputCount: Int) {
        recipeList[itemInput.id] = ItemStack(outputItem, outputCount)
    }

    fun addRecipe(itemInput: Item, outputBlock: Block, outputCount: Int) {
        recipeList[itemInput.id] = ItemStack(outputBlock, outputCount)
    }

    fun addRecipe(blockInput: Block, outputItem: Item, outputCount: Int) {
        recipeList[blockInput.id] = ItemStack(outputItem, outputCount)
    }

    fun addRecipe(blockInput: Block, outputBlock: Block, outputCount: Int) {
        recipeList[blockInput.id] = ItemStack(outputBlock, outputCount)
    }

    fun getResult(i: Int): ItemStack? {
        return recipeList[i] as ItemStack?
    }

    fun getRecipeList(): HashMap<Any?, Any?> {
        return recipeList
    }
}