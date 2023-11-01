package turniplabs.industry.blocks.entities.batbox

import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import turniplabs.industry.IndustryConfig

class TileEntityBatboxLV : TileEntityBatboxBase() {

    init {
        this.setCapacity(8192)
        this.setTransfer(IndustryConfig.cfg.getInt("Energy Values.lowVoltage"))
        this.setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.lowVoltage"))
    }

    override fun getInvName(): String {
        return "BatboxLV"
    }
}