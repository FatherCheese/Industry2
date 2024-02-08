package baboon.industry.item;

import baboon.industry.Industry2;
import baboon.industry.IndustryConfig;
import baboon.industry.IndustryTags;
import baboon.industry.block.I2Blocks;
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

public class I2Items {
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
        armorMaterialHazmat = ArmorHelper.createArmorMaterial(MOD_ID, "hazmat", 500, 0.0f, 0.0f, 150.0f, 0.0f);
        armorMaterialIridium = ArmorHelper.createArmorMaterial(MOD_ID, "iridium", -1, 100.0f, 100.0f, 100.0f, 100.0f);
    }

    public void initializeItems() {
        armorMaterials();

        oreRawTin = ItemHelper.createItem(MOD_ID,
                new Item("ore.raw.tin", nextItemID("oreRawTin")),
                "raw_tin.png");

        oreRawCopper= ItemHelper.createItem(MOD_ID,
                new Item("ore.raw.copper", nextItemID("oreRawCopper")),
                "raw_copper.png");

        oreRawUranium = ItemHelper.createItem(MOD_ID,
                new Item("ore.raw.uranium", nextItemID("oreRawUranium")),
                "raw_uranium.png");

        dustTin = ItemHelper.createItem(MOD_ID,
                new Item("dust.tin", nextItemID("dustTin")),
                "dust_tin.png");

        dustCopper = ItemHelper.createItem(MOD_ID,
                new Item("dust.copper", nextItemID("dustCopper")),
                "dust_copper.png");

        dustBronze = ItemHelper.createItem(MOD_ID,
                new Item("dust.bronze", nextItemID("dustBronze")),
                "dust_bronze.png");

        dustIron = ItemHelper.createItem(MOD_ID,
                new Item("dust.iron", nextItemID("dustIron")),
                "dust_iron.png");

        dustGold = ItemHelper.createItem(MOD_ID,
                new Item("dust.gold", nextItemID("dustGold")),
                "dust_gold.png");

        dustCoal = ItemHelper.createItem(MOD_ID,
                new Item("dust.coal", nextItemID("dustCoal")),
                "dust_coal.png");

        ingotTin = ItemHelper.createItem(MOD_ID,
                new Item("ingot.tin", nextItemID("ingotTin")),
                "ingot_tin.png");

        ingotCopper = ItemHelper.createItem(MOD_ID,
                new Item("ingot.copper", nextItemID("ingotCopper")),
                "ingot_copper.png");

        ingotBronze = ItemHelper.createItem(MOD_ID,
                new Item("ingot.bronze", nextItemID("ingotBronze")),
                "ingot_bronze.png");

        ingotUranium = ItemHelper.createItem(MOD_ID,
                new ItemRadioactive("ingot.uranium", nextItemID("ingotUranium")),
                "ingot_uranium.png");

        ingotIridium = ItemHelper.createItem(MOD_ID,
                new Item("ingot.iridium", nextItemID("ingotIridium")),
                "ingot_iridium.png");

        plateTin = ItemHelper.createItem(MOD_ID,
                new Item("plate.tin", nextItemID("plateTin")),
                "plate_tin.png");

        plateCopper = ItemHelper.createItem(MOD_ID,
                new Item("plate.copper", nextItemID("plateCopper")),
                "plate_copper.png");

        plateBronze = ItemHelper.createItem(MOD_ID,
                new Item("plate.bronze", nextItemID("plateBronze")),
                "plate_bronze.png");

        plateIron = ItemHelper.createItem(MOD_ID,
                new Item("plate.iron", nextItemID("plateIron")),
                "plate_iron.png");

        plateGold = ItemHelper.createItem(MOD_ID,
                new Item("plate.gold", nextItemID("plateGold")),
                "plate_gold.png");

        plateSteel = ItemHelper.createItem(MOD_ID,
                new Item("plate.steel", nextItemID("plateSteel")),
                "plate_steel.png");

        plateIridium = ItemHelper.createItem(MOD_ID,
                new Item("plate.iridium", nextItemID("plateIridium")),
                "plate_iridium.png");

        itemCableTin = ItemHelper.createItem(MOD_ID,
                new ItemCable("cable.tin", nextItemID("itemCableTin"), I2Blocks.blockCableTin),
                "cable_tin.png");

        itemCableCopper = ItemHelper.createItem(MOD_ID,
                new ItemCable("cable.copper", nextItemID("itemCableCopper"), I2Blocks.blockCableCopper),
                "cable_copper.png");

        itemCableGold = ItemHelper.createItem(MOD_ID,
                new ItemCable("cable.gold", nextItemID("itemCableGold"), I2Blocks.blockCableGold),
                "cable_gold.png");

        itemCableSteel = ItemHelper.createItem(MOD_ID,
                new ItemCable("cable.steel", nextItemID("itemCableSteel"), I2Blocks.blockCableSteel),
                "cable_steel.png");

        itemInsulatedCableTin = ItemHelper.createItem(MOD_ID,
                new ItemCable("insulated.cable.tin", nextItemID("itemInsulatedCableTin"), I2Blocks.blockInsulatedCableTin),
                "cable_insulated_tin.png");

        itemInsulatedCableCopper = ItemHelper.createItem(MOD_ID,
                new ItemCable("insulated.cable.copper", nextItemID("itemInsulatedCableCopper"), I2Blocks.blockInsulatedCableCopper),
                "cable_insulated_copper.png");

        itemInsulatedCableGold = ItemHelper.createItem(MOD_ID,
                new ItemCable("insulated.cable.gold", nextItemID("itemInsulatedCableGold"), I2Blocks.blockInsulatedCableGold),
                "cable_insulated_gold.png");

        itemInsulatedCableSteel = ItemHelper.createItem(MOD_ID,
                new ItemCable("insulated.cable.steel", nextItemID("itemInsulatedCableSteel"), I2Blocks.blockInsulatedCableSteel),
                "cable_insulated_steel.png");

        toolTreetap = ItemHelper.createItem(MOD_ID,
                new ItemTreetap("tool.treetap", nextItemID("toolTreetap")),
                "tool_treetap.png").setMaxStackSize(1);

        toolHammer = ItemHelper.createItem(MOD_ID,
                new ItemTools("tool.hammer", nextItemID("toolHammer")),
                "tool_hammer.png");

        toolCutters = ItemHelper.createItem(MOD_ID,
                new ItemTools("tool.cutters", nextItemID("toolCutters")),
                "tool_cutters.png");

        toolWrench = ItemHelper.createItem(MOD_ID,
                new ItemWrench("tool.wrench", nextItemID("toolWrench")),
                "tool_wrench.png").setMaxStackSize(1);

        toolChainsaw = ItemHelper.createItem(MOD_ID,
                new ItemToolChainsaw("tool.chainsaw", nextItemID("toolChainsaw")),
                "tool_chainsaw.png");

        toolDrill = ItemHelper.createItem(MOD_ID,
                new ItemToolDrill("tool.drill", nextItemID("toolDrill"), ToolMaterial.iron),
                "tool_drill.png");

        toolDrillGold = ItemHelper.createItem(MOD_ID,
                new ItemToolDrill("tool.drill.gold", nextItemID("toolDrillGold"), ToolMaterial.gold),
                "tool_drill_gold.png");

        toolDrillDiamond = ItemHelper.createItem(MOD_ID,
                new ItemToolDrill("tool.drill.diamond", nextItemID("toolDrillDiamond"), ToolMaterial.diamond),
                "tool_drill_diamond.png");

        toolNanoSword = ItemHelper.createItem(MOD_ID,
                new ItemToolNanoSword("tool.nanosword", nextItemID("toolNanoSword")));

        armorHelmetHazmat = ItemHelper.createItem(MOD_ID,
                new ItemArmor("armor.helmet.hazmat", nextItemID("armorHelmetHazmat"), armorMaterialHazmat, 0),
                "armor_hazmat_helmet.png");

        armorChestplateHazmat = ItemHelper.createItem(MOD_ID,
                new ItemArmor("armor.chestplate.hazmat", nextItemID("armorChestplateHazmat"), armorMaterialHazmat, 1),
                "armor_hazmat_chestplate.png");

        armorLeggingsHazmat = ItemHelper.createItem(MOD_ID,
                new ItemArmor("armor.leggings.hazmat", nextItemID("armorLeggingsHazmat"), armorMaterialHazmat, 2),
                "armor_hazmat_leggings.png");

        armorBootsHazmat = ItemHelper.createItem(MOD_ID,
                new ItemArmor("armor.boots.hazmat", nextItemID("armorBootsHazmat"), armorMaterialHazmat, 3),
                "armor_hazmat_boots.png");

        armorHelmetIridium = ItemHelper.createItem(MOD_ID,
                new ItemArmorIridium("armor.helmet.iridium", nextItemID("armorHelmetIridium"), armorMaterialIridium, 0),
                "armor_iridium_helmet.png");

        armorChestplateIridium = ItemHelper.createItem(MOD_ID,
                new ItemArmorIridium("armor.chestplate.iridium", nextItemID("armorChestplateIridium"), armorMaterialIridium, 1),
                "armor_iridium_chestplate.png");

        armorLeggingsIridium = ItemHelper.createItem(MOD_ID,
                new ItemArmorIridium("armor.leggings.iridium", nextItemID("armorLeggingsIridium"), armorMaterialIridium, 2),
                "armor_iridium_leggings.png");

        armorBootsIridium = ItemHelper.createItem(MOD_ID,
                new ItemArmorIridium("armor.boots.iridium", nextItemID("armorBootsIridium"), armorMaterialIridium, 3),
                "armor_iridium_boots.png");

        batteryRedstone = ItemHelper.createItem(MOD_ID,
                new ItemBatteryRedstone("battery.redstone", nextItemID("batteryRedstone")));

        batteryAdvanced = ItemHelper.createItem(MOD_ID,
                new ItemBatteryAdvanced("battery.advanced", nextItemID("batteryAdvanced")));

        batteryCrystal = ItemHelper.createItem(MOD_ID,
                new ItemBatteryCrystal("battery.crystal", nextItemID("batteryCrystal")));

        batteryLapis = ItemHelper.createItem(MOD_ID,
                new ItemBatteryLapis("battery.lapis", nextItemID("batteryLapis")));

        cellEmpty = ItemHelper.createItem(MOD_ID,
                new ItemCell("cell.empty", nextItemID("cellEmpty")),
                "cell_empty.png");

        cellWater = ItemHelper.createItem(MOD_ID,
                new Item("cell.water", nextItemID("cellWater")),
                "cell_water.png");

        cellLava = ItemHelper.createItem(MOD_ID,
                new Item("cell.lava", nextItemID("cellLava")),
                "cell_lava.png");

        cellUranium = ItemHelper.createItem(MOD_ID,
                new ItemCellUranium("cell.uranium", nextItemID("cellUranium")),
                "cell_uranium.png").setMaxStackSize(1);

        cellCoolant = ItemHelper.createItem(MOD_ID,
                new ItemCellCoolant("cell.coolant", nextItemID("cellCoolant")),
                "cell_coolant.png").setMaxStackSize(1);

        canEmpty = ItemHelper.createItem(MOD_ID,
                new Item("can.empty", nextItemID("canEmpty")),
                "can_empty.png");

        canFood = ItemHelper.createItem(MOD_ID,
                new ItemFoodStackable("can.food", nextItemID("canFood"), 2, false, 20),
                "can_food.png").setMaxStackSize(10);

        resin = ItemHelper.createItem(MOD_ID,
                new Item("resin", nextItemID("resin")),
                "resin.png");

        rubber = ItemHelper.createItem(MOD_ID,
                new Item("rubber", nextItemID("rubber")),
                "rubber.png");

        circuitBasic = ItemHelper.createItem(MOD_ID,
                new Item("circuit", nextItemID("circuitBasic")),
                "circuit.png");

        circuitAdvanced = ItemHelper.createItem(MOD_ID,
                new Item("circuitadvanced", nextItemID("circuitAdvanced")),
                "circuit_advanced.png");

        scrap = ItemHelper.createItem(MOD_ID,
                new Item("scrap", nextItemID("scrap")),
                "scrap.png");

        reactorPlate = ItemHelper.createItem(MOD_ID,
                new Item("reactorplate", nextItemID("reactorPlate")),
                "plate_reactor.png");

        ingotIridiumScrap = ItemHelper.createItem(MOD_ID,
                new Item("ingot.iridium.scrap", nextItemID("ingotIridiumScrap")),
                "ingot_iridium_scrap.png");

        upgradePlate = ItemHelper.createItem(MOD_ID,
                new Item("upgrade.plate", nextItemID("upgradePlate")),
                "plate_upgrade.png");
        upgradeSpeed = ItemHelper.createItem(MOD_ID,
                new Item("upgrade.speed", nextItemID("upgradeSpeed")).setMaxStackSize(4),
                "upgrade_speed.png");
        upgradeTransformer = ItemHelper.createItem(MOD_ID,
                new Item("upgrade.transformer", nextItemID("upgradeTransformer")).setMaxStackSize(4),
                "upgrade_transformer.png");
        upgradeEnergy = ItemHelper.createItem(MOD_ID,
                new Item("upgrade.energy", nextItemID("upgradeEnergy")).setMaxStackSize(4),
                "upgrade_energy.png");
        upgradePusher = ItemHelper.createItem(MOD_ID,
                new Item("upgrade.pusher", nextItemID("upgradePusher")).setMaxStackSize(4),
                "upgrade_pusher.png");
        upgradePuller = ItemHelper.createItem(MOD_ID,
                new Item("upgrade.puller", nextItemID("upgradePuller")).setMaxStackSize(4),
                "upgrade_puller.png");
        upgradeBlasting = ItemHelper.createItem(MOD_ID,
                new Item("upgrade.blasting", nextItemID("upgradeBlasting")).setMaxStackSize(4),
                "upgrade_blasting.png");

        foodJoffos = ItemHelper.createItem(MOD_ID,
                new ItemFoodStackable("food.joffos", nextItemID("foodJoffos"), 1, false, 10),
                "food_joffos.png");

        taggedItems();
    }
}
