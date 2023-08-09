package turniplabs.industry

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import turniplabs.industry.blocks.ModBlocks
import turniplabs.industry.items.ModItems

object Industry2 {
	val MOD_ID: String = "industry"
	val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)
}

@Suppress("unused")
fun init() {
	Industry2.LOGGER.info("Hello Fabric world!")

	ModBlocks
	ModItems
}