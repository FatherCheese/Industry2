package baboon.industry.block;

import baboon.industry.Industry2;
import baboon.industry.IndustryConfig;
import baboon.industry.block.cables.*;
import baboon.industry.block.cables.entity.TileEntityCable;
import baboon.industry.block.generator.*;
import baboon.industry.block.generator.entity.*;
import baboon.industry.block.storage.BlockBatboxEHV;
import baboon.industry.block.storage.BlockBatboxHV;
import baboon.industry.block.storage.BlockBatboxLV;
import baboon.industry.block.storage.BlockBatboxMV;
import baboon.industry.block.storage.entity.TileEntityBatboxEHV;
import baboon.industry.block.storage.entity.TileEntityBatboxHV;
import baboon.industry.block.storage.entity.TileEntityBatboxLV;
import baboon.industry.block.storage.entity.TileEntityBatboxMV;
import baboon.industry.gui.generator.*;
import baboon.industry.gui.storage.*;
import net.minecraft.client.render.block.model.BlockModelRenderBlocks;
import net.minecraft.client.sound.block.BlockSounds;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.tool.ItemToolPickaxe;
import sunsetsatellite.energyapi.EnergyAPI;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.EntityHelper;

public class IndustryBlocks {
    private final String MOD_ID = Industry2.MOD_ID;

    private int blockID(String configName) {
        return IndustryConfig.cfg.getInt("Block IDs." + configName);
    }

    // Ore
    public static Block oreTinStone;
    public static Block oreTinBasalt;
    public static Block oreTinLimestone;
    public static Block oreTinGranite;
    public static Block oreCopperStone;
    public static Block oreCopperBasalt;
    public static Block oreCopperLimestone;
    public static Block oreCopperGranite;
    public static Block oreUraniumStone;
    public static Block oreUraniumBasalt;
    public static Block oreUraniumLimestone;
    public static Block oreUraniumGranite;

    // Material Blocks
    public static Block blockTin;
    public static Block blockCopper;
    public static Block blockBronze;
    public static Block blockUranium;

    // Cables
    public static Block blockCableTin;
    public static Block blockCableCopper;
    public static Block blockCableGold;
    public static Block blockCableSteel;
    public static Block blockInsulatedCableTin;
    public static Block blockInsulatedCableCopper;
    public static Block blockInsulatedCableGold;
    public static Block blockInsulatedCableSteel;

    // Machines
    public static Block machineCasingBasic;
    public static Block machineCasingAdvanced;
    public static Block generator;
    public static Block generatorWatermill;
    public static Block generatorWindmill;
    public static Block generatorGeothermal;
    public static Block generatorSolar;
    public static Block solarArrayLV;
    public static Block solarArrayMV;
    public static Block solarArrayHV;
    public static Block solarArrayEHV;
    public static Block batboxLV;
    public static Block batboxMV;
    public static Block batboxHV;
    public static Block batboxEHV;
    public static Block transformerMVtoLV;
    public static Block transformerHVtoMV;
    public static Block transformerEHVtoHV;
    public static Block machineFurnace;
    public static Block machineMacerator;
    public static Block machineCompressor;
    public static Block machineCutter;
    public static Block machineExtractor;
    public static Block machineRecycler;
    public static Block machineCannery;
    public static Block advancedMachineFurnace;
    public static Block advancedMachineMacerator;
    public static Block advancedMachineCompressor;
    public static Block advancedMachineCutter;
    public static Block advancedMachineExtractor;

    // Miscellaneous
    public static Block hardenedCoal;
    public static Block leavesRubberWood;
    public static Block logRubberWood;
    public static Block saplingRubberWood;

