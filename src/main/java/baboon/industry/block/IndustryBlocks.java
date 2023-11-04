package baboon.industry.block;

import baboon.industry.Industry2;
import baboon.industry.IndustryTags;
import baboon.industry.block.cables.*;
import baboon.industry.block.cables.entity.TileEntityCable;
import baboon.industry.block.generator.*;
import baboon.industry.block.generator.entity.*;
import baboon.industry.block.machines.advanced.*;
import baboon.industry.block.machines.advanced.entity.*;
import baboon.industry.block.machines.basic.*;
import baboon.industry.block.machines.basic.entity.*;
import baboon.industry.block.storage.*;
import baboon.industry.block.storage.entity.*;
import baboon.industry.gui.generator.*;
import baboon.industry.gui.machine.advanced.*;
import baboon.industry.gui.machine.basic.*;
import baboon.industry.gui.storage.*;
import net.minecraft.client.render.block.color.BlockColorLeaves;
import net.minecraft.client.render.block.model.BlockModelRenderBlocks;
import net.minecraft.client.sound.block.BlockSounds;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLog;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.tool.ItemToolPickaxe;
import sunsetsatellite.energyapi.EnergyAPI;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.EntityHelper;

public class IndustryBlocks {
    private final String MOD_ID = Industry2.MOD_ID;

