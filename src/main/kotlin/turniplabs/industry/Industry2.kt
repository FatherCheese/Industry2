package turniplabs.industry

import net.fabricmc.api.ModInitializer
import net.minecraft.client.render.block.color.BlockColorDispatcher
import net.minecraft.client.render.block.color.BlockColorLeaves
import net.minecraft.client.render.block.model.BlockModelDispatcher
import net.minecraft.client.render.block.model.BlockModelRenderBlocks
import net.minecraft.client.sound.block.BlockSounds
import net.minecraft.core.block.Block
import net.minecraft.core.block.material.Material
import net.minecraft.core.block.tag.BlockTags
import net.minecraft.core.crafting.CraftingManager
import net.minecraft.core.crafting.recipe.RecipesFurnace
import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemStack
import net.minecraft.core.item.tool.ItemToolPickaxe
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import turniplabs.halplibe.helper.BlockBuilder
import turniplabs.halplibe.helper.EntityHelper
import turniplabs.halplibe.helper.ItemHelper
import turniplabs.industry.blocks.*
import turniplabs.industry.blocks.cables.BlockCableCopper
import turniplabs.industry.blocks.cables.BlockCableGold
import turniplabs.industry.blocks.cables.BlockCableTin
import turniplabs.industry.blocks.entities.*
import turniplabs.industry.blocks.machines.*
import turniplabs.industry.items.*

class Industry2: ModInitializer {

	/*
	TODO : More Machines
	TODO : Make machine textures consistent
	TODO : Log resin
	TODO : Fix cable model (add fake bounding box?)
	TODO : Mod Support
	TODO : Lang file descriptions
	TODO : Reactor Stuff
	TODO : Configs (IDs, world gen, .etc)
	*/

