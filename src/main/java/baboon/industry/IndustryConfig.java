package baboon.industry;

import baboon.industry.block.I2Blocks;
import baboon.industry.item.I2Items;
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
    public static ConfigUpdater updater = ConfigUpdater.fromProperties();
    private static final Toml properties = new Toml("Industry2's TOML Config");
    public static TomlConfigHandler cfg;
    static {
        properties.addCategory("Industry2")
                .addEntry("cfgVersion", 5);

        properties.addCategory("Energy Values")
                .addEntry("elvIO", 4)
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


        properties.addCategory("Block IDs");
        properties.addEntry("Block IDs.startingID", 1000);
        properties.addCategory("Item IDs");
        properties.addEntry("Item IDs.startingID", 17000);

        cfg = new TomlConfigHandler(updater, Industry2.MOD_ID, properties);

        int blockIDs = cfg.getInt("Block IDs.startingID");
        int itemIDs = cfg.getInt("Item IDs.startingID");

        List<Field> blockFields = Arrays.stream(I2Blocks.class.getDeclaredFields()).filter((F)-> Block.class.isAssignableFrom(F.getType())).collect(Collectors.toList());
        for (Field blockField : blockFields) {
            properties.addEntry("Block IDs." + blockField.getName(), blockIDs++);
        }
        List<Field> itemFields = Arrays.stream(I2Items.class.getDeclaredFields()).filter((F)-> Item.class.isAssignableFrom(F.getType())).collect(Collectors.toList());
        for (Field itemField : itemFields) {
            properties.addEntry("Item IDs." + itemField.getName(), itemIDs++);
        }

        cfg = new TomlConfigHandler(updater, Industry2.MOD_ID, properties);
    }
}
