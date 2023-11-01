package turniplabs.industry.recipes

import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemStack
import turniplabs.industry.items.IndustryItems

object RecipesCannery {
    private val recipeList = HashMap<Any?, Any?>()

    init {
        addRecipe(Item.foodCookie, 1)
        addRecipe(Item.foodFishRaw, 1)
        addRecipe(Item.cherry, 1)
        addRecipe(Item.foodApple, 2)
        addRecipe(Item.foodPorkchopRaw, 3)
        addRecipe(Item.foodBread, 3)
        addRecipe(Item.foodFishCooked, 3)
        addRecipe(Item.foodPorkchopCooked, 4)
        addRecipe(Item.bucketIcecream, 5)
        addRecipe(Item.foodStewMushroom, 5)
        addRecipe(Item.foodCake, 7)
        addRecipe(Item.foodAppleGold, 21)

        addSpecialRecipe(IndustryItems.uraniumIngot, IndustryItems.cellUranium, 1)
    }

    fun addRecipe(input: Item, canOutputCount: Int) {
        recipeList[input.id] = ItemStack(IndustryItems.canFull, canOutputCount)
    }

    fun addSpecialRecipe(input: Item, outputItem: Item, outputCount: Int) {
        recipeList[input.id] = ItemStack(outputItem, outputCount)
    }

    fun getResult(i: Int): ItemStack? {
        return recipeList[i] as ItemStack?
    }

    fun getRecipeList(): HashMap<Any?, Any?> {
        return recipeList
    }
}