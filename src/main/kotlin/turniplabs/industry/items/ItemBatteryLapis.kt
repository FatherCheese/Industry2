package turniplabs.industry.items

import turniplabs.industry.IndustryConfig

class ItemBatteryLapis(
    i: Int,
) : ItemBatteryBase(
    i,
    32768,
    IndustryConfig.cfg.getInt("Energy Values.superHighVoltage"),
    IndustryConfig.cfg.getInt("Energy Values.superHighVoltage"),
    "battery_lapis_4.png",
    "battery_lapis_3.png",
    "battery_lapis_2.png",
    "battery_lapis_1.png",
    "battery_lapis_0.png"
)