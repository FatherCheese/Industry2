package turniplabs.industry.items

import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemPlaceable
import turniplabs.halplibe.helper.ItemHelper
import turniplabs.industry.Industry2
import turniplabs.industry.IndustryTags
import turniplabs.industry.blocks.IndustryBlocks

object IndustryItems {

    private var itemID = 16999
    private fun nextItemID(): Int {
        return itemID++
    }

    // Ores
    val rawCopperOre: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "ore.raw.copper", "raw_copper.png")
    val rawTinOre: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "ore.raw.tin", "raw_tin.png")
    val rawUranium: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "ore.raw.uranium", "raw_uranium.png")

    // Dusts
    val copperDust: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "dust.copper", "dust_copper.png")
    val tinDust: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "dust.tin", "dust_tin.png")
    val bronzeDust: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "dust.bronze", "dust_bronze.png")
    val ironDust: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "dust.iron", "dust_iron.png")
    val goldDust: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "dust.gold", "dust_gold.png")
    val coalDust: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "dust.coal", "dust_coal.png")

    // Ingots
    val copperIngot: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "ingot.copper", "ingot_copper.png")
    val tinIngot: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "ingot.tin", "ingot_tin.png")
    val bronzeIngot: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "ingot.bronze", "ingot_bronze.png")
    val uraniumIngot: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "ingot.uranium", "ingot_uranium.png")

    // Plates
    val copperPlate: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "plate.copper", "plate_copper.png")
    val tinPlate: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "plate.tin", "plate_tin.png")
    val bronzePlate: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "plate.bronze", "plate_bronze.png")
    val ironPlate: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "plate.iron", "plate_iron.png")
    val goldPlate: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "plate.gold", "plate_gold.png")
    val steelPlate: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "plate.steel", "plate_steel.png")

    // Cables
    val itemCopperCable: Item? = ItemHelper.createItem(
        Industry2.MOD_ID,
        ItemPlaceable("cable.copper", nextItemID(), IndustryBlocks.copperCable),
        "cable.copper",
        "cable_copper.png"
    )
    val itemTinCable: Item = ItemHelper.createItem(
        Industry2.MOD_ID,
        ItemPlaceable("cable.tin", nextItemID(), IndustryBlocks.tinCable),
        "cable.tin",
        "cable_tin.png"
    )
    val itemGoldCable: Item = ItemHelper.createItem(
        Industry2.MOD_ID,
        ItemPlaceable("cable.gold", nextItemID(), IndustryBlocks.goldCable),
        "cable.gold",
        "cable_gold.png"
    )
    val itemSteelCable: Item = ItemHelper.createItem(
        Industry2.MOD_ID,
        ItemPlaceable("cable.steel", nextItemID(), IndustryBlocks.steelCable),
        "cable.steel",
        "cable_steel.png"
    )

    // Insulated Cables
    val itemInsulatedCopperCable: Item = ItemHelper.createItem(
        Industry2.MOD_ID,
        ItemPlaceable("insulated.cable.copper", nextItemID(), IndustryBlocks.insulatedCopperCable),
        "insulated.cable.copper",
        "cable_insulated_copper.png"
    )
    val itemInsulatedTinCable: Item = ItemHelper.createItem(
        Industry2.MOD_ID,
        ItemPlaceable("insulated.cable.tin", nextItemID(), IndustryBlocks.insulatedTinCable),
        "insulated.cable.tin",
        "cable_insulated_tin.png"
    )
    val itemInsulatedGoldCable: Item = ItemHelper.createItem(
        Industry2.MOD_ID,
        ItemPlaceable("insulated.cable.gold", nextItemID(), IndustryBlocks.insulatedGoldCable),
        "insulated.cable.gold",
        "cable_insulated_gold.png"
    )
    val itemInsulatedSteelCable: Item = ItemHelper.createItem(
        Industry2.MOD_ID,
        ItemPlaceable("insulated.cable.steel", nextItemID(), IndustryBlocks.insulatedSteelCable),
        "insulated.cable.steel",
        "cable_insulated_steel.png"
    )

    // Tools
    val hammer: Item = ItemHelper.createItem(Industry2.MOD_ID, ItemHammer(nextItemID()), "tool.hammer", "hammer.png")
    val cutter: Item = ItemHelper.createItem(Industry2.MOD_ID, ItemCutter(nextItemID()), "tool.cutter", "cutter.png")
    val wrench: Item = ItemHelper.createItem(Industry2.MOD_ID, ItemWrench(nextItemID()), "tool.wrench", "wrench.png")

    val batteryRedstone: Item = ItemHelper.createItem(
        Industry2.MOD_ID,
        ItemBatteryRedstone(nextItemID()),
        "tool.battery.redstone"
    ).withTags(IndustryTags.PREVENT_ITEM_RECYCLING)
    val batteryAdvanced: Item = ItemHelper.createItem(
        Industry2.MOD_ID,
        ItemBatteryAdvanced(nextItemID()),
        "tool.battery.advanced",
    ).withTags(IndustryTags.PREVENT_ITEM_RECYCLING)
    val batteryCrystal: Item = ItemHelper.createItem(
        Industry2.MOD_ID,
        ItemBatteryCrystal(nextItemID()),
        "tool.battery.crystal",
    ).withTags(IndustryTags.PREVENT_ITEM_RECYCLING)
    val batteryLapis: Item = ItemHelper.createItem(
        Industry2.MOD_ID,
        ItemBatteryLapis(nextItemID()),
        "tool.battery.lapis",
    ).withTags(IndustryTags.PREVENT_ITEM_RECYCLING)

    val treeTap: Item = ItemHelper.createItem(Industry2.MOD_ID, ItemTap(nextItemID()), "tool.tap", "tree_tap.png")

    val emptyCell: Item = ItemHelper.createItem(Industry2.MOD_ID, ItemCell(nextItemID()), "cell.empty", "cell_empty.png")
    val waterCell: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "cell.water", "cell_water.png")
    val lavaCell: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "cell.lava", "cell_lava.png")
    val uraniumCell: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "cell.uranium", "cell_uranium.png")

    // Materials
    val resin: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "ingredient.resin", "resin.png")
    val rubber: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "ingredient.rubber", "rubber.png")
    val circuit: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "ingredient.circuit", "circuit.png")
    val circuitAdvanced: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "ingredient.advancedcircuit", "circuit_advanced.png")

    // Miscellaneous
    val scrap: Item = ItemHelper.createItem(Industry2.MOD_ID, Item(nextItemID()), "scrap", "scrap.png")
        .withTags(IndustryTags.PREVENT_ITEM_RECYCLING)

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
        emptyCell
        waterCell
        lavaCell
        uraniumCell

        resin
        rubber
        circuit
        circuitAdvanced

        scrap
    }
}