    private void pickaxeLevels() {
        ItemToolPickaxe.miningLevels.put(oreTinStone, 1);
        ItemToolPickaxe.miningLevels.put(oreTinBasalt, 1);
        ItemToolPickaxe.miningLevels.put(oreTinLimestone, 1);
        ItemToolPickaxe.miningLevels.put(oreTinGranite, 1);
        ItemToolPickaxe.miningLevels.put(oreCopperStone, 1);
        ItemToolPickaxe.miningLevels.put(oreCopperBasalt, 1);
        ItemToolPickaxe.miningLevels.put(oreCopperLimestone, 1);
        ItemToolPickaxe.miningLevels.put(oreCopperGranite, 1);
        ItemToolPickaxe.miningLevels.put(oreUraniumStone, 2);
        ItemToolPickaxe.miningLevels.put(oreUraniumBasalt, 2);
        ItemToolPickaxe.miningLevels.put(oreUraniumLimestone, 2);
        ItemToolPickaxe.miningLevels.put(oreUraniumGranite, 2);
        ItemToolPickaxe.miningLevels.put(blockTin, 2);
        ItemToolPickaxe.miningLevels.put(blockCopper, 2);
        ItemToolPickaxe.miningLevels.put(blockBronze, 2);
        ItemToolPickaxe.miningLevels.put(blockUranium, 2);
        ItemToolPickaxe.miningLevels.put(generator, 2);
        ItemToolPickaxe.miningLevels.put(generatorWatermill, 2);
        ItemToolPickaxe.miningLevels.put(generatorWindmill, 2);
        ItemToolPickaxe.miningLevels.put(generatorGeothermal, 2);
        ItemToolPickaxe.miningLevels.put(solarArrayLV, 2);
        ItemToolPickaxe.miningLevels.put(solarArrayMV, 2);
        ItemToolPickaxe.miningLevels.put(solarArrayHV, 2);
        ItemToolPickaxe.miningLevels.put(solarArrayEHV, 2);
        ItemToolPickaxe.miningLevels.put(batboxMV, 2);
        ItemToolPickaxe.miningLevels.put(batboxHV, 2);
        ItemToolPickaxe.miningLevels.put(batboxEHV, 2);
        ItemToolPickaxe.miningLevels.put(transformerMVtoLV, 2);
        ItemToolPickaxe.miningLevels.put(transformerHVtoMV, 2);
        ItemToolPickaxe.miningLevels.put(transformerEHVtoHV, 2);
        ItemToolPickaxe.miningLevels.put(hardenedCoal, 3);
    }

    private void addToGuiMap() {
        EnergyAPI.addToNameGuiMap("IndustryGenerator", GuiGenerator.class, TileEntityGenerator.class, ContainerGenerator.class);
        EnergyAPI.addToNameGuiMap("IndustryWatermill", GuiGeneratorWatermill.class, TileEntityGeneratorWatermill.class, ContainerGeneratorWatermill.class);
        EnergyAPI.addToNameGuiMap("IndustryWindmill", GuiGeneratorWindmill.class, TileEntityGeneratorWindmill.class, ContainerGeneratorWindmill.class);
        EnergyAPI.addToNameGuiMap("IndustryGeothermal", GuiGeneratorGeothermal.class, TileEntityGeneratorGeothermal.class, ContainerGeneratorGeothermal.class);
        EnergyAPI.addToNameGuiMap("IndustrySolar", GuiGeneratorSolar.class, TileEntityGeneratorSolar.class, ContainerSolarBase.class);
        EnergyAPI.addToNameGuiMap("IndustryArrayLV", GuiArrayLV.class, TileEntityArrayLV.class, ContainerSolarBase.class);
        EnergyAPI.addToNameGuiMap("IndustryArrayMV", GuiArrayMV.class, TileEntityArrayMV.class, ContainerSolarBase.class);
        EnergyAPI.addToNameGuiMap("IndustryArrayHV", GuiArrayHV.class, TileEntityArrayHV.class, ContainerSolarBase.class);
        EnergyAPI.addToNameGuiMap("IndustryArrayEHV", GuiArrayEHV.class, TileEntityArrayEHV.class, ContainerSolarBase.class);
        EnergyAPI.addToNameGuiMap("IndustryBatboxLV", GuiBatboxLV.class, TileEntityBatboxLV.class, ContainerBatboxBase.class);
        EnergyAPI.addToNameGuiMap("IndustryBatboxMV", GuiBatboxMV.class, TileEntityBatboxMV.class, ContainerBatboxBase.class);
        EnergyAPI.addToNameGuiMap("IndustryBatboxHV", GuiBatboxHV.class, TileEntityBatboxHV.class, ContainerBatboxBase.class);
        EnergyAPI.addToNameGuiMap("IndustryBatboxEHV", GuiBatboxEHV.class, TileEntityBatboxEHV.class, ContainerBatboxBase.class);
    }

