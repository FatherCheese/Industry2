package baboon.industry.block;

import baboon.industry.Industry2;
import baboon.industry.IndustryConfig;
import baboon.industry.IndustryTags;
import baboon.industry.block.cables.*;
import baboon.industry.block.cables.entity.TileEntityCable;
import baboon.industry.block.generator.*;
import baboon.industry.block.generator.entity.*;
import baboon.industry.block.machines.advanced.*;
import baboon.industry.block.machines.advanced.entity.*;
import baboon.industry.block.machines.basic.*;
import baboon.industry.block.machines.basic.entity.*;
import baboon.industry.block.machines.endgame.BlockEnergyFabricator;
import baboon.industry.block.machines.endgame.entity.TileEntityEnergyFabricator;
import baboon.industry.block.reactor.BlockReactor;
import baboon.industry.block.reactor.BlockReactorChamber;
import baboon.industry.block.reactor.BlockReactorIO;
import baboon.industry.block.reactor.entity.TileEntityReactorIO;
import baboon.industry.block.reactor.entity.TileEntityReactorNew;
import baboon.industry.block.storage.*;
import baboon.industry.block.storage.entity.*;
import baboon.industry.gui.generator.*;
import baboon.industry.gui.machine.advanced.*;
import baboon.industry.gui.machine.basic.*;
import baboon.industry.gui.machine.endgame.ContainerFabricator;
import baboon.industry.gui.machine.endgame.GuiFabricator;
import baboon.industry.gui.reactor.ContainerReactor;
import baboon.industry.gui.reactor.GuiReactor;
import baboon.industry.gui.storage.*;
import net.minecraft.client.render.block.color.BlockColorLeaves;
import net.minecraft.client.render.block.model.BlockModelRenderBlocks;
import net.minecraft.client.sound.block.BlockSounds;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLog;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.tool.ItemToolPickaxe;
import sunsetsatellite.catalyst.Catalyst;
import sunsetsatellite.catalyst.core.util.MpGuiEntry;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.EntityHelper;

public class I2Blocks {
    private final String MOD_ID = Industry2.MOD_ID;

    private int blockID(String blockName) {
        return IndustryConfig.cfg.getInt("Block IDs." + blockName);
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
    public static Block machineTrommel;
    public static Block advancedMachineFurnace;
    public static Block advancedMachineMacerator;
    public static Block advancedMachineCompressor;
    public static Block advancedMachineWiremill;
    public static Block advancedMachineExtractor;

    // Miscellaneous
    public static Block hardenedCoal;
    public static Block leavesRubberWood;
    public static Block logRubberWood;
    public static Block logRubberWoodResin;
    public static Block logRubberWoodResinFull;
    public static Block saplingRubberWood;

    // Nuclear
    public static Block nuclearReactor;
    public static Block nuclearChamber;
    public static Block nuclearIO;

    // End Game
    public static Block energyFabricator;

    public static Block alarm;

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
        ItemToolPickaxe.miningLevels.put(alarm, 2);
        ItemToolPickaxe.miningLevels.put(hardenedCoal, 3);
    }

