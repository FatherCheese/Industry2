package baboon.industry.item;

import baboon.industry.Industry2;
import baboon.industry.IndustryTags;
import baboon.industry.block.IndustryBlocks;
import baboon.industry.item.battery.ItemBatteryAdvanced;
import baboon.industry.item.battery.ItemBatteryCrystal;
import baboon.industry.item.battery.ItemBatteryLapis;
import baboon.industry.item.battery.ItemBatteryRedstone;
import baboon.industry.item.toolelectric.ItemToolChainsaw;
import baboon.industry.item.toolelectric.ItemToolDrill;
import baboon.industry.item.toolelectric.ItemToolNanoSword;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemArmor;
import net.minecraft.core.item.ItemFoodStackable;
import net.minecraft.core.item.material.ArmorMaterial;
import net.minecraft.core.item.material.ToolMaterial;
import turniplabs.halplibe.helper.ArmorHelper;
import turniplabs.halplibe.helper.ItemHelper;

public class IndustryItems {
    private final String MOD_ID = Industry2.MOD_ID;

    private int itemID = 17000;
    private int nextItemID() {
        return itemID++;
    }

    private static ArmorMaterial armorMaterialHazmat;
    private static ArmorMaterial armorMaterialIridium;

    // Raw Ore
    public static Item oreRawTin;
    public static Item oreRawCopper;
    public static Item oreRawUranium;
    public static Item oreRawIridium;

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
    public static Item plateIridium;

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
    public static Item toolChainsaw;
    public static Item toolDrill;
    public static Item toolDrillGold;
    public static Item toolDrillDiamond;
    public static Item toolNanoSword;

    // Armor
    public static Item armorHelmetHazmat;
    public static Item armorChestplateHazmat;
    public static Item armorLeggingsHazmat;
    public static Item armorBootsHazmat;
    public static Item armorHelmetIridium;
    public static Item armorChestplateIridium;
    public static Item armorLeggingsIridium;
    public static Item armorBootsIridium;

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

    public static Item reactorPlate;

    private void taggedItems() {
        batteryRedstone.withTags(IndustryTags.PREVENT_ITEM_RECYCLING);
        batteryAdvanced.withTags(IndustryTags.PREVENT_ITEM_RECYCLING);
        batteryCrystal.withTags(IndustryTags.PREVENT_ITEM_RECYCLING);
        batteryLapis.withTags(IndustryTags.PREVENT_ITEM_RECYCLING);
        scrap.withTags(IndustryTags.PREVENT_ITEM_RECYCLING);

        Item.flint.withTags(IndustryTags.PREVENT_ITEM_RECYCLING);
        Item.seedsWheat.withTags(IndustryTags.PREVENT_ITEM_RECYCLING);
        Item.stick.withTags(IndustryTags.PREVENT_ITEM_RECYCLING);
        Item.string.withTags(IndustryTags.PREVENT_ITEM_RECYCLING);
    }

    private void armorMaterials() {
        armorMaterialHazmat = ArmorHelper.createArmorMaterial("hazmat", 500, 0.0f, 0.0f, 150.0f, 0.0f);
        armorMaterialIridium = ArmorHelper.createArmorMaterial("iridium", -1, 100.0f, 100.0f, 100.0f, 100.0f);
    }

    public void initializeItems() {
        armorMaterials();

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

        oreRawIridium = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "ore.raw.iridium",
                "raw_iridium.png");

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
                new ItemRadioactive(nextItemID()),
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

