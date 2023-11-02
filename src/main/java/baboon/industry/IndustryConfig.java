package baboon.industry;

import turniplabs.halplibe.util.ConfigUpdater;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

public class IndustryConfig {
    public static TomlConfigHandler cfg;

    private int blockID = 1000;
    private int nextBlockID() {
        return blockID++;
    }

    private int itemID = 17000;
    private int nextItemID() {
        return itemID++;
    }

    public IndustryConfig() {
        Toml properties = new Toml("Industry2's TOML Config");
        ConfigUpdater updater = ConfigUpdater.fromProperties();

        properties.addCategory("Energy Values")
                .addEntry("lowVoltage", 16)
                .addEntry("mediumVoltage", 32)
                .addEntry("highVoltage", 256)
                .addEntry("extraHighVoltage", 512)
                .addEntry("lvStorage", 1024)
                .addEntry("mvStorage", 4096)
                .addEntry("hvStorage", 16384)
                .addEntry("ehvStorage", 65536);

        properties.addCategory("Item IDs")
                .addEntry("rawTin", nextItemID())
                .addEntry("rawCopper", nextItemID())
                .addEntry("rawUranium", nextItemID())
                .addEntry("dustTin", nextItemID())
                .addEntry("dustCopper", nextItemID())
                .addEntry("dustBronze", nextItemID())
                .addEntry("dustIron", nextItemID())
                .addEntry("dustGold", nextItemID())
                .addEntry("dustCoal", nextItemID())
                .addEntry("ingotTin", nextItemID())
                .addEntry("ingotCopper", nextItemID())
                .addEntry("ingotBronze", nextItemID())
                .addEntry("ingotUranium", nextItemID())
                .addEntry("plateTin", nextItemID())
                .addEntry("plateCopper", nextItemID())
                .addEntry("plateBronze", nextItemID())
                .addEntry("plateIron", nextItemID())
                .addEntry("plateGold", nextItemID())
                .addEntry("plateSteel", nextItemID())
                .addEntry("itemTinCable", nextItemID())
                .addEntry("itemCopperCable", nextItemID())
                .addEntry("itemGoldCable", nextItemID())
                .addEntry("itemSteelCable", nextItemID())
                .addEntry("itemInsulatedTinCable", nextItemID())
                .addEntry("itemInsulatedCopperCable", nextItemID())
                .addEntry("itemInsulatedGoldCable", nextItemID())
                .addEntry("itemInsulatedSteelCable", nextItemID())
                .addEntry("toolTreetap", nextItemID())
                .addEntry("toolHammer", nextItemID())
                .addEntry("toolCutters", nextItemID())
                .addEntry("toolWrench", nextItemID())
                .addEntry("batteryRedstone", nextItemID())
                .addEntry("batteryAdvanced", nextItemID())
                .addEntry("batteryCrystal", nextItemID())
                .addEntry("batteryLapis", nextItemID())
                .addEntry("cellEmpty", nextItemID())
                .addEntry("cellWater", nextItemID())
                .addEntry("cellLava", nextItemID())
                .addEntry("cellUranium", nextItemID())
                .addEntry("cellCoolant", nextItemID())
                .addEntry("canEmpty", nextItemID())
                .addEntry("canFood", nextItemID())
                .addEntry("resin", nextItemID())
                .addEntry("rubber", nextItemID())
                .addEntry("circuitBasic", nextItemID())
                .addEntry("circuitAdvanced", nextItemID())
                .addEntry("scrap", nextItemID());

        properties.addCategory("Block IDs")
                .addEntry("oreTinStone", nextBlockID())
                .addEntry("oreTinBasalt", nextBlockID())
                .addEntry("oreTinLimestone", nextBlockID())
                .addEntry("oreTinGranite", nextBlockID())
                .addEntry("oreCopperStone", nextBlockID())
                .addEntry("oreCopperBasalt", nextBlockID())
                .addEntry("oreCopperLimestone", nextBlockID())
                .addEntry("oreCopperGranite", nextBlockID())
                .addEntry("oreUraniumStone", nextBlockID())
                .addEntry("oreUraniumBasalt", nextBlockID())
                .addEntry("oreUraniumLimestone", nextBlockID())
                .addEntry("oreUraniumGranite", nextBlockID())
                .addEntry("blockTin", nextBlockID())
                .addEntry("blockCopper", nextBlockID())
                .addEntry("blockBronze", nextBlockID())
                .addEntry("blockUranium", nextBlockID())
                .addEntry("blockTinCable", nextBlockID())
                .addEntry("blockCopperCable", nextBlockID())
                .addEntry("blockGoldCable", nextBlockID())
                .addEntry("blockSteelCable", nextBlockID())
                .addEntry("blockInsulatedTinCable", nextBlockID())
                .addEntry("blockInsulatedCopperCable", nextBlockID())
                .addEntry("blockInsulatedGoldCable", nextBlockID())
                .addEntry("blockInsulatedSteelCable", nextBlockID())
                .addEntry("machineCasing", nextBlockID())
                .addEntry("machineCasingAdvanced", nextBlockID())
                .addEntry("generator", nextBlockID())
                .addEntry("generatorWatermill", nextBlockID())
                .addEntry("generatorWindmill", nextBlockID())
                .addEntry("generatorGeothermal", nextBlockID())
                .addEntry("generatorSolar", nextBlockID())
                .addEntry("generatorSolarArrayLV", nextBlockID())
                .addEntry("generatorSolarArrayMV", nextBlockID())
                .addEntry("generatorSolarArrayHV", nextBlockID())
                .addEntry("generatorSolarArraySHV", nextBlockID())
                .addEntry("batboxLV", nextBlockID())
                .addEntry("batboxMV", nextBlockID())
                .addEntry("batboxHV", nextBlockID())
                .addEntry("batboxSHV", nextBlockID())
                .addEntry("transformerMVtoLV", nextBlockID())
                .addEntry("transformerHVtoMV", nextBlockID())
                .addEntry("transformerSHVtoMV", nextBlockID())
                .addEntry("electricFurnace", nextBlockID())
                .addEntry("macerator", nextBlockID())
                .addEntry("compressor", nextBlockID())
                .addEntry("wiremill", nextBlockID())
                .addEntry("extractor", nextBlockID())
                .addEntry("recycler", nextBlockID())
                .addEntry("cannery", nextBlockID())
                .addEntry("inductionFurnace", nextBlockID())
                .addEntry("rotaryMacerator", nextBlockID())
                .addEntry("singularityCompressor", nextBlockID())
                .addEntry("laserCutter", nextBlockID())
                .addEntry("CentrifugeExtractor", nextBlockID())
                .addEntry("hardenedCoal", nextBlockID())
                .addEntry("rubberLeaves", nextBlockID())
                .addEntry("rubberLog", nextBlockID())
                .addEntry("rubberSapling", nextBlockID());

        cfg = new TomlConfigHandler(updater, Industry2.MOD_ID, properties);
    }
}
