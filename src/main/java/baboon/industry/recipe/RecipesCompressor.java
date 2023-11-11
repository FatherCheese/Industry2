package baboon.industry.recipe;

import baboon.industry.block.IndustryBlocks;
import baboon.industry.item.IndustryItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;

import java.util.HashMap;

public class RecipesCompressor {
    private static final HashMap<Integer, ItemStack> recipeList = new HashMap<>();

    public RecipesCompressor() {
        addRecipe(IndustryItems.ingotTin.id, new ItemStack(IndustryItems.plateTin, 2));
        addRecipe(IndustryBlocks.blockTin.id, new ItemStack(IndustryItems.plateTin, 18));

        addRecipe(IndustryItems.ingotCopper.id, new ItemStack(IndustryItems.plateCopper, 2));
        addRecipe(IndustryBlocks.blockCopper.id, new ItemStack(IndustryItems.plateCopper, 18));

        addRecipe(IndustryItems.ingotBronze.id, new ItemStack(IndustryItems.plateBronze, 2));
        addRecipe(IndustryBlocks.blockBronze.id, new ItemStack(IndustryItems.plateBronze, 18));

        addRecipe(IndustryItems.oreRawUranium.id, new ItemStack(IndustryItems.ingotUranium, 1));
        addRecipe(IndustryItems.ingotIridium.id, new ItemStack(IndustryItems.plateIridium, 1));

        addRecipe(Item.ingotIron.id, new ItemStack(IndustryItems.plateIron, 2));
        addRecipe(Block.blockIron.id, new ItemStack(IndustryItems.plateIron, 18));

        addRecipe(Item.ingotGold.id, new ItemStack(IndustryItems.plateGold, 2));
        addRecipe(Block.blockGold.id, new ItemStack(IndustryItems.plateGold, 18));

        addRecipe(Item.ingotSteel.id, new ItemStack(IndustryItems.plateSteel, 2));
        addRecipe(Block.blockSteel.id, new ItemStack(IndustryItems.plateSteel, 18));

        addRecipe(IndustryBlocks.hardenedCoal.id, new ItemStack(Item.diamond, 1));
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