	companion object {
		const val MOD_ID: String = "industry"
		val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

		private var blockID: Int = 999
		private fun nextBlockID(): Int {
			return blockID++
		}

		private var itemID = 16999
		private fun nextItemID(): Int {
			return itemID++
		}

		/* BLOCKS */

		// Ores
		private val oreBuilder = BlockBuilder(MOD_ID)
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
		private val blockBuilder = BlockBuilder(MOD_ID)
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
		private val cableBuilder = BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.METAL)
			.setHardness(1.0f)
			.setTags(BlockTags.NOT_IN_CREATIVE_MENU)

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
		private val insulatedCableBuilder = BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.CLOTH)
			.setHardness(1.0f)
			.setTags(BlockTags.NOT_IN_CREATIVE_MENU)

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
		private val machineBuilder = BlockBuilder(MOD_ID)
			.setTopBottomTexture("machine_casing_basic.png")
			.setEastTexture("machine_casing_basic.png")
			.setSouthTexture("machine_casing_basic.png")
			.setWestTexture("machine_casing_basic.png")
			.setBlockSound(BlockSounds.METAL)
			.setHardness(5.0f)
			.setResistance(10.0f)

		private val machineBuilderBlank = BlockBuilder(MOD_ID)
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

		// Miscellaneous
		val hardenedCoal: Block = BlockBuilder(MOD_ID)
			.setTextures("hardened_coal.png")
			.setBlockSound(BlockSounds.STONE)
			.setHardness(10.0f)
			.setResistance(60.0f)
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.build(Block("block.coal.hardened", nextBlockID(), Material.stone))

		val rubberLeaves: Block = BlockBuilder(MOD_ID)
			.setTextures(2, 20)
			.setBlockSound(BlockSounds.GRASS)
			.setHardness(0.2f)
			.setLightOpacity(1)
			.setTags(BlockTags.SHEARS_DO_SILK_TOUCH, BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_HOE, BlockTags.MINEABLE_BY_SWORD, BlockTags.MINEABLE_BY_SHEARS)
			.build(BlockLeavesRubber("leaves.rubber", nextBlockID(), Material.leaves, false))

		val rubberLog: Block = BlockBuilder(MOD_ID)
			.setSideTextures("log_rubber.png")
			.setTopBottomTexture("log_rubber_top.png")
			.setBlockSound(BlockSounds.WOOD)
			.setHardness(2.0f)
			.setTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE)
			.build(BlockLogRubber("log.rubber", nextBlockID()))

		val rubberSapling: Block = BlockBuilder(MOD_ID)
			.setTextures("sapling_rubber.png")
			.setBlockSound(BlockSounds.GRASS)
			.setTags(BlockTags.BROKEN_BY_FLUIDS)
			.build(BlockSaplingRubber("sapling.rubber", nextBlockID()))

		/* ITEMS */

		// Ores
		val rawCopperOre: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "ore.raw.copper", "raw_copper.png")
		val rawTinOre: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "ore.raw.tin", "raw_tin.png")
		val rawUranium: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "ore.raw.uranium", "raw_uranium.png")

		// Dusts
		val copperDust: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "dust.copper", "dust_copper.png")
		val tinDust: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "dust.tin", "dust_tin.png")
		val bronzeDust: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "dust.bronze", "dust_bronze.png")
		val ironDust: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "dust.iron", "dust_iron.png")
		val goldDust: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "dust.gold", "dust_gold.png")
		val coalDust: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "dust.coal", "dust_coal.png")

		// Ingots
		val copperIngot: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "ingot.copper", "ingot_copper.png")
		val tinIngot: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "ingot.tin", "ingot_tin.png")
		val bronzeIngot: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "ingot.bronze", "ingot_bronze.png")
		val uraniumIngot: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "ingot.uranium", "ingot_uranium.png")

		// Plates
		val copperPlate: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "plate.copper", "plate_copper.png")
		val tinPlate: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "plate.tin", "plate_tin.png")
		val bronzePlate: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "plate.bronze", "plate_bronze.png")
		val ironPlate: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "plate.iron", "plate_iron.png")
		val goldPlate: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "plate.gold", "plate_gold.png")
		val steelPlate: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "plate.steel", "plate_steel.png")

		// Cables
		val itemCopperCable: Item? = ItemHelper.createItem(
			MOD_ID,
			ItemCable("cable.copper", nextItemID(), copperCable),
			"cable.copper",
			"cable_copper.png"
		)
		val itemTinCable: Item = ItemHelper.createItem(
			MOD_ID,
			ItemCable("cable.tin", nextItemID(), tinCable),
			"cable.tin",
			"cable_tin.png"
		)
		val itemGoldCable: Item = ItemHelper.createItem(
			MOD_ID,
			ItemCable("cable.gold", nextItemID(), goldCable),
			"cable.gold",
			"cable_gold.png"
		)
		val itemSteelCable: Item = ItemHelper.createItem(
			MOD_ID,
			ItemCable("cable.steel", nextItemID(), steelCable),
			"cable.steel",
			"cable_steel.png"
		)

		// Insulated Cables
		val itemInsulatedCopperCable: Item = ItemHelper.createItem(
			MOD_ID,
			ItemCable("insulated.cable.copper", nextItemID(), insulatedCopperCable),
			"insulated.cable.copper",
			"cable_insulated_copper.png"
		)
		val itemInsulatedTinCable: Item = ItemHelper.createItem(
			MOD_ID,
			ItemCable("insulated.cable.tin", nextItemID(), insulatedTinCable),
			"insulated.cable.tin",
			"cable_insulated_tin.png"
		)
		val itemInsulatedGoldCable: Item = ItemHelper.createItem(
			MOD_ID,
			ItemCable("insulated.cable.gold", nextItemID(), insulatedGoldCable),
			"insulated.cable.gold",
			"cable_insulated_gold.png"
		)
		val itemInsulatedSteelCable: Item = ItemHelper.createItem(
			MOD_ID,
			ItemCable("insulated.cable.steel", nextItemID(), insulatedSteelCable),
			"insulated.cable.steel",
			"cable_insulated_steel.png"
		)

		// Tools
		val itemBatteryRedstone: Item = ItemHelper.createItem(
			MOD_ID,
			ItemBatteryRedstone(nextItemID()),
			"tool.battery.redstone"
		)
		val itemBatteryAdvanced: Item = ItemHelper.createItem(
			MOD_ID,
			ItemBatteryAdvanced(nextItemID()),
			"tool.battery.advanced",
		)
		val itemBatteryCrystal: Item = ItemHelper.createItem(
			MOD_ID,
			ItemBatteryCrystal(nextItemID()),
			"tool.battery.crystal",
		)
		val itemBatteryLapis: Item = ItemHelper.createItem(
			MOD_ID,
			ItemBatteryLapis(nextItemID()),
			"tool.battery.lapis",
		)

		val treeTap: Item = ItemHelper.createItem(MOD_ID, ItemTap(nextItemID()), "tool.tap", "tree_tap.png")

		val emptyCell: Item = ItemHelper.createItem(MOD_ID, ItemCell(nextItemID()), "cell.empty", "cell_empty.png")
		val waterCell: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "cell.water", "cell_water.png")
		val lavaCell: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "cell.lava", "cell_lava.png")
		val uraniumCell: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "cell.uranium", "cell_uranium.png")

		// Materials
		val resin: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "ingredient.resin", "resin.png")
		val rubber: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "ingredient.rubber", "rubber.png")
		val circuit: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "ingredient.circuit", "circuit.png")
		val circuitAdvanced: Item = ItemHelper.createItem(MOD_ID, Item(nextItemID()), "ingredient.advancedcircuit", "circuit_advanced.png")
	}

	override fun onInitialize() {
		LOGGER.info("Industry 2 initialized. Have fun!")

		ItemToolPickaxe.miningLevels[oreCopperBasalt] = 1
		ItemToolPickaxe.miningLevels[oreCopperStone] = 1
		ItemToolPickaxe.miningLevels[oreCopperLimestone] = 1
		ItemToolPickaxe.miningLevels[oreCopperGranite] = 1
		ItemToolPickaxe.miningLevels[oreTinBasalt] = 1
		ItemToolPickaxe.miningLevels[oreTinStone] = 1
		ItemToolPickaxe.miningLevels[oreTinLimestone] = 1
		ItemToolPickaxe.miningLevels[oreTinGranite] = 1
		ItemToolPickaxe.miningLevels[oreUraniumBasalt] = 2
		ItemToolPickaxe.miningLevels[oreUraniumStone] = 2
		ItemToolPickaxe.miningLevels[oreUraniumLimestone] = 2
		ItemToolPickaxe.miningLevels[oreUraniumGranite] = 2
		ItemToolPickaxe.miningLevels[hardenedCoal] = 3

		BlockModelDispatcher.getInstance().addDispatch(copperCable, BlockModelRenderBlocks(32))
		BlockModelDispatcher.getInstance().addDispatch(tinCable, BlockModelRenderBlocks(32))
		BlockModelDispatcher.getInstance().addDispatch(goldCable, BlockModelRenderBlocks(32))
		BlockModelDispatcher.getInstance().addDispatch(steelCable, BlockModelRenderBlocks(32))
		BlockModelDispatcher.getInstance().addDispatch(insulatedCopperCable, BlockModelRenderBlocks(32))
		BlockModelDispatcher.getInstance().addDispatch(insulatedTinCable, BlockModelRenderBlocks(32))
		BlockModelDispatcher.getInstance().addDispatch(insulatedGoldCable, BlockModelRenderBlocks(32))
		BlockModelDispatcher.getInstance().addDispatch(insulatedSteelCable, BlockModelRenderBlocks(32))
		BlockModelDispatcher.getInstance().addDispatch(rubberSapling, BlockModelRenderBlocks(1))

		BlockColorDispatcher.getInstance().addDispatch(rubberLeaves, BlockColorLeaves("pine"))

		EntityHelper.createTileEntity(TileEntityCable::class.java, "Cable")
		EntityHelper.createTileEntity(TileEntityGenerator::class.java, "IndustryGenerator")
		EntityHelper.createTileEntity(TileEntityElectricFurnace::class.java, "ElectricFurnace")
		EntityHelper.createTileEntity(TileEntitySolarGenerator::class.java, "SolarGenerator")
		EntityHelper.createTileEntity(TileEntitySolarLV::class.java, "LVSolarArray")
		EntityHelper.createTileEntity(TileEntitySolarMV::class.java, "MVSolarArray")
		EntityHelper.createTileEntity(TileEntitySolarHV::class.java, "HVSolarArray")
		EntityHelper.createTileEntity(TileEntitySolarSHV::class.java, "SHVSolarArray")
		EntityHelper.createTileEntity(TIleEntityBatboxLV::class.java, "BatboxLV")
		EntityHelper.createTileEntity(TIleEntityBatboxMV::class.java, "BatboxMV")
		EntityHelper.createTileEntity(TIleEntityBatboxHV::class.java, "BatboxHV")
		EntityHelper.createTileEntity(TIleEntityBatboxSHV::class.java, "BatboxSHV")
		EntityHelper.createTileEntity(TileEntityMacerator::class.java, "Macerator")
		EntityHelper.createTileEntity(TileEntityCompressor::class.java, "Compressor")
		EntityHelper.createTileEntity(TileEntityCutter::class.java, "Cutter")
		EntityHelper.createTileEntity(TileEntityExtractor::class.java, "Extractor")

		RecipesFurnace.smelting().addSmelting(oreCopperStone.id, ItemStack(copperIngot))
		RecipesFurnace.smelting().addSmelting(oreCopperBasalt.id, ItemStack(copperIngot))
		RecipesFurnace.smelting().addSmelting(oreCopperLimestone.id, ItemStack(copperIngot))
		RecipesFurnace.smelting().addSmelting(oreCopperGranite.id, ItemStack(copperIngot))
		RecipesFurnace.smelting().addSmelting(oreTinStone.id, ItemStack(tinIngot))
		RecipesFurnace.smelting().addSmelting(oreTinBasalt.id, ItemStack(tinIngot))
		RecipesFurnace.smelting().addSmelting(oreTinLimestone.id, ItemStack(tinIngot))
		RecipesFurnace.smelting().addSmelting(oreTinGranite.id, ItemStack(tinIngot))
		RecipesFurnace.smelting().addSmelting(oreUraniumStone.id, ItemStack(rawUranium))
		RecipesFurnace.smelting().addSmelting(oreUraniumBasalt.id, ItemStack(rawUranium))
		RecipesFurnace.smelting().addSmelting(oreUraniumLimestone.id, ItemStack(rawUranium))
		RecipesFurnace.smelting().addSmelting(oreUraniumGranite.id, ItemStack(rawUranium))
		RecipesFurnace.smelting().addSmelting(rawCopperOre.id, ItemStack(copperIngot))
		RecipesFurnace.smelting().addSmelting(copperDust.id, ItemStack(copperIngot))
		RecipesFurnace.smelting().addSmelting(rawTinOre.id, ItemStack(tinIngot))
		RecipesFurnace.smelting().addSmelting(tinDust.id, ItemStack(tinIngot))
		RecipesFurnace.smelting().addSmelting(bronzeDust.id, ItemStack(bronzeIngot))
		RecipesFurnace.smelting().addSmelting(ironDust.id, ItemStack(Item.ingotIron))
		RecipesFurnace.smelting().addSmelting(goldDust.id, ItemStack(Item.ingotGold))

		CraftingManager.getInstance().addRecipe(
			ItemStack(copperBlock),
			"111", "111", "111",
			'1', copperIngot
		)
		CraftingManager.getInstance().addRecipe(
			ItemStack(tinBlock),
			"111", "111", "111",
			'1', tinIngot
		)
		CraftingManager.getInstance().addRecipe(
			ItemStack(bronzeBlock),
			"111", "111", "111",
			'1', bronzeIngot
		)
		CraftingManager.getInstance().addRecipe(
			ItemStack(uraniumBlock),
			"111", "111", "111",
			'1', uraniumIngot
		)
		CraftingManager.getInstance().addRecipe(
			ItemStack(machineCasing),
			"111", "1#1", "111",
			'1', ironPlate
		)
		CraftingManager.getInstance().addRecipe(
			ItemStack(machineCasingAdvanced),
			"111", "1#1", "111",
			'1', steelPlate
		)
		CraftingManager.getInstance().addRecipe(
			ItemStack(machineGenerator),
			"1", "2", "3",
			'1', itemBatteryRedstone,
			'2', machineCasing,
			'3', Block.furnaceStoneIdle
		)
		CraftingManager.getInstance().addRecipe(
			ItemStack(machineGeothermalGenerator),
			"121", "121", "343",
			'1', Block.glass,
			'2', emptyCell,
			'3', steelPlate,
			'4', machineGenerator
		)
		CraftingManager.getInstance().addRecipe(
			ItemStack(machineSolarGenerator),
			"121", "212", "343",
			'1', coalDust,
			'2', Block.glass,
			'3', circuit,
			'4', machineGenerator
		)
		CraftingManager.getInstance().addRecipe(
			ItemStack(machineSolarArrayLV),
			"111", "121", "111",
			'1', machineSolarGenerator,
			'2', batboxLV
		)
		CraftingManager.getInstance().addRecipe(
			ItemStack(machineSolarArrayMV),
			"111", "121", "111",
			'1', machineSolarArrayLV,
			'2', batboxMV
		)
		CraftingManager.getInstance().addRecipe(
			ItemStack(machineSolarArrayHV),
			"111", "121", "111",
			'1', machineSolarArrayMV,
			'2', batboxHV
		)
		CraftingManager.getInstance().addRecipe(
			ItemStack(machineSolarArraySHV),
			"111", "121", "111",
			'1', machineSolarArrayHV,
			'2', batboxSHV
		)
		CraftingManager.getInstance().addRecipe(
			ItemStack(batboxLV),
			"121", "333", "111",
			'1', Block.planksOak,
			'2', itemTinCable,
			'3', itemBatteryRedstone
		)
		CraftingManager.getInstance().addRecipe(
			ItemStack(batboxMV),
			"121", "333", "111",
			'1', copperPlate,
			'2', itemCopperCable,
			'3', itemBatteryAdvanced
		)
		CraftingManager.getInstance().addRecipe(
			ItemStack(batboxHV),
			"121", "333", "111",
			'1', ironPlate,
			'2', itemGoldCable,
			'3', itemBatteryCrystal
		)
		CraftingManager.getInstance().addRecipe(
			ItemStack(batboxSHV),
			"121", "333", "111",
			'1', steelPlate,
			'2', itemSteelCable,
			'3', itemBatteryLapis
		)
		CraftingManager.getInstance().addRecipe(
			ItemStack(machineElectricFurnace),
			"#1#", "232", "#4#",
			'1', circuit,
			'2', Item.dustRedstone,
			'3', machineCasing,
			'4', Block.furnaceStoneIdle
		)
		CraftingManager.getInstance().addRecipe(
			ItemStack(machineMacerator),
			"111", "232", "#4#",
			'1', Item.flint,
			'2', Block.cobbleStone,
			'3', machineCasing,
			'4', circuit
		)
		CraftingManager.getInstance().addRecipe(
			ItemStack(machineCompressor),
			"1#1", "121", "131",
			'1', Block.stone,
			'2', machineCasing,
			'3', circuit
		)
		CraftingManager.getInstance().addRecipe(
			ItemStack(machineCutter),
			"1#1", "121", "131",
			'1', Item.ingotIron,
			'2', machineCasing,
			'3', circuit
		)
		CraftingManager.getInstance().addRecipe(
			ItemStack(hardenedCoal),
			"121", "232", "121",
			'1', Item.nethercoal,
			'2', coalDust,
			'3', Block.obsidian
		)
		CraftingManager.getInstance().addShapelessRecipe(ItemStack(Block.planksOakPainted, 4, 7), rubberLog)
	}
}
