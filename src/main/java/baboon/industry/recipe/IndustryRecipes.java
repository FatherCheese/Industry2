package baboon.industry.recipe;

import baboon.industry.block.IndustryBlocks;
import baboon.industry.item.IndustryItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.crafting.LookupFuelFurnace;
import net.minecraft.core.data.DataLoader;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeNamespace;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.item.ItemStack;
import sunsetsatellite.catalyst.energy.api.LookupFuelEnergy;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class IndustryRecipes implements RecipeEntrypoint {
    public static final RecipeNamespace INDUSTRY = new RecipeNamespace();
    public static final RecipeGroup<RecipeEntryCrafting<?, ?>> WORKBENCH = new RecipeGroup<>(new RecipeSymbol(new ItemStack(Block.workbench)));
    @Override
    public void onRecipesReady() {
        INDUSTRY.register("workbench", WORKBENCH);
        Registries.ITEM_GROUPS.register("industry:rubber_logs", Registries.stackListOf(IndustryBlocks.logRubberWood, IndustryBlocks.logRubberWoodResin, IndustryBlocks.logRubberWoodResinFull));
        Registries.ITEM_GROUPS.register("industry:ores_copper", Registries.stackListOf(IndustryBlocks.oreCopperBasalt, IndustryBlocks.oreCopperStone, IndustryBlocks.oreCopperLimestone, IndustryBlocks.oreCopperGranite));
        Registries.ITEM_GROUPS.register("industry:ores_tin", Registries.stackListOf(IndustryBlocks.oreTinBasalt, IndustryBlocks.oreTinStone, IndustryBlocks.oreTinLimestone, IndustryBlocks.oreTinGranite));
        Registries.RECIPES.register("industry", INDUSTRY);
        DataLoader.loadRecipes("/assets/industry/recipes/workbench/items.json");
        DataLoader.loadRecipes("/assets/industry/recipes/workbench/blocks.json");
        DataLoader.loadRecipes("/assets/industry/recipes/furnace.json");
        DataLoader.loadRecipes("/assets/industry/recipes/blast_furnace.json");
        LookupFuelFurnace.instance.addFuelEntry(IndustryItems.dustCoal.id, 1600);
        LookupFuelEnergy.fuelEnergy().addFuelEntry(IndustryItems.dustCoal.id, 8);
    }
}
