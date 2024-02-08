package baboon.industry.item.battery;

import baboon.industry.IndustryConfig;

public class ItemBatteryCrystal extends ItemBatteryBase {

    public ItemBatteryCrystal(String name, int i) {
        super(name, i,
                IndustryConfig.cfg.getInt("Energy Values.hvBatteryStorage"),
                IndustryConfig.cfg.getInt("Energy Values.hvIO"),
                IndustryConfig.cfg.getInt("Energy Values.hvIO"),
                "battery_crystal_4.png",
                "battery_crystal_3.png",
                "battery_crystal_2.png",
                "battery_crystal_1.png",
                "battery_crystal_0.png");
    }
}
