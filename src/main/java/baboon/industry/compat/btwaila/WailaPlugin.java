package baboon.industry.compat.btwaila;

import org.slf4j.Logger;
import toufoumaster.btwaila.entryplugins.waila.BTWailaCustomTooltipPlugin;
import toufoumaster.btwaila.tooltips.TooltipRegistry;

public class WailaPlugin implements BTWailaCustomTooltipPlugin {
    @Override
    public void initializePlugin(TooltipRegistry tooltipRegistry, Logger logger) {
        logger.info("Adding tooltips for: Industry2");
    }
}
