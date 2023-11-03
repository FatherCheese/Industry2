package baboon.industry.gui.plugins;

import org.slf4j.Logger;
import toufoumaster.btwaila.BTWaila;
import toufoumaster.btwaila.BTWailaCustomTootltipPlugin;

public class WailaPlugin implements BTWailaCustomTootltipPlugin {

    @Override
    public void initializePlugin(Logger logger) {
        BTWaila.LOGGER.info("Initialized tooltips for Industry2.");
    }
}