    private int blockID = 1000;
    private int nextBlockID() {
        return blockID++;
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
    public static Block machineWiremill;
    public static Block machineExtractor;
    public static Block machineRecycler;
    public static Block machineCannery;
    public static Block advancedMachineFurnace;
    public static Block advancedMachineMacerator;
    public static Block advancedMachineCompressor;
    public static Block advancedMachineWiremill;
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
        EnergyAPI.addToNameGuiMap("IndustryMachineFurnace", GuiMachineFurnace.class, TileEntityMachineFurnace.class, ContainerMachineBase.class);
        EnergyAPI.addToNameGuiMap("IndustryMachineMacerator", GuiMachineMacerator.class, TileEntityMachineMacerator.class, ContainerMachineBase.class);
        EnergyAPI.addToNameGuiMap("IndustryMachineCompressor", GuiMachineCompressor.class, TileEntityMachineCompressor.class, ContainerMachineBase.class);
        EnergyAPI.addToNameGuiMap("IndustryMachineWiremill", GuiMachineWiremill.class, TileEntityMachineWiremill.class, ContainerMachineBase.class);
        EnergyAPI.addToNameGuiMap("IndustryMachineExtractor", GuiMachineExtractor.class, TileEntityMachineExtractor.class, ContainerMachineBase.class);
        EnergyAPI.addToNameGuiMap("IndustryMachineRecycler", GuiMachineRecycler.class, TileEntityMachineRecycler.class, ContainerMachineBase.class);
        EnergyAPI.addToNameGuiMap("IndustryMachineCannery", GuiMachineCannery.class, TileEntityMachineCannery.class, ContainerMachineCannery.class);
        EnergyAPI.addToNameGuiMap("IndustryAdvancedFurnace", GuiAdvancedFurnace.class, TileEntityAdvancedFurnace.class, ContainerAdvancedBase.class);
        EnergyAPI.addToNameGuiMap("IndustryAdvancedMacerator", GuiAdvancedMacerator.class, TileEntityAdvancedMacerator.class, ContainerAdvancedBase.class);
        EnergyAPI.addToNameGuiMap("IndustryAdvancedCompressor", GuiAdvancedCompressor.class, TileEntityAdvancedCompressor.class, ContainerAdvancedBase.class);
        EnergyAPI.addToNameGuiMap("IndustryAdvancedWiremill", GuiAdvancedWiremill.class, TileEntityAdvancedWiremill.class, ContainerAdvancedBase.class);
        EnergyAPI.addToNameGuiMap("IndustryAdvancedExtractor", GuiAdvancedExtractor.class, TIleEntityAdvancedExtractor.class, ContainerAdvancedBase.class);
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
        EntityHelper.createTileEntity(TileEntityTransformerMVtoLV.class, "IndustryTransformerMVtoLV");
        EntityHelper.createTileEntity(TileEntityTransformerHVtoMV.class, "IndustryTransformerHVtoMV");
        EntityHelper.createTileEntity(TileEntityTransformerEHVtoHV.class, "IndustryTransformerEHVtoHV");
        EntityHelper.createTileEntity(TileEntityMachineFurnace.class, "IndustryMachineFurnace");
        EntityHelper.createTileEntity(TileEntityMachineMacerator.class, "IndustryMachineMacerator");
        EntityHelper.createTileEntity(TileEntityMachineCompressor.class, "IndustryMachineCompressor");
        EntityHelper.createTileEntity(TileEntityMachineWiremill.class, "IndustryMachineWiremill");
        EntityHelper.createTileEntity(TileEntityMachineExtractor.class, "IndustryMachineExtractor");
        EntityHelper.createTileEntity(TileEntityMachineRecycler.class, "IndustryMachineRecycler");
        EntityHelper.createTileEntity(TileEntityMachineCannery.class, "IndustryMachineCannery");
        EntityHelper.createTileEntity(TileEntityAdvancedFurnace.class, "IndustryAdvancedFurnace");
        EntityHelper.createTileEntity(TileEntityAdvancedMacerator.class, "IndustryAdvancedMacerator");
        EntityHelper.createTileEntity(TileEntityAdvancedCompressor.class, "IndustryAdvancedCompressor");
        EntityHelper.createTileEntity(TileEntityAdvancedWiremill.class, "IndustryAdvancedWiremill");
        EntityHelper.createTileEntity(TIleEntityAdvancedExtractor.class, "IndustryAdvancedExtractor");
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
                .build(new BlockOreTin("ore.tin.stone", nextBlockID(), Material.stone));

        oreTinBasalt = oreBuilder
                .setTextures("ore_tin_basalt.png")
                .build(new BlockOreTin("ore.tin.basalt",nextBlockID(), Material.stone));

        oreTinLimestone = oreBuilder
                .setTextures("ore_tin_limestone.png")
                .build(new BlockOreTin("ore.tin.limestone", nextBlockID(), Material.stone));

        oreTinGranite = oreBuilder
                .setTextures("ore_tin_granite.png")
                .build(new BlockOreTin("ore.tin.granite", nextBlockID(), Material.stone));

        oreCopperStone = oreBuilder
                .setTextures("ore_copper_stone.png")
                .build(new BlockOreCopper("ore.copper.stone", nextBlockID(), Material.stone));

        oreCopperBasalt = oreBuilder
                .setTextures("ore_copper_basalt.png")
                .build(new BlockOreCopper("ore.copper.basalt", nextBlockID(), Material.stone));

        oreCopperLimestone = oreBuilder
                .setTextures("ore_copper_limestone.png")
                .build(new BlockOreCopper("ore.copper.limestone", nextBlockID(), Material.stone));

        oreCopperGranite = oreBuilder
                .setTextures("ore_copper_granite.png")
                .build(new BlockOreCopper("ore.copper.granite", nextBlockID(), Material.stone));

        oreUraniumStone = oreBuilder
                .setTextures("ore_uranium_stone.png")
                .build(new BlockOreUranium("ore.uranium.stone", nextBlockID(), Material.stone));

        oreUraniumBasalt = oreBuilder
                .setTextures("ore_uranium_basalt.png")
                .build(new BlockOreUranium("ore.uranium.basalt", nextBlockID(), Material.stone));

        oreUraniumLimestone = oreBuilder
                .setTextures("ore_uranium_limestone.png")
                .build(new BlockOreUranium("ore.uranium.limestone", nextBlockID(), Material.stone));

        oreUraniumGranite = oreBuilder
                .setTextures("ore_uranium_granite.png")
                .build(new BlockOreUranium("ore.uranium.granite", nextBlockID(), Material.stone));

        blockTin = materialBlockBuilder
                .setTopTexture("block_tin_top.png")
                .setSideTextures("block_tin_sides.png")
                .setBottomTexture("block_tin_bottom.png")
                .build(new Block("block.tin", nextBlockID(), Material.metal));

        blockCopper = materialBlockBuilder
                .setTopTexture("block_copper_top.png")
                .setSideTextures("block_copper_sides.png")
                .setBottomTexture("block_copper_bottom.png")
                .build(new Block("block.copper", nextBlockID(), Material.metal));

        blockBronze = materialBlockBuilder
                .setTopTexture("block_bronze_top.png")
                .setSideTextures("block_bronze_sides.png")
                .setBottomTexture("block_bronze_bottom.png")
                .build(new Block("block.bronze", nextBlockID(), Material.metal));

        blockUranium = materialBlockBuilder
                .setTopTexture("block_uranium_top.png")
                .setSideTextures("block_uranium_sides.png")
                .setBottomTexture("block_uranium_bottom.png")
                .build(new Block("block.uranium", nextBlockID(), Material.metal));

        blockCableTin = cableBuilder
                .setTextures("block_tin_top.png")
                .build(new BlockCableTin("cable.tin", nextBlockID(), Material.metal));

        blockCableCopper = cableBuilder
                .setTextures("block_copper_top.png")
                .build(new BlockCableCopper("cable.copper", nextBlockID(), Material.metal));

        blockCableGold = cableBuilder
                .setTextures(17, 4)
                .build(new BlockCableGold("cable.gold", nextBlockID(), Material.metal));

        blockCableSteel = cableBuilder
                .setTextures(19, 4)
                .build(new BlockCableSteel("cable.steel", nextBlockID(), Material.metal));

        blockInsulatedCableTin = insulatedCableBuilder
                .setTextures("insulated_cable_tin.png")
                .build(new BlockInsulatedCableTin("cable.tin", nextBlockID(), Material.cloth));

        blockInsulatedCableCopper = insulatedCableBuilder
                .setTextures("insulated_cable_copper.png")
                .build(new BlockInsulatedCableCopper("cable.copper", nextBlockID(), Material.cloth));

        blockInsulatedCableGold = insulatedCableBuilder
                .setTextures("insulated_cable_gold.png")
                .build(new BlockInsulatedCableGold("cable.gold", nextBlockID(), Material.cloth));

        blockInsulatedCableSteel = insulatedCableBuilder
                .setTextures("insulated_cable_steel.png")
                .build(new BlockInsulatedCableSteel("cable.steel", nextBlockID(), Material.cloth));

        machineCasingBasic = machineBuilderBlank
                .setTextures("machine_casing_basic.png")
                .build(new Block("machine.casing", nextBlockID(), Material.metal));

        machineCasingAdvanced = machineBuilderBlank
                .setTextures("machine_casing_advanced.png")
                .build(new Block("machine.casing.advanced", nextBlockID(), Material.metal));

        generator = machineBuilder
                .setNorthTexture("generator.png")
                .build(new BlockGenerator("generator", nextBlockID(), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        generatorWatermill = machineBuilderBlank
                .setTopBottomTexture("machine_casing_basic.png")
                .setSideTextures("generator_watermill.png")
                .build(new BlockGeneratorWatermill("generator.watermill", nextBlockID(), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        generatorWindmill = machineBuilder
                .setNorthTexture("generator_windmill.png")
                .build(new BlockGeneratorWindmill("generator.windmill", nextBlockID(), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        generatorGeothermal = machineBuilder
                .setNorthTexture("generator_geothermal.png")
                .build(new BlockGeneratorGeothermal("generator.geothermal", nextBlockID(), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        generatorSolar = machineBuilderBlank
                .setTopTexture("generator_solar.png")
                .setSideTextures("machine_casing_basic.png")
                .setBottomTexture("machine_casing_basic.png")
                .build(new BlockGeneratorSolar("generator.solar", nextBlockID(), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        solarArrayLV = new BlockBuilder(MOD_ID)
                .setBlockSound(BlockSounds.WOOD)
                .setHardness(5.0f)
                .setResistance(0.0f)
                .setTopTexture("arrayLV.png")
                .setSideTextures("batboxLV.png")
                .setBottomTexture("batboxLV.png")
                .build(new BlockArrayLV("array.lv", nextBlockID(), Material.wood))
                .withTags(BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_PICKAXE);

        solarArrayMV = machineBuilderBlank
                .setTopTexture("arrayMV.png")
                .setSideTextures("batboxMV.png")
                .setBottomTexture("batboxMV.png")
                .build(new BlockArrayMV("array.mv", nextBlockID(), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        solarArrayHV = machineBuilderBlank
                .setTopTexture("generator_solar.png")
                .setSideTextures("batboxHV.png")
                .setBottomTexture("batboxHV.png")
                .build(new BlockArrayHV("array.hv", nextBlockID(), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        solarArrayEHV = machineBuilderBlank
                .setTopTexture("arrayEHV.png")
                .setSideTextures("batboxEHV.png")
                .setBottomTexture("batboxEHV.png")
                .build(new BlockArrayEHV("array.ehv", nextBlockID(), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        batboxLV = new BlockBuilder(MOD_ID)
                .setBlockSound(BlockSounds.WOOD)
                .setHardness(5.0f)
                .setResistance(0.0f)
                .setTopTexture("batboxLV_input.png")
                .setSideTextures("batboxLV.png")
                .setBottomTexture("batboxLV.png")
                .build(new BlockBatboxLV("batbox.lv", nextBlockID(), Material.wood))
                .withTags(BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_PICKAXE);

        batboxMV = machineBuilderBlank
                .setTopTexture("batboxMV_input.png")
                .setSideTextures("batboxMV.png")
                .setBottomTexture("batboxMV.png")
                .build(new BlockBatboxMV("batbox.mv", nextBlockID(), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        batboxHV = machineBuilderBlank
                .setTopTexture("batboxHV_input.png")
                .setSideTextures("batboxHV.png")
                .setBottomTexture("batboxHV.png")
                .build(new BlockBatboxHV("batbox.hv", nextBlockID(), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        batboxEHV = machineBuilderBlank
                .setTopTexture("batboxEHV_input.png")
                .setSideTextures("batboxEHV.png")
                .setBottomTexture("batboxEHV.png")
                .build(new BlockBatboxEHV("batbox.ehv", nextBlockID(), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        transformerMVtoLV = machineBuilderBlank
                .setTopTexture("batboxMV_input.png")
                .setSideTextures("transformerMV_LV.png")
                .setBottomTexture("batboxLV_input.png")
                .build(new BlockTransformerMVtoLV("transformer.mvtolv", nextBlockID(), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        transformerHVtoMV = machineBuilderBlank
                .setTopTexture("batboxHV_input.png")
                .setSideTextures("transformerHV_MV.png")
                .setBottomTexture("batboxMV_input.png")
                .build(new BlockTransformerHVtoMV("transformer.hvtomv", nextBlockID(), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        transformerEHVtoHV = machineBuilderBlank
                .setTopTexture("batboxEHV_input.png")
                .setSideTextures("transformerEHV_HV.png")
                .setBottomTexture("batboxHV_input.png")
                .build(new BlockTransformerEHVtoHV("transformer.ehvtohv", nextBlockID(), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        machineFurnace = machineBuilder
                .setNorthTexture("machine_furnace.png")
                .build(new BlockMachineFurnace("machine.furnace", nextBlockID(), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        machineMacerator = machineBuilder
                .setNorthTexture("machine_macerator.png")
                .build(new BlockMachineMacerator("machine.macerator", nextBlockID(), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        machineCompressor = machineBuilder
                .setNorthTexture("machine_compressor.png")
                .build(new BlockMachineCompressor("machine.compressor", nextBlockID(), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        machineWiremill = machineBuilder
                .setNorthTexture("machine_wiremill.png")
                .build(new BlockMachineWiremill("machine.wiremill", nextBlockID(), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        machineExtractor = machineBuilder
                .setNorthTexture("machine_extractor.png")
                .build(new BlockMachineExtractor("machine.extractor", nextBlockID(), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        machineRecycler = machineBuilder
                .setNorthTexture("machine_recycler.png")
                .build(new BlockMachineRecycler("machine.recycler", nextBlockID(), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        machineCannery = machineBuilder
                .setNorthTexture("machine_cannery.png")
                .build(new BlockMachineCannery("machine.cannery", nextBlockID(), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        advancedMachineFurnace = advancedMachineBuilder
                .setNorthTexture("advanced_furnace.png")
                .build(new BlockAdvancedFurnace("advanced.furnace", nextBlockID(), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        advancedMachineMacerator = advancedMachineBuilder
                .setNorthTexture("advanced_macerator.png")
                .build(new BlockAdvancedMacerator("advanced.macerator", nextBlockID(), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        advancedMachineCompressor = advancedMachineBuilder
                .setNorthTexture("advanced_compressor.png")
                .build(new BlockAdvancedCompressor("advanced.compressor", nextBlockID(), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        advancedMachineWiremill = advancedMachineBuilder
                .setNorthTexture("advanced_wiremill.png")
                .build(new BlockAdvancedWiremill("advanced.wiremill", nextBlockID(), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        advancedMachineWiremill = advancedMachineBuilder
                .setNorthTexture("advanced_extractor.png")
                .build(new BlockAdvancedExtractor("advanced.extractor", nextBlockID(), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        hardenedCoal = new BlockBuilder(MOD_ID)
                .setTextures("hardened_coal.png")
                .setBlockSound(BlockSounds.STONE)
                .setHardness(10.0f)
                .setResistance(2000.0f)
                .build(new Block("block.coal.hardened", nextBlockID(), Material.stone))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        leavesRubberWood = new BlockBuilder(MOD_ID)
                .setTextures(2, 20)
                .setBlockSound(BlockSounds.GRASS)
                .setBlockColor(new BlockColorLeaves("pine"))
                .setHardness(0.2f)
                .setLightOpacity(1)
                .build(new BlockLeavesRubberwood("leaves.rubber", nextBlockID(), Material.leaves, false))
                .withTags(BlockTags.SHEARS_DO_SILK_TOUCH, BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_HOE, BlockTags.MINEABLE_BY_SWORD, BlockTags.MINEABLE_BY_SHEARS)
                .withDisabledNeighborNotifyOnMetadataChange()
                .withDisabledStats();

        logRubberWood = new BlockBuilder(MOD_ID)
                .setTopBottomTexture("log_rubber_top.png")
                .setSideTextures("log_rubber.png")
                .setHardness(2.0f)
                .build(new BlockLog("log.rubber", nextBlockID()))
                .withTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT);

        saplingRubberWood = new BlockBuilder(MOD_ID)
                .setTextures("sapling_rubber.png")
                .setBlockSound(BlockSounds.GRASS)
                .setBlockModel(new BlockModelRenderBlocks(1))
                .build(new BlockSaplingRubberwood("sapling.rubber", nextBlockID()))
                .withTags(BlockTags.BROKEN_BY_FLUIDS)
                .withDisabledNeighborNotifyOnMetadataChange();

        addToGuiMap();
        initializeTiles();
        pickaxeLevels();
    }
}
