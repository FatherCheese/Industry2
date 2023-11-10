package baboon.industry;

import baboon.industry.block.IndustryBlocks;
import baboon.industry.item.IndustryItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.util.ConfigUpdater;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IndustryConfig {
    public static TomlConfigHandler cfg;

    public IndustryConfig() {
        Toml properties = new Toml("Industry2's TOML Config");
        ConfigUpdater updater = ConfigUpdater.fromProperties();

        properties.addCategory("Energy Values")
                .addEntry("extraLowVoltage", 2)
                .addEntry("lowVoltage", 16)
                .addEntry("mediumVoltage", 32)
                .addEntry("highVoltage", 256)
                .addEntry("extraHighVoltage", 512)
                .addEntry("elvStorage", 128)
                .addEntry("lvStorage", 1024)
                .addEntry("mvStorage", 4096)
                .addEntry("hvStorage", 16384)
                .addEntry("ehvStorage", 65536);

        properties.addCategory("World Gen")
                .addEntry("copperOre", true)
                .addEntry("tinOre", true)
                .addEntry("uraniumOre", true)
                .addEntry("treeRubberwood", true);

        int blockIDs = 1000;
        int itemIDs = 17000;

        properties.addCategory("Block IDs");
        properties.addCategory("Item IDs");

        List<Field> blockFields = Arrays.stream(IndustryBlocks.class.getDeclaredFields()).filter((F)-> Block.class.isAssignableFrom(F.getType())).collect(Collectors.toList());
        for (Field blockField : blockFields) {
            properties.addEntry("Block IDs." + blockField.getName(), blockIDs++);
        }
        List<Field> itemFields = Arrays.stream(IndustryItems.class.getDeclaredFields()).filter((F)-> Item.class.isAssignableFrom(F.getType())).collect(Collectors.toList());
        for (Field itemField : itemFields) {
            properties.addEntry("Item IDs." + itemField.getName(), itemIDs++);
        }

        cfg = new TomlConfigHandler(updater, Industry2.MOD_ID, properties);
    }
}
