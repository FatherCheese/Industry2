package baboon.industry.recipe;

import baboon.industry.block.I2Blocks;
import baboon.industry.item.I2Items;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;

import java.util.HashMap;

public class RecipesMacerator {
    private static final HashMap<Integer, ItemStack> recipeList = new HashMap<>();

    public RecipesMacerator() {
        addRecipe(I2Blocks.blockTin.id, new ItemStack(I2Items.dustTin, 9));
        addRecipe(I2Blocks.oreTinStone.id, new ItemStack(I2Items.dustTin, 2));
        addRecipe(I2Blocks.oreTinBasalt.id, new ItemStack(I2Items.dustTin, 2));
        addRecipe(I2Blocks.oreTinLimestone.id, new ItemStack(I2Items.dustTin, 2));
        addRecipe(I2Blocks.oreTinGranite.id, new ItemStack(I2Items.dustTin, 2));
        addRecipe(I2Items.oreRawTin.id, new ItemStack(I2Items.dustTin, 2));
        addRecipe(I2Items.ingotTin.id, new ItemStack(I2Items.dustTin, 1));

        addRecipe(I2Blocks.blockCopper.id, new ItemStack(I2Items.dustCopper, 9));
        addRecipe(I2Blocks.oreCopperStone.id, new ItemStack(I2Items.dustCopper, 2));
        addRecipe(I2Blocks.oreCopperBasalt.id, new ItemStack(I2Items.dustCopper, 2));
        addRecipe(I2Blocks.oreCopperLimestone.id, new ItemStack(I2Items.dustCopper, 2));
        addRecipe(I2Blocks.oreCopperGranite.id, new ItemStack(I2Items.dustCopper, 2));
        addRecipe(I2Items.oreRawCopper.id, new ItemStack(I2Items.dustCopper, 2));
        addRecipe(I2Items.ingotCopper.id, new ItemStack(I2Items.dustCopper, 1));

        addRecipe(I2Blocks.blockBronze.id, new ItemStack(I2Items.dustBronze, 9));
        addRecipe(I2Items.ingotBronze.id, new ItemStack(I2Items.dustBronze, 1));

        addRecipe(Block.blockIron.id, new ItemStack(I2Items.dustIron, 9));
        addRecipe(Block.oreIronStone.id, new ItemStack(I2Items.dustIron, 2));
        addRecipe(Block.oreIronBasalt.id, new ItemStack(I2Items.dustIron, 2));
        addRecipe(Block.oreIronLimestone.id, new ItemStack(I2Items.dustIron, 2));
        addRecipe(Block.oreIronGranite.id, new ItemStack(I2Items.dustIron, 2));
        addRecipe(Item.oreRawIron.id, new ItemStack(I2Items.dustIron, 2));
        addRecipe(Item.ingotIron.id, new ItemStack(I2Items.dustIron, 1));

        addRecipe(Block.blockGold.id, new ItemStack(I2Items.dustGold, 9));
        addRecipe(Block.oreGoldStone.id, new ItemStack(I2Items.dustGold, 2));
        addRecipe(Block.oreGoldBasalt.id, new ItemStack(I2Items.dustGold, 2));
        addRecipe(Block.oreGoldLimestone.id, new ItemStack(I2Items.dustGold, 2));
        addRecipe(Block.oreGoldGranite.id, new ItemStack(I2Items.dustGold, 2));
        addRecipe(Item.oreRawGold.id, new ItemStack(I2Items.dustGold, 2));
        addRecipe(Item.ingotGold.id, new ItemStack(I2Items.dustGold, 1));

        addRecipe(Block.blockCoal.id, new ItemStack(I2Items.dustCoal, 8));
        addRecipe(Item.coal.id, new ItemStack(I2Items.dustCoal, 1));
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
