package baboon.industry.recipe;

import baboon.industry.block.IndustryBlocks;
import baboon.industry.item.IndustryItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.crafting.LookupFuelFurnace;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import sunsetsatellite.catalyst.energy.api.LookupFuelEnergy;
import turniplabs.halplibe.helper.RecipeHelper;

public class IndustryRecipes {

    private void craftingRecipesItems() {
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.upgradeSpeed), "#1#", "121", "#3#",
                '1', IndustryItems.cellCoolant,
                '2', IndustryItems.upgradePlate,
                '3', IndustryItems.circuitBasic);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.upgradeTransformer), "1#2", "#3#", "456",
                '1', IndustryItems.itemInsulatedCableCopper,
                '2', IndustryItems.itemInsulatedCableGold,
                '3', IndustryItems.upgradePlate,
                '4', IndustryItems.itemInsulatedCableSteel,
                '5', IndustryItems.circuitBasic,
                '6', IndustryItems.itemInsulatedCableTin);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.upgradeEnergy), "1", "2", "3",
                '1', IndustryItems.batteryRedstone,
                '2', IndustryItems.upgradePlate,
                '3', IndustryItems.circuitBasic);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.upgradePuller), "1", "2", "3",
                '1', Block.pistonBaseSticky,
                '2', IndustryItems.upgradePlate,
                '3', IndustryItems.circuitBasic);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.upgradePusher), "1", "2", "3",
                '1', Block.pistonBase,
                '2', IndustryItems.upgradePlate,
                '3', IndustryItems.circuitBasic);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.upgradeBlasting), "#1#", "121", "#3#",
                '1', Item.nethercoal,
                '2', IndustryItems.upgradePlate,
                '3', IndustryItems.circuitBasic);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.foodJoffos), "1", "2", "3",
                '1', new ItemStack(Item.dye, 1, 3),
                '2', Block.pumpkin,
                '3', Item.wheat);
    }

    private void craftingRecipesBlocks() {
        // Blocks
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.blockTin), "111", "111", "111",
                '1', IndustryItems.ingotTin);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.blockCopper), "111", "111", "111",
                '1', IndustryItems.ingotCopper);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.blockBronze), "111", "111", "111",
                '1', IndustryItems.ingotBronze);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.blockUranium), "111", "111", "111",
                '1', IndustryItems.ingotUranium);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.machineCasingBasic), "111", "121", "111",
                '1', IndustryItems.plateIron,
                '2', IndustryItems.batteryRedstone);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.machineCasingAdvanced), "111", "121", "111",
                '1', IndustryItems.plateSteel,
                '2', IndustryItems.batteryAdvanced);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.generator), "1", "2",
                '1', IndustryBlocks.machineCasingBasic,
                '2', Block.furnaceStoneIdle);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.generatorWatermill), true, "121", "232", "121",
                '1', Item.stick,
                '2', Block.planksOak,
                '3', IndustryBlocks.generator);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.generatorWindmill), "#1#", "121", "#1#",
                '1', IndustryBlocks.blockTin,
                '2', IndustryBlocks.generator);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.generatorGeothermal), "121", "121", "343",
                '1', Block.glass,
                '2', IndustryItems.cellEmpty,
                '3', IndustryItems.plateSteel,
                '4', IndustryBlocks.generator);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.generatorSolar), "121", "212", "343",
                '1', IndustryItems.dustCoal,
                '2', Block.glass,
                '3', IndustryItems.circuitBasic,
                '4', IndustryBlocks.generator);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.generatorSolar), "121", "212", "343",
                '1', Block.glass,
                '2', IndustryItems.dustCoal,
                '3', IndustryItems.circuitBasic,
                '4', IndustryBlocks.generator);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.solarArrayLV), "111", "121", "111",
                '1', IndustryBlocks.generatorSolar,
                '2', IndustryBlocks.batboxLV);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.solarArrayMV), "111", "121", "111",
                '1', IndustryBlocks.solarArrayLV,
                '2', IndustryBlocks.batboxMV);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.solarArrayHV), "111", "121", "111",
                '1', IndustryBlocks.solarArrayMV,
                '2', IndustryBlocks.batboxHV);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.solarArrayEHV), "111", "121", "111",
                '1', IndustryBlocks.solarArrayHV,
                '2', IndustryBlocks.batboxEHV);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.batboxLV), true, "121", "333", "111",
                '1', Block.planksOak,
                '2', IndustryItems.itemInsulatedCableTin,
                '3', IndustryItems.batteryRedstone);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.batboxMV), "121", "333", "111",
                '1', IndustryItems.plateBronze,
                '2', IndustryItems.itemInsulatedCableCopper,
                '3', IndustryItems.batteryAdvanced);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.batboxHV), "121", "333", "111",
                '1', IndustryItems.plateIron,
                '2', IndustryItems.itemInsulatedCableGold,
                '3', IndustryItems.batteryCrystal);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.batboxEHV), "121", "333", "111",
                '1', IndustryItems.plateSteel,
                '2', IndustryItems.itemInsulatedCableSteel,
                '3', IndustryItems.batteryLapis);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.transformerMVtoLV), true, "121", "134", "454",
                '1', IndustryItems.plateBronze,
                '2', IndustryItems.itemInsulatedCableCopper,
                '3', IndustryItems.batteryRedstone,
                '4', Block.planksOak,
                '5', IndustryItems.itemInsulatedCableTin);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.transformerHVtoMV), "121", "134", "454",
                '1', IndustryItems.plateIron,
                '2', IndustryItems.itemInsulatedCableGold,
                '3', IndustryItems.batteryAdvanced,
                '4', IndustryItems.plateBronze,
                '5', IndustryItems.itemInsulatedCableCopper);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.transformerEHVtoHV), "121", "134", "454",
                '1', IndustryItems.plateSteel,
                '2', IndustryItems.itemInsulatedCableSteel,
                '3', IndustryItems.batteryCrystal,
                '4', IndustryItems.plateIron,
                '5', IndustryItems.itemInsulatedCableGold);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.machineFurnace), "#1#", "232", "#4#",
                '1', IndustryItems.circuitBasic,
                '2', Item.dustRedstone,
                '3', IndustryBlocks.machineCasingBasic,
                '4', Block.furnaceStoneIdle);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.machineMacerator), true, "111", "232", "#4#",
                '1', Item.flint,
                '2', Block.cobbleStone,
                '3', IndustryBlocks.machineCasingBasic,
                '4', IndustryItems.circuitBasic);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.machineCompressor), true, "1#1", "121", "131",
                '1', Block.stone,
                '2', IndustryBlocks.machineCasingBasic,
                '3', IndustryItems.circuitBasic);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.machineWiremill), "1#1", "121", "#3#",
                '1', Item.ingotIron,
                '2', IndustryBlocks.machineCasingBasic,
                '3', IndustryItems.circuitBasic);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.machineExtractor), "121", "131",
                '1', IndustryItems.toolTreetap,
                '2', IndustryBlocks.machineCasingBasic,
                '3', IndustryItems.circuitBasic);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.machineRecycler), "#1#", "232",
                '1', IndustryItems.circuitBasic,
                '2', Block.dirt,
                '3', IndustryBlocks.machineCasingBasic);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.machineTrommel), "1", "2", "3",
                '1', IndustryBlocks.machineCasingBasic,
                '2', Block.trommelIdle,
                '3', IndustryItems.circuitBasic);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.advancedMachineFurnace), "#1#", "232", "#4#",
                '1', IndustryItems.circuitAdvanced,
                '2', Item.dustRedstone,
                '3', IndustryBlocks.machineCasingAdvanced,
                '4', Block.furnaceStoneIdle);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.advancedMachineMacerator), "1", "2", "3",
                '1', Block.spikes,
                '2', IndustryBlocks.machineCasingAdvanced,
                '3', IndustryItems.circuitAdvanced);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.advancedMachineCompressor), "121", "#3#",
                '1', Block.obsidian,
                '2', IndustryBlocks.machineCasingAdvanced,
                '3', IndustryItems.circuitAdvanced);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.advancedMachineWiremill), "1", "2", "3",
                '1', Item.quartz,
                '2', IndustryBlocks.machineCasingAdvanced,
                '3', IndustryItems.circuitAdvanced);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.advancedMachineExtractor), "#1#", "232", "#4#",
                '1', IndustryItems.cellCoolant,
                '2', IndustryItems.toolTreetap,
                '3', IndustryBlocks.machineCasingAdvanced,
                '4', IndustryItems.circuitAdvanced);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.hardenedCoal), "121", "232", "121",
                '1', IndustryItems.dustCoal,
                '2', Block.blockCoal,
                '3', Block.obsidian);

        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.nuclearReactor), "1", "2", "3",
                '1', IndustryItems.circuitAdvanced,
                '2', IndustryBlocks.nuclearChamber,
                '3', IndustryBlocks.generator);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.nuclearChamber), "111", "121", "111",
                '1', IndustryItems.reactorPlate,
                '2', IndustryItems.batteryLapis);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.nuclearIO), "1", "2", "1",
                '1', IndustryItems.itemInsulatedCableSteel,
                '2', IndustryBlocks.nuclearChamber);
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.energyFabricator), true, "121", "343", "151",
                '1', IndustryItems.circuitAdvanced,
                '2', Block.netherrack,
                '3', Block.blockRedstone,
                '4', IndustryBlocks.nuclearChamber,
                '5', Block.stone);

        RecipeHelper.craftingManager.addShapelessRecipe(new ItemStack(Block.planksOakPainted, 4, 15), IndustryBlocks.logRubberWood);
        RecipeHelper.craftingManager.addShapelessRecipe(new ItemStack(Block.planksOakPainted, 4, 15), IndustryBlocks.logRubberWoodResin);
        RecipeHelper.craftingManager.addShapelessRecipe(new ItemStack(Block.planksOakPainted, 4, 15), IndustryBlocks.logRubberWoodResinFull);

        RecipeHelper.Crafting.createShapelessRecipe(Block.obsidian, 1, new Object[]{IndustryItems.cellWater, IndustryItems.cellLava});
    }

    private void furnaceRecipes() {
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
    }

    public void initializeRecipes() {
        craftingRecipesItems();
        craftingRecipesBlocks();
        furnaceRecipes();
    }
}
