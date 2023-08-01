package turniplabs.examplemod

import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ExampleMod: ModInitializer {

	companion object {
		val MOD_ID: String = "examplemod"
		val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID);
	}
	override fun onInitialize() {
		LOGGER.info("ExampleMod initialized from Kotlin.")
	}
}