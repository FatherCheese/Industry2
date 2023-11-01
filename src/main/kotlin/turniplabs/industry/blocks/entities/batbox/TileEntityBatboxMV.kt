package turniplabs.industry.blocks.entities.batbox

import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import turniplabs.industry.IndustryConfig

class TileEntityBatboxMV() : TileEntityBatboxBase() {

    init {
        this.setCapacity(32768)
        this.setTransfer(IndustryConfig.cfg.getInt("Energy Values.mediumVoltage"))
        this.setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.mediumVoltage"))

        for (dir in Direction.values())
            this.setConnection(dir, Connection.OUTPUT)

        this.setConnection(Direction.Y_POS, Connection.INPUT)
    }

    override fun getInvName(): String {
        return "BatboxMV"
    }
}