package turniplabs.industry.blocks

import net.minecraft.client.render.block.color.BlockColorLeaves
import net.minecraft.client.render.block.model.BlockModelRenderBlocks
import net.minecraft.client.sound.block.BlockSounds
import net.minecraft.core.block.Block
import net.minecraft.core.block.material.Material
import net.minecraft.core.block.tag.BlockTags
import net.minecraft.core.item.tool.ItemToolPickaxe
import sunsetsatellite.energyapi.EnergyAPI
import turniplabs.halplibe.helper.BlockBuilder
import turniplabs.halplibe.helper.EntityHelper
import turniplabs.industry.Industry2
import turniplabs.industry.blocks.cables.BlockCableCopper
import turniplabs.industry.blocks.cables.BlockCableGold
import turniplabs.industry.blocks.cables.BlockCableTin
import turniplabs.industry.blocks.entities.*
import turniplabs.industry.blocks.entities.batbox.TileEntityBatboxHV
import turniplabs.industry.blocks.entities.batbox.TileEntityBatboxLV
import turniplabs.industry.blocks.entities.batbox.TileEntityBatboxMV
import turniplabs.industry.blocks.entities.batbox.TileEntityBatboxSHV
import turniplabs.industry.blocks.entities.lv.*
import turniplabs.industry.blocks.entities.mv.TileEntityCompressorSingularity
import turniplabs.industry.blocks.entities.mv.TileEntityMaceratorRotary
import turniplabs.industry.blocks.entities.solar.TileEntitySolarHV
import turniplabs.industry.blocks.entities.solar.TileEntitySolarLV
import turniplabs.industry.blocks.entities.solar.TileEntitySolarMV
import turniplabs.industry.blocks.entities.solar.TileEntitySolarSHV
import turniplabs.industry.blocks.machines.BlockGenerator
import turniplabs.industry.blocks.machines.BlockGeothermalGenerator
import turniplabs.industry.blocks.machines.BlockWatermill
import turniplabs.industry.blocks.machines.BlockWindmill
import turniplabs.industry.blocks.machines.batbox.BlockBatboxHV
import turniplabs.industry.blocks.machines.batbox.BlockBatboxLV
import turniplabs.industry.blocks.machines.batbox.BlockBatboxMV
import turniplabs.industry.blocks.machines.batbox.BlockBatboxSHV
import turniplabs.industry.blocks.machines.lv.*
import turniplabs.industry.blocks.machines.mv.BlockCompressorSingularity
import turniplabs.industry.blocks.machines.mv.BlockMaceratorRotary
import turniplabs.industry.blocks.machines.solar.*
import turniplabs.industry.gui.*
import turniplabs.industry.gui.batbox.*
import turniplabs.industry.gui.lv.*
import turniplabs.industry.gui.mv.ContainerCompressorSingularity
import turniplabs.industry.gui.mv.ContainerMaceratorRotary
import turniplabs.industry.gui.mv.GuiCompressorSingularity
import turniplabs.industry.gui.mv.GuiMaceratorRotary
import turniplabs.industry.gui.solar.*

object IndustryBlocks {

    private var blockID: Int = 999
    private fun nextBlockID(): Int {
        return blockID++
    }

    // Ores
    private val oreBuilder = BlockBuilder(Industry2.MOD_ID)
        .setBlockSound(BlockSounds.STONE)
        .setHardness(3.5f)
        .setResistance(5.0f)
        .setTags(BlockTags.MINEABLE_BY_PICKAXE)

    val oreCopperStone: Block = oreBuilder
        .setTextures("ore_copper_stone.png")
        .build(BlockCopperOre("ore.copper.stone", nextBlockID(), Material.stone))

    val oreCopperBasalt: Block = oreBuilder
        .setTextures("ore_copper_basalt.png")
        .build(BlockCopperOre("ore.copper.basalt", nextBlockID(), Material.stone))

