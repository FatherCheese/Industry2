package turniplabs.industry.blocks.entities.batbox

import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import turniplabs.industry.IndustryConfig

class TileEntityBatboxMV : TileEntityBatboxBase() {

    init {
        this.setCapacity(32768)
        this.setTransfer(IndustryConfig.cfg.getInt("Energy Values.mediumVoltage"))
        this.setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.mediumVoltage"))
    }

    override fun getInvName(): String {
        return "BatboxMV"
    }
}