    private void initializeTiles() {
        EntityHelper.createTileEntity(TileEntityCable.class, "Cable");
        EntityHelper.createTileEntity(TileEntityGenerator.class, "IndustryGenerator");
        EntityHelper.createTileEntity(TileEntityGeneratorWatermill.class, "IndustryWatermill");
        EntityHelper.createTileEntity(TileEntityGeneratorWindmill.class, "IndustryWindmill");
        EntityHelper.createTileEntity(TileEntityGeneratorGeothermal.class, "IndustryGeothermal");
        EntityHelper.createTileEntity(TileEntityGeneratorSolar.class, "IndustrySolar");
        EntityHelper.createTileEntity(TileEntityArrayLV.class, "IndustryArrayLV");
        EntityHelper.createTileEntity(TileEntityArrayMV.class, "IndustryArrayMV");
        EntityHelper.createTileEntity(TileEntityArrayHV.class, "IndustryArrayHV");
        EntityHelper.createTileEntity(TileEntityArrayEHV.class, "IndustryArrayEHV");
        EntityHelper.createTileEntity(TileEntityBatboxLV.class, "IndustryBatboxLV");
        EntityHelper.createTileEntity(TileEntityBatboxMV.class, "IndustryBatboxMV");
        EntityHelper.createTileEntity(TileEntityBatboxHV.class, "IndustryBatboxHV");
        EntityHelper.createTileEntity(TileEntityBatboxEHV.class, "IndustryBatboxEHV");
    }

