package baboon.industry.item.battery;

import baboon.industry.IndustryConfig;

public class ItemBatteryLapis extends ItemBatteryBase {

    public ItemBatteryLapis(int i) {
        super(i,
                IndustryConfig.cfg.getInt("Energy Values.ehvBatteryStorage"),
                IndustryConfig.cfg.getInt("Energy Values.ehvIO"),
                IndustryConfig.cfg.getInt("Energy Values.ehvIO"),
                "battery_lapis_4.png",
                "battery_lapis_3.png",
                "battery_lapis_2.png",
                "battery_lapis_1.png",
                "battery_lapis_0.png");
    }
}
