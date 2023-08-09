package turniplabs.industry.blocks

import net.minecraft.client.sound.block.BlockSounds
import net.minecraft.core.block.Block
import net.minecraft.core.block.material.Material
import net.minecraft.core.block.tag.BlockTags
import turniplabs.halplibe.helper.BlockHelper
import turniplabs.industry.Industry2.MOD_ID
import turniplabs.industry.items.ModItems

object ModBlocks
{

    private var blockID: Int = 999

    private fun nextBlockID(): Int {
        return blockID++
    }

    // Copper
    val oreCopperStone: Block = BlockHelper.createBlock(
        MOD_ID,
        BlockCopperOre("ore.copper.stone", nextBlockID(), Material.stone),
        "ore_copper_stone.png",
        BlockSounds.STONE,
        3.0f,
        5.0f,
        0.0f
    ).withTags(BlockTags.MINEABLE_BY_PICKAXE)

    val oreCopperBasalt: Block = BlockHelper.createBlock(
        MOD_ID,
        BlockCopperOre("ore.copper.basalt", nextBlockID(), Material.stone),
        "ore_copper_basalt.png",
        BlockSounds.STONE,
        3.0f,
        5.0f,
        0.0f
    ).withTags(BlockTags.MINEABLE_BY_PICKAXE)

    val oreCopperLimestone: Block = BlockHelper.createBlock(
        MOD_ID,
        BlockCopperOre("ore.copper.limestone", nextBlockID(), Material.stone),
        "ore_copper_limestone.png",
        BlockSounds.STONE,
        3.0f,
        5.0f,
        0.0f
    ).withTags(BlockTags.MINEABLE_BY_PICKAXE)

    val oreCopperGranite: Block = BlockHelper.createBlock(
        MOD_ID,
        BlockCopperOre("ore.copper.granite", nextBlockID(), Material.stone),
        "ore_copper_granite.png",
        BlockSounds.STONE,
        3.0f,
        5.0f,
        0.0f
    ).withTags(BlockTags.MINEABLE_BY_PICKAXE)

    val copperBlock: Block = BlockHelper.createBlock(
        MOD_ID,
        Block("block.copper", nextBlockID(), Material.metal),
        "block_copper_top.png",
        "block_copper_bottom.png",
        "block_copper_sides.png",
        BlockSounds.METAL,
        5.0f,
        10.0f,
        0.0f
    )

    val copperCable: Block = BlockHelper.createBlock(
        MOD_ID,
        BlockCable("cable.copper", nextBlockID(), Material.metal, 32, 0, 32, 2, ModItems.copperCable),
        "block_copper_top.png",
        "block_copper_bottom.png",
        "block_copper_sides.png",
        BlockSounds.METAL,
        1.0f,
        0.0f,
        0.0f
    ).withTags(BlockTags.NOT_IN_CREATIVE_MENU)

    val insulatedCopperCable: Block = BlockHelper.createBlock(
        MOD_ID,
        BlockCable("cable.copper", nextBlockID(), Material.cloth, 32, 0, 32, 0, ModItems.insulatedCopperCable),
        "block_copper_bottom.png",
        BlockSounds.CLOTH,
        1.0f,
        0.0f,
        0.0f
    ).withTags(BlockTags.NOT_IN_CREATIVE_MENU)

    // Tin
    val oreTinStone: Block = BlockHelper.createBlock(
        MOD_ID,
        BlockCopperOre("ore.tin.stone", nextBlockID(), Material.stone),
        "ore_tin_stone.png",
        BlockSounds.STONE,
        3.0f,
        5.0f,
        0.0f
    ).withTags(BlockTags.MINEABLE_BY_PICKAXE)

    val oreTinBasalt: Block = BlockHelper.createBlock(
        MOD_ID,
        BlockCopperOre("ore.tin.basalt", nextBlockID(), Material.stone),
        "ore_tin_basalt.png",
        BlockSounds.STONE,
        3.0f,
        5.0f,
        0.0f
    ).withTags(BlockTags.MINEABLE_BY_PICKAXE)

    val oreTinLimestone: Block = BlockHelper.createBlock(
        MOD_ID,
        BlockCopperOre("ore.tin.limestone", nextBlockID(), Material.stone),
        "ore_tin_limestone.png",
        BlockSounds.STONE,
        3.0f,
        5.0f,
        0.0f
    ).withTags(BlockTags.MINEABLE_BY_PICKAXE)

