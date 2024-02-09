package baboon.industry.recipe;

import baboon.industry.block.I2Blocks;
import baboon.industry.item.I2Items;
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

public class I2Recipes implements RecipeEntrypoint {
    public static final RecipeNamespace INDUSTRY = new RecipeNamespace();
    public static final RecipeGroup<RecipeEntryCrafting<?, ?>> WORKBENCH = new RecipeGroup<>(new RecipeSymbol(new ItemStack(Block.workbench)));
    @Override
    public void onRecipesReady() {
        INDUSTRY.register("workbench", WORKBENCH);
        Registries.ITEM_GROUPS.register("industry:rubber_logs", Registries.stackListOf(I2Blocks.logRubberWood, I2Blocks.logRubberWoodResin, I2Blocks.logRubberWoodResinFull));
        Registries.ITEM_GROUPS.register("industry:ores_copper", Registries.stackListOf(I2Blocks.oreCopperBasalt, I2Blocks.oreCopperStone, I2Blocks.oreCopperLimestone, I2Blocks.oreCopperGranite));
        Registries.ITEM_GROUPS.register("industry:ores_tin", Registries.stackListOf(I2Blocks.oreTinBasalt, I2Blocks.oreTinStone, I2Blocks.oreTinLimestone, I2Blocks.oreTinGranite));
        Registries.RECIPES.register("industry", INDUSTRY);
        DataLoader.loadRecipes("/assets/industry/recipes/workbench/items.json");
        DataLoader.loadRecipes("/assets/industry/recipes/workbench/blocks.json");
        DataLoader.loadRecipes("/assets/industry/recipes/furnace.json");
        DataLoader.loadRecipes("/assets/industry/recipes/blast_furnace.json");
        LookupFuelFurnace.instance.addFuelEntry(I2Items.dustCoal.id, 1600);
        LookupFuelEnergy.fuelEnergy().addFuelEntry(I2Items.dustCoal.id, 8);
    }
}
