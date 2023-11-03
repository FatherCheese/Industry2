package baboon.industry.item;

import baboon.industry.Industry2;
import baboon.industry.IndustryConfig;
import baboon.industry.IndustryTags;
import baboon.industry.block.IndustryBlocks;
import baboon.industry.item.battery.ItemBatteryAdvanced;
import baboon.industry.item.battery.ItemBatteryCrystal;
import baboon.industry.item.battery.ItemBatteryLapis;
import baboon.industry.item.battery.ItemBatteryRedstone;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemFoodStackable;
import turniplabs.halplibe.helper.ItemHelper;

public class IndustryItems {
    private final String MOD_ID = Industry2.MOD_ID;

    private static int itemID(String configName) {
        return IndustryConfig.cfg.getInt("Item IDs." + configName);
    }

    // Raw Ore
    public static Item oreRawTin;
    public static Item oreRawCopper;
    public static Item oreRawUranium;

    // Dust
    public static Item dustTin;
    public static Item dustCopper;
    public static Item dustBronze;
    public static Item dustIron;
    public static Item dustGold;
    public static Item dustCoal;

    // Ingots
    public static Item ingotTin;
    public static Item ingotCopper;
    public static Item ingotBronze;
    public static Item ingotUranium;

    // Plates
    public static Item plateTin;
    public static Item plateCopper;
    public static Item plateBronze;
    public static Item plateIron;
    public static Item plateGold;
    public static Item plateSteel;

    // Cables
    public static Item itemCableTin;
    public static Item itemCableCopper;
    public static Item itemCableGold;
    public static Item itemCableSteel;
    public static Item itemInsulatedCableTin;
    public static Item itemInsulatedCableCopper;
    public static Item itemInsulatedCableGold;
    public static Item itemInsulatedCableSteel;

    // Tools
    public static Item toolTreetap;
    public static Item toolHammer;
    public static Item toolCutters;
    public static Item toolWrench;

    // Batteries
    public static Item batteryRedstone;
    public static Item batteryAdvanced;
    public static Item batteryCrystal;
    public static Item batteryLapis;

    // Cells
    public static Item cellEmpty;
    public static Item cellWater;
    public static Item cellLava;
    public static Item cellUranium;
    public static Item cellCoolant;

    // Miscellaneous
    public static Item canEmpty;
    public static Item canFood;
    public static Item resin;
    public static Item rubber;
    public static Item circuitBasic;
    public static Item circuitAdvanced;
    public static Item scrap;

