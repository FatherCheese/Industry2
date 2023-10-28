package turniplabs.industry.blocks.entities.batbox

import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction

class TileEntityBatboxSHV() : TileEntityBatboxBase() {

    init {
        this.setCapacity(131072)
        this.setTransfer(1024)
        this.setMaxReceive(1024)

        for (dir in Direction.values())
            this.setConnection(dir, Connection.OUTPUT)

        this.setConnection(Direction.Y_POS, Connection.INPUT)
    }

    override fun getInvName(): String {
        return "BatboxSHV"
    }
}