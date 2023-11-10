package baboon.industry.recipe;

import baboon.industry.block.IndustryBlocks;
import baboon.industry.item.IndustryItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.crafting.LookupFuelFurnace;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import sunsetsatellite.energyapi.api.LookupFuelEnergy;
import turniplabs.halplibe.helper.RecipeHelper;

public class IndustryRecipes {

    private void craftingRecipesItems() {
        RecipeHelper.craftingManager.addShapelessRecipe(new ItemStack(IndustryItems.ingotTin, 9), new Object[]{
                IndustryBlocks.blockTin
        });
        RecipeHelper.craftingManager.addShapelessRecipe(new ItemStack(IndustryItems.ingotCopper, 9), new Object[]{
                IndustryBlocks.blockCopper
        });
        RecipeHelper.craftingManager.addShapelessRecipe(new ItemStack(IndustryItems.ingotUranium, 9), new Object[]{
                IndustryBlocks.blockUranium
        });

        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.plateTin, 1), false, false, false, new Object[]{
                "12",
                '1', IndustryItems.ingotTin,
                '2', IndustryItems.toolHammer
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.plateCopper, 1), false, false, false, new Object[]{
                "12",
                '1', IndustryItems.ingotCopper,
                '2', IndustryItems.toolHammer
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.plateBronze, 1), false, false, false, new Object[]{
                "12",
                '1', IndustryItems.ingotBronze,
                '2', IndustryItems.toolHammer
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.plateIron, 1), false, false, false, new Object[]{
                "12",
                '1', Item.ingotIron,
                '2', IndustryItems.toolHammer
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.plateGold, 1), false, false, false, new Object[]{
                "12",
                '1', Item.ingotGold,
                '2', IndustryItems.toolHammer
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.plateSteel, 1), false, false, false, new Object[]{
                "12",
                '1', Item.ingotSteel,
                '2', IndustryItems.toolHammer
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.itemCableTin, 2), false, false, false, new Object[]{
                "12",
                '1', IndustryItems.ingotTin,
                '2', IndustryItems.toolCutters
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.itemCableCopper, 2), false, false, false, new Object[]{
                "12",
                '1', IndustryItems.ingotCopper,
                '2', IndustryItems.toolCutters
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.itemCableGold, 2), false, false, false, new Object[]{
                "12",
                '1', Item.ingotGold,
                '2', IndustryItems.toolCutters
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.itemCableSteel, 2), false, false, false, new Object[]{
                "12",
                '1', Item.ingotSteel,
                '2', IndustryItems.toolCutters
        });

        RecipeHelper.craftingManager.addShapelessRecipe(new ItemStack(IndustryItems.itemInsulatedCableTin), new Object[]{
                IndustryItems.itemCableTin,
                IndustryItems.rubber
        });
        RecipeHelper.craftingManager.addShapelessRecipe(new ItemStack(IndustryItems.itemInsulatedCableCopper), new Object[]{
                IndustryItems.itemCableCopper,
                IndustryItems.rubber
        });
        RecipeHelper.craftingManager.addShapelessRecipe(new ItemStack(IndustryItems.itemInsulatedCableGold), new Object[]{
                IndustryItems.itemCableGold,
                IndustryItems.rubber
        });
        RecipeHelper.craftingManager.addShapelessRecipe(new ItemStack(IndustryItems.itemInsulatedCableSteel), new Object[]{
                IndustryItems.itemCableSteel,
                IndustryItems.rubber
        });

        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.toolTreetap), true, new Object[]{
                "#1#", "111", "1##",
                '1', Block.planksOak
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.toolHammer), new Object[]{
                "111", "121", "#2#",
                '1', Item.ingotIron,
                '2', Item.stick
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.toolCutters), new Object[]{
                "1#1", "#1#", "2#2",
                '1', IndustryItems.plateIron,
                '2', Item.ingotIron
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.toolWrench), new Object[]{
                "1#1", "111", "#1#",
                '1', IndustryItems.plateBronze
        });

        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.batteryRedstone), new Object[]{
                "#1#", "232", "232",
                '1', IndustryItems.itemInsulatedCableTin,
                '2', IndustryItems.plateTin,
                '3', Item.dustRedstone
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.batteryAdvanced), new Object[]{
                "#1#", "232", "242",
                '1', IndustryItems.itemInsulatedCableCopper,
                '2', IndustryItems.plateBronze,
                '3', Item.dustRedstone,
                '4', Item.sulphur
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.batteryCrystal), new Object[]{
                "121", "232", "121",
                '1', IndustryItems.itemInsulatedCableGold,
                '2', Item.dustRedstone,
                '3', Item.diamond
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.batteryLapis), new Object[]{
                "121", "343", "121",
                '1', IndustryItems.itemInsulatedCableSteel,
                '2', new ItemStack(Item.dye, 1, 4),
                '3', IndustryItems.circuitAdvanced,
                '4', Item.diamond
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.cellEmpty, 8), new Object[]{
                "#1#", "1#1", "#1#",
                '1', IndustryItems.ingotTin
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.batteryLapis, 3), new Object[]{
                "1#1", "#1#",
                '1', IndustryItems.plateTin
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.circuitBasic), new Object[]{
                "111", "232", "111",
                '1', IndustryItems.itemInsulatedCableCopper,
                '2', Item.dustRedstone,
                '3', IndustryItems.plateSteel
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.circuitAdvanced), new Object[]{
                "111", "234", "111",
                '1', IndustryItems.itemInsulatedCableGold,
                '2', Item.quartz,
                '3', IndustryItems.plateSteel,
                '4', Item.dustRedstone
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.toolChainsaw), new Object[]{
                "#11", "121", "31#",
                '1', IndustryItems.plateSteel,
                '2', IndustryItems.circuitBasic,
                '3', IndustryItems.batteryAdvanced
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.toolDrill), new Object[]{
                "#1#", "121", "131",
                '1', IndustryItems.plateSteel,
                '2', IndustryItems.circuitBasic,
                '3', IndustryItems.batteryAdvanced
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.toolDrillGold), new Object[]{
                "#1#", "121",
                '1', Item.ingotGold,
                '2', IndustryItems.toolDrill
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.toolDrillDiamond), new Object[]{
                "#1#", "121",
                '1', Item.diamond,
                '2', IndustryItems.toolDrill
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.armorHelmetHazmat), new Object[]{
                "111", "121", "#3#",
                '1', IndustryItems.rubber,
                '2', Block.glass,
                '3', new ItemStack(Item.dye, 1, 11)
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.armorChestplateHazmat), new Object[]{
                "121", "111", "111",
                '1', IndustryItems.rubber,
                '2', new ItemStack(Item.dye, 1, 11)
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.armorLeggingsHazmat), new Object[]{
                "111", "121", "1#1",
                '1', IndustryItems.rubber,
                '2', new ItemStack(Item.dye, 1, 11)
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryItems.armorBootsHazmat), new Object[]{
                "1#1", "1#1",
                '1', IndustryItems.rubber
        });
        RecipeHelper.craftingManager.addShapelessRecipe(new ItemStack(IndustryItems.reactorPlate), new Object[]{
                IndustryItems.plateSteel,
                IndustryItems.plateSteel
        });
        RecipeHelper.craftingManager.addShapelessRecipe(new ItemStack(IndustryItems.reactorPlate), new Object[]{
                Item.ingotSteel,
                Item.ingotSteel,
                IndustryItems.toolHammer
        });
    }

    private void craftingRecipesBlocks() {
        // Blocks
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.blockTin), new Object[]{
                "111", "111", "111",
                '1', IndustryItems.ingotTin
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.blockCopper), new Object[]{
                "111", "111", "111",
                '1', IndustryItems.ingotCopper
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.blockBronze), new Object[]{
                "111", "111", "111",
                '1', IndustryItems.ingotBronze
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.blockUranium), new Object[]{
                "111", "111", "111",
                '1', IndustryItems.ingotUranium
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.machineCasingBasic), new Object[]{
                "111", "121", "111",
                '1', IndustryItems.plateIron,
                '2', IndustryItems.batteryRedstone
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.machineCasingAdvanced), new Object[]{
                "111", "121", "111",
                '1', IndustryItems.plateSteel,
                '2', IndustryItems.batteryAdvanced
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.generator), new Object[]{
                "1", "2",
                '1', IndustryBlocks.machineCasingBasic,
                '2', Block.furnaceStoneIdle
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.generatorWatermill), true, new Object[]{
                "121", "232", "121",
                '1', Item.stick,
                '2', Block.planksOak,
                '3', IndustryBlocks.generator
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.generatorWindmill), new Object[]{
                "#1#", "121", "#1#",
                '1', IndustryBlocks.blockTin,
                '2', IndustryBlocks.generator
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.generatorGeothermal), new Object[]{
                "121", "121", "343",
                '1', Block.glass,
                '2', IndustryItems.cellEmpty,
                '3', IndustryItems.plateSteel,
                '4', IndustryBlocks.generator
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.generatorSolar), new Object[]{
                "121", "212", "343",
                '1', IndustryItems.dustCoal,
                '2', Block.glass,
                '3', IndustryItems.circuitBasic,
                '4', IndustryBlocks.generator
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.generatorSolar), new Object[]{
                "121", "212", "343",
                '1', Block.glass,
                '2', IndustryItems.dustCoal,
                '3', IndustryItems.circuitBasic,
                '4', IndustryBlocks.generator
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.solarArrayLV), new Object[]{
                "111", "121", "111",
                '1', IndustryBlocks.generatorSolar,
                '2', IndustryBlocks.batboxLV
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.solarArrayMV), new Object[]{
                "111", "121", "111",
                '1', IndustryBlocks.solarArrayLV,
                '2', IndustryBlocks.batboxMV
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.solarArrayHV), new Object[]{
                "111", "121", "111",
                '1', IndustryBlocks.solarArrayMV,
                '2', IndustryBlocks.batboxHV
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.solarArrayEHV), new Object[]{
                "111", "121", "111",
                '1', IndustryBlocks.solarArrayHV,
                '2', IndustryBlocks.batboxEHV
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.batboxLV), true, new Object[]{
                "121", "333", "111",
                '1', Block.planksOak,
                '2', IndustryItems.itemInsulatedCableTin,
                '3', IndustryItems.batteryRedstone
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.batboxMV), new Object[]{
                "121", "333", "111",
                '1', IndustryItems.plateBronze,
                '2', IndustryItems.itemInsulatedCableCopper,
                '3', IndustryItems.batteryAdvanced
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.batboxHV), new Object[]{
                "121", "333", "111",
                '1', IndustryItems.plateIron,
                '2', IndustryItems.itemInsulatedCableGold,
                '3', IndustryItems.batteryCrystal
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.batboxEHV), new Object[]{
                "121", "333", "111",
                '1', IndustryItems.plateSteel,
                '2', IndustryItems.itemInsulatedCableSteel,
                '3', IndustryItems.batteryLapis
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.transformerMVtoLV), true, new Object[]{
                "121", "134", "454",
                '1', IndustryItems.plateBronze,
                '2', IndustryItems.itemInsulatedCableCopper,
                '3', IndustryItems.batteryRedstone,
                '4', Block.planksOak,
                '5', IndustryItems.itemInsulatedCableTin
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.transformerHVtoMV), new Object[]{
                "121", "134", "454",
                '1', IndustryItems.plateIron,
                '2', IndustryItems.itemInsulatedCableGold,
                '3', IndustryItems.batteryAdvanced,
                '4', IndustryItems.plateBronze,
                '5', IndustryItems.itemInsulatedCableCopper
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.transformerEHVtoHV), new Object[]{
                "121", "134", "454",
                '1', IndustryItems.plateSteel,
                '2', IndustryItems.itemInsulatedCableSteel,
                '3', IndustryItems.batteryCrystal,
                '4', IndustryItems.plateIron,
                '5', IndustryItems.itemInsulatedCableGold
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.machineFurnace), new Object[]{
                "#1#", "232", "#4#",
                '1', IndustryItems.circuitBasic,
                '2', Item.dustRedstone,
                '3', IndustryBlocks.machineCasingBasic,
                '4', Block.furnaceStoneIdle
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.machineMacerator), true, new Object[]{
                "111", "232", "#4#",
                '1', Item.flint,
                '2', Block.cobbleStone,
                '3', IndustryBlocks.machineCasingBasic,
                '4', IndustryItems.circuitBasic
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.machineCompressor), true, new Object[]{
                "1#1", "121", "131",
                '1', Block.stone,
                '2', IndustryBlocks.machineCasingBasic,
                '3', IndustryItems.circuitBasic
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.machineWiremill), new Object[]{
                "1#1", "121", "#3#",
                '1', Item.ingotIron,
                '2', IndustryBlocks.machineCasingBasic,
                '3', IndustryItems.circuitBasic
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.machineExtractor), new Object[]{
                "121", "131",
                '1', IndustryItems.toolTreetap,
                '2', IndustryBlocks.machineCasingBasic,
                '3', IndustryItems.circuitBasic
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.machineRecycler), new Object[]{
                "#1#", "232",
                '1', IndustryItems.circuitBasic,
                '2', Block.dirt,
                '3', IndustryBlocks.machineCasingBasic
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.advancedMachineFurnace), new Object[]{
                "#1#", "232", "#4#",
                '1', IndustryItems.circuitAdvanced,
                '2', Item.dustRedstone,
                '3', IndustryBlocks.machineCasingAdvanced,
                '4', Block.furnaceStoneIdle
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.advancedMachineMacerator), new Object[]{
                "1", "2", "3",
                '1', Block.spikes,
                '2', IndustryBlocks.machineCasingAdvanced,
                '3', IndustryItems.circuitAdvanced
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.advancedMachineCompressor), new Object[]{
                "121", "#3#",
                '1', Block.obsidian,
                '2', IndustryBlocks.machineCasingAdvanced,
                '3', IndustryItems.circuitAdvanced
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.advancedMachineWiremill), new Object[]{
                "1", "2", "3",
                '1', Item.quartz,
                '2', IndustryBlocks.machineCasingAdvanced,
                '3', IndustryItems.circuitAdvanced
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.advancedMachineExtractor), new Object[]{
                "#1#", "232", "#4#",
                '1', IndustryItems.cellCoolant,
                '2', IndustryItems.toolTreetap,
                '3', IndustryBlocks.machineCasingAdvanced,
                '4', IndustryItems.circuitAdvanced
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.hardenedCoal), new Object[]{
                "121", "232", "121",
                '1', IndustryItems.dustCoal,
                '2', Block.blockCoal,
                '3', Block.obsidian
        });

        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.nuclearReactor), new Object[]{
                "1", "2", "3",
                '1', IndustryItems.circuitAdvanced,
                '2', IndustryBlocks.nuclearChamber,
                '3', IndustryBlocks.generator
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.nuclearChamber), new Object[]{
                "111", "121", "111",
                '1', IndustryItems.reactorPlate,
                '2', IndustryItems.batteryLapis
        });
        RecipeHelper.craftingManager.addRecipe(new ItemStack(IndustryBlocks.energyFabricator), true, new Object[]{
                "#1#", "232", "#4#",
                '1', Block.netherrack,
                '2', IndustryItems.circuitAdvanced,
                '3', IndustryBlocks.nuclearChamber,
                '4', Block.stone
        });

        RecipeHelper.Crafting.createShapelessRecipe(Block.planksOakPainted, 4, new Object[]{IndustryBlocks.logRubberWood});

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
    }

    public void initializeRecipes() {
        craftingRecipesItems();
        craftingRecipesBlocks();
        furnaceRecipes();
    }
}
