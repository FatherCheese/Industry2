package turniplabs.industry

import net.fabricmc.api.ModInitializer
import net.minecraft.core.item.Item
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import turniplabs.industry.blocks.IndustryBlocks
import turniplabs.industry.items.IndustryItems
import turniplabs.industry.recipes.IndustryRecipes

class Industry2: ModInitializer {

	/*
	TODO : More Machines
	TODO : Log resin
	TODO : Make machines drop inventory
	TODO : Make the textures more consistent
	TODO : Lang file descriptions
	TODO : Mod Support
	TODO : Reactor Stuff
	TODO : Configs (IDs, world gen, .etc)
	TODO : MP Support
	*/

	companion object {
		const val MOD_ID: String = "industry"
		val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)
	}

	override fun onInitialize() {

		// Do not touch this! It's a bug fix for class loading issues. Thanks Useless!
		try {
			Class.forName("net.minecraft.core.block.Block")
			Class.forName("net.minecraft.core.item.Item")
		} catch (ignored: ClassNotFoundException) {}

		LOGGER.info("Industry2 has been initialized. Have fun automating!")

		IndustryItems.initializeItems()
		IndustryBlocks.initializeBlocks()
		IndustryRecipes.initializeRecipes()

		Item.ammoSnowball.withTags(IndustryTags.PREVENT_ITEM_RECYCLING)
		Item.flint.withTags(IndustryTags.PREVENT_ITEM_RECYCLING)
		Item.seedsWheat.withTags(IndustryTags.PREVENT_ITEM_RECYCLING)
		Item.stick.withTags(IndustryTags.PREVENT_ITEM_RECYCLING)
	}
}
