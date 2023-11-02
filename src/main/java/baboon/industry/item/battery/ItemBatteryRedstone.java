package baboon.industry.item.battery;

import baboon.industry.IndustryConfig;

public class ItemBatteryRedstone extends ItemBatteryBase {

    public ItemBatteryRedstone(int i) {
        super(i, IndustryConfig.cfg.getInt("Energy Values.lvStorage"), IndustryConfig.cfg.getInt("Energy Values.lowVoltage"), IndustryConfig.cfg.getInt("Energy Values.lowVoltage"), "battery_redstone_4.png", "battery_redstone_3.png", "battery_redstone_2.png", "battery_redstone_1.png", "battery_redstone_0.png");
    }
}
