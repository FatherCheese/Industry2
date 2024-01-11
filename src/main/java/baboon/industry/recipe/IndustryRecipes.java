package baboon.industry.recipe;

import baboon.industry.block.IndustryBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.data.DataLoader;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeNamespace;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class IndustryRecipes implements RecipeEntrypoint {

    /*private void furnaceRecipes() {
        LookupFuelFurnace.instance.addFuelEntry(IndustryItems.dustCoal.id, 1600);
        LookupFuelEnergy.fuelEnergy().addFuelEntry(IndustryItems.dustCoal.id, 8);

        RecipeHelper.smeltingManager.addSmelting(IndustryBlocks.logRubberWood.id, new ItemStack(Item.coal, 1, 1));
        RecipeHelper.smeltingManager.addSmelting(IndustryItems.resin.id, new ItemStack(IndustryItems.rubber));

        RecipeHelper.smeltingManager.addSmelting(IndustryBlocks.oreTinStone.id, new ItemStack(IndustryItems.ingotTin));
        RecipeHelper.smeltingManager.addSmelting(IndustryBlocks.oreTinBasalt.id, new ItemStack(IndustryItems.ingotTin));
        RecipeHelper.smeltingManager.addSmelting(IndustryBlocks.oreTinLimestone.id, new ItemStack(IndustryItems.ingotTin));
        RecipeHelper.smeltingManager.addSmelting(IndustryBlocks.oreTinGranite.id, new ItemStack(IndustryItems.ingotTin));
        RecipeHelper.smeltingManager.addSmelting(IndustryItems.oreRawTin.id, new ItemStack(IndustryItems.ingotTin));
        RecipeHelper.smeltingManager.addSmelting(IndustryItems.dustTin.id, new ItemStack(IndustryItems.ingotTin));
        RecipeHelper.smeltingManager.addSmelting(IndustryBlocks.oreCopperStone.id, new ItemStack(IndustryItems.ingotCopper));
        RecipeHelper.smeltingManager.addSmelting(IndustryBlocks.oreCopperBasalt.id, new ItemStack(IndustryItems.ingotCopper));
        RecipeHelper.smeltingManager.addSmelting(IndustryBlocks.oreCopperLimestone.id, new ItemStack(IndustryItems.ingotCopper));
        RecipeHelper.smeltingManager.addSmelting(IndustryBlocks.oreCopperGranite.id, new ItemStack(IndustryItems.ingotCopper));
        RecipeHelper.smeltingManager.addSmelting(IndustryItems.oreRawCopper.id, new ItemStack(IndustryItems.ingotCopper));
        RecipeHelper.smeltingManager.addSmelting(IndustryItems.dustCopper.id, new ItemStack(IndustryItems.ingotCopper));
        RecipeHelper.smeltingManager.addSmelting(IndustryItems.dustBronze.id, new ItemStack(IndustryItems.ingotBronze));
        RecipeHelper.smeltingManager.addSmelting(IndustryItems.dustIron.id, new ItemStack(Item.ingotIron));
        RecipeHelper.smeltingManager.addSmelting(IndustryItems.dustGold.id, new ItemStack(Item.ingotGold));

        RecipeHelper.blastingManager.addSmelting(IndustryBlocks.oreTinStone.id, new ItemStack(IndustryItems.ingotTin));
        RecipeHelper.blastingManager.addSmelting(IndustryBlocks.oreTinBasalt.id, new ItemStack(IndustryItems.ingotTin));
        RecipeHelper.blastingManager.addSmelting(IndustryBlocks.oreTinLimestone.id, new ItemStack(IndustryItems.ingotTin));
        RecipeHelper.blastingManager.addSmelting(IndustryBlocks.oreTinGranite.id, new ItemStack(IndustryItems.ingotTin));
        RecipeHelper.smeltingManager.addSmelting(IndustryItems.oreRawTin.id, new ItemStack(IndustryItems.ingotTin));
        RecipeHelper.blastingManager.addSmelting(IndustryItems.dustTin.id, new ItemStack(IndustryItems.ingotTin));
        RecipeHelper.blastingManager.addSmelting(IndustryBlocks.oreCopperStone.id, new ItemStack(IndustryItems.ingotCopper));
        RecipeHelper.blastingManager.addSmelting(IndustryBlocks.oreCopperBasalt.id, new ItemStack(IndustryItems.ingotCopper));
        RecipeHelper.blastingManager.addSmelting(IndustryBlocks.oreCopperLimestone.id, new ItemStack(IndustryItems.ingotCopper));
        RecipeHelper.blastingManager.addSmelting(IndustryBlocks.oreCopperGranite.id, new ItemStack(IndustryItems.ingotCopper));
        RecipeHelper.smeltingManager.addSmelting(IndustryItems.oreRawCopper.id, new ItemStack(IndustryItems.ingotCopper));
        RecipeHelper.blastingManager.addSmelting(IndustryItems.dustCopper.id, new ItemStack(IndustryItems.ingotCopper));
        RecipeHelper.blastingManager.addSmelting(IndustryItems.dustBronze.id, new ItemStack(IndustryItems.ingotBronze));
        RecipeHelper.blastingManager.addSmelting(IndustryItems.dustIron.id, new ItemStack(Item.ingotIron));
        RecipeHelper.blastingManager.addSmelting(IndustryItems.dustGold.id, new ItemStack(Item.ingotGold));

        RecipeHelper.blastingManager.addSmelting(Block.blockIron.id, new ItemStack(Item.ingotSteelCrude, 9));
    }*/
    public static final RecipeNamespace INDUSTRY = new RecipeNamespace();
    public static final RecipeGroup<RecipeEntryCrafting<?, ?>> WORKBENCH = new RecipeGroup<>(new RecipeSymbol(new ItemStack(Block.workbench)));
    @Override
    public void onRecipesReady() {
        INDUSTRY.register("workbench", WORKBENCH);
        Registries.ITEM_GROUPS.register("industry:rubber_logs", Registries.stackListOf(IndustryBlocks.logRubberWood, IndustryBlocks.logRubberWoodResin, IndustryBlocks.logRubberWoodResinFull));
        Registries.RECIPES.register("industry", INDUSTRY);
        DataLoader.loadRecipes("/assets/industry/recipes/workbench/items.json");
        DataLoader.loadRecipes("/assets/industry/recipes/workbench/blocks.json");
//        furnaceRecipes();
    }
}