    private void addToGuiMap() {
        Catalyst.GUIS.register("IndustryGenerator", new MpGuiEntry(TileEntityGenerator.class, GuiGenerator.class, ContainerGenerator.class));
        Catalyst.GUIS.register("IndustryWatermill", new MpGuiEntry(TileEntityGeneratorWatermill.class,GuiGeneratorWatermill.class,  ContainerGeneratorWatermill.class));
        Catalyst.GUIS.register("IndustryWindmill", new MpGuiEntry(TileEntityGeneratorWindmill.class,GuiGeneratorWindmill.class,  ContainerGeneratorWindmill.class));
        Catalyst.GUIS.register("IndustryGeothermal", new MpGuiEntry(TileEntityGeneratorGeothermal.class,GuiGeneratorGeothermal.class,  ContainerGeneratorGeothermal.class));
        Catalyst.GUIS.register("IndustrySolar", new MpGuiEntry(TileEntityGeneratorSolar.class,GuiGeneratorSolar.class,  ContainerSolarBase.class));
        Catalyst.GUIS.register("IndustryArrayLV", new MpGuiEntry(TileEntityArrayLV.class,GuiArrayLV.class,  ContainerSolarBase.class));
        Catalyst.GUIS.register("IndustryArrayMV", new MpGuiEntry(TileEntityArrayMV.class,GuiArrayMV.class,  ContainerSolarBase.class));
        Catalyst.GUIS.register("IndustryArrayHV", new MpGuiEntry(TileEntityArrayHV.class,GuiArrayHV.class,  ContainerSolarBase.class));
        Catalyst.GUIS.register("IndustryArrayEHV", new MpGuiEntry(TileEntityArrayEHV.class,GuiArrayEHV.class,  ContainerSolarBase.class));
        Catalyst.GUIS.register("IndustryBatboxLV", new MpGuiEntry(TileEntityBatboxLV.class,GuiBatboxLV.class,  ContainerBatboxBase.class));
        Catalyst.GUIS.register("IndustryBatboxMV", new MpGuiEntry(TileEntityBatboxMV.class,GuiBatboxMV.class,  ContainerBatboxBase.class));
        Catalyst.GUIS.register("IndustryBatboxHV", new MpGuiEntry(TileEntityBatboxHV.class,GuiBatboxHV.class,  ContainerBatboxBase.class));
        Catalyst.GUIS.register("IndustryBatboxEHV", new MpGuiEntry(TileEntityBatboxEHV.class,GuiBatboxEHV.class,  ContainerBatboxBase.class));
        Catalyst.GUIS.register("IndustryMachineFurnace", new MpGuiEntry(TileEntityMachineFurnace.class,GuiMachineFurnace.class,  ContainerMachineBase.class));
        Catalyst.GUIS.register("IndustryMachineMacerator", new MpGuiEntry(TileEntityMachineMacerator.class,GuiMachineMacerator.class,  ContainerMachineBase.class));
        Catalyst.GUIS.register("IndustryMachineCompressor", new MpGuiEntry(TileEntityMachineCompressor.class,GuiMachineCompressor.class,  ContainerMachineBase.class));
        Catalyst.GUIS.register("IndustryMachineWiremill", new MpGuiEntry(TileEntityMachineWiremill.class,GuiMachineWiremill.class,  ContainerMachineBase.class));
        Catalyst.GUIS.register("IndustryMachineExtractor", new MpGuiEntry(TileEntityMachineExtractor.class,GuiMachineExtractor.class,  ContainerMachineBase.class));
        Catalyst.GUIS.register("IndustryMachineRecycler", new MpGuiEntry(TileEntityMachineRecycler.class,GuiMachineRecycler.class,  ContainerMachineBase.class));
        Catalyst.GUIS.register("IndustryMachineCannery", new MpGuiEntry(TileEntityMachineCannery.class,GuiMachineCannery.class,  ContainerMachineCannery.class));
        Catalyst.GUIS.register("IndustryMachineTrommel", new MpGuiEntry(TileEntityMachineTrommel.class,GuiMachineTrommel.class,  ContainerMachineTrommel.class));
        Catalyst.GUIS.register("IndustryAdvancedFurnace", new MpGuiEntry(TileEntityAdvancedFurnace.class,GuiAdvancedFurnace.class,  ContainerAdvancedBase.class));
        Catalyst.GUIS.register("IndustryAdvancedMacerator", new MpGuiEntry(TileEntityAdvancedMacerator.class,GuiAdvancedMacerator.class,  ContainerAdvancedBase.class));
        Catalyst.GUIS.register("IndustryAdvancedCompressor", new MpGuiEntry(TileEntityAdvancedCompressor.class,GuiAdvancedCompressor.class,  ContainerAdvancedBase.class));
        Catalyst.GUIS.register("IndustryAdvancedWiremill", new MpGuiEntry(TileEntityAdvancedWiremill.class,GuiAdvancedWiremill.class,  ContainerAdvancedBase.class));
        Catalyst.GUIS.register("IndustryAdvancedExtractor", new MpGuiEntry(TIleEntityAdvancedExtractor.class,GuiAdvancedExtractor.class,  ContainerAdvancedBase.class));
        Catalyst.GUIS.register("IndustryReactor", new MpGuiEntry(TileEntityReactorNew.class,GuiReactor.class,  ContainerReactor.class));
        Catalyst.GUIS.register("IndustryFabricator", new MpGuiEntry(TileEntityEnergyFabricator.class,GuiFabricator.class,  ContainerFabricator.class));
    }

