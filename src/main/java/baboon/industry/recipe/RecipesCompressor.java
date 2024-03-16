package baboon.industry.recipe;

import baboon.industry.block.I2Blocks;
import baboon.industry.item.I2Items;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;

import java.util.HashMap;

public class RecipesCompressor {
    private static final HashMap<Integer, ItemStack> recipeList = new HashMap<>();

    public RecipesCompressor() {
        addRecipe(I2Items.ingotTin.id, new ItemStack(I2Items.plateTin, 2));
        addRecipe(I2Blocks.blockTin.id, new ItemStack(I2Items.plateTin, 18));

        addRecipe(I2Items.ingotCopper.id, new ItemStack(I2Items.plateCopper, 2));
        addRecipe(I2Blocks.blockCopper.id, new ItemStack(I2Items.plateCopper, 18));

        addRecipe(I2Items.ingotBronze.id, new ItemStack(I2Items.plateBronze, 2));
        addRecipe(I2Blocks.blockBronze.id, new ItemStack(I2Items.plateBronze, 18));

        addRecipe(I2Items.ingotIridium.id, new ItemStack(I2Items.plateIridium, 1));

        addRecipe(Item.ingotIron.id, new ItemStack(I2Items.plateIron, 2));
        addRecipe(Block.blockIron.id, new ItemStack(I2Items.plateIron, 18));

        addRecipe(Item.ingotGold.id, new ItemStack(I2Items.plateGold, 2));
        addRecipe(Block.blockGold.id, new ItemStack(I2Items.plateGold, 18));

        addRecipe(Item.ingotSteel.id, new ItemStack(I2Items.plateSteel, 2));
        addRecipe(Block.blockSteel.id, new ItemStack(I2Items.plateSteel, 18));

        addRecipe(I2Blocks.hardenedCoal.id, new ItemStack(Item.diamond, 1));
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
