package baboon.industry;

import turniplabs.halplibe.util.ConfigUpdater;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

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

        cfg = new TomlConfigHandler(updater, Industry2.MOD_ID, properties);
    }
}