    val oreCopperLimestone: Block = oreBuilder
        .setTextures("ore_copper_limestone.png")
        .build(BlockCopperOre("ore.copper.limestone", nextBlockID(), Material.stone))

    val oreCopperGranite: Block = oreBuilder
        .setTextures("ore_copper_granite.png")
        .build(BlockCopperOre("ore.copper.granite", nextBlockID(), Material.stone))

    val oreTinStone: Block = oreBuilder
        .setTextures("ore_tin_stone.png")
        .build(BlockTinOre("ore.tin.stone", nextBlockID(), Material.stone))

    val oreTinBasalt: Block = oreBuilder
        .setTextures("ore_tin_basalt.png")
        .build(BlockTinOre("ore.tin.basalt", nextBlockID(), Material.stone))

    val oreTinLimestone: Block = oreBuilder
        .setTextures("ore_tin_limestone.png")
        .build(BlockTinOre("ore.tin.limestone", nextBlockID(), Material.stone))

    val oreTinGranite: Block = oreBuilder
        .setTextures("ore_tin_granite.png")
        .build(BlockTinOre("ore.tin.granite", nextBlockID(), Material.stone))

    val oreUraniumStone: Block = oreBuilder
        .setTextures("ore_uranium_stone.png")
        .build(BlockUraniumOre("ore.uranium.stone", nextBlockID(), Material.stone))

    val oreUraniumBasalt: Block = oreBuilder
        .setTextures("ore_uranium_basalt.png")
        .build(BlockUraniumOre("ore.uranium.basalt", nextBlockID(), Material.stone))

    val oreUraniumLimestone: Block = oreBuilder
        .setTextures("ore_uranium_limestone.png")
        .build(BlockUraniumOre("ore.uranium.limestone", nextBlockID(), Material.stone))

    val oreUraniumGranite: Block = oreBuilder
        .setTextures("ore_uranium_granite.png")
        .build(BlockUraniumOre("ore.uranium.granite", nextBlockID(), Material.stone))

    // Blocks
    private val blockBuilder = BlockBuilder(Industry2.MOD_ID)
        .setBlockSound(BlockSounds.METAL)
        .setHardness(5.0f)
        .setResistance(10.0f)
        .setTags(BlockTags.MINEABLE_BY_PICKAXE)

    val copperBlock: Block = blockBuilder
        .setTopTexture("block_copper_top.png")
        .setSideTextures("block_copper_sides.png")
        .setBottomTexture("block_copper_bottom.png")
        .build(Block("block.copper", nextBlockID(), Material.metal))

    val tinBlock: Block = blockBuilder
        .setTopTexture("block_tin_top.png")
        .setSideTextures("block_tin_sides.png")
        .setBottomTexture("block_tin_bottom.png")
        .build(Block("block.tin", nextBlockID(), Material.metal))

    val bronzeBlock: Block = blockBuilder
        .setTopTexture("block_bronze_top.png")
        .setSideTextures("block_bronze_sides.png")
        .setBottomTexture("block_bronze_bottom.png")
        .build(Block("block.bronze", nextBlockID(), Material.metal))

    val uraniumBlock: Block = blockBuilder
        .setTopTexture("block_uranium_top.png")
        .setSideTextures("block_uranium_sides.png")
        .setBottomTexture("block_uranium_bottom.png")
        .build(Block("block.uranium", nextBlockID(), Material.metal))

    // Cables
    private val cableBuilder = BlockBuilder(Industry2.MOD_ID)
        .setBlockSound(BlockSounds.METAL)
        .setHardness(1.0f)
        .setTags(BlockTags.NOT_IN_CREATIVE_MENU)
        .setBlockModel(BlockModelRenderBlocks(32))

    val copperCable: Block = cableBuilder
        .setTextures("block_copper_top.png")
        .build(BlockCableCopper("cable.copper", nextBlockID(), Material.metal, 32, 32, 2))

    val tinCable: Block = cableBuilder
        .setTextures("block_tin_top.png")
        .build(BlockCableTin("cable.tin", nextBlockID(), Material.metal, 16, 16, 1))

