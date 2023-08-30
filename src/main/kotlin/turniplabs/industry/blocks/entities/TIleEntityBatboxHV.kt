package turniplabs.industry.blocks.entities

import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction

class TIleEntityBatboxHV() : TileEntityBatboxBase() {

    init {
        this.setCapacity(65536)
        this.setTransfer(512)
        this.setMaxReceive(512)

        for (dir in Direction.values())
            this.setConnection(dir, Connection.OUTPUT)

        this.setConnection(Direction.Y_POS, Connection.INPUT)
    }

    override fun getInvName(): String {
        return "BatboxHV"
    }
}