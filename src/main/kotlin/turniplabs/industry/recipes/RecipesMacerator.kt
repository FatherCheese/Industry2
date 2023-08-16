package turniplabs.industry.recipes

import net.minecraft.core.block.Block
import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemStack
import turniplabs.industry.Industry2

object RecipesMacerator {
    private val recipeList = HashMap<Any?, Any?>()

    init {
        addRecipe(Industry2.oreCopperStone.id, ItemStack(Industry2.copperDust))
        addRecipe(Industry2.oreCopperBasalt.id, ItemStack(Industry2.copperDust))
        addRecipe(Industry2.oreCopperLimestone.id, ItemStack(Industry2.copperDust))
        addRecipe(Industry2.oreCopperGranite.id, ItemStack(Industry2.copperDust))

        addRecipe(Industry2.oreTinStone.id, ItemStack(Industry2.tinDust))
        addRecipe(Industry2.oreTinBasalt.id, ItemStack(Industry2.tinDust))
        addRecipe(Industry2.oreTinLimestone.id, ItemStack(Industry2.tinDust))
        addRecipe(Industry2.oreTinGranite.id, ItemStack(Industry2.tinDust))

        addRecipe(Block.oreIronStone.id, ItemStack(Industry2.ironDust))
        addRecipe(Block.oreIronBasalt.id, ItemStack(Industry2.ironDust))
        addRecipe(Block.oreIronLimestone.id, ItemStack(Industry2.ironDust))
        addRecipe(Block.oreIronGranite.id, ItemStack(Industry2.ironDust))

        addRecipe(Block.oreGoldStone.id, ItemStack(Industry2.goldDust))
        addRecipe(Block.oreGoldBasalt.id, ItemStack(Industry2.goldDust))
        addRecipe(Block.oreGoldLimestone.id, ItemStack(Industry2.goldDust))
        addRecipe(Block.oreGoldGranite.id, ItemStack(Industry2.goldDust))

        addRecipe(Industry2.rawCopperOre.id, ItemStack(Industry2.copperDust))
        addRecipe(Industry2.copperIngot.id, ItemStack(Industry2.copperDust))
        addRecipe(Industry2.rawTinOre.id, ItemStack(Industry2.tinDust))
        addRecipe(Industry2.tinIngot.id, ItemStack(Industry2.tinDust))
        addRecipe(Item.oreRawIron.id, ItemStack(Industry2.ironDust))
        addRecipe(Item.ingotIron.id, ItemStack(Industry2.ironDust))
        addRecipe(Item.oreRawGold.id, ItemStack(Industry2.goldDust))
        addRecipe(Item.ingotGold.id, ItemStack(Industry2.goldDust))
    }

    fun addRecipe(input: Int, output: ItemStack) {
        recipeList[input] = output
    }

    fun getResult(i: Int): ItemStack {
        return recipeList[i] as ItemStack
    }

    fun getRecipeList(): HashMap<Any?, Any?> {
        return recipeList
    }
}