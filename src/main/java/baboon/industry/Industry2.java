package baboon.industry;

import baboon.industry.block.IndustryBlocks;
import baboon.industry.interfaces.IEntityPlayerMP;
import baboon.industry.item.IndustryItems;
import baboon.industry.recipe.IndustryRecipes;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.Container;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.server.entity.player.EntityPlayerMP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.AchievementHelper;
import turniplabs.halplibe.helper.SoundHelper;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

import java.util.ArrayList;
import java.util.HashMap;

public class Industry2 implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {

    // TODO LIST
    // Fix MP GUI issues

    public static String MOD_ID = "industry";
    public static Logger logger = LoggerFactory.getLogger(MOD_ID);
    public static HashMap<String, ArrayList<Class<?>>> nameToGuiMap = new HashMap<>();

    @Override
    public void onInitialize() {

        // DO NOT TOUCH THIS! It's an error prevention method. Thanks Useless!
        try {
            Class.forName("net.minecraft.core.block.Block");
            Class.forName("net.minecraft.core.item.Item");
        } catch (ClassNotFoundException ignored) {}

        SoundHelper.addSound(MOD_ID, "zap.wav");
        SoundHelper.addSound(MOD_ID, "laser.wav");
        SoundHelper.addSound(MOD_ID, "tap.wav");
        SoundHelper.addSound(MOD_ID, "alarm.wav");

        new IndustryConfig();

        new IndustryBlocks().initializeBlocks();
        new IndustryItems().initializeItems();
        new IndustryRecipes().initializeRecipes();
        AchievementHelper.addPage(new IndustryAchievements());

        logger.info("Industry2 has been initialized. Have fun automating!");
    }

    @Override
    public void beforeGameStart() {

    }

    @Override
    public void afterGameStart() {

    }

    @Override
    public void onRecipesReady() {

    }
}
