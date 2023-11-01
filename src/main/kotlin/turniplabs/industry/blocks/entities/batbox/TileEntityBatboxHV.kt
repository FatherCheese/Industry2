package turniplabs.industry.blocks.entities.batbox

import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import turniplabs.industry.Industry2
import turniplabs.industry.IndustryConfig

class TileEntityBatboxHV() : TileEntityBatboxBase() {

    init {
        this.setCapacity(65536)
        this.setTransfer(IndustryConfig.cfg.getInt("Energy Values.highVoltage"))
        this.setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.highVoltage"))

        for (dir in Direction.values())
            this.setConnection(dir, Connection.OUTPUT)

        this.setConnection(Direction.Y_POS, Connection.INPUT)
    }

    override fun getInvName(): String {
        return "BatboxHV"
    }
}