package turniplabs.industry

import net.fabricmc.api.ModInitializer
import net.minecraft.client.render.block.model.BlockModelDispatcher
import net.minecraft.client.render.block.model.BlockModelRenderBlocks
import net.minecraft.client.sound.block.BlockSounds
import net.minecraft.core.block.Block
import net.minecraft.core.block.material.Material
import net.minecraft.core.block.tag.BlockTags
import net.minecraft.core.crafting.recipe.RecipesFurnace
import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemPlaceable
import net.minecraft.core.item.ItemStack
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import turniplabs.halplibe.helper.BlockBuilder
import turniplabs.halplibe.helper.EntityHelper
import turniplabs.halplibe.helper.ItemHelper
import turniplabs.industry.blocks.BlockCopperOre
import turniplabs.industry.blocks.BlockTinOre
import turniplabs.industry.blocks.BlockUraniumOre
import turniplabs.industry.blocks.cables.BlockCableCopper
import turniplabs.industry.blocks.cables.BlockCableGold
import turniplabs.industry.blocks.cables.BlockCableTin
import turniplabs.industry.blocks.entities.*
import turniplabs.industry.blocks.machines.BlockElectricFurnace
import turniplabs.industry.blocks.machines.BlockGenerator
import turniplabs.industry.blocks.machines.BlockMacerator
import turniplabs.industry.blocks.machines.BlockSolarGenerator
import turniplabs.industry.items.ItemBatteryRedstone

class Industry2: ModInitializer {

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
			.setSides("block_copper_sides.png")
			.setBottomTexture("block_copper_bottom.png")
			.build(Block("block.copper", nextBlockID(), Material.metal))

		val tinBlock: Block = blockBuilder
			.setTopTexture("block_tin_top.png")
			.setSides("block_tin_sides.png")
			.setBottomTexture("block_tin_bottom.png")
			.build(Block("block.tin", nextBlockID(), Material.metal))

		val bronzeBlock: Block = blockBuilder
			.setTopTexture("block_bronze_top.png")
			.setSides("block_bronze_sides.png")
			.setBottomTexture("block_bronze_bottom.png")
			.build(Block("block.bronze", nextBlockID(), Material.metal))

		val uraniumBlock: Block = blockBuilder
			.setTopTexture("block_uranium_top.png")
			.setSides("block_uranium_sides.png")
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
			.setBlockSound(BlockSounds.METAL)
			.setHardness(5.0f)
			.setResistance(10.0f)

		val machineCasing: Block = machineBuilder
			.setTextures("machine_casing_basic.png")
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.build(Block("machine.casing", nextBlockID(), Material.metal))

		val machineCasingAdvanced: Block = machineBuilder
			.setTextures("machine_casing_advanced.png")
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.build(Block("machine.casing.advanced", nextBlockID(), Material.metal))

		val machineGenerator: Block = machineBuilder
			.setTextures("machine_casing_basic.png")
			.build(BlockGenerator("machine.generator", nextBlockID(), Material.metal))

		val machineSolarGenerator: Block = machineBuilder
			.setTopTexture("machine_generator_solar.png")
			.setSides("machine_casing_basic.png")
			.setBottomTexture("machine_casing_basic.png")
			.build(BlockSolarGenerator("machine.generator.solar", nextBlockID(), Material.metal))

		val machineElectricFurnace: Block = machineBuilder
			.setTextures("machine_casing_basic.png")
			.build(BlockElectricFurnace("machine.furnace", nextBlockID(), Material.metal))

		val machineMacerator: Block = machineBuilder
			.setTextures("machine_casing_basic.png")
			.build(BlockMacerator("machine.macerator", nextBlockID(), Material.metal))

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
			ItemPlaceable("cable.copper", nextItemID(), copperCable),
			"cable.copper",
			"cable_copper.png"
		)
		val itemTinCable: Item = ItemHelper.createItem(
			MOD_ID, ItemPlaceable("cable.tin", nextItemID(), tinCable),
			"cable.tin",
			"cable_tin.png"
		)
		val itemGoldCable: Item = ItemHelper.createItem(
			MOD_ID, ItemPlaceable("cable.gold", nextItemID(), goldCable),
			"cable.gold",
			"cable_gold.png"
		)
		val itemSteelCable: Item = ItemHelper.createItem(
			MOD_ID, ItemPlaceable("cable.steel", nextItemID(), steelCable),
			"cable.steel",
			"cable_steel.png"
		)

		// Insulated Cables
		val itemInsulatedCopperCable: Item = ItemHelper.createItem(
			MOD_ID,
			ItemPlaceable("insulated.cable.copper", nextItemID(), insulatedCopperCable),
			"insulated.cable.copper",
			"cable_insulated_copper.png"
		)
		val itemInsulatedTinCable: Item = ItemHelper.createItem(
			MOD_ID,
			ItemPlaceable("insulated.cable.tin", nextItemID(), insulatedTinCable),
			"insulated.cable.tin",
			"cable_insulated_tin.png"
		)
		val itemInsulatedGoldCable: Item = ItemHelper.createItem(
			MOD_ID,
			ItemPlaceable("insulated.cable.gold", nextItemID(), insulatedGoldCable),
			"insulated.cable.gold",
			"cable_insulated_gold.png"
		)
		val itemInsulatedSteelCable: Item = ItemHelper.createItem(
			MOD_ID,
			ItemPlaceable("insulated.cable.steel", nextItemID(), insulatedSteelCable),
			"insulated.cable.steel",
			"cable_insulated_steel.png"
		)

		// Tools
		val itemBatteryRedstone: Item = ItemHelper.createItem(
			MOD_ID,
			ItemBatteryRedstone(nextItemID()),
			"tool.battery.redstone"
		).setMaxStackSize(1)
	}

	override fun onInitialize() {
		LOGGER.info("Industry 2 initialized. Have fun!")

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

		BlockModelDispatcher.getInstance().addDispatch(copperCable, BlockModelRenderBlocks(32))
		BlockModelDispatcher.getInstance().addDispatch(tinCable, BlockModelRenderBlocks(32))
		BlockModelDispatcher.getInstance().addDispatch(goldCable, BlockModelRenderBlocks(32))
		BlockModelDispatcher.getInstance().addDispatch(steelCable, BlockModelRenderBlocks(32))
		BlockModelDispatcher.getInstance().addDispatch(insulatedCopperCable, BlockModelRenderBlocks(32))
		BlockModelDispatcher.getInstance().addDispatch(insulatedTinCable, BlockModelRenderBlocks(32))
		BlockModelDispatcher.getInstance().addDispatch(insulatedGoldCable, BlockModelRenderBlocks(32))
		BlockModelDispatcher.getInstance().addDispatch(insulatedSteelCable, BlockModelRenderBlocks(32))

		EntityHelper.createTileEntity(TileEntityCable::class.java, "Cable")
		EntityHelper.createTileEntity(TileEntityGenerator::class.java, "IndustryGenerator")
		EntityHelper.createTileEntity(TileEntitySolarGenerator::class.java, "SolarGenerator")
		EntityHelper.createTileEntity(TileEntityElectricFurnace::class.java, "ElectricFurnace")
		EntityHelper.createTileEntity(TileEntityMacerator::class.java, "Macerator")
	}
}