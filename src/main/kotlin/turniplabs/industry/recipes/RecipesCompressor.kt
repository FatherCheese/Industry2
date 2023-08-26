package turniplabs.industry.recipes

import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemStack
import turniplabs.industry.Industry2

object RecipesCompressor {
    private val recipeList = HashMap<Any?, Any?>()

    init {
        addRecipe(Industry2.copperIngot.id, ItemStack(Industry2.copperPlate, 2))
        addRecipe(Industry2.tinIngot.id, ItemStack(Industry2.tinPlate, 2))
        addRecipe(Industry2.bronzeIngot.id, ItemStack(Industry2.bronzePlate, 2))
        addRecipe(Item.ingotIron.id, ItemStack(Industry2.ironPlate, 2))
        addRecipe(Item.ingotGold.id, ItemStack(Industry2.goldPlate, 2))
        addRecipe(Item.ingotSteel.id, ItemStack(Industry2.steelPlate, 2))
        addRecipe(Industry2.rawUranium.id, ItemStack(Industry2.uraniumIngot))
        addRecipe(Industry2.hardenedCoal.id, ItemStack(Item.diamond))
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