    val goldCable: Block = cableBuilder
        .setTextures(17, 4)
        .build(BlockCableGold("cable.gold", nextBlockID(), Material.metal, 512, 512, 6))

    val steelCable: Block = cableBuilder
        .setTextures(19, 4)
        .build(BlockCableGold("cable.steel", nextBlockID(), Material.metal, 1024, 1024, 8))

    // Insulated Cables
    private val insulatedCableBuilder = BlockBuilder(Industry2.MOD_ID)
        .setBlockSound(BlockSounds.CLOTH)
        .setHardness(1.0f)
        .setTags(BlockTags.NOT_IN_CREATIVE_MENU)
        .setBlockModel(BlockModelRenderBlocks(32))

    val insulatedCopperCable: Block = insulatedCableBuilder
        .setTextures("insulated_cable_copper.png")
        .build(BlockCableCopper("cable.copper", nextBlockID(), Material.cloth, 32, 32, 0))

    val insulatedTinCable: Block = insulatedCableBuilder
        .setTextures("insulated_cable_tin.png")
        .build(BlockCableTin("cable.tin", nextBlockID(), Material.metal, 16, 16, 0))

    val insulatedGoldCable: Block = insulatedCableBuilder
        .setTextures("insulated_cable_gold.png")
        .build(BlockCableGold("cable.gold", nextBlockID(), Material.cloth, 512, 512, 0))

    val insulatedSteelCable: Block = insulatedCableBuilder
        .setTextures("insulated_cable_steel.png")
        .build(BlockCableGold("cable.steel", nextBlockID(), Material.cloth, 1024, 1024, 0))

    // Machines
    private val machineBuilder = BlockBuilder(Industry2.MOD_ID)
        .setTopBottomTexture("machine_casing_basic.png")
        .setEastTexture("machine_casing_basic.png")
        .setSouthTexture("machine_casing_basic.png")
        .setWestTexture("machine_casing_basic.png")
        .setBlockSound(BlockSounds.METAL)
        .setHardness(5.0f)
        .setResistance(0.0f)

    private val machineBuilderBlank = BlockBuilder(Industry2.MOD_ID)
        .setBlockSound(BlockSounds.METAL)
        .setHardness(5.0f)
        .setResistance(10.0f)

    val machineCasing: Block = blockBuilder
        .setTextures("machine_casing_basic.png")
        .setBlockSound(BlockSounds.METAL)
        .setHardness(5.0f)
        .setResistance(10.0f)
        .setTags(BlockTags.MINEABLE_BY_PICKAXE)
        .build(Block("machine.casing", nextBlockID(), Material.metal))

    val machineCasingAdvanced: Block = blockBuilder
        .setTextures("machine_casing_advanced.png")
        .setBlockSound(BlockSounds.METAL)
        .setHardness(5.0f)
        .setResistance(10.0f)
        .setTags(BlockTags.MINEABLE_BY_PICKAXE)
        .build(Block("machine.casing.advanced", nextBlockID(), Material.metal))

    val machineGenerator: Block = machineBuilder
        .setNorthTexture("machine_generator.png")
        .build(BlockGenerator("machine.generator", nextBlockID(), Material.metal))

    val machineWatermill: Block = machineBuilderBlank
        .setTopBottomTexture("machine_casing_basic.png")
        .setSideTextures("machine_generator_watermill.png")
        .build(BlockWatermill("machine.generator.watermill", nextBlockID(), Material.metal))

    val machineWindmill: Block = machineBuilder
        .setNorthTexture("machine_generator_windmill.png")
        .build(BlockWindmill("machine.generator.windmill", nextBlockID(), Material.metal))

    val machineGeothermalGenerator: Block = machineBuilder
        .setNorthTexture("machine_generator_geothermal.png")
        .build(BlockGeothermalGenerator("machine.generator.geothermal", nextBlockID(), Material.metal))

