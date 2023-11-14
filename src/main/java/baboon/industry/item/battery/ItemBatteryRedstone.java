package baboon.industry.item.battery;

import baboon.industry.IndustryConfig;

public class ItemBatteryRedstone extends ItemBatteryBase {

    public ItemBatteryRedstone(int i) {
        super(i,
                IndustryConfig.cfg.getInt("Energy Values.lvBatteryStorage"),
                IndustryConfig.cfg.getInt("Energy Values.lvIO"),
                IndustryConfig.cfg.getInt("Energy Values.lvIO"),
                "battery_redstone_4.png",
                "battery_redstone_3.png",
                "battery_redstone_2.png",
                "battery_redstone_1.png",
                "battery_redstone_0.png");
    }
}
