package turniplabs.industry.items

import turniplabs.industry.IndustryConfig

class ItemBatteryCrystal(
    i: Int,
) : ItemBatteryBase(
    i,
    16384,
    IndustryConfig.cfg.getInt("Energy Values.highVoltage"),
    IndustryConfig.cfg.getInt("Energy Values.highVoltage"),
    "battery_crystal_4.png",
    "battery_crystal_3.png",
    "battery_crystal_2.png",
    "battery_crystal_1.png",
    "battery_crystal_0.png"
)