    val machineSolarGenerator: Block = machineBuilderBlank
        .setTopTexture("machine_generator_solar.png")
        .setSideTextures("machine_casing_basic.png")
        .setBottomTexture("machine_casing_basic.png")
        .build(BlockSolarGenerator("machine.generator.solar", nextBlockID(), Material.metal))

    val machineSolarArrayLV: Block = blockBuilder
        .setTopTexture("lv_solar_array.png")
        .setSideTextures("lv_batbox.png")
        .setBottomTexture("lv_batbox.png")
        .setBlockSound(BlockSounds.WOOD)
        .setHardness(5.0f)
        .setResistance(10.0f)
        .build(BlockSolarArrayLV("machine.array.lv", nextBlockID(), Material.wood))

    val machineSolarArrayMV: Block = machineBuilderBlank
        .setTopTexture("mv_solar_array.png")
        .setSideTextures("mv_batbox.png")
        .setBottomTexture("mv_batbox.png")
        .build(BlockSolarArrayMV("machine.array.mv", nextBlockID(), Material.metal))

    val machineSolarArrayHV: Block = machineBuilderBlank
        .setTopTexture("machine_generator_solar.png")
        .setSideTextures("hv_batbox.png")
        .setBottomTexture("hv_batbox.png")
        .build(BlockSolarArrayHV("machine.array.hv", nextBlockID(), Material.metal))

    val machineSolarArraySHV: Block = machineBuilderBlank
        .setTopTexture("shv_solar_array.png")
        .setSideTextures("shv_batbox.png")
        .setBottomTexture("shv_batbox.png")
        .build(BlockSolarArraySHV("machine.array.shv", nextBlockID(), Material.metal))

    val batboxLV: Block = blockBuilder
        .setTopTexture("lv_batbox_input.png")
        .setBottomTexture("lv_batbox.png")
        .setSideTextures("lv_batbox.png")
        .setBlockSound(BlockSounds.WOOD)
        .setHardness(5.0f)
        .setResistance(10.0f)
        .build(BlockBatboxLV("machine.batbox.lv", nextBlockID(), Material.wood))

    val batboxMV: Block = machineBuilderBlank
        .setTopTexture("mv_batbox_input.png")
        .setBottomTexture("mv_batbox.png")
        .setSideTextures("mv_batbox.png")
        .build(BlockBatboxMV("machine.batbox.mv", nextBlockID(), Material.metal))

    val batboxHV: Block = machineBuilderBlank
        .setTopTexture("hv_batbox_input.png")
        .setBottomTexture("hv_batbox.png")
        .setSideTextures("hv_batbox.png")
        .build(BlockBatboxHV("machine.batbox.hv", nextBlockID(), Material.metal))

    val batboxSHV: Block = machineBuilderBlank
        .setTopTexture("shv_batbox_input.png")
        .setBottomTexture("shv_batbox.png")
        .setSideTextures("shv_batbox.png")
        .build(BlockBatboxSHV("machine.batbox.shv", nextBlockID(), Material.metal))

    val machineElectricFurnace: Block = machineBuilder
        .setNorthTexture("machine_furnace.png")
        .build(BlockElectricFurnace("machine.furnace", nextBlockID(), Material.metal))

    val machineMacerator: Block = machineBuilder
        .setNorthTexture("machine_macerator.png")
        .build(BlockMacerator("machine.macerator", nextBlockID(), Material.metal))

    val machineCompressor: Block = machineBuilder
        .setNorthTexture("machine_compressor.png")
        .build(BlockCompressor("machine.compressor", nextBlockID(), Material.metal))

    val machineCutter: Block = machineBuilder
        .setNorthTexture("machine_cutter.png")
        .build(BlockCutter("machine.cutter", nextBlockID(), Material.metal))

    val machineExtractor: Block = machineBuilder
        .setNorthTexture("machine_extractor.png")
        .build(BlockExtractor("machine.extractor", nextBlockID(), Material.metal))

