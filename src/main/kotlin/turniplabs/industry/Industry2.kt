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
import turniplabs.halplibe.helper.BlockHelper
import turniplabs.halplibe.helper.EntityHelper
import turniplabs.halplibe.helper.ItemHelper
import turniplabs.industry.blocks.BlockCopperOre
import turniplabs.industry.blocks.BlockTinOre
import turniplabs.industry.blocks.BlockUraniumOre
import turniplabs.industry.blocks.cables.*
import turniplabs.industry.blocks.entities.TileEntityCable
import turniplabs.industry.blocks.entities.TileEntityElectricFurnace
import turniplabs.industry.blocks.entities.TileEntityGenerator
import turniplabs.industry.blocks.machines.BlockElectricFurnace
import turniplabs.industry.blocks.machines.BlockGenerator
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
		val oreCopperStone: Block = BlockHelper.createBlock(
			MOD_ID,
			BlockCopperOre("industry.ore.copper.stone", nextBlockID(), Material.stone),
			"ore_copper_stone.png",
			BlockSounds.STONE,
			3.0f,
			5.0f,
			0.0f
		).withTags(BlockTags.MINEABLE_BY_PICKAXE)

		val oreCopperBasalt: Block = BlockHelper.createBlock(
			MOD_ID,
			BlockCopperOre("industry.ore.copper.basalt", nextBlockID(), Material.stone),
			"ore_copper_basalt.png",
			BlockSounds.STONE,
			3.0f,
			5.0f,
			0.0f
		).withTags(BlockTags.MINEABLE_BY_PICKAXE)

		val oreCopperLimestone: Block = BlockHelper.createBlock(
			MOD_ID,
			BlockCopperOre("industry.ore.copper.limestone", nextBlockID(), Material.stone),
			"ore_copper_limestone.png",
			BlockSounds.STONE,
			3.0f,
			5.0f,
			0.0f
		).withTags(BlockTags.MINEABLE_BY_PICKAXE)

		val oreCopperGranite: Block = BlockHelper.createBlock(
			MOD_ID,
			BlockCopperOre("industry.ore.copper.granite", nextBlockID(), Material.stone),
			"ore_copper_granite.png",
			BlockSounds.STONE,
			3.0f,
			5.0f,
			0.0f
		).withTags(BlockTags.MINEABLE_BY_PICKAXE)

		val oreTinStone: Block = BlockHelper.createBlock(
			MOD_ID,
			BlockTinOre("industry.ore.tin.stone", nextBlockID(), Material.stone),
			"ore_tin_stone.png",
			BlockSounds.STONE,
			3.0f,
			5.0f,
			0.0f
		).withTags(BlockTags.MINEABLE_BY_PICKAXE)

		val oreTinBasalt: Block = BlockHelper.createBlock(
			MOD_ID,
			BlockTinOre("industry.ore.tin.basalt", nextBlockID(), Material.stone),
			"ore_tin_basalt.png",
			BlockSounds.STONE,
			3.0f,
			5.0f,
			0.0f
		).withTags(BlockTags.MINEABLE_BY_PICKAXE)

		val oreTinLimestone: Block = BlockHelper.createBlock(
			MOD_ID,
			BlockTinOre("industry.ore.tin.limestone", nextBlockID(), Material.stone),
			"ore_tin_limestone.png",
			BlockSounds.STONE,
			3.0f,
			5.0f,
			0.0f
		).withTags(BlockTags.MINEABLE_BY_PICKAXE)

		val oreTinGranite: Block = BlockHelper.createBlock(
			MOD_ID,
			BlockTinOre("industry.ore.tin.granite", nextBlockID(), Material.stone),
			"ore_tin_granite.png",
			BlockSounds.STONE,
			3.0f,
			5.0f,
			0.0f
		).withTags(BlockTags.MINEABLE_BY_PICKAXE)

		val oreUraniumStone: Block = BlockHelper.createBlock(
			MOD_ID,
			BlockUraniumOre("industry.ore.uranium.stone", nextBlockID(), Material.stone),
			"ore_uranium_stone.png",
			BlockSounds.STONE,
			3.0f,
			5.0f,
			0.0f
		).withTags(BlockTags.MINEABLE_BY_PICKAXE)

		val oreUraniumBasalt: Block = BlockHelper.createBlock(
			MOD_ID,
			BlockUraniumOre("industry.ore.uranium.basalt", nextBlockID(), Material.stone),
			"ore_uranium_basalt.png",
			BlockSounds.STONE,
			3.0f,
			5.0f,
			0.0f
		).withTags(BlockTags.MINEABLE_BY_PICKAXE)

		val oreUraniumLimestone: Block = BlockHelper.createBlock(
			MOD_ID,
			BlockUraniumOre("industry.ore.uranium.limestone", nextBlockID(), Material.stone),
			"ore_uranium_limestone.png",
			BlockSounds.STONE,
			3.0f,
			5.0f,
			0.0f
		).withTags(BlockTags.MINEABLE_BY_PICKAXE)

		val oreUraniumGranite: Block = BlockHelper.createBlock(
			MOD_ID,
			BlockUraniumOre("industry.ore.uranium.granite", nextBlockID(), Material.stone),
			"ore_uranium_granite.png",
			BlockSounds.STONE,
			3.0f,
			5.0f,
			0.0f
		).withTags(BlockTags.MINEABLE_BY_PICKAXE)

		// Blocks
		val copperBlock: Block = BlockHelper.createBlock(
			MOD_ID,
			Block("industry.block.copper", nextBlockID(), Material.metal),
			"block_copper_top.png",
			"block_copper_bottom.png",
			"block_copper_sides.png",
			BlockSounds.METAL,
			5.0f,
			10.0f,
			0.0f
		).withTags(BlockTags.MINEABLE_BY_PICKAXE)

		val tinBlock: Block = BlockHelper.createBlock(
			MOD_ID,
			Block("industry.block.tin", nextBlockID(), Material.metal),
			"block_tin_top.png",
			"block_tin_bottom.png",
			"block_tin_sides.png",
			BlockSounds.METAL,
			5.0f,
			10.0f,
			0.0f
		).withTags(BlockTags.MINEABLE_BY_PICKAXE)

		val bronzeBlock: Block = BlockHelper.createBlock(
			MOD_ID,
			Block("industry.block.bronze", nextBlockID(), Material.metal),
			"block_bronze_top.png",
			"block_bronze_bottom.png",
			"block_bronze_sides.png",
			BlockSounds.METAL,
			5.0f,
			10.0f,
			0.0f
		).withTags(BlockTags.MINEABLE_BY_PICKAXE)

		val uraniumBlock: Block = BlockHelper.createBlock(
			MOD_ID,
			Block("industry.block.uranium", nextBlockID(), Material.metal),
			"block_uranium_top.png",
			"block_uranium_bottom.png",
			"block_uranium_sides.png",
			BlockSounds.METAL,
			5.0f,
			10.0f,
			0.0f
		).withTags(BlockTags.MINEABLE_BY_PICKAXE)

		// Cables
		val copperCable: Block = BlockHelper.createBlock(
			MOD_ID,
			BlockCableCopper("industry.cable.copper", nextBlockID(), Material.metal, 32, 32, 2),
			"block_copper_top.png",
			BlockSounds.METAL,
			1.0f,
			0.0f,
			0.0f
		).withTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.NOT_IN_CREATIVE_MENU)

		val tinCable: Block = BlockHelper.createBlock(
			MOD_ID,
			BlockCableTin("industry.cable.tin", nextBlockID(), Material.metal, 16, 16, 1),
			"block_tin_top.png",
			BlockSounds.METAL,
			1.0f,
			0.0f,
			0.0f
		).withTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.NOT_IN_CREATIVE_MENU)

		val goldCable: Block = BlockHelper.createBlock(
			MOD_ID,
			BlockCableGold("industry.cable.gold", nextBlockID(), Material.metal, 512, 512, 6),
			"block_gold_top.png",
			BlockSounds.METAL,
			1.0f,
			0.0f,
			0.0f
		).withTags(BlockTags.NOT_IN_CREATIVE_MENU)

		val steelCable: Block = BlockHelper.createBlock(
			MOD_ID,
			BlockCableSteel("industry.cable.steel", nextBlockID(), Material.metal, 1024, 1024, 8),
			"block_steel_top.png",
			BlockSounds.METAL,
			1.0f,
			0.0f,
			0.0f
		).withTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.NOT_IN_CREATIVE_MENU)

		// Insulated Cables
		val insulatedCopperCable: Block = BlockHelper.createBlock(
			MOD_ID,
			BlockCableCopperInsulated("industry.cable.copper", nextBlockID(), Material.cloth, 32, 32, 0),
			"insulated_cable_sides.png",
			BlockSounds.CLOTH,
			1.0f,
			0.0f,
			0.0f
		).withTags(BlockTags.NOT_IN_CREATIVE_MENU)

		val insulatedTinCable: Block = BlockHelper.createBlock(
			MOD_ID,
			BlockCableTinInsulated("industry.cable.tin", nextBlockID(), Material.cloth, 16, 16, 0),
			"insulated_cable_sides.png",
			BlockSounds.CLOTH,
			1.0f,
			0.0f,
			0.0f
		).withTags(BlockTags.NOT_IN_CREATIVE_MENU)

		val insulatedGoldCable: Block = BlockHelper.createBlock(
			MOD_ID,
			BlockCableGoldInsulated("industry.cable.gold", nextBlockID(), Material.cloth, 512, 512, 0),
			"insulated_cable_sides.png",
			BlockSounds.CLOTH,
			1.0f,
			0.0f,
			0.0f
		).withTags(BlockTags.NOT_IN_CREATIVE_MENU)

		val insulatedSteelCable: Block = BlockHelper.createBlock(
			MOD_ID,
			BlockCableSteelInsulated("industry.cable.steel", nextBlockID(), Material.cloth, 1024, 1024, 0),
			"insulated_cable_sides.png",
			BlockSounds.CLOTH,
			1.0f,
			0.0f,
			0.0f
		).withTags(BlockTags.NOT_IN_CREATIVE_MENU)

		// Machines
		val machineCasing: Block = BlockHelper.createBlock(
			MOD_ID,
			Block("industry.machine.casing", nextBlockID(), Material.metal),
			"machine_casing_basic.png",
			BlockSounds.METAL,
			3.5f,
			10.0f,
			0.0f
		)

		val machineCasingAdvanced: Block = BlockHelper.createBlock(
			MOD_ID,
			Block("industry.machine.casing.advanced", nextBlockID(), Material.metal),
			"machine_casing_advanced.png",
			BlockSounds.METAL,
			3.5f,
			10.0f,
			0.0f
		)

		val machineGenerator: Block = BlockHelper.createBlock(
			MOD_ID,
			BlockGenerator("industry.machine.generator", nextBlockID(), Material.metal),
			"machine_casing_basic.png",
			"machine_casing_basic.png",
			"machine_generator.png",
			"machine_casing_basic.png",
			"machine_casing_basic.png",
			"machine_casing_basic.png",
			BlockSounds.METAL,
			3.5f,
			10.0f,
			0.0f
		)

		val machineElectricFurnace: Block = BlockHelper.createBlock(
			MOD_ID,
			BlockElectricFurnace("industry.machine.furnace", nextBlockID(), Material.metal),
			"machine_casing_basic.png",
			"machine_casing_basic.png",
			"machine_furnace.png",
			"machine_casing_basic.png",
			"machine_casing_basic.png",
			"machine_casing_basic.png",
			BlockSounds.METAL,
			3.5f,
			10.0f,
			0.0f
		)

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
		)
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
		BlockModelDispatcher.getInstance().addDispatch(insulatedCopperCable, BlockModelRenderBlocks(32))
		BlockModelDispatcher.getInstance().addDispatch(tinCable, BlockModelRenderBlocks(32))
		BlockModelDispatcher.getInstance().addDispatch(insulatedTinCable, BlockModelRenderBlocks(32))
		BlockModelDispatcher.getInstance().addDispatch(goldCable, BlockModelRenderBlocks(32))
		BlockModelDispatcher.getInstance().addDispatch(insulatedGoldCable, BlockModelRenderBlocks(32))
		BlockModelDispatcher.getInstance().addDispatch(steelCable, BlockModelRenderBlocks(32))
		BlockModelDispatcher.getInstance().addDispatch(insulatedSteelCable, BlockModelRenderBlocks(32))

		EntityHelper.createTileEntity(TileEntityCable::class.java, "Cable")
		EntityHelper.createTileEntity(TileEntityElectricFurnace::class.java, "ElectricFurnace")
		EntityHelper.createTileEntity(TileEntityGenerator::class.java, "IndustryGenerator")
	}
}