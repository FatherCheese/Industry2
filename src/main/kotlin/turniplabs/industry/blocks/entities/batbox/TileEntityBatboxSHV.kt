package turniplabs.industry.blocks.entities.batbox

import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import turniplabs.industry.IndustryConfig

class TileEntityBatboxSHV : TileEntityBatboxBase() {

    init {
        this.setCapacity(131072)
        this.setTransfer(IndustryConfig.cfg.getInt("Energy Values.superHighVoltage"))
        this.setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.superHighVoltage"))
    }

    override fun getInvName(): String {
        return "BatboxSHV"
    }
}