    val machineRecycler: Block = machineBuilderBlank
        .setTopTexture("machine_recycler.png")
        .setBottomTexture("machine_casing_basic.png")
        .setEastTexture("machine_casing_basic.png")
        .setSouthTexture("machine_casing_basic.png")
        .setWestTexture("machine_casing_basic.png")
        .setNorthTexture("machine_compressor.png")
        .build(BlockRecycler("machine.recycler", nextBlockID(), Material.metal))

    private val advancedMachineBuilder = BlockBuilder(Industry2.MOD_ID)
        .setTopBottomTexture("machine_casing_advanced.png")
        .setEastTexture("machine_casing_advanced.png")
        .setSouthTexture("machine_casing_advanced.png")
        .setWestTexture("machine_casing_advanced.png")
        .setBlockSound(BlockSounds.METAL)
        .setHardness(5.0f)
        .setResistance(0.0f)

    val advancedMachineMacerator: Block = advancedMachineBuilder
        .setNorthTexture("advanced_machine_macerator.png")
        .build(BlockMaceratorRotary("advanced.macerator", nextBlockID(), Material.metal))

    val advancedMachineCompressor: Block = advancedMachineBuilder
        .setNorthTexture("advanced_machine_compressor.png")
        .build(BlockCompressorSingularity("advanced.compressor", nextBlockID(), Material.metal))

    // Miscellaneous
    val hardenedCoal: Block = BlockBuilder(Industry2.MOD_ID)
        .setTextures("hardened_coal.png")
        .setBlockSound(BlockSounds.STONE)
        .setHardness(10.0f)
        .setResistance(60.0f)
        .setTags(BlockTags.MINEABLE_BY_PICKAXE)
        .build(Block("block.coal.hardened", nextBlockID(), Material.stone))

    val rubberLeaves: Block = BlockBuilder(Industry2.MOD_ID)
        .setTextures(2, 20)
        .setBlockSound(BlockSounds.GRASS)
        .setHardness(0.2f)
        .setLightOpacity(1)
        .setTags(BlockTags.SHEARS_DO_SILK_TOUCH, BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_HOE, BlockTags.MINEABLE_BY_SWORD, BlockTags.MINEABLE_BY_SHEARS)
        .setBlockColor(BlockColorLeaves("pine"))
        .build(BlockLeavesRubber("leaves.rubber", nextBlockID(), Material.leaves, false))

