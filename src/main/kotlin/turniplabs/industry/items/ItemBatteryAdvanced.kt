package turniplabs.industry.items

import turniplabs.industry.IndustryConfig

class ItemBatteryAdvanced(
    i: Int,
) : ItemBatteryBase(
    i,
    8192,
    IndustryConfig.cfg.getInt("Energy Values.mediumVoltage"),
    IndustryConfig.cfg.getInt("Energy Values.mediumVoltage"),
    "battery_advanced_4.png",
    "battery_advanced_3.png",
    "battery_advanced_2.png",
    "battery_advanced_1.png",
    "battery_advanced_0.png"
)