    val oreTinGranite: Block = BlockHelper.createBlock(
        MOD_ID,
        BlockCopperOre("ore.tin.granite", nextBlockID(), Material.stone),
        "ore_tin_granite.png",
        BlockSounds.STONE,
        3.0f,
        5.0f,
        0.0f
    ).withTags(BlockTags.MINEABLE_BY_PICKAXE)

    val tinBlock: Block = BlockHelper.createBlock(
        MOD_ID,
        Block("block.tin", nextBlockID(), Material.metal),
        "block_tin_top.png",
        "block_tin_bottom.png",
        "block_tin_sides.png",
        BlockSounds.METAL,
        5.0f,
        10.0f,
        0.0f
    )

    val tinCable: Block = BlockHelper.createBlock(
        MOD_ID,
        BlockCable("cable.tin", nextBlockID(), Material.metal, 32, 0, 16, 0, ModItems.tinCable),
        "block_tin_top.png",
        "block_tin_bottom.png",
        "block_tin_sides.png",
        BlockSounds.METAL,
        1.0f,
        0.0f,
        0.0f
    ).withTags(BlockTags.NOT_IN_CREATIVE_MENU)

    val insulatedTinCable: Block = BlockHelper.createBlock(
        MOD_ID,
        BlockCable("cable.tin", nextBlockID(), Material.cloth, 32, 0, 16, 0, ModItems.insulatedTinCable),
        "block_tin_top.png",
        "block_tin_bottom.png",
        "insulated_cable_sides.png",
        BlockSounds.CLOTH,
        1.0f,
        0.0f,
        0.0f
    ).withTags(BlockTags.NOT_IN_CREATIVE_MENU)

    // Bronze
    val bronzeBlock: Block = BlockHelper.createBlock(
        MOD_ID,
        Block("block.bronze", nextBlockID(), Material.metal),
        "block_bronze_top.png",
        "block_bronze_bottom.png",
        "block_bronze_sides.png",
        BlockSounds.METAL,
        5.0f,
        10.0f,
        0.0f
    )

    // Vanilla
    val goldCable: Block = BlockHelper.createBlock(
        MOD_ID,
        BlockCable("cable.gold", nextBlockID(), Material.metal, 512, 0, 512, 6, ModItems.goldCable),
        "block_gold_top.png",
        "block_gold_bottom.png",
        "block_gold_sides.png",
        BlockSounds.METAL,
        1.0f,
        0.0f,
        0.0f
    ).withTags(BlockTags.NOT_IN_CREATIVE_MENU)

    val insulatedGoldCable: Block = BlockHelper.createBlock(
        MOD_ID,
        BlockCable("cable.gold", nextBlockID(), Material.cloth, 512, 0, 512, 6, ModItems.insulatedGoldCable),
        "block_gold_top.png",
        "block_gold_bottom.png",
        "insulated_cable_sides.png",
        BlockSounds.CLOTH,
        1.0f,
        0.0f,
        0.0f
    ).withTags(BlockTags.NOT_IN_CREATIVE_MENU)

    val steelCable: Block = BlockHelper.createBlock(
        MOD_ID,
        BlockCable("cable.steel", nextBlockID(), Material.metal, 1024, 0, 1024, 8, ModItems.steelCable),
        "block_steel_top.png",
        "block_steel_bottom.png",
        "block_steel_sides.png",
        BlockSounds.METAL,
        1.0f,
        0.0f,
        0.0f
    ).withTags(BlockTags.NOT_IN_CREATIVE_MENU)

    val insulatedSteelCable: Block = BlockHelper.createBlock(
        MOD_ID,
        BlockCable("cable.steel", nextBlockID(), Material.cloth, 1024, 0, 1024, 0, ModItems.insulatedSteelCable),
        "block_steel_top.png",
        "block_steel_bottom.png",
        "insulated_cable_sides.png",
        BlockSounds.CLOTH,
        1.0f,
        0.0f,
        0.0f
    ).withTags(BlockTags.NOT_IN_CREATIVE_MENU)

    // Miscellaneous


    // Machines
}