    val rubberLog: Block = BlockBuilder(Industry2.MOD_ID)
        .setSideTextures("log_rubber.png")
        .setTopBottomTexture("log_rubber_top.png")
        .setBlockSound(BlockSounds.WOOD)
        .setHardness(2.0f)
        .setTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE)
        .build(BlockLogRubber("log.rubber", nextBlockID()))

    val rubberSapling: Block = BlockBuilder(Industry2.MOD_ID)
        .setTextures("sapling_rubber.png")
        .setBlockSound(BlockSounds.GRASS)
        .setTags(BlockTags.BROKEN_BY_FLUIDS)
        .setBlockModel(BlockModelRenderBlocks(1))
        .build(BlockSaplingRubber("sapling.rubber", nextBlockID()))

    private fun pickaxeLevels() {
        ItemToolPickaxe.miningLevels[oreCopperStone] = 1
        ItemToolPickaxe.miningLevels[oreCopperBasalt] = 1
        ItemToolPickaxe.miningLevels[oreCopperLimestone] = 1
        ItemToolPickaxe.miningLevels[oreCopperGranite] = 1
        ItemToolPickaxe.miningLevels[oreTinStone] = 1
        ItemToolPickaxe.miningLevels[oreTinBasalt] = 1
        ItemToolPickaxe.miningLevels[oreTinLimestone] = 1
        ItemToolPickaxe.miningLevels[oreTinGranite] = 1
        ItemToolPickaxe.miningLevels[oreUraniumStone] = 2
        ItemToolPickaxe.miningLevels[oreUraniumBasalt] = 2
        ItemToolPickaxe.miningLevels[oreUraniumLimestone] = 2
        ItemToolPickaxe.miningLevels[oreUraniumGranite] = 2
        ItemToolPickaxe.miningLevels[copperBlock] = 2
        ItemToolPickaxe.miningLevels[tinBlock] = 2
        ItemToolPickaxe.miningLevels[bronzeBlock] = 2
        ItemToolPickaxe.miningLevels[uraniumBlock] = 2
        ItemToolPickaxe.miningLevels[hardenedCoal] = 3
    }

    private fun addToGUI() {
        EnergyAPI.addToNameGuiMap("IndustryGenerator", GuiGenerator::class.java, TileEntityGenerator::class.java, ContainerGenerator::class.java)
        EnergyAPI.addToNameGuiMap("Watermill", GuiWatermill::class.java, TileEntityWatermill::class.java, ContainerWatermill::class.java)
        EnergyAPI.addToNameGuiMap("Windmill", GuiWindmill::class.java, TileEntityWindmill::class.java, ContainerWindmill::class.java)
        EnergyAPI.addToNameGuiMap("Geothermal", GuiGeothermal::class.java, TileEntityGeothermal::class.java, ContainerGeothermal::class.java)
        EnergyAPI.addToNameGuiMap("ElectricFurnace", GuiElectricFurnace::class.java, TileEntityElectricFurnace::class.java, ContainerElectricFurnace::class.java)
        EnergyAPI.addToNameGuiMap("SolarGenerator", GuiSolarGenerator::class.java, TileEntitySolarGenerator::class.java, ContainerSolarBase::class.java)
        EnergyAPI.addToNameGuiMap("LVSolarArray", GuiSolarArrayLV::class.java, TileEntitySolarLV::class.java, ContainerSolarBase::class.java)
        EnergyAPI.addToNameGuiMap("MVSolarArray", GuiSolarArrayMV::class.java, TileEntitySolarMV::class.java, ContainerSolarBase::class.java)
        EnergyAPI.addToNameGuiMap("HVSolarArray", GuiSolarArrayHV::class.java, TileEntitySolarHV::class.java, ContainerSolarBase::class.java)
        EnergyAPI.addToNameGuiMap("SHVSolarArray", GuiSolarArraySHV::class.java, TileEntitySolarSHV::class.java, ContainerSolarBase::class.java)
        EnergyAPI.addToNameGuiMap("BatboxLV", GuiBatboxLV::class.java, TileEntityBatboxLV::class.java, ContainerBatboxBase::class.java)
        EnergyAPI.addToNameGuiMap("BatboxMV", GuiBatboxMV::class.java, TileEntityBatboxMV::class.java, ContainerBatboxBase::class.java)
        EnergyAPI.addToNameGuiMap("BatboxHV", GuiBatboxHV::class.java, TileEntityBatboxHV::class.java, ContainerBatboxBase::class.java)
        EnergyAPI.addToNameGuiMap("BatboxSHV", GuiBatboxSHV::class.java, TileEntityBatboxSHV::class.java, ContainerBatboxBase::class.java)
        EnergyAPI.addToNameGuiMap("Macerator", GuiMacerator::class.java, TileEntityMacerator::class.java, ContainerMacerator::class.java)
        EnergyAPI.addToNameGuiMap("Compressor", GuiCompressor::class.java, TileEntityCompressor::class.java, ContainerCompressor::class.java)
        EnergyAPI.addToNameGuiMap("Cutter", GuiCutter::class.java, TileEntityCutter::class.java, ContainerCutter::class.java)
        EnergyAPI.addToNameGuiMap("Extractor", GuiExtractor::class.java, TileEntityExtractor::class.java, ContainerExtractor::class.java)
        EnergyAPI.addToNameGuiMap("Recycler", GuiRecycler::class.java, TileEntityRecycler::class.java, ContainerRecycler::class.java)
        EnergyAPI.addToNameGuiMap("RotaryMacerator", GuiMaceratorRotary::class.java, TileEntityMaceratorRotary::class.java, ContainerMaceratorRotary::class.java)
        EnergyAPI.addToNameGuiMap("SingularityCompressor", GuiCompressorSingularity::class.java, TileEntityCompressorSingularity::class.java, ContainerCompressorSingularity::class.java)
    }

    private fun createTileEntities() {
        EntityHelper.createTileEntity(TileEntityCable::class.java, "Cable")
        EntityHelper.createTileEntity(TileEntityGenerator::class.java, "IndustryGenerator")
        EntityHelper.createTileEntity(TileEntityWatermill::class.java, "Watermill")
        EntityHelper.createTileEntity(TileEntityWindmill::class.java, "Windmill")
        EntityHelper.createTileEntity(TileEntityGeothermal::class.java, "Geothermal")
        EntityHelper.createTileEntity(TileEntityElectricFurnace::class.java, "ElectricFurnace")
        EntityHelper.createTileEntity(TileEntitySolarGenerator::class.java, "SolarGenerator")
        EntityHelper.createTileEntity(TileEntitySolarLV::class.java, "LVSolarArray")
        EntityHelper.createTileEntity(TileEntitySolarMV::class.java, "MVSolarArray")
        EntityHelper.createTileEntity(TileEntitySolarHV::class.java, "HVSolarArray")
        EntityHelper.createTileEntity(TileEntitySolarSHV::class.java, "SHVSolarArray")
        EntityHelper.createTileEntity(TileEntityBatboxLV::class.java, "BatboxLV")
        EntityHelper.createTileEntity(TileEntityBatboxMV::class.java, "BatboxMV")
        EntityHelper.createTileEntity(TileEntityBatboxHV::class.java, "BatboxHV")
        EntityHelper.createTileEntity(TileEntityBatboxSHV::class.java, "BatboxSHV")
        EntityHelper.createTileEntity(TileEntityMacerator::class.java, "Macerator")
        EntityHelper.createTileEntity(TileEntityCompressor::class.java, "Compressor")
        EntityHelper.createTileEntity(TileEntityCutter::class.java, "Cutter")
        EntityHelper.createTileEntity(TileEntityExtractor::class.java, "Extractor")
        EntityHelper.createTileEntity(TileEntityRecycler::class.java, "Recycler")
        EntityHelper.createTileEntity(TileEntityMaceratorRotary::class.java, "RotaryMacerator")
        EntityHelper.createTileEntity(TileEntityCompressorSingularity::class.java, "SingularityCompressor")
    }

    fun initializeBlocks() {
        oreCopperStone
        oreCopperBasalt
        oreCopperLimestone
        oreCopperGranite
        oreTinStone
        oreTinBasalt
        oreTinLimestone
        oreTinGranite
        oreUraniumStone
        oreUraniumBasalt
        oreUraniumLimestone
        oreUraniumGranite

        copperBlock
        tinBlock
        bronzeBlock
        uraniumBlock

        copperCable
        tinCable
        goldCable
        steelCable
        insulatedCopperCable
        insulatedTinCable
        insulatedGoldCable
        insulatedSteelCable

        machineCasing
        machineCasingAdvanced
        machineGenerator
        machineWatermill
        machineWindmill
        machineGeothermalGenerator
        machineSolarGenerator
        machineSolarArrayLV
        machineSolarArrayMV
        machineSolarArrayHV
        machineSolarArraySHV
        batboxLV
        batboxMV
        batboxHV
        batboxSHV
        machineElectricFurnace
        machineMacerator
        machineCompressor
        machineCutter
        machineExtractor
        machineRecycler
        advancedMachineMacerator
        advancedMachineCompressor

        hardenedCoal
        rubberLeaves
        rubberLog
        rubberSapling

        pickaxeLevels()
        addToGUI()
        createTileEntities()
    }
}