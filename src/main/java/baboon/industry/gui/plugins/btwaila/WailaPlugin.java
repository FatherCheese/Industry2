package baboon.industry.gui.plugins.btwaila;

import org.slf4j.Logger;
import toufoumaster.btwaila.BTWaila;
import toufoumaster.btwaila.BTWailaCustomTootltipPlugin;

public class WailaPlugin implements BTWailaCustomTootltipPlugin {

    @Override
    public void initializePlugin(Logger logger) {
        new TooltipCable().addTooltip();
        new TooltipGenerator().addTooltip();
        new TooltipGeneratorWatermill().addTooltip();
        new TooltipGeneratorWindmill().addTooltip();
        new TooltipGeneratorGeothermal().addTooltip();
        new TooltipSolar().addTooltip();
        new TooltipBatbox().addTooltip();
        new TooltipTransformer().addTooltip();

        new TooltipMachineBasic().addTooltip();
        new TooltipMachineCannery().addTooltip();
        new TooltipMachineAdvanced().addTooltip();

        new TooltipReactor().addTooltip();
        new TooltipFabricator().addTooltip();

        BTWaila.LOGGER.info("Adding tooltips for: Industry2");
    }
}
