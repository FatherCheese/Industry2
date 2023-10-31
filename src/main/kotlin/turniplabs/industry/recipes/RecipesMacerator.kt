package turniplabs.industry.recipes

import net.minecraft.core.block.Block
import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemStack
import turniplabs.industry.blocks.IndustryBlocks
import turniplabs.industry.items.IndustryItems

object RecipesMacerator {
    private val recipeList = HashMap<Any?, Any?>()

    init {
        addRecipe(IndustryBlocks.oreCopperStone, IndustryItems.copperDust, 2)
        addRecipe(IndustryBlocks.oreCopperBasalt, IndustryItems.copperDust, 2)
        addRecipe(IndustryBlocks.oreCopperLimestone, IndustryItems.copperDust, 2)
        addRecipe(IndustryBlocks.oreCopperGranite, IndustryItems.copperDust, 2)
        addRecipe(IndustryBlocks.oreTinStone, IndustryItems.tinDust, 2)
        addRecipe(IndustryBlocks.oreTinBasalt, IndustryItems.tinDust, 2)
        addRecipe(IndustryBlocks.oreTinLimestone, IndustryItems.tinDust, 2)
        addRecipe(IndustryBlocks.oreTinGranite, IndustryItems.tinDust, 2)
        addRecipe(Block.oreIronStone, IndustryItems.ironDust, 2)
        addRecipe(Block.oreIronBasalt, IndustryItems.ironDust, 2)
        addRecipe(Block.oreIronLimestone, IndustryItems.ironDust, 2)
        addRecipe(Block.oreIronGranite, IndustryItems.ironDust, 2)
        addRecipe(Block.oreGoldStone, IndustryItems.goldDust, 2)
        addRecipe(Block.oreGoldBasalt, IndustryItems.goldDust, 2)
        addRecipe(Block.oreGoldLimestone, IndustryItems.goldDust, 2)
        addRecipe(Block.oreGoldGranite, IndustryItems.goldDust, 2)

        addRecipe(IndustryBlocks.copperBlock, IndustryItems.copperDust, 9)
        addRecipe(IndustryBlocks.tinBlock, IndustryItems.tinDust, 9)
        addRecipe(IndustryBlocks.bronzeBlock, IndustryItems.bronzeDust, 9)
        addRecipe(Block.blockIron, IndustryItems.ironDust, 9)
        addRecipe(Block.blockGold, IndustryItems.goldDust, 9)
        addRecipe(Block.blockCoal, IndustryItems.coalDust, 8)

        addRecipe(IndustryItems.rawCopperOre, IndustryItems.copperDust, 2)
        addRecipe(IndustryItems.rawTinOre, IndustryItems.tinDust, 2)
        addRecipe(Item.oreRawIron, IndustryItems.ironDust, 2)
        addRecipe(Item.oreRawGold, IndustryItems.goldDust, 2)
        addRecipe(IndustryItems.copperIngot, IndustryItems.copperDust, 1)
        addRecipe(IndustryItems.tinDust, IndustryItems.tinDust, 1)
        addRecipe(IndustryItems.bronzeIngot, IndustryItems.bronzeDust, 1)
        addRecipe(Item.ingotIron, IndustryItems.ironDust, 1)
        addRecipe(Item.ingotGold, IndustryItems.goldDust, 1)
        addRecipe(Item.coal, IndustryItems.coalDust, 1)
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