package baboon.industry.item.battery;

import baboon.industry.IndustryConfig;

public class ItemBatteryAdvanced extends ItemBatteryBase {

    public ItemBatteryAdvanced(String name, int i) {
        super(name, i,
                IndustryConfig.cfg.getInt("Energy Values.mvBatteryStorage"),
                IndustryConfig.cfg.getInt("Energy Values.mvIO"),
                IndustryConfig.cfg.getInt("Energy Values.mvIO"),
                "battery_advanced_4.png",
                "battery_advanced_3.png",
                "battery_advanced_2.png",
                "battery_advanced_1.png",
                "battery_advanced_0.png");
    }
}
