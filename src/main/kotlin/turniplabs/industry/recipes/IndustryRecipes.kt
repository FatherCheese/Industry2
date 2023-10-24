package turniplabs.industry.recipes


import net.minecraft.core.block.Block
import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemStack
import turniplabs.halplibe.helper.RecipeHelper.Crafting
import turniplabs.halplibe.helper.RecipeHelper.Smelting
import turniplabs.industry.blocks.IndustryBlocks
import turniplabs.industry.items.IndustryItems


object IndustryRecipes {

    private fun furnaceRecipes() {
        Smelting.createRecipe(IndustryItems.copperIngot, IndustryBlocks.oreCopperStone)
        Smelting.createRecipe(IndustryItems.copperIngot, IndustryBlocks.oreCopperBasalt)
        Smelting.createRecipe(IndustryItems.copperIngot, IndustryBlocks.oreCopperLimestone)
        Smelting.createRecipe(IndustryItems.copperIngot, IndustryBlocks.oreCopperGranite)

        Smelting.createRecipe(IndustryItems.tinIngot, IndustryBlocks.oreTinStone)
        Smelting.createRecipe(IndustryItems.tinIngot, IndustryBlocks.oreTinBasalt)
        Smelting.createRecipe(IndustryItems.tinIngot, IndustryBlocks.oreTinLimestone)
        Smelting.createRecipe(IndustryItems.tinIngot, IndustryBlocks.oreTinGranite)

        Smelting.createRecipe(IndustryItems.rawUranium, IndustryBlocks.oreUraniumStone)
        Smelting.createRecipe(IndustryItems.rawUranium, IndustryBlocks.oreUraniumBasalt)
        Smelting.createRecipe(IndustryItems.rawUranium, IndustryBlocks.oreUraniumLimestone)
        Smelting.createRecipe(IndustryItems.rawUranium, IndustryBlocks.oreUraniumGranite)

        Smelting.createRecipe(IndustryItems.copperIngot, IndustryItems.copperDust)
        Smelting.createRecipe(IndustryItems.tinIngot, IndustryItems.tinDust)
        Smelting.createRecipe(IndustryItems.bronzeIngot, IndustryItems.bronzeDust)
        Smelting.createRecipe(Item.ingotIron, IndustryItems.ironDust)
        Smelting.createRecipe(Item.ingotGold, IndustryItems.goldDust)

        Smelting.createRecipe(IndustryItems.rubber, IndustryItems.resin)
    }

