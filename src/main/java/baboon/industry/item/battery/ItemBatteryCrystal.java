package baboon.industry.item.battery;

import baboon.industry.IndustryConfig;

public class ItemBatteryCrystal extends ItemBatteryBase {

    public ItemBatteryCrystal(int i) {
        super(i, IndustryConfig.cfg.getInt("Energy Values.hvStorage"), IndustryConfig.cfg.getInt("Energy Values.highVoltage"), IndustryConfig.cfg.getInt("Energy Values.highVoltage"), "battery_crystal_4.png", "battery_crystal_3.png", "battery_crystal_2.png", "battery_crystal_1.png", "battery_crystal_0.png");
    }
}