    private void initializeTiles() {
        EntityHelper.Core.createTileEntity(TileEntityCable.class, "Cable");
        EntityHelper.Core.createTileEntity(TileEntityGenerator.class, "IndustryGenerator");
        EntityHelper.Core.createTileEntity(TileEntityGeneratorWatermill.class, "IndustryWatermill");
        EntityHelper.Core.createTileEntity(TileEntityGeneratorWindmill.class, "IndustryWindmill");
        EntityHelper.Core.createTileEntity(TileEntityGeneratorGeothermal.class, "IndustryGeothermal");
        EntityHelper.Core.createTileEntity(TileEntityGeneratorSolar.class, "IndustrySolar");
        EntityHelper.Core.createTileEntity(TileEntityArrayLV.class, "IndustryArrayLV");
        EntityHelper.Core.createTileEntity(TileEntityArrayMV.class, "IndustryArrayMV");
        EntityHelper.Core.createTileEntity(TileEntityArrayHV.class, "IndustryArrayHV");
        EntityHelper.Core.createTileEntity(TileEntityArrayEHV.class, "IndustryArrayEHV");
        EntityHelper.Core.createTileEntity(TileEntityBatboxLV.class, "IndustryBatboxLV");
        EntityHelper.Core.createTileEntity(TileEntityBatboxMV.class, "IndustryBatboxMV");
        EntityHelper.Core.createTileEntity(TileEntityBatboxHV.class, "IndustryBatboxHV");
        EntityHelper.Core.createTileEntity(TileEntityBatboxEHV.class, "IndustryBatboxEHV");
        EntityHelper.Core.createTileEntity(TileEntityTransformerMVtoLV.class, "IndustryTransformerMVtoLV");
        EntityHelper.Core.createTileEntity(TileEntityTransformerHVtoMV.class, "IndustryTransformerHVtoMV");
        EntityHelper.Core.createTileEntity(TileEntityTransformerEHVtoHV.class, "IndustryTransformerEHVtoHV");
        EntityHelper.Core.createTileEntity(TileEntityMachineFurnace.class, "IndustryMachineFurnace");
        EntityHelper.Core.createTileEntity(TileEntityMachineMacerator.class, "IndustryMachineMacerator");
        EntityHelper.Core.createTileEntity(TileEntityMachineCompressor.class, "IndustryMachineCompressor");
        EntityHelper.Core.createTileEntity(TileEntityMachineWiremill.class, "IndustryMachineWiremill");
        EntityHelper.Core.createTileEntity(TileEntityMachineExtractor.class, "IndustryMachineExtractor");
        EntityHelper.Core.createTileEntity(TileEntityMachineRecycler.class, "IndustryMachineRecycler");
        EntityHelper.Core.createTileEntity(TileEntityMachineCannery.class, "IndustryMachineCannery");
        EntityHelper.Core.createTileEntity(TileEntityMachineTrommel.class, "IndustryMachineTrommel");
        EntityHelper.Core.createTileEntity(TileEntityAdvancedFurnace.class, "IndustryAdvancedFurnace");
        EntityHelper.Core.createTileEntity(TileEntityAdvancedMacerator.class, "IndustryAdvancedMacerator");
        EntityHelper.Core.createTileEntity(TileEntityAdvancedCompressor.class, "IndustryAdvancedCompressor");
        EntityHelper.Core.createTileEntity(TileEntityAdvancedWiremill.class, "IndustryAdvancedWiremill");
        EntityHelper.Core.createTileEntity(TIleEntityAdvancedExtractor.class, "IndustryAdvancedExtractor");
        EntityHelper.Core.createTileEntity(TileEntityReactorNew.class, "IndustryReactor");
        EntityHelper.Core.createTileEntity(TileEntityReactorIO.class, "IndustryReactorIO");
        EntityHelper.Core.createTileEntity(TileEntityEnergyFabricator.class, "IndustryFabricator");
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
                .build(new BlockCableTin("cable.tin", blockID("blockCableTin"), Material.metal));

        blockCableCopper = cableBuilder
                .setTextures("block_copper_top.png")
                .build(new BlockCableCopper("cable.copper", blockID("blockCableCopper"), Material.metal));

        blockCableGold = cableBuilder
                .setTextures(17, 4)
                .build(new BlockCableGold("cable.gold", blockID("blockCableGold"), Material.metal));

        blockCableSteel = cableBuilder
                .setTextures(19, 4)
                .build(new BlockCableSteel("cable.steel", blockID("blockCableSteel"), Material.metal));

        blockInsulatedCableTin = insulatedCableBuilder
                .setTextures("insulated_cable_tin.png")
                .build(new BlockInsulatedCableTin("cable.tin", blockID("blockInsulatedCableTin"), Material.cloth));

        blockInsulatedCableCopper = insulatedCableBuilder
                .setTextures("insulated_cable_copper.png")
                .build(new BlockInsulatedCableCopper("cable.copper", blockID("blockInsulatedCableCopper"), Material.cloth));

        blockInsulatedCableGold = insulatedCableBuilder
                .setTextures("insulated_cable_gold.png")
                .build(new BlockInsulatedCableGold("cable.gold", blockID("blockInsulatedCableGold"), Material.cloth));

        blockInsulatedCableSteel = insulatedCableBuilder
                .setTextures("insulated_cable_steel.png")
                .build(new BlockInsulatedCableSteel("cable.steel", blockID("blockInsulatedCableSteel"), Material.cloth));

        machineCasingBasic = machineBuilderBlank
                .setTextures("machine_casing_basic.png")
                .build(new Block("machine.casing", blockID("machineCasingBasic"), Material.metal));

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
                .setBottomTexture("batboxLV_bottom.png")
                .setNorthTexture("batboxLV_front.png")
                .setSouthTexture("batboxLV_front.png")
                .setEastTexture("batboxLV_sides.png")
                .setWestTexture("batboxLV_sides.png")
                .build(new BlockArrayLV("array.lv", blockID("solarArrayLV"), Material.wood))
                .withTags(BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_PICKAXE);

        solarArrayMV = machineBuilderBlank
                .setTopTexture("arrayMV.png")
                .setBottomTexture("batboxMV_bottom.png")
                .setNorthTexture("batboxMV_front.png")
                .setSouthTexture("batboxMV_front.png")
                .setEastTexture("batboxMV_sides.png")
                .setWestTexture("batboxMV_sides.png")
                .build(new BlockArrayMV("array.mv", blockID("solarArrayMV"), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        solarArrayHV = machineBuilderBlank
                .setTopTexture("generator_solar.png")
                .setBottomTexture("batboxHV_bottom.png")
                .setNorthTexture("batboxHV_front.png")
                .setSouthTexture("batboxHV_front.png")
                .setEastTexture("batboxHV_sides.png")
                .setWestTexture("batboxHV_sides.png")
                .build(new BlockArrayHV("array.hv", blockID("solarArrayHV"), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        solarArrayEHV = machineBuilderBlank
                .setTopTexture("arrayEHV.png")
                .setBottomTexture("batboxEHV_bottom.png")
                .setNorthTexture("batboxEHV_front.png")
                .setSouthTexture("batboxEHV_front.png")
                .setEastTexture("batboxEHV_sides.png")
                .setWestTexture("batboxEHV_sides.png")
                .build(new BlockArrayEHV("array.ehv", blockID("solarArrayEHV"), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        batboxLV = new BlockBuilder(MOD_ID)
                .setBlockSound(BlockSounds.WOOD)
                .setHardness(5.0f)
                .setResistance(0.0f)
                .setTopTexture("batboxLV_top.png")
                .setBottomTexture("batboxLV_bottom.png")
                .setNorthTexture("batboxLV_front.png")
                .setSouthTexture("batboxLV_front.png")
                .setEastTexture("batboxLV_sides.png")
                .setWestTexture("batboxLV_sides.png")
                .build(new BlockBatboxLV("batbox.lv", blockID("batboxLV"), Material.wood))
                .withTags(BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_PICKAXE);

        batboxMV = machineBuilderBlank
                .setTopTexture("batboxMV_top.png")
                .setBottomTexture("batboxMV_bottom.png")
                .setNorthTexture("batboxMV_front.png")
                .setSouthTexture("batboxMV_front.png")
                .setEastTexture("batboxMV_sides.png")
                .setWestTexture("batboxMV_sides.png")
                .build(new BlockBatboxMV("batbox.mv", blockID("batboxMV"), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        batboxHV = machineBuilderBlank
                .setTopTexture("batboxHV_top.png")
                .setBottomTexture("batboxHV_bottom.png")
                .setNorthTexture("batboxHV_front.png")
                .setSouthTexture("batboxHV_front.png")
                .setEastTexture("batboxHV_sides.png")
                .setWestTexture("batboxHV_sides.png")
                .build(new BlockBatboxHV("batbox.hv", blockID("batboxHV"), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        batboxEHV = machineBuilderBlank
                .setTopTexture("batboxEHV_top.png")
                .setBottomTexture("batboxEHV_bottom.png")
                .setNorthTexture("batboxEHV_front.png")
                .setSouthTexture("batboxEHV_front.png")
                .setEastTexture("batboxEHV_sides.png")
                .setWestTexture("batboxEHV_sides.png")
                .build(new BlockBatboxEHV("batbox.ehv", blockID("batboxEHV"), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        transformerMVtoLV = machineBuilderBlank
                .setTopTexture("batboxMV_top.png")
                .setBottomTexture("batboxLV_bottom.png")
                .setNorthTexture("transformerMV_LV_front.png")
                .setSouthTexture("transformerMV_LV_front.png")
                .setEastTexture("transformerMV_LV_sides.png")
                .setWestTexture("transformerMV_LV_sides.png")
                .build(new BlockTransformerMVtoLV("transformer.mvtolv", blockID("transformerMVtoLV"), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        transformerHVtoMV = machineBuilderBlank
                .setTopTexture("batboxHV_top.png")
                .setBottomTexture("batboxMV_bottom.png")
                .setNorthTexture("transformerHV_MV_front.png")
                .setSouthTexture("transformerHV_MV_front.png")
                .setEastTexture("transformerHV_MV_sides.png")
                .setWestTexture("transformerHV_MV_sides.png")
                .build(new BlockTransformerHVtoMV("transformer.hvtomv", blockID("transformerHVtoMV"), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        transformerEHVtoHV = machineBuilderBlank
                .setTopTexture("batboxEHV_top.png")
                .setBottomTexture("batboxHV_bottom.png")
                .setNorthTexture("transformerEHV_HV_front.png")
                .setSouthTexture("transformerEHV_HV_front.png")
                .setEastTexture("transformerEHV_HV_sides.png")
                .setWestTexture("transformerEHV_HV_sides.png")
                .build(new BlockTransformerEHVtoHV("transformer.ehvtohv", blockID("transformerEHVtoHV"), Material.metal))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        machineFurnace = machineBuilder
                .setNorthTexture("machine_furnace.png")
                .build(new BlockMachineFurnace("machine.furnace", blockID("machineFurnace"), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        machineMacerator = machineBuilder
                .setNorthTexture("machine_macerator.png")
                .build(new BlockMachineMacerator("machine.macerator", blockID("machineMacerator"), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        machineCompressor = machineBuilder
                .setNorthTexture("machine_compressor.png")
                .build(new BlockMachineCompressor("machine.compressor", blockID("machineCompressor"), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        machineWiremill = machineBuilder
                .setNorthTexture("machine_wiremill.png")
                .build(new BlockMachineWiremill("machine.wiremill", blockID("machineWiremill"), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        machineExtractor = machineBuilder
                .setNorthTexture("machine_extractor.png")
                .build(new BlockMachineExtractor("machine.extractor", blockID("machineExtractor"), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        machineRecycler = machineBuilder
                .setNorthTexture("machine_recycler.png")
                .build(new BlockMachineRecycler("machine.recycler", blockID("machineRecycler"), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        machineCannery = machineBuilder
                .setNorthTexture("machine_cannery.png")
                .build(new BlockMachineCannery("machine.cannery", blockID("machineCannery"), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        machineTrommel = machineBuilder
                .setNorthTexture("machine_trommel.png")
                .build(new BlockMachineTrommel("machine.trommel", blockID("machineTrommel"), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        advancedMachineFurnace = advancedMachineBuilder
                .setNorthTexture("advanced_furnace.png")
                .build(new BlockAdvancedFurnace("advanced.furnace", blockID("advancedMachineFurnace"), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        advancedMachineMacerator = advancedMachineBuilder
                .setNorthTexture("advanced_macerator.png")
                .build(new BlockAdvancedMacerator("advanced.macerator", blockID("advancedMachineMacerator"), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        advancedMachineCompressor = advancedMachineBuilder
                .setNorthTexture("advanced_compressor.png")
                .build(new BlockAdvancedCompressor("advanced.compressor", blockID("advancedMachineCompressor"), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        advancedMachineWiremill = advancedMachineBuilder
                .setNorthTexture("advanced_wiremill.png")
                .build(new BlockAdvancedWiremill("advanced.wiremill", blockID("advancedMachineWiremill"), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        advancedMachineExtractor = advancedMachineBuilder
                .setNorthTexture("advanced_extractor.png")
                .build(new BlockAdvancedExtractor("advanced.extractor", blockID("advancedMachineExtractor"), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        hardenedCoal = new BlockBuilder(MOD_ID)
                .setTextures("hardened_coal.png")
                .setBlockSound(BlockSounds.STONE)
                .setHardness(10.0f)
                .setResistance(2000.0f)
                .build(new Block("block.coal.hardened", blockID("hardenedCoal"), Material.stone))
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        leavesRubberWood = new BlockBuilder(MOD_ID)
                .setTextures(2, 20)
                .setBlockSound(BlockSounds.GRASS)
                .setBlockColor(new BlockColorLeaves("pine"))
                .setHardness(0.2f)
                .setLightOpacity(1)
                .build(new BlockLeavesRubberwood("leaves.rubber", blockID("leavesRubberWood")))
                .withTags(BlockTags.SHEARS_DO_SILK_TOUCH, BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_HOE, BlockTags.MINEABLE_BY_SWORD, BlockTags.MINEABLE_BY_SHEARS)
                .setTicking(true)
                .withDisabledNeighborNotifyOnMetadataChange()
                .withDisabledStats();

        logRubberWood = new BlockBuilder(MOD_ID)
                .setTopBottomTexture("log_rubber_top.png")
                .setSideTextures("log_rubber.png")
                .setBlockSound(BlockSounds.WOOD)
                .setHardness(2.0f)
                .build(new BlockLog("log.rubber", blockID("logRubberWood")))
                .withTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT)
                .withDisabledNeighborNotifyOnMetadataChange();

        logRubberWoodResin = new BlockBuilder(MOD_ID)
                .setTopBottomTexture("log_rubber_top.png")
                .setSideTextures("log_rubber.png")
                .setBlockSound(BlockSounds.WOOD)
                .setHardness(2.0f)
                .setBlockModel(new BlockModelRenderBlocks(27))
                .build(new BlockLogResin("log.rubber.resin", blockID("logRubberWoodResin")))
                .withTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT, BlockTags.NOT_IN_CREATIVE_MENU)
                .withDisabledNeighborNotifyOnMetadataChange();

        logRubberWoodResinFull = new BlockBuilder(MOD_ID)
                .setTopBottomTexture("log_rubber_top.png")
                .setSideTextures("log_rubber.png")
                .setBlockSound(BlockSounds.WOOD)
                .setHardness(2.0f)
                .setBlockModel(new BlockModelRenderBlocks(27))
                .build(new BlockLogResinFull("log.rubber.resin", blockID("logRubberWoodResinFull")))
                .withTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT)
                .withDisabledNeighborNotifyOnMetadataChange();

        saplingRubberWood = new BlockBuilder(MOD_ID)
                .setTextures("sapling_rubber.png")
                .setBlockSound(BlockSounds.GRASS)
                .setBlockModel(new BlockModelRenderBlocks(1))
                .build(new BlockSaplingRubberwood("sapling.rubber", blockID("saplingRubberWood")))
                .withTags(BlockTags.BROKEN_BY_FLUIDS)
                .setTicking(true)
                .withDisabledNeighborNotifyOnMetadataChange();

        nuclearReactor = new BlockBuilder(MOD_ID)
                .setTopBottomTexture("reactorIO_bottom.png")
                .setSideTextures("reactor.png")
                .setBlockSound(BlockSounds.METAL)
                .setHardness(10.0f)
                .setResistance(0.0f)
                .build(new BlockReactor("reactor", blockID("nuclearReactor"), false));

        nuclearChamber = new BlockBuilder(MOD_ID)
                .setTopBottomTexture("reactor_casing_top.png")
                .setSideTextures("reactor_casing_sides.png")
                .setBlockSound(BlockSounds.METAL)
                .setHardness(10.0f)
                .setResistance(0.0f)
                .build(new BlockReactorChamber("reactor.chamber", blockID("nuclearChamber"), Material.metal));

        nuclearIO = new BlockBuilder(MOD_ID)
                .setTopTexture("reactorIO_top.png")
                .setBottomTexture("reactorIO_bottom.png")
                .setNorthTexture("reactorIO_front.png")
                .setSouthTexture("reactorIO_front.png")
                .setEastTexture("reactorIO_sides.png")
                .setWestTexture("reactorIO_sides.png")
                .setBlockSound(BlockSounds.METAL)
                .setHardness(10.0F)
                .setResistance(0.0F)
                .build(new BlockReactorIO("reactor.io", blockID("nuclearIO"), Material.metal));

        energyFabricator = new BlockBuilder(MOD_ID)
                .setTopBottomTexture("machine_casing_advanced.png")
                .setNorthTexture("endgame_fabricator.png")
                .setEastTexture("machine_casing_advanced.png")
                .setSouthTexture("machine_casing_advanced.png")
                .setWestTexture("machine_casing_advanced.png")
                .setBlockSound(BlockSounds.METAL)
                .setHardness(10.0f)
                .setResistance(0.0f)
                .build(new BlockEnergyFabricator("fabricator", blockID("energyFabricator"), Material.metal))
                .withTags(IndustryTags.REQUIRES_WRENCH);

        alarm = new BlockBuilder(MOD_ID)
                .setTopBottomTexture("block_bronze_sides.png")
                .setSideTextures("alarm_front.png")
                .setBlockSound(BlockSounds.METAL)
                .setHardness(5.0F)
                .build(new BlockAlarm("alarm", blockID("alarm")))
                .setTicking(true)
                .withTags(BlockTags.MINEABLE_BY_PICKAXE);

        addToGuiMap();
        initializeTiles();
        pickaxeLevels();
    }
}
