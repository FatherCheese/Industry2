package baboon.industry;

import baboon.industry.block.IndustryBlocks;
import baboon.industry.item.IndustryItems;
import baboon.industry.recipe.IndustryRecipes;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.SoundHelper;

public class Industry2 implements ModInitializer {

    // TODO LIST
    // Look into custom damage types
    // Fix inconsistencies
    // Look into damageable crafting

    public static String MOD_ID = "industry";
    public static Logger logger = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

        // DO NOT TOUCH THIS! It's an error prevention method. Thanks Useless!
        try {
            Class.forName("net.minecraft.core.block.Block");
            Class.forName("net.minecraft.core.item.Item");
        } catch (ClassNotFoundException ignored) {}

        SoundHelper.addSound(MOD_ID, "zap.wav");

        new IndustryConfig();

        new IndustryBlocks().initializeBlocks();
        new IndustryItems().initializeItems();
        new IndustryRecipes().initializeRecipes();

        logger.info("Industry2 has been initialized. Have fun automating!");
    }
}
