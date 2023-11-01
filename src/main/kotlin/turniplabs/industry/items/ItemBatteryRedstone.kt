package turniplabs.industry.items

import turniplabs.industry.IndustryConfig

class ItemBatteryRedstone(
    i: Int,
) : ItemBatteryBase(
    i,
    2048,
    IndustryConfig.cfg.getInt("Energy Values.lowVoltage"),
    IndustryConfig.cfg.getInt("Energy Values.lowVoltage"),
    "battery_redstone_4.png",
    "battery_redstone_3.png",
    "battery_redstone_2.png",
    "battery_redstone_1.png",
    "battery_redstone_0.png"
)