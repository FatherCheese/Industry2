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

    private int itemID = 17000;
    private int nextItemID() {
        return itemID++;
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
                new Item(nextItemID()),
                "ore.raw.tin",
                "raw_tin.png");

        oreRawCopper= ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "ore.raw.copper",
                "raw_copper.png");

        oreRawUranium = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "ore.raw.uranium",
                "raw_uranium.png");

        dustTin = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "dust.tin",
                "dust_tin.png");

        dustCopper = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "dust.copper",
                "dust_copper.png");

        dustBronze = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "dust.bronze",
                "dust_bronze.png");

        dustIron = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "dust.iron",
                "dust_iron.png");

        dustGold = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "dust.gold",
                "dust_gold.png");

        dustCoal = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "dust.coal",
                "dust_coal.png");

        ingotTin = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "ingot.tin",
                "ingot_tin.png");

        ingotCopper = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "ingot.copper",
                "ingot_copper.png");

        ingotBronze = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "ingot.bronze",
                "ingot_bronze.png");

        ingotUranium = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "ingot.uranium",
                "ingot_uranium.png");

        plateTin = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "plate.tin",
                "plate_tin.png");

        plateCopper = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "plate.copper",
                "plate_copper.png");

        plateBronze = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "plate.bronze",
                "plate_bronze.png");

        plateIron = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "plate.iron",
                "plate_iron.png");

        plateGold = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "plate.gold",
                "plate_gold.png");

        plateSteel = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "plate.steel",
                "plate_steel.png");

        itemCableTin = ItemHelper.createItem(MOD_ID,
                new ItemCable("cable.tin", nextItemID(), IndustryBlocks.blockCableTin),
                "cable.tin",
                "cable_tin.png");

        itemCableCopper = ItemHelper.createItem(MOD_ID,
                new ItemCable("cable.copper", nextItemID(), IndustryBlocks.blockCableCopper),
                "cable.copper",
                "cable_copper.png");

        itemCableGold = ItemHelper.createItem(MOD_ID,
                new ItemCable("cable.gold", nextItemID(), IndustryBlocks.blockCableGold),
                "cable.gold",
                "cable_gold.png");

        itemCableSteel = ItemHelper.createItem(MOD_ID,
                new ItemCable("cable.steel", nextItemID(), IndustryBlocks.blockCableSteel),
                "cable.steel",
                "cable_steel.png");

        itemInsulatedCableTin = ItemHelper.createItem(MOD_ID,
                new ItemCable("insulated.cable.tin", nextItemID(), IndustryBlocks.blockInsulatedCableTin),
                "insulated.cable.tin",
                "cable_insulated_tin.png");

        itemInsulatedCableCopper = ItemHelper.createItem(MOD_ID,
                new ItemCable("insulated.cable.copper", nextItemID(), IndustryBlocks.blockInsulatedCableCopper),
                "insulated.cable.copper",
                "cable_insulated_copper.png");

        itemInsulatedCableGold = ItemHelper.createItem(MOD_ID,
                new ItemCable("insulated.cable.gold", nextItemID(), IndustryBlocks.blockInsulatedCableGold),
                "insulated.cable.gold",
                "cable_insulated_gold.png");

        itemInsulatedCableSteel = ItemHelper.createItem(MOD_ID,
                new ItemCable("insulated.cable.steel", nextItemID(), IndustryBlocks.blockInsulatedCableSteel),
                "insulated.cable.steel",
                "cable_insulated_steel.png");

        toolTreetap = ItemHelper.createItem(MOD_ID,
                new ItemTreetap(nextItemID()),
                "tool.treetap",
                "tool_treetap.png");

        toolHammer = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "tool.hammer",
                "tool_hammer.png");

        toolCutters = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "tool.cutters",
                "tool_cutters.png");

        toolWrench = ItemHelper.createItem(MOD_ID,
                new ItemWrench(nextItemID()),
                "tool.wrench",
                "tool_wrench.png");

        batteryRedstone = ItemHelper.createItem(MOD_ID,
                new ItemBatteryRedstone(nextItemID()),
                "battery.redstone").setMaxStackSize(1).withTags(IndustryTags.PREVENT_ITEM_RECYCLING);

        batteryAdvanced = ItemHelper.createItem(MOD_ID,
                new ItemBatteryAdvanced(nextItemID()),
                "battery.advanced").setMaxStackSize(1).withTags(IndustryTags.PREVENT_ITEM_RECYCLING);

        batteryCrystal = ItemHelper.createItem(MOD_ID,
                new ItemBatteryCrystal(nextItemID()),
                "battery.crystal").setMaxStackSize(1).withTags(IndustryTags.PREVENT_ITEM_RECYCLING);

        batteryLapis = ItemHelper.createItem(MOD_ID,
                new ItemBatteryLapis(nextItemID()),
                "battery.lapis").setMaxStackSize(1).withTags(IndustryTags.PREVENT_ITEM_RECYCLING);

        cellEmpty = ItemHelper.createItem(MOD_ID,
                new ItemCell(nextItemID()),
                "cell.empty",
                "cell_empty.png");

        cellWater = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "cell.water",
                "cell_water.png");

        cellLava = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "cell.lava",
                "cell_lava.png");

        cellUranium = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "cell.uranium",
                "cell_uranium.png").setMaxStackSize(1);

        cellCoolant = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "cell.coolant",
                "cell_coolant.png").setMaxStackSize(1);

        canEmpty = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "can.empty",
                "can_empty.png");

        canFood = ItemHelper.createItem(MOD_ID,
                new ItemFoodStackable("can.food", nextItemID(), 2, false, 20),
                "can.food",
                "can_food.png").setMaxStackSize(10);

        resin = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "resin",
                "resin.png");

        rubber = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "rubber",
                "rubber.png");

        circuitBasic = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "circuit",
                "circuit.png");

        circuitAdvanced = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "circuitadvanced",
                "circuit_advanced.png");

        scrap = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "scrap",
                "scrap.png").withTags(IndustryTags.PREVENT_ITEM_RECYCLING);
    }
}
