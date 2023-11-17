package baboon.industry.item;

import baboon.industry.Industry2;
import baboon.industry.IndustryConfig;
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

    private int nextItemID(String itemName) {
        return IndustryConfig.cfg.getInt("Item IDs." + itemName);
    }

    private static ArmorMaterial armorMaterialHazmat;
    private static ArmorMaterial armorMaterialIridium;

    // Raw Ore
    public static Item oreRawTin;
    public static Item oreRawCopper;
    public static Item oreRawUranium;
    public static Item ingotIridium;

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

    public static Item ingotIridiumScrap;
    public static Item upgradePlate;
    public static Item upgradeSpeed;
    public static Item upgradeTransformer;
    public static Item upgradeEnergy;
    public static Item upgradePusher;
    public static Item upgradePuller;
    public static Item upgradeBlasting;

    public static Item foodJoffos;

    private void taggedItems() {
        batteryRedstone.withTags(IndustryTags.PREVENT_ITEM_RECYCLING, IndustryTags.PREVENT_FABRICATING);
        batteryAdvanced.withTags(IndustryTags.PREVENT_ITEM_RECYCLING, IndustryTags.PREVENT_FABRICATING);
        batteryCrystal.withTags(IndustryTags.PREVENT_ITEM_RECYCLING, IndustryTags.PREVENT_FABRICATING);
        batteryLapis.withTags(IndustryTags.PREVENT_ITEM_RECYCLING, IndustryTags.PREVENT_FABRICATING);
        scrap.withTags(IndustryTags.PREVENT_ITEM_RECYCLING, IndustryTags.PREVENT_FABRICATING);
        ingotIridium.withTags(IndustryTags.PREVENT_FABRICATING);
        plateIridium.withTags(IndustryTags.PREVENT_FABRICATING);
        armorHelmetIridium.withTags(IndustryTags.PREVENT_FABRICATING);
        armorChestplateIridium.withTags(IndustryTags.PREVENT_FABRICATING);
        armorLeggingsIridium.withTags(IndustryTags.PREVENT_FABRICATING);
        armorBootsIridium.withTags(IndustryTags.PREVENT_FABRICATING);

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
                new Item(nextItemID("oreRawTin")),
                "ore.raw.tin",
                "raw_tin.png");

        oreRawCopper= ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("oreRawCopper")),
                "ore.raw.copper",
                "raw_copper.png");

        oreRawUranium = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("oreRawUranium")),
                "ore.raw.uranium",
                "raw_uranium.png");

        dustTin = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("dustTin")),
                "dust.tin",
                "dust_tin.png");

        dustCopper = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("dustCopper")),
                "dust.copper",
                "dust_copper.png");

        dustBronze = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("dustBronze")),
                "dust.bronze",
                "dust_bronze.png");

        dustIron = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("dustIron")),
                "dust.iron",
                "dust_iron.png");

        dustGold = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("dustGold")),
                "dust.gold",
                "dust_gold.png");

        dustCoal = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("dustCoal")),
                "dust.coal",
                "dust_coal.png");

        ingotTin = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("ingotTin")),
                "ingot.tin",
                "ingot_tin.png");

        ingotCopper = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("ingotCopper")),
                "ingot.copper",
                "ingot_copper.png");

        ingotBronze = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("ingotBronze")),
                "ingot.bronze",
                "ingot_bronze.png");

        ingotUranium = ItemHelper.createItem(MOD_ID,
                new ItemRadioactive(nextItemID("ingotUranium")),
                "ingot.uranium",
                "ingot_uranium.png");

        ingotIridium = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("ingotIridium")),
                "ingot.iridium",
                "ingot_iridium.png");

        plateTin = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("plateTin")),
                "plate.tin",
                "plate_tin.png");

        plateCopper = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("plateCopper")),
                "plate.copper",
                "plate_copper.png");

        plateBronze = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("plateBronze")),
                "plate.bronze",
                "plate_bronze.png");

        plateIron = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("plateIron")),
                "plate.iron",
                "plate_iron.png");

        plateGold = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("plateGold")),
                "plate.gold",
                "plate_gold.png");

        plateSteel = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("plateSteel")),
                "plate.steel",
                "plate_steel.png");

        plateIridium = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("plateIridium")),
                "plate.iridium",
                "plate_iridium.png");

        itemCableTin = ItemHelper.createItem(MOD_ID,
                new ItemCable("cable.tin", nextItemID("itemCableTin"), IndustryBlocks.blockCableTin),
                "cable.tin",
                "cable_tin.png");

        itemCableCopper = ItemHelper.createItem(MOD_ID,
                new ItemCable("cable.copper", nextItemID("itemCableCopper"), IndustryBlocks.blockCableCopper),
                "cable.copper",
                "cable_copper.png");

        itemCableGold = ItemHelper.createItem(MOD_ID,
                new ItemCable("cable.gold", nextItemID("itemCableGold"), IndustryBlocks.blockCableGold),
                "cable.gold",
                "cable_gold.png");

        itemCableSteel = ItemHelper.createItem(MOD_ID,
                new ItemCable("cable.steel", nextItemID("itemCableSteel"), IndustryBlocks.blockCableSteel),
                "cable.steel",
                "cable_steel.png");

        itemInsulatedCableTin = ItemHelper.createItem(MOD_ID,
                new ItemCable("insulated.cable.tin", nextItemID("itemInsulatedCableTin"), IndustryBlocks.blockInsulatedCableTin),
                "insulated.cable.tin",
                "cable_insulated_tin.png");

        itemInsulatedCableCopper = ItemHelper.createItem(MOD_ID,
                new ItemCable("insulated.cable.copper", nextItemID("itemInsulatedCableCopper"), IndustryBlocks.blockInsulatedCableCopper),
                "insulated.cable.copper",
                "cable_insulated_copper.png");

        itemInsulatedCableGold = ItemHelper.createItem(MOD_ID,
                new ItemCable("insulated.cable.gold", nextItemID("itemInsulatedCableGold"), IndustryBlocks.blockInsulatedCableGold),
                "insulated.cable.gold",
                "cable_insulated_gold.png");

        itemInsulatedCableSteel = ItemHelper.createItem(MOD_ID,
                new ItemCable("insulated.cable.steel", nextItemID("itemInsulatedCableSteel"), IndustryBlocks.blockInsulatedCableSteel),
                "insulated.cable.steel",
                "cable_insulated_steel.png");

        toolTreetap = ItemHelper.createItem(MOD_ID,
                new ItemTreetap(nextItemID("toolTreetap")),
                "tool.treetap",
                "tool_treetap.png").setMaxStackSize(1);

        toolHammer = ItemHelper.createItem(MOD_ID,
                new ItemTools(nextItemID("toolHammer")),
                "tool.hammer",
                "tool_hammer.png");

        toolCutters = ItemHelper.createItem(MOD_ID,
                new ItemTools(nextItemID("toolCutters")),
                "tool.cutters",
                "tool_cutters.png");

        toolWrench = ItemHelper.createItem(MOD_ID,
                new ItemWrench(nextItemID("toolWrench")),
                "tool.wrench",
                "tool_wrench.png").setMaxStackSize(1);

        toolChainsaw = ItemHelper.createItem(MOD_ID,
                new ItemToolChainsaw(nextItemID("toolChainsaw")),
                "tool.chainsaw",
                "tool_chainsaw.png");

        toolDrill = ItemHelper.createItem(MOD_ID,
                new ItemToolDrill(nextItemID("toolDrill"), ToolMaterial.iron),
                "tool.drill",
                "tool_drill.png");

        toolDrillGold = ItemHelper.createItem(MOD_ID,
                new ItemToolDrill(nextItemID("toolDrillGold"), ToolMaterial.gold),
                "tool.drill.gold",
                "tool_drill_gold.png");

        toolDrillDiamond = ItemHelper.createItem(MOD_ID,
                new ItemToolDrill(nextItemID("toolDrillDiamond"), ToolMaterial.diamond),
                "tool.drill.diamond",
                "tool_drill_diamond.png");

        toolNanoSword = ItemHelper.createItem(MOD_ID,
                new ItemToolNanoSword(nextItemID("toolNanoSword")),
                "tool.nanosword");

        armorHelmetHazmat = ItemHelper.createItem(MOD_ID,
                new ItemArmor("armor.helmet.hazmat", nextItemID("armorHelmetHazmat"), armorMaterialHazmat, 0),
                "armor.helmet.hazmat",
                "armor_hazmat_helmet.png");

        armorChestplateHazmat = ItemHelper.createItem(MOD_ID,
                new ItemArmor("armor.chestplate.hazmat", nextItemID("armorChestplateHazmat"), armorMaterialHazmat, 1),
                "armor.chestplate.hazmat",
                "armor_hazmat_chestplate.png");

        armorLeggingsHazmat = ItemHelper.createItem(MOD_ID,
                new ItemArmor("armor.leggings.hazmat", nextItemID("armorLeggingsHazmat"), armorMaterialHazmat, 2),
                "armor.leggings.hazmat",
                "armor_hazmat_leggings.png");

        armorBootsHazmat = ItemHelper.createItem(MOD_ID,
                new ItemArmor("armor.boots.hazmat", nextItemID("armorBootsHazmat"), armorMaterialHazmat, 3),
                "armor.boots.hazmat",
                "armor_hazmat_boots.png");

        armorHelmetIridium = ItemHelper.createItem(MOD_ID,
                new ItemArmorIridium("armor.helmet.iridium", nextItemID("armorHelmetIridium"), armorMaterialIridium, 0),
                "armor.helmet.iridium",
                "armor_iridium_helmet.png");

        armorChestplateIridium = ItemHelper.createItem(MOD_ID,
                new ItemArmorIridium("armor.chestplate.iridium", nextItemID("armorChestplateIridium"), armorMaterialIridium, 1),
                "armor.chestplate.iridium",
                "armor_iridium_chestplate.png");

        armorLeggingsIridium = ItemHelper.createItem(MOD_ID,
                new ItemArmorIridium("armor.leggings.iridium", nextItemID("armorLeggingsIridium"), armorMaterialIridium, 2),
                "armor.leggings.iridium",
                "armor_iridium_leggings.png");

        armorBootsIridium = ItemHelper.createItem(MOD_ID,
                new ItemArmorIridium("armor.boots.iridium", nextItemID("armorBootsIridium"), armorMaterialIridium, 3),
                "armor.boots.iridium",
                "armor_iridium_boots.png");

        batteryRedstone = ItemHelper.createItem(MOD_ID,
                new ItemBatteryRedstone(nextItemID("batteryRedstone")),
                "battery.redstone");

        batteryAdvanced = ItemHelper.createItem(MOD_ID,
                new ItemBatteryAdvanced(nextItemID("batteryAdvanced")),
                "battery.advanced");

        batteryCrystal = ItemHelper.createItem(MOD_ID,
                new ItemBatteryCrystal(nextItemID("batteryCrystal")),
                "battery.crystal");

        batteryLapis = ItemHelper.createItem(MOD_ID,
                new ItemBatteryLapis(nextItemID("batteryLapis")),
                "battery.lapis");

        cellEmpty = ItemHelper.createItem(MOD_ID,
                new ItemCell(nextItemID("cellEmpty")),
                "cell.empty",
                "cell_empty.png");

        cellWater = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("cellWater")),
                "cell.water",
                "cell_water.png");

        cellLava = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("cellLava")),
                "cell.lava",
                "cell_lava.png");

        cellUranium = ItemHelper.createItem(MOD_ID,
                new ItemCellUranium(nextItemID("cellUranium")),
                "cell.uranium",
                "cell_uranium.png").setMaxStackSize(1);

        cellCoolant = ItemHelper.createItem(MOD_ID,
                new ItemCellCoolant(nextItemID("cellCoolant")),
                "cell.coolant",
                "cell_coolant.png").setMaxStackSize(1);

        canEmpty = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("canEmpty")),
                "can.empty",
                "can_empty.png");

        canFood = ItemHelper.createItem(MOD_ID,
                new ItemFoodStackable("can.food", nextItemID("canFood"), 2, false, 20),
                "can.food",
                "can_food.png").setMaxStackSize(10);

        resin = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("resin")),
                "resin",
                "resin.png");

        rubber = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("rubber")),
                "rubber",
                "rubber.png");

        circuitBasic = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("circuitBasic")),
                "circuit",
                "circuit.png");

        circuitAdvanced = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("circuitAdvanced")),
                "circuitadvanced",
                "circuit_advanced.png");

        scrap = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("scrap")),
                "scrap",
                "scrap.png");

        reactorPlate = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("reactorPlate")),
                "reactorplate",
                "plate_reactor.png");

        ingotIridiumScrap = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("ingotIridiumScrap")),
                "ingot.iridium.scrap",
                "ingot_iridium_scrap.png");

        upgradePlate = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("upgradePlate")),
                "upgrade.plate",
                "plate_upgrade.png");
        upgradeSpeed = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("upgradeSpeed")).setMaxStackSize(4),
                "upgrade.speed",
                "upgrade_speed.png");
        upgradeTransformer = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("upgradeTransformer")).setMaxStackSize(4),
                "upgrade.transformer",
                "upgrade_transformer.png");
        upgradeEnergy = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("upgradeEnergy")).setMaxStackSize(4),
                "upgrade.energy",
                "upgrade_energy.png");
        upgradePusher = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("upgradePusher")).setMaxStackSize(4),
                "upgrade.pusher",
                "upgrade_pusher.png");
        upgradePuller = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("upgradePuller")).setMaxStackSize(4),
                "upgrade.puller",
                "upgrade_puller.png");
        upgradeBlasting = ItemHelper.createItem(MOD_ID,
                new Item(nextItemID("upgradeBlasting")).setMaxStackSize(4),
                "upgrade.blasting",
                "upgrade_blasting.png");

        foodJoffos = ItemHelper.createItem(MOD_ID,
                new ItemFoodStackable("food.joffos", nextItemID("foodJoffos"), 1, false, 10),
                "food.joffos",
                "food_joffos.png");

        taggedItems();
    }
}
