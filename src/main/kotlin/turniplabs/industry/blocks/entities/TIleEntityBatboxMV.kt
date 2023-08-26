package turniplabs.industry.blocks.entities

import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction

class TIleEntityBatboxMV() : TileEntityBatboxBase() {

    init {
        this.setCapacity(16384)
        this.setTransfer(32)
        this.setMaxReceive(32)

        for (dir in Direction.values())
            this.setConnection(dir, Connection.OUTPUT)

        this.setConnection(Direction.Y_POS, Connection.INPUT)
    }

    override fun getInvName(): String {
        return "BatboxMV"
    }
}