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
        RecipeHelper.Crafting.createShapelessRecipe(IndustryItems.ingotTin, 9, new Object[]{IndustryBlocks.blockTin});
        RecipeHelper.Crafting.createShapelessRecipe(IndustryItems.ingotCopper, 9, new Object[]{IndustryBlocks.blockCopper});
        RecipeHelper.Crafting.createShapelessRecipe(IndustryItems.ingotBronze, 9, new Object[]{IndustryBlocks.blockBronze});
        RecipeHelper.Crafting.createShapelessRecipe(IndustryItems.ingotUranium, 9, new Object[]{IndustryBlocks.blockUranium});
        RecipeHelper.Crafting.createShapelessRecipe(IndustryItems.plateTin, 1, new Object[]{IndustryItems.ingotTin, IndustryItems.toolHammer});
        RecipeHelper.Crafting.createShapelessRecipe(IndustryItems.plateCopper, 1, new Object[]{IndustryItems.ingotCopper, IndustryItems.toolHammer});
        RecipeHelper.Crafting.createShapelessRecipe(IndustryItems.plateBronze, 1, new Object[]{IndustryItems.ingotBronze, IndustryItems.toolHammer});
        RecipeHelper.Crafting.createShapelessRecipe(IndustryItems.plateIron, 1, new Object[]{Item.ingotIron, IndustryItems.toolHammer});
        RecipeHelper.Crafting.createShapelessRecipe(IndustryItems.plateGold, 1, new Object[]{Item.ingotGold, IndustryItems.toolHammer});
        RecipeHelper.Crafting.createShapelessRecipe(IndustryItems.plateSteel, 1, new Object[]{Item.ingotSteel, IndustryItems.toolHammer});
        RecipeHelper.Crafting.createShapelessRecipe(IndustryItems.itemCableTin, 2, new Object[]{IndustryItems.ingotTin, IndustryItems.toolCutters});
        RecipeHelper.Crafting.createShapelessRecipe(IndustryItems.itemCableCopper, 2, new Object[]{IndustryItems.ingotCopper, IndustryItems.toolCutters});
        RecipeHelper.Crafting.createShapelessRecipe(IndustryItems.itemCableGold, 2, new Object[]{Item.ingotGold, IndustryItems.toolCutters});
        RecipeHelper.Crafting.createShapelessRecipe(IndustryItems.itemCableSteel, 2, new Object[]{Item.ingotSteel, IndustryItems.toolCutters});
        RecipeHelper.Crafting.createShapelessRecipe(IndustryItems.itemInsulatedCableTin, 1, new Object[]{IndustryItems.itemCableTin, IndustryItems.rubber});
        RecipeHelper.Crafting.createShapelessRecipe(IndustryItems.itemInsulatedCableCopper, 1, new Object[]{IndustryItems.itemCableCopper, IndustryItems.rubber});
        RecipeHelper.Crafting.createShapelessRecipe(IndustryItems.itemInsulatedCableGold, 1, new Object[]{IndustryItems.itemCableGold, IndustryItems.rubber});
        RecipeHelper.Crafting.createShapelessRecipe(IndustryItems.itemInsulatedCableSteel, 1, new Object[]{IndustryItems.itemCableSteel, IndustryItems.rubber});

        RecipeHelper.Crafting.createRecipe(IndustryItems.toolTreetap, 1, new Object[]{
                "#1#", "111", "1##",
                '1', Block.planksOak
        });
        RecipeHelper.Crafting.createRecipe(IndustryItems.toolHammer, 1, new Object[]{
                "111", "121", "#2#",
                '1', Item.ingotIron,
                '2', Item.stick
        });
        RecipeHelper.Crafting.createRecipe(IndustryItems.toolCutters, 1, new Object[]{
                "1#1", "#1#", "2#2",
                '1', IndustryItems.plateIron,
                '2', Item.ingotIron
        });
        RecipeHelper.Crafting.createRecipe(IndustryItems.toolWrench, 1, new Object[]{
                "1#1", "111", "#1#",
                '1', IndustryItems.plateBronze
        });

        RecipeHelper.Crafting.createRecipe(IndustryItems.batteryRedstone, 1, new Object[]{
                "#1#", "232", "232",
                '1', IndustryItems.itemInsulatedCableTin,
                '2', IndustryItems.plateTin,
                '3', Item.dustRedstone
        });
        RecipeHelper.Crafting.createRecipe(IndustryItems.batteryAdvanced, 1, new Object[]{
                "#1#", "232", "242",
                '1', IndustryItems.itemInsulatedCableCopper,
                '2', IndustryItems.plateBronze,
                '3', Item.dustRedstone,
                '4', Item.sulphur
        });
        RecipeHelper.Crafting.createRecipe(IndustryItems.batteryCrystal, 1, new Object[]{
                "121", "232", "121",
                '1', IndustryItems.itemInsulatedCableGold,
                '2', Item.dustRedstone,
                '3', Item.diamond
        });
        RecipeHelper.Crafting.createRecipe(IndustryItems.batteryLapis, 1, new Object[]{
                "121", "343", "121",
                '1', IndustryItems.itemInsulatedCableSteel,
                '2', new ItemStack(Item.dye, 1, 4),
                '3', IndustryItems.circuitAdvanced,
                '4', Item.diamond
        });
        RecipeHelper.Crafting.createRecipe(IndustryItems.cellEmpty, 8, new Object[]{
                "#1#", "1#1", "#1#",
                '1', IndustryItems.ingotTin
        });
        RecipeHelper.Crafting.createRecipe(IndustryItems.canEmpty, 3, new Object[]{
                "1#1", "#1#",
                '1', IndustryItems.plateTin
        });
        RecipeHelper.Crafting.createRecipe(IndustryItems.circuitBasic, 1, new Object[]{
                "111", "232", "111",
                '1', IndustryItems.itemInsulatedCableCopper,
                '2', Item.dustRedstone,
                '3', IndustryItems.plateSteel
        });
        RecipeHelper.Crafting.createRecipe(IndustryItems.circuitAdvanced, 1, new Object[]{
                "111", "234", "111",
                '1', IndustryItems.itemInsulatedCableGold,
                '2', Item.quartz,
                '3', IndustryItems.plateSteel,
                '4', Item.dustRedstone
        });
    }

    private void craftingRecipesBlocks() {
        // Blocks
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.blockTin, 1, new Object[]{
                "111", "111", "111",
                '1', IndustryItems.ingotTin
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.blockCopper, 1, new Object[]{
                "111", "111", "111",
                '1', IndustryItems.ingotCopper
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.blockBronze, 1, new Object[]{
                "111", "111", "111",
                '1', IndustryItems.ingotBronze
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.blockUranium, 1, new Object[]{
                "111", "111", "111",
                '1', IndustryItems.ingotUranium
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.machineCasingBasic, 1, new Object[]{
                "111", "121", "111",
                '1', IndustryItems.plateIron,
                '2', IndustryItems.batteryRedstone
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.machineCasingAdvanced, 1, new Object[]{
                "111", "121", "111",
                '1', IndustryItems.plateSteel,
                '2', IndustryItems.batteryAdvanced
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.generator, 1, new Object[]{
                "1", "2",
                '1', IndustryBlocks.machineCasingBasic,
                '2', Block.furnaceStoneIdle
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.generatorWatermill, 1, new Object[]{
                "121", "232", "121",
                '1', Item.stick,
                '2', Block.planksOak,
                '3', IndustryBlocks.generator
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.generatorWindmill, 1, new Object[]{
                "#1#", "121", "#1#",
                '1', IndustryBlocks.blockTin,
                '2', IndustryBlocks.generator
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.generatorGeothermal, 1, new Object[]{
                "121", "121", "343",
                '1', Block.glass,
                '2', IndustryItems.cellEmpty,
                '3', IndustryItems.plateSteel,
                '4', IndustryBlocks.generator
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.generatorSolar, 1, new Object[]{
                "121", "212", "343",
                '1', IndustryItems.dustCoal,
                '2', Block.glass,
                '3', IndustryItems.circuitBasic,
                '4', IndustryBlocks.generator
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.generatorSolar, 1, new Object[]{
                "121", "212", "343",
                '1', Block.glass,
                '2', IndustryItems.dustCoal,
                '3', IndustryItems.circuitBasic,
                '4', IndustryBlocks.generator
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.solarArrayLV, 1, new Object[]{
                "111", "121", "111",
                '1', IndustryBlocks.generatorSolar,
                '2', IndustryBlocks.batboxLV
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.solarArrayMV, 1, new Object[]{
                "111", "121", "111",
                '1', IndustryBlocks.solarArrayLV,
                '2', IndustryBlocks.batboxMV
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.solarArrayHV, 1, new Object[]{
                "111", "121", "111",
                '1', IndustryBlocks.solarArrayMV,
                '2', IndustryBlocks.batboxHV
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.solarArrayEHV, 1, new Object[]{
                "111", "121", "111",
                '1', IndustryBlocks.solarArrayHV,
                '2', IndustryBlocks.batboxEHV
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.batboxLV, 1, new Object[]{
                "121", "333", "111",
                '1', Block.planksOak,
                '2', IndustryItems.itemInsulatedCableTin,
                '3', IndustryItems.batteryRedstone
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.batboxMV, 1, new Object[]{
                "121", "333", "111",
                '1', IndustryItems.plateBronze,
                '2', IndustryItems.itemInsulatedCableCopper,
                '3', IndustryItems.batteryAdvanced
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.batboxHV, 1, new Object[]{
                "121", "333", "111",
                '1', IndustryItems.plateIron,
                '2', IndustryItems.itemInsulatedCableGold,
                '3', IndustryItems.batteryCrystal
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.batboxEHV, 1, new Object[]{
                "121", "333", "111",
                '1', IndustryItems.plateSteel,
                '2', IndustryItems.itemInsulatedCableSteel,
                '3', IndustryItems.batteryLapis
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.transformerMVtoLV, 1, new Object[]{
                "121", "134", "454",
                '1', IndustryItems.plateBronze,
                '2', IndustryItems.itemInsulatedCableCopper,
                '3', IndustryItems.batteryRedstone,
                '4', Block.planksOak,
                '5', IndustryItems.itemInsulatedCableTin
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.transformerHVtoMV, 1, new Object[]{
                "121", "134", "454",
                '1', IndustryItems.plateIron,
                '2', IndustryItems.itemInsulatedCableGold,
                '3', IndustryItems.batteryAdvanced,
                '4', IndustryItems.plateBronze,
                '5', IndustryItems.itemInsulatedCableCopper
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.transformerEHVtoHV, 1, new Object[]{
                "121", "134", "454",
                '1', IndustryItems.plateSteel,
                '2', IndustryItems.itemInsulatedCableSteel,
                '3', IndustryItems.batteryCrystal,
                '4', IndustryItems.plateIron,
                '5', IndustryItems.itemInsulatedCableGold
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.machineFurnace, 1, new Object[]{
                "#1#", "232", "#4#",
                '1', IndustryItems.circuitBasic,
                '2', Item.dustRedstone,
                '3', IndustryBlocks.machineCasingBasic,
                '4', Block.furnaceStoneIdle
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.machineMacerator, 1, new Object[]{
                "111", "232", "#4#",
                '1', Item.flint,
                '2', Block.cobbleStone,
                '3', IndustryBlocks.machineCasingBasic,
                '4', IndustryItems.circuitBasic
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.machineCompressor, 1, new Object[]{
                "1#1", "121", "131",
                '1', Block.stone,
                '2', IndustryBlocks.machineCasingBasic,
                '3', IndustryItems.circuitBasic
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.machineWiremill, 1, new Object[]{
                "1#1", "121", "#3#",
                '1', Item.ingotIron,
                '2', IndustryBlocks.machineCasingBasic,
                '3', IndustryItems.circuitBasic
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.machineExtractor, 1, new Object[]{
                "121", "131",
                '1', IndustryItems.toolTreetap,
                '2', IndustryBlocks.machineCasingBasic,
                '3', IndustryItems.circuitBasic
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.machineRecycler, 1, new Object[]{
                "#1#", "232",
                '1', IndustryItems.circuitBasic,
                '2', Block.dirt,
                '3', IndustryBlocks.machineCasingBasic
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.advancedMachineFurnace, 1, new Object[]{
                "#1#", "232", "#4#",
                '1', IndustryItems.circuitAdvanced,
                '2', Item.dustRedstone,
                '3', IndustryBlocks.machineCasingAdvanced,
                '4', Block.furnaceStoneIdle
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.advancedMachineMacerator, 1, new Object[]{
                "1", "2", "3",
                '1', Block.spikes,
                '2', IndustryBlocks.machineCasingAdvanced,
                '3', IndustryItems.circuitAdvanced
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.advancedMachineCompressor, 1, new Object[]{
                "121", "#3#",
                '1', Block.obsidian,
                '2', IndustryBlocks.machineCasingAdvanced,
                '3', IndustryItems.circuitAdvanced
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.advancedMachineWiremill, 1, new Object[]{
                "1", "2", "3",
                '1', Item.quartz,
                '2', IndustryBlocks.machineCasingAdvanced,
                '3', IndustryItems.circuitAdvanced
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.advancedMachineExtractor, 1, new Object[]{
                "#1#", "232", "#4#",
                '1', IndustryItems.cellCoolant,
                '2', IndustryItems.toolTreetap,
                '3', IndustryBlocks.machineCasingAdvanced,
                '4', IndustryItems.circuitAdvanced
        });
        RecipeHelper.Crafting.createRecipe(IndustryBlocks.hardenedCoal, 1, new Object[]{
                "121", "232", "121",
                '1', IndustryItems.dustCoal,
                '2', Block.blockCoal,
                '3', Block.obsidian
        });
        RecipeHelper.Crafting.createShapelessRecipe(Block.planksOakPainted, 4, new Object[]{IndustryBlocks.logRubberWood});

        RecipeHelper.Crafting.createShapelessRecipe(Block.obsidian, 1, new Object[]{IndustryItems.cellWater, IndustryItems.cellLava});
    }

    private void furnaceRecipes() {
        LookupFuelFurnace.instance.addFuelEntry(IndustryItems.dustCoal.id, 1600);
        LookupFuelEnergy.fuelEnergy().addFuelEntry(IndustryItems.dustCoal.id, 8);

        RecipeHelper.smeltingManager.addSmelting(IndustryBlocks.logRubberWood.id, new ItemStack(Item.coal, 1, 1));

        RecipeHelper.smeltingManager.addSmelting(IndustryBlocks.oreTinStone.id, new ItemStack(IndustryItems.ingotTin));
        RecipeHelper.smeltingManager.addSmelting(IndustryBlocks.oreTinBasalt.id, new ItemStack(IndustryItems.ingotTin));
        RecipeHelper.smeltingManager.addSmelting(IndustryBlocks.oreTinLimestone.id, new ItemStack(IndustryItems.ingotTin));
        RecipeHelper.smeltingManager.addSmelting(IndustryBlocks.oreTinGranite.id, new ItemStack(IndustryItems.ingotTin));
        RecipeHelper.smeltingManager.addSmelting(IndustryItems.dustTin.id, new ItemStack(IndustryItems.ingotTin));
        RecipeHelper.smeltingManager.addSmelting(IndustryBlocks.oreCopperStone.id, new ItemStack(IndustryItems.ingotCopper));
        RecipeHelper.smeltingManager.addSmelting(IndustryBlocks.oreCopperBasalt.id, new ItemStack(IndustryItems.ingotCopper));
        RecipeHelper.smeltingManager.addSmelting(IndustryBlocks.oreCopperLimestone.id, new ItemStack(IndustryItems.ingotCopper));
        RecipeHelper.smeltingManager.addSmelting(IndustryBlocks.oreCopperGranite.id, new ItemStack(IndustryItems.ingotCopper));
        RecipeHelper.smeltingManager.addSmelting(IndustryItems.dustCopper.id, new ItemStack(IndustryItems.ingotCopper));
        RecipeHelper.smeltingManager.addSmelting(IndustryItems.dustBronze.id, new ItemStack(IndustryItems.ingotBronze));
        RecipeHelper.smeltingManager.addSmelting(IndustryItems.dustIron.id, new ItemStack(Item.ingotIron));
        RecipeHelper.smeltingManager.addSmelting(IndustryItems.dustGold.id, new ItemStack(Item.ingotGold));

        RecipeHelper.blastingManager.addSmelting(IndustryBlocks.oreTinStone.id, new ItemStack(IndustryItems.ingotTin));
        RecipeHelper.blastingManager.addSmelting(IndustryBlocks.oreTinBasalt.id, new ItemStack(IndustryItems.ingotTin));
        RecipeHelper.blastingManager.addSmelting(IndustryBlocks.oreTinLimestone.id, new ItemStack(IndustryItems.ingotTin));
        RecipeHelper.blastingManager.addSmelting(IndustryBlocks.oreTinGranite.id, new ItemStack(IndustryItems.ingotTin));
        RecipeHelper.blastingManager.addSmelting(IndustryItems.dustTin.id, new ItemStack(IndustryItems.ingotTin));
        RecipeHelper.blastingManager.addSmelting(IndustryBlocks.oreCopperStone.id, new ItemStack(IndustryItems.ingotCopper));
        RecipeHelper.blastingManager.addSmelting(IndustryBlocks.oreCopperBasalt.id, new ItemStack(IndustryItems.ingotCopper));
        RecipeHelper.blastingManager.addSmelting(IndustryBlocks.oreCopperLimestone.id, new ItemStack(IndustryItems.ingotCopper));
        RecipeHelper.blastingManager.addSmelting(IndustryBlocks.oreCopperGranite.id, new ItemStack(IndustryItems.ingotCopper));
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
