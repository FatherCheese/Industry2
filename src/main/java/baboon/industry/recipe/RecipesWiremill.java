package baboon.industry.recipe;

import baboon.industry.block.IndustryBlocks;
import baboon.industry.item.IndustryItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;

import java.util.HashMap;

public class RecipesWiremill {
    private static final HashMap<Integer, ItemStack> recipeList = new HashMap<>();

    public RecipesWiremill() {
        addRecipe(IndustryItems.ingotTin.id, new ItemStack(IndustryItems.itemCableTin, 4));
        addRecipe(IndustryBlocks.blockTin.id, new ItemStack(IndustryItems.itemCableTin, 36));

        addRecipe(IndustryItems.ingotCopper.id, new ItemStack(IndustryItems.itemCableCopper, 4));
        addRecipe(IndustryBlocks.blockCopper.id, new ItemStack(IndustryItems.itemCableCopper, 36));

        addRecipe(Item.ingotGold.id, new ItemStack(IndustryItems.itemCableGold, 4));
        addRecipe(Block.blockGold.id, new ItemStack(IndustryItems.itemCableGold, 36));

        addRecipe(Item.ingotSteel.id, new ItemStack(IndustryItems.itemCableSteel, 4));
        addRecipe(Block.blockSteel.id, new ItemStack(IndustryItems.itemCableSteel, 36));

        addRecipe(Block.wool.id, new ItemStack(Item.string, 4));
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
