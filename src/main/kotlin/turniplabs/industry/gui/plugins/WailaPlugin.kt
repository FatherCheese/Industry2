package turniplabs.industry.gui.plugins

import org.slf4j.Logger
import toufoumaster.btwaila.BTWaila
import toufoumaster.btwaila.BTWailaCustomTootltipPlugin
import turniplabs.industry.gui.plugins.tooltips.*

class WailaPlugin : BTWailaCustomTootltipPlugin {

    override fun initializePlugin(logger: Logger?) {
        BTWaila.LOGGER.info("Adding tooltips for Industry2.")

        CableTooltip.addTooltip()
        GeneratorTooltip.addTooltip()
        WatermillTooltip.addTooltip()
        WindmillTooltip.addTooltip()
        GeothermalGeneratorTooltip.addTooltip()
        SolarTooltip.addTooltip()
        BatboxTooltip.addTooltip()
        ElectricFurnaceTooltip.addTooltip()
        MaceratorTooltip.addTooltip()
        CompressorTooltip.addTooltip()
        CutterTooltip.addTooltip()
        ExtractorTooltip.addTooltip()
        RecyclerTooltip.addTooltip()
        CanneryTooltip.addTooltip()
        MaceratorRotaryTooltip.addTooltip()
        CompressorSingularityTooltip.addTooltip()
        LaserCutterTooltip.addTooltip()
    }
}