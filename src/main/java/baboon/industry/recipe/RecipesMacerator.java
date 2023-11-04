package baboon.industry.recipe;

import baboon.industry.block.IndustryBlocks;
import baboon.industry.item.IndustryItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;

import java.util.HashMap;

public class RecipesMacerator {
    private static final HashMap<Integer, ItemStack> recipeList = new HashMap<>();

    public RecipesMacerator() {
        addRecipe(IndustryBlocks.blockTin.id, new ItemStack(IndustryItems.dustTin, 9));
        addRecipe(IndustryBlocks.oreTinStone.id, new ItemStack(IndustryItems.dustTin, 2));
        addRecipe(IndustryBlocks.oreTinBasalt.id, new ItemStack(IndustryItems.dustTin, 2));
        addRecipe(IndustryBlocks.oreTinLimestone.id, new ItemStack(IndustryItems.dustTin, 2));
        addRecipe(IndustryBlocks.oreTinGranite.id, new ItemStack(IndustryItems.dustTin, 2));
        addRecipe(IndustryItems.oreRawTin.id, new ItemStack(IndustryItems.dustTin, 2));
        addRecipe(IndustryItems.ingotTin.id, new ItemStack(IndustryItems.dustTin, 1));

        addRecipe(IndustryBlocks.blockCopper.id, new ItemStack(IndustryItems.dustCopper, 9));
        addRecipe(IndustryBlocks.oreCopperStone.id, new ItemStack(IndustryItems.dustCopper, 2));
        addRecipe(IndustryBlocks.oreCopperBasalt.id, new ItemStack(IndustryItems.dustCopper, 2));
        addRecipe(IndustryBlocks.oreCopperLimestone.id, new ItemStack(IndustryItems.dustCopper, 2));
        addRecipe(IndustryBlocks.oreCopperGranite.id, new ItemStack(IndustryItems.dustCopper, 2));
        addRecipe(IndustryItems.oreRawCopper.id, new ItemStack(IndustryItems.dustCopper, 2));
        addRecipe(IndustryItems.ingotCopper.id, new ItemStack(IndustryItems.dustCopper, 1));

        addRecipe(IndustryBlocks.blockBronze.id, new ItemStack(IndustryItems.dustBronze, 9));
        addRecipe(IndustryItems.ingotBronze.id, new ItemStack(IndustryItems.dustBronze, 1));

        addRecipe(Block.blockIron.id, new ItemStack(IndustryItems.dustIron, 9));
        addRecipe(Block.oreIronStone.id, new ItemStack(IndustryItems.dustIron, 2));
        addRecipe(Block.oreIronBasalt.id, new ItemStack(IndustryItems.dustIron, 2));
        addRecipe(Block.oreIronLimestone.id, new ItemStack(IndustryItems.dustIron, 2));
        addRecipe(Block.oreIronGranite.id, new ItemStack(IndustryItems.dustIron, 2));
        addRecipe(Item.oreRawIron.id, new ItemStack(IndustryItems.dustIron, 2));
        addRecipe(Item.ingotIron.id, new ItemStack(IndustryItems.dustIron, 1));

        addRecipe(Block.blockGold.id, new ItemStack(IndustryItems.dustGold, 9));
        addRecipe(Block.oreGoldStone.id, new ItemStack(IndustryItems.dustGold, 2));
        addRecipe(Block.oreGoldBasalt.id, new ItemStack(IndustryItems.dustGold, 2));
        addRecipe(Block.oreGoldLimestone.id, new ItemStack(IndustryItems.dustGold, 2));
        addRecipe(Block.oreGoldGranite.id, new ItemStack(IndustryItems.dustGold, 2));
        addRecipe(Item.oreRawGold.id, new ItemStack(IndustryItems.dustGold, 2));
        addRecipe(Item.ingotGold.id, new ItemStack(IndustryItems.dustGold, 1));

        addRecipe(Block.blockCoal.id, new ItemStack(IndustryItems.dustCoal, 8));
        addRecipe(Item.coal.id, new ItemStack(IndustryItems.dustCoal, 1));
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