    public void initializeBlocks() {
        BlockBuilder oreBuilder = new BlockBuilder(MOD_ID)
                .setBlockSound(BlockSounds.STONE)
                .setHardness(3.5f)
                .setResistance(5.0f)
                .setTags(BlockTags.MINEABLE_BY_PICKAXE);

        BlockBuilder materialBlockBuilder = new BlockBuilder(MOD_ID)
                .setBlockSound(BlockSounds.METAL)
                .setHardness(5.0f)
                .setResistance(10.0f)
                .setTags(BlockTags.MINEABLE_BY_PICKAXE);

        BlockBuilder cableBuilder = new BlockBuilder(MOD_ID)
                .setBlockSound(BlockSounds.METAL)
                .setHardness(0.2f)
                .setResistance(0.0f)
                .setBlockModel(new BlockModelRenderBlocks(32))
                .setTags(BlockTags.NOT_IN_CREATIVE_MENU);

        BlockBuilder insulatedCableBuilder = new BlockBuilder(MOD_ID)
                .setBlockSound(BlockSounds.CLOTH)
                .setHardness(0.2f)
                .setResistance(0.0f)
                .setBlockModel(new BlockModelRenderBlocks(32))
                .setTags(BlockTags.NOT_IN_CREATIVE_MENU);

        BlockBuilder machineBuilderBlank = new BlockBuilder(MOD_ID)
                .setBlockSound(BlockSounds.METAL)
                .setHardness(5.0f)
                .setResistance(0.0f);

        BlockBuilder machineBuilder = machineBuilderBlank
                .setTopBottomTexture("machine_casing_basic.png")
                .setEastTexture("machine_casing_basic.png")
                .setSouthTexture("machine_casing_basic.png")
                .setWestTexture("machine_casing_basic.png");

        BlockBuilder advancedMachineBuilder = machineBuilderBlank
                .setTopBottomTexture("machine_casing_advanced.png")
                .setEastTexture("machine_casing_advanced.png")
                .setSouthTexture("machine_casing_advanced.png")
                .setWestTexture("machine_casing_advanced.png");

        oreTinStone = oreBuilder
                .setTextures("ore_tin_stone.png")
                .build(new BlockOreTin("ore.tin.stone", blockID("oreTinStone"), Material.stone));

        oreTinBasalt = oreBuilder
                .setTextures("ore_tin_basalt.png")
                .build(new BlockOreTin("ore.tin.basalt", blockID("oreTinBasalt"), Material.stone));

        oreTinLimestone = oreBuilder
                .setTextures("ore_tin_limestone.png")
                .build(new BlockOreTin("ore.tin.limestone", blockID("oreTinLimestone"), Material.stone));

        oreTinGranite = oreBuilder
                .setTextures("ore_tin_granite.png")
                .build(new BlockOreTin("ore.tin.granite", blockID("oreTinGranite"), Material.stone));

        oreCopperStone = oreBuilder
                .setTextures("ore_copper_stone.png")
                .build(new BlockOreCopper("ore.copper.stone", blockID("oreCopperStone"), Material.stone));

        oreCopperBasalt = oreBuilder
                .setTextures("ore_copper_basalt.png")
                .build(new BlockOreCopper("ore.copper.basalt", blockID("oreCopperBasalt"), Material.stone));

        oreCopperLimestone = oreBuilder
                .setTextures("ore_copper_limestone.png")
                .build(new BlockOreCopper("ore.copper.limestone", blockID("oreCopperLimestone"), Material.stone));

        oreCopperGranite = oreBuilder
                .setTextures("ore_copper_granite.png")
                .build(new BlockOreCopper("ore.copper.granite", blockID("oreCopperGranite"), Material.stone));

        oreUraniumStone = oreBuilder
                .setTextures("ore_uranium_stone.png")
                .build(new BlockOreUranium("ore.uranium.stone", blockID("oreUraniumStone"), Material.stone));

        oreUraniumBasalt = oreBuilder
                .setTextures("ore_uranium_basalt.png")
                .build(new BlockOreUranium("ore.uranium.basalt", blockID("oreUraniumBasalt"), Material.stone));

        oreUraniumLimestone = oreBuilder
                .setTextures("ore_uranium_limestone.png")
                .build(new BlockOreUranium("ore.uranium.limestone", blockID("oreUraniumLimestone"), Material.stone));

        oreUraniumGranite = oreBuilder
                .setTextures("ore_uranium_granite.png")
                .build(new BlockOreUranium("ore.uranium.granite", blockID("oreUraniumGranite"), Material.stone));

        blockTin = materialBlockBuilder
                .setTopTexture("block_tin_top.png")
                .setSideTextures("block_tin_sides.png")
                .setBottomTexture("block_tin_bottom.png")
                .build(new Block("block.tin", blockID("blockTin"), Material.metal));

        blockCopper = materialBlockBuilder
                .setTopTexture("block_copper_top.png")
                .setSideTextures("block_copper_sides.png")
                .setBottomTexture("block_copper_bottom.png")
                .build(new Block("block.copper", blockID("blockCopper"), Material.metal));

        blockBronze = materialBlockBuilder
                .setTopTexture("block_bronze_top.png")
                .setSideTextures("block_bronze_sides.png")
                .setBottomTexture("block_bronze_bottom.png")
                .build(new Block("block.bronze", blockID("blockBronze"), Material.metal));

        blockUranium = materialBlockBuilder
                .setTopTexture("block_uranium_top.png")
                .setSideTextures("block_uranium_sides.png")
                .setBottomTexture("block_uranium_bottom.png")
                .build(new Block("block.uranium", blockID("blockUranium"), Material.metal));

        blockCableTin = cableBuilder
                .setTextures("block_tin_top.png")
                .build(new BlockCableTin("cable.tin", blockID("blockTinCable"), Material.metal));

        blockCableCopper = cableBuilder
                .setTextures("block_copper_top.png")
                .build(new BlockCableCopper("cable.copper", blockID("blockCopperCable"), Material.metal));

        blockCableGold = cableBuilder
                .setTextures(17, 4)
                .build(new BlockCableGold("cable.gold", blockID("blockGoldCable"), Material.metal));

        blockCableSteel = cableBuilder
                .setTextures(19, 4)
                .build(new BlockCableSteel("cable.steel", blockID("blockSteelCable"), Material.metal));

        blockInsulatedCableTin = insulatedCableBuilder
                .setTextures("insulated_cable_tin.png")
                .build(new BlockInsulatedCableTin("cable.tin", blockID("blockInsulatedTinCable"), Material.cloth));

        blockInsulatedCableCopper = insulatedCableBuilder
                .setTextures("insulated_cable_copper.png")
                .build(new BlockInsulatedCableCopper("cable.copper", blockID("blockInsulatedCopperCable"), Material.cloth));

        blockInsulatedCableGold = insulatedCableBuilder
                .setTextures("insulated_cable_gold.png")
                .build(new BlockInsulatedCableGold("cable.gold", blockID("blockInsulatedGoldCable"), Material.cloth));

        blockInsulatedCableSteel = insulatedCableBuilder
                .setTextures("insulated_cable_steel.png")
                .build(new BlockInsulatedCableSteel("cable.steel", blockID("blockInsulatedSteelCable"), Material.cloth));

        machineCasingBasic = machineBuilderBlank
                .setTextures("machine_casing_basic.png")
                .build(new Block("machine.casing", blockID("machineCasing"), Material.metal));

        machineCasingAdvanced = machineBuilderBlank
                .setTextures("machine_casing_advanced.png")
                .build(new Block("machine.casing.advanced", blockID("machineCasingAdvanced"), Material.metal));

        generator = machineBuilder
                .setNorthTexture("generator.png")
                .build(new BlockGenerator("generator", blockID("generator"), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        generatorWatermill = machineBuilderBlank
                .setTopBottomTexture("machine_casing_basic.png")
                .setSideTextures("generator_watermill.png")
                .build(new BlockGeneratorWatermill("generator.watermill", blockID("generatorWatermill"), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        generatorWindmill = machineBuilder
                .setNorthTexture("generator_windmill.png")
                .build(new BlockGeneratorWindmill("generator.windmill", blockID("generatorWindmill"), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        generatorGeothermal = machineBuilder
                .setNorthTexture("generator_geothermal.png")
                .build(new BlockGeneratorGeothermal("generator.geothermal", blockID("generatorGeothermal"), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        generatorSolar = machineBuilderBlank
                .setTopTexture("generator_solar.png")
                .setSideTextures("machine_casing_basic.png")
                .setBottomTexture("machine_casing_basic.png")
                .build(new BlockGeneratorSolar("generator.solar", blockID("generatorSolar"), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        solarArrayLV = new BlockBuilder(MOD_ID)
                .setBlockSound(BlockSounds.WOOD)
                .setHardness(5.0f)
                .setResistance(0.0f)
                .setTopTexture("arrayLV.png")
                .setSideTextures("batboxLV.png")
                .setBottomTexture("batboxLV.png")
                .build(new BlockArrayLV("array.lv", blockID("arrayLV"), Material.wood))
                .withTags(BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_PICKAXE);

        solarArrayMV = machineBuilderBlank
                .setTopTexture("arrayMV.png")
                .setSideTextures("batboxMV.png")
                .setBottomTexture("batboxMV.png")
                .build(new BlockArrayMV("array.mv", blockID("arrayMV"), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        solarArrayHV = machineBuilderBlank
                .setTopTexture("generator_solar.png")
                .setSideTextures("batboxHV.png")
                .setBottomTexture("batboxHV.png")
                .build(new BlockArrayHV("array.hv", blockID("arrayHV"), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        solarArrayEHV = machineBuilderBlank
                .setTopTexture("arrayEHV.png")
                .setSideTextures("batboxEHV.png")
                .setBottomTexture("batboxEHV.png")
                .build(new BlockArrayEHV("array.ehv", blockID("arrayEHV"), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        batboxLV = new BlockBuilder(MOD_ID)
                .setBlockSound(BlockSounds.WOOD)
                .setHardness(5.0f)
                .setResistance(0.0f)
                .setTopTexture("batboxLV_input.png")
                .setSideTextures("batboxLV.png")
                .setBottomTexture("batboxLV.png")
                .build(new BlockBatboxLV("batbox.lv", blockID("batboxLV"), Material.wood))
                .withTags(BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_PICKAXE);

        batboxMV = machineBuilderBlank
                .setTopTexture("batboxMV_input.png")
                .setSideTextures("batboxMV.png")
                .setBottomTexture("batboxMV.png")
                .build(new BlockBatboxMV("batbox.mv", blockID("batboxMV"), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        batboxHV = machineBuilderBlank
                .setTopTexture("batboxHV_input.png")
                .setSideTextures("batboxHV.png")
                .setBottomTexture("batboxHV.png")
                .build(new BlockBatboxHV("batbox.hv", blockID("batboxHV"), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        batboxEHV = machineBuilderBlank
                .setTopTexture("batboxEHV_input.png")
                .setSideTextures("batboxEHV.png")
                .setBottomTexture("batboxEHV.png")
                .build(new BlockBatboxEHV("batbox.ehv", blockID("batboxEHV"), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        addToGuiMap();
        initializeTiles();
    }
}
