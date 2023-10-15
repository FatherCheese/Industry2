package turniplabs.industry.recipes

import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemStack
import turniplabs.industry.blocks.IndustryBlocks
import turniplabs.industry.items.IndustryItems

object RecipesCompressor {
    private val recipeList = HashMap<Any?, Any?>()

    init {
        addRecipe(IndustryItems.copperIngot.id, ItemStack(IndustryItems.copperPlate, 2))
        addRecipe(IndustryItems.tinIngot.id, ItemStack(IndustryItems.tinPlate, 2))
        addRecipe(IndustryItems.bronzeIngot.id, ItemStack(IndustryItems.bronzePlate, 2))
        addRecipe(Item.ingotIron.id, ItemStack(IndustryItems.ironPlate, 2))
        addRecipe(Item.ingotGold.id, ItemStack(IndustryItems.goldPlate, 2))
        addRecipe(Item.ingotSteel.id, ItemStack(IndustryItems.steelPlate, 2))
        addRecipe(IndustryItems.rawUranium.id, ItemStack(IndustryItems.uraniumIngot))
        addRecipe(IndustryBlocks.hardenedCoal.id, ItemStack(Item.diamond))
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