    public void initializeItems() {
        oreRawTin = ItemHelper.createItem(MOD_ID,
                new Item(itemID("rawTin")),
                "ore.raw.tin",
                "raw_tin.png");

        oreRawCopper= ItemHelper.createItem(MOD_ID,
                new Item(itemID("rawCopper")),
                "ore.raw.copper",
                "raw_copper.png");

        oreRawUranium = ItemHelper.createItem(MOD_ID,
                new Item(itemID("rawUranium")),
                "ore.raw.uranium",
                "raw_uranium.png");

        dustTin = ItemHelper.createItem(MOD_ID,
                new Item(itemID("dustTin")),
                "dust.tin",
                "dust_tin.png");

        dustCopper = ItemHelper.createItem(MOD_ID,
                new Item(itemID("dustCopper")),
                "dust.copper",
                "dust_copper.png");

        dustBronze = ItemHelper.createItem(MOD_ID,
                new Item(itemID("dustBronze")),
                "dust.bronze",
                "dust_bronze.png");

        dustIron = ItemHelper.createItem(MOD_ID,
                new Item(itemID("dustIron")),
                "dust.iron",
                "dust_iron.png");

        dustGold = ItemHelper.createItem(MOD_ID,
                new Item(itemID("dustGold")),
                "dust.gold",
                "dust_gold.png");

        dustCoal = ItemHelper.createItem(MOD_ID,
                new Item(itemID("dustCoal")),
                "dust.coal",
                "dust_coal.png");

        ingotTin = ItemHelper.createItem(MOD_ID,
                new Item(itemID("ingotTin")),
                "ingot.tin",
                "ingot_tin.png");

        ingotCopper = ItemHelper.createItem(MOD_ID,
                new Item(itemID("ingotCopper")),
                "ingot.copper",
                "ingot_copper.png");

        ingotBronze = ItemHelper.createItem(MOD_ID,
                new Item(itemID("ingotBronze")),
                "ingot.bronze",
                "ingot_bronze.png");

        ingotUranium = ItemHelper.createItem(MOD_ID,
                new Item(itemID("ingotUranium")),
                "ingot.uranium",
                "ingot_uranium.png");

        plateTin = ItemHelper.createItem(MOD_ID,
                new Item(itemID("plateTin")),
                "plate.tin",
                "plate_tin.png");

        plateCopper = ItemHelper.createItem(MOD_ID,
                new Item(itemID("plateCopper")),
                "plate.copper",
                "plate_copper.png");

        plateBronze = ItemHelper.createItem(MOD_ID,
                new Item(itemID("plateBronze")),
                "plate.bronze",
                "plate_bronze.png");

        plateIron = ItemHelper.createItem(MOD_ID,
                new Item(itemID("plateIron")),
                "plate.iron",
                "plate_iron.png");

        plateGold = ItemHelper.createItem(MOD_ID,
                new Item(itemID("plateGold")),
                "plate.gold",
                "plate_gold.png");

        plateSteel = ItemHelper.createItem(MOD_ID,
                new Item(itemID("plateSteel")),
                "plate.steel",
                "plate_steel.png");

        itemCableTin = ItemHelper.createItem(MOD_ID,
                new ItemCable("cable.tin", itemID("itemTinCable"), IndustryBlocks.blockCableTin),
                "cable.tin",
                "cable_tin.png");

        itemCableCopper = ItemHelper.createItem(MOD_ID,
                new ItemCable("cable.copper", itemID("itemCopperCable"), IndustryBlocks.blockCableCopper),
                "cable.copper",
                "cable_copper.png");

        itemCableGold = ItemHelper.createItem(MOD_ID,
                new ItemCable("cable.gold", itemID("itemGoldCable"), IndustryBlocks.blockCableGold),
                "cable.gold",
                "cable_gold.png");

        itemCableSteel = ItemHelper.createItem(MOD_ID,
                new ItemCable("cable.steel", itemID("itemSteelCable"), IndustryBlocks.blockCableSteel),
                "cable.steel",
                "cable_steel.png");

        itemInsulatedCableTin = ItemHelper.createItem(MOD_ID,
                new ItemCable("insulated.cable.tin", itemID("itemInsulatedTinCable"), IndustryBlocks.blockInsulatedCableTin),
                "insulated.cable.tin",
                "cable_insulated_tin.png");

        itemInsulatedCableCopper = ItemHelper.createItem(MOD_ID,
                new ItemCable("insulated.cable.copper", itemID("itemInsulatedCopperCable"), IndustryBlocks.blockInsulatedCableCopper),
                "insulated.cable.copper",
                "cable_insulated_copper.png");

        itemInsulatedCableGold = ItemHelper.createItem(MOD_ID,
                new ItemCable("insulated.cable.gold", itemID("itemInsulatedGoldCable"), IndustryBlocks.blockInsulatedCableGold),
                "insulated.cable.gold",
                "cable_insulated_gold.png");

        itemInsulatedCableSteel = ItemHelper.createItem(MOD_ID,
                new ItemCable("insulated.cable.steel", itemID("itemInsulatedSteelCable"), IndustryBlocks.blockInsulatedCableSteel),
                "insulated.cable.steel",
                "cable_insulated_steel.png");

        toolTreetap = ItemHelper.createItem(MOD_ID,
                new ItemTreetap(itemID("toolTreetap")),
                "tool.treetap",
                "tool_treetap.png");

        toolHammer = ItemHelper.createItem(MOD_ID,
                new Item(itemID("toolHammer")),
                "tool.hammer",
                "tool_hammer.png");

        toolCutters = ItemHelper.createItem(MOD_ID,
                new Item(itemID("toolCutters")),
                "tool.cutters",
                "tool_cutters.png");

        toolWrench = ItemHelper.createItem(MOD_ID,
                new ItemWrench(itemID("toolWrench")),
                "tool.wrench",
                "tool_wrench.png");

        batteryRedstone = ItemHelper.createItem(MOD_ID,
                new ItemBatteryRedstone(itemID("batteryRedstone")),
                "battery.redstone").setMaxStackSize(1).withTags(IndustryTags.PREVENT_ITEM_RECYCLING);

        batteryAdvanced = ItemHelper.createItem(MOD_ID,
                new ItemBatteryAdvanced(itemID("batteryAdvanced")),
                "battery.advanced").setMaxStackSize(1).withTags(IndustryTags.PREVENT_ITEM_RECYCLING);

        batteryCrystal = ItemHelper.createItem(MOD_ID,
                new ItemBatteryCrystal(itemID("batteryCrystal")),
                "battery.crystal").setMaxStackSize(1).withTags(IndustryTags.PREVENT_ITEM_RECYCLING);

        batteryLapis = ItemHelper.createItem(MOD_ID,
                new ItemBatteryLapis(itemID("batteryLapis")),
                "battery.lapis").setMaxStackSize(1).withTags(IndustryTags.PREVENT_ITEM_RECYCLING);

        cellEmpty = ItemHelper.createItem(MOD_ID,
                new ItemCell(itemID("cellEmpty")),
                "cell.empty",
                "cell_empty.png");

        cellWater = ItemHelper.createItem(MOD_ID,
                new Item(itemID("cellWater")),
                "cell.water",
                "cell_water.png");

        cellLava = ItemHelper.createItem(MOD_ID,
                new Item(itemID("cellLava")),
                "cell.lava",
                "cell_lava.png");

        cellUranium = ItemHelper.createItem(MOD_ID,
                new Item(itemID("cellUranium")),
                "cell.uranium",
                "cell_uranium.png").setMaxStackSize(1);

        cellCoolant = ItemHelper.createItem(MOD_ID,
                new Item(itemID("cellCoolant")),
                "cell.coolant",
                "cell_coolant.png").setMaxStackSize(1);

        canEmpty = ItemHelper.createItem(MOD_ID,
                new Item(itemID("canEmpty")),
                "can.empty",
                "can_empty.png");

        canFood = ItemHelper.createItem(MOD_ID,
                new ItemFoodStackable("can.food", itemID("canFood"), 2, false, 20),
                "can.food",
                "can_food.png").setMaxStackSize(10);

        resin = ItemHelper.createItem(MOD_ID,
                new Item(itemID("resin")),
                "resin",
                "resin.png");

        rubber = ItemHelper.createItem(MOD_ID,
                new Item(itemID("rubber")),
                "rubber",
                "rubber.png");

        circuitBasic = ItemHelper.createItem(MOD_ID,
                new Item(itemID("circuitBasic")),
                "circuit",
                "circuit.png");

        circuitAdvanced = ItemHelper.createItem(MOD_ID,
                new Item(itemID("circuitAdvanced")),
                "circuitadvanced",
                "circuit_advanced.png");

        scrap = ItemHelper.createItem(MOD_ID,
                new Item(itemID("scrap")),
                "scrap",
                "scrap.png").withTags(IndustryTags.PREVENT_ITEM_RECYCLING);
    }
}
