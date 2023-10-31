package turniplabs.industry.gui.plugins

import org.slf4j.Logger
import toufoumaster.btwaila.BTWaila
import toufoumaster.btwaila.BTWailaCustomTootltipPlugin

class WailaPlugin : BTWailaCustomTootltipPlugin {

    override fun initializePlugin(logger: Logger?) {
        BTWaila.LOGGER.info("Adding tooltips for Industry2.")

        GeneratorTooltip().addTooltip()
        EnergyConductorTooltip().addTooltip()
    }
}