package turniplabs.industry.blocks.entities.batbox

import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import turniplabs.industry.IndustryConfig

class TileEntityBatboxLV() : TileEntityBatboxBase() {

    init {
        this.setCapacity(8192)
        this.setTransfer(IndustryConfig.cfg.getInt("Energy Values.lowVoltage"))
        this.setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.lowVoltage"))

        for (dir in Direction.values())
            this.setConnection(dir, Connection.OUTPUT)

        this.setConnection(Direction.Y_POS, Connection.INPUT)
    }

    override fun getInvName(): String {
        return "BatboxLV"
    }
}