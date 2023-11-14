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
                .addEntry("elvIO", 5)
                .addEntry("lvIO", 32)
                .addEntry("mvIO", 128)
                .addEntry("hvIO", 512)
                .addEntry("ehvIO", 2048)
                .addEntry("lvBatteryStorage", 10000)
                .addEntry("mvBatteryStorage", 100000)
                .addEntry("hvBatteryStorage", 1000000)
                .addEntry("ehvBatteryStorage", 10000000)
                .addEntry("elvMachineStorage", 100)
                .addEntry("lvMachineStorage", 1000)
                .addEntry("mvMachineStorage", 10000)
                .addEntry("ehvMachineStorage", 100000);

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
