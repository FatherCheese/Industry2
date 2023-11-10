package baboon.industry.recipe;

import baboon.industry.block.IndustryBlocks;
import baboon.industry.item.IndustryItems;
import net.minecraft.core.item.ItemStack;

import java.util.HashMap;

public class RecipesExtractor {
    private static final HashMap<Integer, ItemStack> recipeList = new HashMap<>();

    public RecipesExtractor() {
        addRecipe(IndustryBlocks.logRubberWood.id, new ItemStack(IndustryItems.rubber, 1));
        addRecipe(IndustryBlocks.logRubberWoodResin.id, new ItemStack(IndustryItems.rubber, 1));
        addRecipe(IndustryBlocks.logRubberWoodResinFull.id, new ItemStack(IndustryItems.rubber, 1));
        addRecipe(IndustryBlocks.saplingRubberWood.id, new ItemStack(IndustryItems.rubber, 1));
        addRecipe(IndustryItems.resin.id, new ItemStack(IndustryItems.rubber, 3));

        addRecipe(IndustryItems.cellWater.id, new ItemStack(IndustryItems.cellCoolant, 1));
    }

    public static void addRecipe(int input, ItemStack output) {
        recipeList.put(input, output);
    }

    public ItemStack getRecipeResult(int i) {
        return recipeList.get(i);
    }

    public HashMap<Integer, ItemStack> getRecipeList() {
        return recipeList;
    }
}
