package turniplabs.industry.blocks.entities

import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction

class TileEntityBatboxLV() : TileEntityBatboxBase() {

    init {
        this.setCapacity(8192)
        this.setTransfer(16)
        this.setMaxReceive(16)

        for (dir in Direction.values())
            this.setConnection(dir, Connection.OUTPUT)

        this.setConnection(Direction.Y_POS, Connection.INPUT)
    }

    override fun getInvName(): String {
        return "BatboxLV"
    }
}