        plateIridium = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "plate.iridium",
                "plate_iridium.png");

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
                "tool_treetap.png").setMaxStackSize(1);

        toolHammer = ItemHelper.createItem(MOD_ID,
                new ItemTools(nextItemID()),
                "tool.hammer",
                "tool_hammer.png");

        toolCutters = ItemHelper.createItem(MOD_ID,
                new ItemTools(nextItemID()),
                "tool.cutters",
                "tool_cutters.png");

        toolWrench = ItemHelper.createItem(MOD_ID,
                new ItemWrench(nextItemID()),
                "tool.wrench",
                "tool_wrench.png").setMaxStackSize(1);

        toolChainsaw = ItemHelper.createItem(MOD_ID,
                new ItemToolChainsaw(nextItemID()),
                "tool.chainsaw",
                "tool_chainsaw.png");

        toolDrill = ItemHelper.createItem(MOD_ID,
                new ItemToolDrill(nextItemID(), ToolMaterial.iron),
                "tool.drill",
                "tool_drill.png");

        toolDrillGold = ItemHelper.createItem(MOD_ID,
                new ItemToolDrill(nextItemID(), ToolMaterial.gold),
                "tool.drill.gold",
                "tool_drill_gold.png");

        toolDrillDiamond = ItemHelper.createItem(MOD_ID,
                new ItemToolDrill(nextItemID(), ToolMaterial.diamond),
                "tool.drill.diamond",
                "tool_drill_diamond.png");

        toolNanoSword = ItemHelper.createItem(MOD_ID,
                new ItemToolNanoSword(nextItemID()),
                "tool.nanosword");

        armorHelmetHazmat = ItemHelper.createItem(MOD_ID,
                new ItemArmor("armor.helmet.hazmat", nextItemID(), armorMaterialHazmat, 0),
                "armor.helmet.hazmat",
                "armor_hazmat_helmet.png");

        armorChestplateHazmat = ItemHelper.createItem(MOD_ID,
                new ItemArmor("armor.chestplate.hazmat", nextItemID(), armorMaterialHazmat, 1),
                "armor.chestplate.hazmat",
                "armor_hazmat_chestplate.png");

        armorLeggingsHazmat = ItemHelper.createItem(MOD_ID,
                new ItemArmor("armor.leggings.hazmat", nextItemID(), armorMaterialHazmat, 2),
                "armor.leggings.hazmat",
                "armor_hazmat_leggings.png");

        armorBootsHazmat = ItemHelper.createItem(MOD_ID,
                new ItemArmor("armor.boots.hazmat", nextItemID(), armorMaterialHazmat, 3),
                "armor.boots.hazmat",
                "armor_hazmat_boots.png");

        armorHelmetIridium = ItemHelper.createItem(MOD_ID,
                new ItemArmorIridium("armor.helmet.iridium", nextItemID(), armorMaterialIridium, 0),
                "armor.helmet.iridium",
                "armor_iridium_helmet.png");

        armorChestplateIridium = ItemHelper.createItem(MOD_ID,
                new ItemArmorIridium("armor.chestplate.iridium", nextItemID(), armorMaterialIridium, 1),
                "armor.chestplate.iridium",
                "armor_iridium_chestplate.png");

        armorLeggingsIridium = ItemHelper.createItem(MOD_ID,
                new ItemArmorIridium("armor.leggings.iridium", nextItemID(), armorMaterialIridium, 2),
                "armor.leggings.iridium",
                "armor_iridium_leggings.png");

        armorBootsIridium = ItemHelper.createItem(MOD_ID,
                new ItemArmorIridium("armor.boots.iridium", nextItemID(), armorMaterialIridium, 3),
                "armor.boots.iridium",
                "armor_iridium_boots.png");

        batteryRedstone = ItemHelper.createItem(MOD_ID,
                new ItemBatteryRedstone(nextItemID()),
                "battery.redstone");

        batteryAdvanced = ItemHelper.createItem(MOD_ID,
                new ItemBatteryAdvanced(nextItemID()),
                "battery.advanced");

        batteryCrystal = ItemHelper.createItem(MOD_ID,
                new ItemBatteryCrystal(nextItemID()),
                "battery.crystal");

        batteryLapis = ItemHelper.createItem(MOD_ID,
                new ItemBatteryLapis(nextItemID()),
                "battery.lapis");

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
                new ItemCellUranium(nextItemID()),
                "cell.uranium",
                "cell_uranium.png").setMaxStackSize(1);

        cellCoolant = ItemHelper.createItem(MOD_ID,
                new ItemCellCoolant(nextItemID()),
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
                "scrap.png");

        reactorPlate = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID()),
                "reactorplate",
                "plate_reactor.png");

        taggedItems();
    }
}
