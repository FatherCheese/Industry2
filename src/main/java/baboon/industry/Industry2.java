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
    public static String MOD_ID = "industry";
    public static Logger logger = LoggerFactory.getLogger(MOD_ID);

    // IDEAS
    // Replace 'Uranium' with 'pure' radioactive redstone?
    // Flight chestplate (i.e. gravisuite chestplate and/or jetpack)
    // Charger items/plates that you can just walk on or wear
    // Powered bow?
    // Oil and plastic? Needs more than just a use as fuel

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
