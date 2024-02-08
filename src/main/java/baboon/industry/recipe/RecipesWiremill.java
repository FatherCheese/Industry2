package baboon.industry.recipe;

import baboon.industry.block.I2Blocks;
import baboon.industry.item.I2Items;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;

import java.util.HashMap;

public class RecipesWiremill {
    private static final HashMap<Integer, ItemStack> recipeList = new HashMap<>();

    public RecipesWiremill() {
        addRecipe(I2Items.ingotTin.id, new ItemStack(I2Items.itemCableTin, 4));
        addRecipe(I2Blocks.blockTin.id, new ItemStack(I2Items.itemCableTin, 36));

        addRecipe(I2Items.ingotCopper.id, new ItemStack(I2Items.itemCableCopper, 4));
        addRecipe(I2Blocks.blockCopper.id, new ItemStack(I2Items.itemCableCopper, 36));

        addRecipe(Item.ingotGold.id, new ItemStack(I2Items.itemCableGold, 4));
        addRecipe(Block.blockGold.id, new ItemStack(I2Items.itemCableGold, 36));

        addRecipe(Item.ingotSteel.id, new ItemStack(I2Items.itemCableSteel, 4));
        addRecipe(Block.blockSteel.id, new ItemStack(I2Items.itemCableSteel, 36));

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
