package turniplabs.industry.blocks.entities.batbox

import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import turniplabs.industry.IndustryConfig

class TileEntityBatboxSHV() : TileEntityBatboxBase() {

    init {
        this.setCapacity(131072)
        this.setTransfer(IndustryConfig.cfg.getInt("Energy Values.superHighVoltage"))
        this.setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.superHighVoltage"))

        for (dir in Direction.values())
            this.setConnection(dir, Connection.OUTPUT)

        this.setConnection(Direction.Y_POS, Connection.INPUT)
    }

    override fun getInvName(): String {
        return "BatboxSHV"
    }
}