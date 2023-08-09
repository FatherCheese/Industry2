package turniplabs.industry.items

import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemPlaceable
import turniplabs.halplibe.helper.ItemHelper
import turniplabs.industry.Industry2.MOD_ID
import turniplabs.industry.blocks.ModBlocks

object ModItems {

    private var itemID = 16999;

    private fun nextItemID(): Int {
        return itemID++
    }

    // Copper
    val rawCopperOre: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "ore.raw.copper", "raw_copper.png")
    val copperDust: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "dust.copper", "dust_copper.png")
    val copperIngot: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "ingot.copper", "ingot_copper.png")
    val copperPlate: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "plate.copper", "plate_copper.png")

    val copperCable: Item = ItemHelper.createItem(
        MOD_ID, ItemPlaceable("cable.copper", nextItemID(), ModBlocks.copperCable),
        "cable.copper",
        "cable_copper.png"
    )

    val insulatedCopperCable: Item = ItemHelper.createItem(
        MOD_ID,
        ItemPlaceable("insulated.cable.copper", nextItemID(), ModBlocks.insulatedCopperCable),
        "insulated.cable.copper",
        "cable_insulated_copper.png"
    )

    // Tin
    val rawTinOre: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "ore.raw.tin", "raw_tin.png")
    val tinDust: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "dust.tin", "dust_tin.png")
    val tinIngot: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "ingot.tin", "ingot_tin.png")
    val tinPlate: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "plate.tin", "plate_tin.png")

    val tinCable: Item = ItemHelper.createItem(
        MOD_ID, ItemPlaceable("cable.tin", nextItemID(), ModBlocks.tinCable),
        "cable.tin",
        "cable_tin.png"
    )

    val insulatedTinCable: Item = ItemHelper.createItem(
        MOD_ID,
        ItemPlaceable("insulated.cable.tin", nextItemID(), ModBlocks.insulatedTinCable),
        "insulated.cable.tin",
        "cable_insulated_tin.png"
    )

    // Bronze
    val bronzeDust: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "dust.bronze", "dust_bronze.png")
    val bronzeIngot: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "ingot.bronze", "ingot_bronze.png")
    val bronzePlate: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "plate.bronze", "plate_bronze.png")

    // Vanilla
    val ironDust: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "dust.iron", "dust_iron.png")
    val ironPlate: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "plate.iron", "plate_iron.png")

    val goldDust: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "dust.gold", "dust_gold.png")
    val goldPlate: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "plate.gold", "plate_gold.png")

    val goldCable: Item = ItemHelper.createItem(
        MOD_ID, ItemPlaceable("cable.gold", nextItemID(), ModBlocks.copperCable),
        "cable.gold",
        "cable_gold.png"
    )

    val insulatedGoldCable: Item = ItemHelper.createItem(
        MOD_ID,
        ItemPlaceable("insulated.cable.gold", nextItemID(), ModBlocks.insulatedCopperCable),
        "insulated.cable.gold",
        "cable_insulated_gold.png"
    )

    val steelPlate: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "plate.steel", "plate_steel.png")

    val steelCable: Item = ItemHelper.createItem(
        MOD_ID, ItemPlaceable("cable.steel", nextItemID(), ModBlocks.copperCable),
        "cable.steel",
        "cable_steel.png"
    )

    val insulatedSteelCable: Item = ItemHelper.createItem(
        MOD_ID,
        ItemPlaceable("insulated.cable.steel", nextItemID(), ModBlocks.insulatedCopperCable),
        "insulated.cable.steel",
        "cable_insulated_steel.png"
    )

    // Miscellaneous
    val rawUranium: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "ore.raw.uranium", "raw_uranium.png")
    val uraniumIngot: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "ingot.uranium", "ingot_uranium.png")
}
