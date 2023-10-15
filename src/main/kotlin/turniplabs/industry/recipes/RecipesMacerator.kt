package turniplabs.industry.recipes

import net.minecraft.core.block.Block
import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemStack
import turniplabs.industry.blocks.IndustryBlocks
import turniplabs.industry.items.IndustryItems

object RecipesMacerator {
    private val recipeList = HashMap<Any?, Any?>()

    init {
        addRecipe(IndustryBlocks.oreCopperStone.id, ItemStack(IndustryItems.copperDust, 2))
        addRecipe(IndustryBlocks.oreCopperBasalt.id, ItemStack(IndustryItems.copperDust, 2))
        addRecipe(IndustryBlocks.oreCopperLimestone.id, ItemStack(IndustryItems.copperDust, 2))
        addRecipe(IndustryBlocks.oreCopperGranite.id, ItemStack(IndustryItems.copperDust, 2))

        addRecipe(IndustryBlocks.oreTinStone.id, ItemStack(IndustryItems.tinDust, 2))
        addRecipe(IndustryBlocks.oreTinBasalt.id, ItemStack(IndustryItems.tinDust, 2))
        addRecipe(IndustryBlocks.oreTinLimestone.id, ItemStack(IndustryItems.tinDust, 2))
        addRecipe(IndustryBlocks.oreTinGranite.id, ItemStack(IndustryItems.tinDust, 2))

        addRecipe(Block.oreIronStone.id, ItemStack(IndustryItems.ironDust, 2))
        addRecipe(Block.oreIronBasalt.id, ItemStack(IndustryItems.ironDust, 2))
        addRecipe(Block.oreIronLimestone.id, ItemStack(IndustryItems.ironDust, 2))
        addRecipe(Block.oreIronGranite.id, ItemStack(IndustryItems.ironDust, 2))

        addRecipe(Block.oreGoldStone.id, ItemStack(IndustryItems.goldDust, 2))
        addRecipe(Block.oreGoldBasalt.id, ItemStack(IndustryItems.goldDust, 2))
        addRecipe(Block.oreGoldLimestone.id, ItemStack(IndustryItems.goldDust, 2))
        addRecipe(Block.oreGoldGranite.id, ItemStack(IndustryItems.goldDust, 2))

        addRecipe(IndustryItems.rawCopperOre.id, ItemStack(IndustryItems.copperDust, 2))
        addRecipe(IndustryItems.copperIngot.id, ItemStack(IndustryItems.copperDust))
        addRecipe(IndustryItems.rawTinOre.id, ItemStack(IndustryItems.tinDust, 2))
        addRecipe(IndustryItems.tinIngot.id, ItemStack(IndustryItems.tinDust))
        addRecipe(Item.oreRawIron.id, ItemStack(IndustryItems.ironDust, 2))
        addRecipe(Item.ingotIron.id, ItemStack(IndustryItems.ironDust))
        addRecipe(Item.oreRawGold.id, ItemStack(IndustryItems.goldDust, 2))
        addRecipe(Item.ingotGold.id, ItemStack(IndustryItems.goldDust))
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