package turniplabs.industry.recipes

import net.minecraft.core.block.Block
import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemStack
import turniplabs.industry.blocks.IndustryBlocks
import turniplabs.industry.items.IndustryItems

object RecipesCompressor {
    private val recipeList = HashMap<Any?, Any?>()

    init {
        addRecipe(IndustryItems.copperIngot, IndustryItems.copperPlate, 2)
        addRecipe(IndustryItems.tinIngot, IndustryItems.tinPlate, 2)
        addRecipe(IndustryItems.bronzeIngot, IndustryItems.bronzePlate, 2)
        addRecipe(Item.ingotIron, IndustryItems.ironPlate, 2)
        addRecipe(Item.ingotGold, IndustryItems.goldPlate, 2)
        addRecipe(Item.ingotSteel, IndustryItems.steelPlate, 2)
        addRecipe(IndustryItems.rawUranium, IndustryItems.uraniumIngot, 1)

        addRecipe(IndustryBlocks.copperBlock, IndustryItems.copperPlate, 18)
        addRecipe(IndustryBlocks.tinBlock, IndustryItems.tinPlate, 18)
        addRecipe(IndustryBlocks.bronzeBlock, IndustryItems.bronzePlate, 18)
        addRecipe(Block.blockIron, IndustryItems.ironPlate, 18)
        addRecipe(Block.blockGold, IndustryItems.goldPlate, 18)
        addRecipe(Block.blockSteel, IndustryItems.steelPlate, 18)

        addRecipe(IndustryBlocks.hardenedCoal, Item.diamond, 1)
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