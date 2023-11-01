package turniplabs.industry.items

import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemFoodStackable
import turniplabs.halplibe.helper.ItemHelper
import turniplabs.industry.Industry2
import turniplabs.industry.IndustryConfig
import turniplabs.industry.IndustryTags
import turniplabs.industry.blocks.IndustryBlocks

object IndustryItems {

    private fun itemID(configItemName: String): Int {
        return IndustryConfig.cfg.getInt("Item IDs.$configItemName")
    }

    // Ores
    val rawCopperOre: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("rawCopper")), "ore.raw.copper", "raw_copper.png")
    val rawTinOre: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("rawTin")), "ore.raw.tin", "raw_tin.png")
    val rawUranium: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("rawUranium")), "ore.raw.uranium", "raw_uranium.png")

    // Dusts
    val copperDust: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("dustCopper")), "dust.copper", "dust_copper.png")
    val tinDust: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("dustTin")), "dust.tin", "dust_tin.png")
    val bronzeDust: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("dustBronze")), "dust.bronze", "dust_bronze.png")
    val ironDust: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("dustIron")), "dust.iron", "dust_iron.png")
    val goldDust: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("dustGold")), "dust.gold", "dust_gold.png")
    val coalDust: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("dustCoal")), "dust.coal", "dust_coal.png")

    // Ingots
    val copperIngot: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("ingotCopper")), "ingot.copper", "ingot_copper.png")
    val tinIngot: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("ingotTin")), "ingot.tin", "ingot_tin.png")
    val bronzeIngot: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("ingotBronze")), "ingot.bronze", "ingot_bronze.png")
    val uraniumIngot: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("ingotUranium")), "ingot.uranium", "ingot_uranium.png")

    // Plates
    val copperPlate: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("plateCopper")), "plate.copper", "plate_copper.png")
    val tinPlate: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("plateTin")), "plate.tin", "plate_tin.png")
    val bronzePlate: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("plateBronze")), "plate.bronze", "plate_bronze.png")
    val ironPlate: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("plateIron")), "plate.iron", "plate_iron.png")
    val goldPlate: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("plateGold")), "plate.gold", "plate_gold.png")
    val steelPlate: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("plateSteel")), "plate.steel", "plate_steel.png")

    // Cables
    val itemCopperCable: Item = ItemHelper.createItem(
        Industry2.MOD_ID,
        ItemCable("cable.copper", itemID("itemCopperCable"), IndustryBlocks.copperCable),
        "cable.copper",
        "cable_copper.png"
    )
    val itemTinCable: Item = ItemHelper.createItem(
        Industry2.MOD_ID,
        ItemCable("cable.tin", itemID("itemTinCable"), IndustryBlocks.tinCable),
        "cable.tin",
        "cable_tin.png"
    )
    val itemGoldCable: Item = ItemHelper.createItem(
        Industry2.MOD_ID,
        ItemCable("cable.gold", itemID("itemGoldCable"), IndustryBlocks.goldCable),
        "cable.gold",
        "cable_gold.png"
    )
    val itemSteelCable: Item = ItemHelper.createItem(
        Industry2.MOD_ID,
        ItemCable("cable.steel", itemID("itemSteelCable"), IndustryBlocks.steelCable),
        "cable.steel",
        "cable_steel.png"
    )

    // Insulated Cables
    val itemInsulatedCopperCable: Item = ItemHelper.createItem(
        Industry2.MOD_ID,
        ItemCable("insulated.cable.copper", itemID("itemInsulatedCopperCable"), IndustryBlocks.insulatedCopperCable),
        "insulated.cable.copper",
        "cable_insulated_copper.png"
    )
    val itemInsulatedTinCable: Item = ItemHelper.createItem(
        Industry2.MOD_ID,
        ItemCable("insulated.cable.tin", itemID("itemInsulatedTinCable"), IndustryBlocks.insulatedTinCable),
        "insulated.cable.tin",
        "cable_insulated_tin.png"
    )
    val itemInsulatedGoldCable: Item = ItemHelper.createItem(
        Industry2.MOD_ID,
        ItemCable("insulated.cable.gold", itemID("itemInsulatedGoldCable"), IndustryBlocks.insulatedGoldCable),
        "insulated.cable.gold",
        "cable_insulated_gold.png"
    )
    val itemInsulatedSteelCable: Item = ItemHelper.createItem(
        Industry2.MOD_ID,
        ItemCable("insulated.cable.steel", itemID("itemInsulatedSteelCable"), IndustryBlocks.insulatedSteelCable),
        "insulated.cable.steel",
        "cable_insulated_steel.png"
    )

    // Tools
    val hammer: Item = ItemHelper.createItem(Industry2.MOD_ID, ItemHammer(itemID("toolHammer")), "tool.hammer", "hammer.png")
    val cutter: Item = ItemHelper.createItem(Industry2.MOD_ID, ItemCutter(itemID("toolCutter")), "tool.cutter", "cutter.png")
    val wrench: Item = ItemHelper.createItem(Industry2.MOD_ID, ItemWrench(itemID("toolWrench")), "tool.wrench", "wrench.png")
    val treeTap: Item = ItemHelper.createItem(Industry2.MOD_ID, ItemTap(itemID("toolTreetap")), "tool.tap", "tree_tap.png")

    val batteryRedstone: Item = ItemHelper.createItem(
        Industry2.MOD_ID,
        ItemBatteryRedstone(itemID("batteryRedstone")),
        "tool.battery.redstone"
    ).withTags(IndustryTags.PREVENT_ITEM_RECYCLING)
    val batteryAdvanced: Item = ItemHelper.createItem(
        Industry2.MOD_ID,
        ItemBatteryAdvanced(itemID("batteryAdvanced")),
        "tool.battery.advanced",
    ).withTags(IndustryTags.PREVENT_ITEM_RECYCLING)
    val batteryCrystal: Item = ItemHelper.createItem(
        Industry2.MOD_ID,
        ItemBatteryCrystal(itemID("batteryCrystal")),
        "tool.battery.crystal",
    ).withTags(IndustryTags.PREVENT_ITEM_RECYCLING)
    val batteryLapis: Item = ItemHelper.createItem(
        Industry2.MOD_ID,
        ItemBatteryLapis(itemID("batteryLapis")),
        "tool.battery.lapis",
    ).withTags(IndustryTags.PREVENT_ITEM_RECYCLING)

    val cellEmpty: Item = ItemHelper.createItem(Industry2.MOD_ID, ItemCell(itemID("cellEmpty")), "cell.empty", "cell_empty.png")
    val cellWater: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("cellWater")), "cell.water", "cell_water.png")
    val cellLava: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("cellLava")), "cell.lava", "cell_lava.png")
    val cellUranium: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("cellUranium")), "cell.uranium", "cell_uranium.png").setMaxStackSize(1)
    val cellCoolant: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("cellCoolant")), "cell.coolant", "cell_coolant.png").setMaxStackSize(1)

    val canEmpty: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("canEmpty")), "can.empty", "can.png")
    val canFull: Item = ItemHelper.createItem(Industry2.MOD_ID,
        ItemFoodStackable("can.full", itemID("canFood"), 2, false, 64),
        "can.full",
        "can_full.png")

    // Materials
    val resin: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("resin")), "ingredient.resin", "resin.png")
    val rubber: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("rubber")), "ingredient.rubber", "rubber.png")
    val circuit: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("circuitBasic")), "ingredient.circuit", "circuit.png")
    val circuitAdvanced: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("circuitAdvanced")), "ingredient.advancedcircuit", "circuit_advanced.png")

    // Miscellaneous
    val scrap: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(itemID("scrap")), "scrap", "scrap.png").withTags(IndustryTags.PREVENT_ITEM_RECYCLING)

    fun initializeItems() {
        rawCopperOre
        rawTinOre
        rawUranium

        copperDust
        tinDust
        bronzeDust
        ironDust
        goldDust
        coalDust

        copperIngot
        tinIngot
        bronzeIngot
        uraniumIngot

        copperPlate
        tinPlate
        bronzePlate
        ironPlate
        goldPlate
        steelPlate

        itemCopperCable
        itemTinCable
        itemGoldCable
        itemSteelCable
        itemInsulatedCopperCable
        itemInsulatedTinCable
        itemInsulatedGoldCable
        itemInsulatedSteelCable

        hammer
        cutter
        batteryRedstone
        batteryAdvanced
        batteryCrystal
        batteryLapis
        treeTap
        cellEmpty
        cellWater
        cellLava
        cellUranium
        cellCoolant

        resin
        rubber
        circuit
        circuitAdvanced

        scrap
    }
}