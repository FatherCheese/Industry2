package baboon.industry;

import baboon.industry.block.IndustryBlocks;
import baboon.industry.item.IndustryItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.AchievementHelper;
import turniplabs.halplibe.helper.SoundHelper;
import turniplabs.halplibe.util.ClientStartEntrypoint;
import turniplabs.halplibe.util.GameStartEntrypoint;

public class Industry2 implements GameStartEntrypoint, ClientStartEntrypoint {

    // TODO LIST
    // Fix MP GUI issues

    public static String MOD_ID = "industry";
    public static Logger logger = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void beforeGameStart() {
        new IndustryBlocks().initializeBlocks();
        new IndustryItems().initializeItems();
        AchievementHelper.addPage(new IndustryAchievements());

        logger.info("Industry2 has been initialized. Have fun automating!");
    }

    @Override
    public void afterGameStart() {

    }

    @Override
    public void beforeClientStart() {
        SoundHelper.Client.addSound(MOD_ID, "zap.wav");
        SoundHelper.Client.addSound(MOD_ID, "laser.wav");
        SoundHelper.Client.addSound(MOD_ID, "tap.wav");
        SoundHelper.Client.addSound(MOD_ID, "alarm.wav");
    }

    @Override
    public void afterClientStart() {

    }
}