    private fun craftingRecipes() {

        Crafting.createRecipe(ItemStack(IndustryBlocks.copperBlock), arrayOf(
            "111", "111", "111",
            '1', IndustryItems.copperIngot
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.tinBlock), arrayOf(
            "111", "111", "111",
            '1', IndustryItems.tinIngot
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.bronzeBlock), arrayOf(
            "111", "111", "111",
            '1', IndustryItems.bronzeIngot
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.uraniumBlock), arrayOf(
            "111", "111", "111",
            '1', IndustryItems.uraniumIngot
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.machineCasing), arrayOf(
            "111", "1#1", "111",
            '1', IndustryItems.ironPlate
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.machineCasingAdvanced), arrayOf(
            "111", "1#1", "111",
            '1', IndustryItems.steelPlate
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.machineGenerator), arrayOf(
            "1", "2", "3",
            '1', IndustryItems.batteryRedstone,
            '2', IndustryBlocks.machineCasing,
            '3', Block.furnaceStoneIdle
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.machineWatermill), arrayOf(
            "121", "232", "121",
            '1', Item.stick,
            '2', Block.planksOak,
            '3', IndustryBlocks.machineGenerator
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.machineWatermill), arrayOf(
            "121", "232", "121",
            '1', Item.stick,
            '2', Block.planksOakPainted,
            '3', IndustryBlocks.machineGenerator
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.machineGeothermalGenerator), arrayOf(
            "121", "121", "343",
            '1', Block.glass,
            '2', IndustryItems.emptyCell,
            '3', IndustryItems.ironPlate,
            '4', IndustryBlocks.machineGenerator
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.machineSolarGenerator), arrayOf(
            "121", "212", "343",
            '1', IndustryItems.coalDust,
            '2', Block.glass,
            '3', IndustryItems.circuit,
            '4', IndustryBlocks.machineGenerator
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.machineSolarArrayLV), arrayOf(
            "111", "121", "111",
            '1', IndustryBlocks.machineSolarGenerator,
            '2', IndustryBlocks.batboxLV
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.machineSolarArrayMV), arrayOf(
            "111", "121", "111",
            '1', IndustryBlocks.machineSolarArrayLV,
            '2', IndustryBlocks.batboxMV
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.machineSolarArrayHV), arrayOf(
            "111", "121", "111",
            '1', IndustryBlocks.machineSolarArrayMV,
            '2', IndustryBlocks.batboxHV
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.machineSolarArraySHV), arrayOf(
            "111", "121", "111",
            '1', IndustryBlocks.machineSolarArrayHV,
            '2', IndustryBlocks.batboxSHV
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.batboxLV), arrayOf(
            "121", "333", "111",
            '1', Block.planksOak,
            '2', IndustryItems.itemInsulatedTinCable,
            '3', IndustryItems.batteryRedstone
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.batboxLV), arrayOf(
            "121", "333", "111",
            '1', Block.planksOakPainted,
            '2', IndustryItems.itemInsulatedTinCable,
            '3', IndustryItems.batteryRedstone
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.batboxMV), arrayOf(
            "121", "333", "111",
            '1', IndustryItems.bronzePlate,
            '2', IndustryItems.itemInsulatedCopperCable,
            '3', IndustryItems.batteryAdvanced
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.batboxHV), arrayOf(
            "121", "333", "111",
            '1', IndustryItems.ironPlate,
            '2', IndustryItems.itemInsulatedGoldCable,
            '3', IndustryItems.batteryCrystal
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.batboxSHV), arrayOf(
            "121", "333", "111",
            '1', IndustryItems.steelPlate,
            '2', IndustryItems.itemInsulatedSteelCable,
            '3', IndustryItems.batteryLapis
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.machineElectricFurnace), arrayOf(
            "#1#", "232", "#4#",
            '1', IndustryItems.circuit,
            '2', Item.dustRedstone,
            '3', IndustryBlocks.machineCasing,
            '4', Block.furnaceStoneIdle
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.machineMacerator), arrayOf(
            "111", "232", "#4#",
            '1', Item.flint,
            '2', Block.cobbleStone,
            '3', IndustryBlocks.machineCasing,
            '4', IndustryItems.circuit
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.machineCompressor), arrayOf(
            "1#1", "121", "131",
            '1', Block.stone,
            '2', IndustryBlocks.machineCasing,
            '3', IndustryItems.circuit
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.machineCutter), arrayOf(
            "1#1", "121", "131",
            '1', Item.ingotIron,
            '2', IndustryBlocks.machineCasing,
            '3', IndustryItems.circuit
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.machineExtractor), arrayOf(
            "121", "131",
            '1', IndustryItems.treeTap,
            '2', IndustryBlocks.machineCasing,
            '3', IndustryItems.circuit
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.machineRecycler), arrayOf(
            "#1#", "232",
            '1', IndustryItems.circuit,
            '2', Block.dirt,
            '3', IndustryBlocks.machineCasing
        ))
        Crafting.createRecipe(ItemStack(IndustryBlocks.hardenedCoal), arrayOf(
          "121", "232", "121",
            '1', IndustryItems.coalDust,
            '2', Item.nethercoal,
            '3', Block.obsidian
        ))
        Crafting.createRecipe(ItemStack(IndustryItems.hammer), arrayOf(
            "111", "121", "#2#",
            '1', Item.ingotIron,
            '2', Item.stick
        ))
        Crafting.createRecipe(ItemStack(IndustryItems.cutter), arrayOf(
            "1#1", "#1#", "2#2",
            '1', IndustryItems.ironPlate,
            '2', Item.ingotIron
        ))
        Crafting.createRecipe(ItemStack(IndustryItems.batteryRedstone), arrayOf(
            "#1#", "232", "232",
            '1', IndustryItems.itemInsulatedTinCable,
            '2', IndustryItems.tinPlate,
            '3', Item.dustRedstone
        ))
        Crafting.createRecipe(ItemStack(IndustryItems.batteryAdvanced), arrayOf(
            "#1#", "232", "242",
            '1', IndustryItems.itemInsulatedCopperCable,
            '2', IndustryItems.bronzePlate,
            '3', Item.dustRedstone,
            '4', Item.sulphur
        ))
        Crafting.createRecipe(ItemStack(IndustryItems.batteryCrystal), arrayOf(
            "121", "232", "121",
            '1', IndustryItems.itemInsulatedGoldCable,
            '2', Item.dustRedstone,
            '3', Item.diamond
        ))
        Crafting.createRecipe(ItemStack(IndustryItems.batteryLapis), arrayOf(
            "121", "343", "121",
            '1', IndustryItems.itemInsulatedSteelCable,
            '2', IndustryItems.circuit,
            '3', ItemStack(Item.dye, 1, 4),
            '4', IndustryItems.batteryCrystal
        ))
        Crafting.createRecipe(ItemStack(IndustryItems.treeTap), arrayOf(
            "#1#", "111", "1##",
            '1', Block.planksOak
        ))
        Crafting.createRecipe(ItemStack(IndustryItems.treeTap), arrayOf(
            "#1#", "111", "1##",
            '1', Block.planksOakPainted
        ))
        Crafting.createRecipe(ItemStack(IndustryItems.emptyCell), arrayOf(
            "#1#", "1#1", "1##",
            '1', IndustryItems.tinIngot
        ))
        Crafting.createRecipe(ItemStack(IndustryItems.circuit), arrayOf(
            "111", "232", "111",
            '1', IndustryItems.itemInsulatedCopperCable,
            '2', Item.dustRedstone,
            '3', IndustryItems.ironPlate
        ))
        Crafting.createRecipe(ItemStack(IndustryItems.circuitAdvanced), arrayOf(
            "111", "234", "111",
            '1', IndustryItems.itemInsulatedCopperCable,
            '2', Item.dustRedstone,
            '3', IndustryItems.steelPlate,
            '4', Item.dustGlowstone
        ))
        Crafting.createRecipe(ItemStack(IndustryItems.circuitAdvanced), arrayOf(
            "111", "234", "111",
            '1', IndustryItems.itemInsulatedCopperCable,
            '2', Item.dustGlowstone,
            '3', IndustryItems.steelPlate,
            '4', Item.dustRedstone
        ))

        Crafting.createShapelessRecipe(ItemStack(IndustryItems.copperPlate), arrayOf(IndustryItems.copperIngot, IndustryItems.hammer))
        Crafting.createShapelessRecipe(ItemStack(IndustryItems.tinPlate), arrayOf(IndustryItems.tinIngot, IndustryItems.hammer))
        Crafting.createShapelessRecipe(ItemStack(IndustryItems.bronzePlate), arrayOf(IndustryItems.bronzeIngot, IndustryItems.hammer))
        Crafting.createShapelessRecipe(ItemStack(IndustryItems.ironPlate), arrayOf(Item.ingotIron, IndustryItems.hammer))
        Crafting.createShapelessRecipe(ItemStack(IndustryItems.goldPlate), arrayOf(Item.ingotGold, IndustryItems.hammer))
        Crafting.createShapelessRecipe(ItemStack(IndustryItems.steelPlate), arrayOf(Item.ingotSteel, IndustryItems.hammer))

        Crafting.createShapelessRecipe(ItemStack(IndustryItems.itemCopperCable, 2), arrayOf(IndustryItems.copperIngot, IndustryItems.cutter))
        Crafting.createShapelessRecipe(ItemStack(IndustryItems.itemTinCable, 2), arrayOf(IndustryItems.tinIngot, IndustryItems.cutter))
        Crafting.createShapelessRecipe(ItemStack(IndustryItems.itemGoldCable, 2), arrayOf(Item.ingotGold, IndustryItems.cutter))
        Crafting.createShapelessRecipe(ItemStack(IndustryItems.itemSteelCable, 2), arrayOf(Item.ingotSteel, IndustryItems.cutter))

        Crafting.createShapelessRecipe(ItemStack(IndustryItems.itemInsulatedCopperCable), arrayOf(IndustryItems.itemCopperCable, IndustryItems.rubber))
        Crafting.createShapelessRecipe(ItemStack(IndustryItems.itemInsulatedTinCable), arrayOf(IndustryItems.itemTinCable, IndustryItems.rubber))
        Crafting.createShapelessRecipe(ItemStack(IndustryItems.itemInsulatedGoldCable), arrayOf(IndustryItems.itemGoldCable, IndustryItems.rubber))
        Crafting.createShapelessRecipe(ItemStack(IndustryItems.itemInsulatedSteelCable), arrayOf(IndustryItems.itemSteelCable, IndustryItems.rubber))
    }

    fun initializeRecipes() {
        furnaceRecipes()
        craftingRecipes()
    }
}