package baboon.industry.recipe;

import baboon.industry.block.I2Blocks;
import baboon.industry.item.I2Items;
import net.minecraft.core.item.ItemStack;

import java.util.HashMap;

public class RecipesExtractor {
    private static final HashMap<Integer, ItemStack> recipeList = new HashMap<>();

    public RecipesExtractor() {
        addRecipe(I2Blocks.logRubberWood.id, new ItemStack(I2Items.rubber, 1));
        addRecipe(I2Blocks.logRubberWoodResin.id, new ItemStack(I2Items.rubber, 1));
        addRecipe(I2Blocks.logRubberWoodResinFull.id, new ItemStack(I2Items.rubber, 1));
        addRecipe(I2Blocks.saplingRubberWood.id, new ItemStack(I2Items.rubber, 1));
        addRecipe(I2Items.resin.id, new ItemStack(I2Items.rubber, 3));

        addRecipe(I2Items.cellWater.id, new ItemStack(I2Items.cellCoolant, 1));
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
