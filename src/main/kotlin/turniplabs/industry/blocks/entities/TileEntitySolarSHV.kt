package turniplabs.industry.blocks.entities

import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction

class TileEntitySolarSHV : TileEntitySolarBase(256) {

    init {
        setCapacity(40000000)
        setTransfer(1024)
        setMaxReceive(0)

        for (dir: Direction in Direction.values())
            setConnection(dir, Connection.OUTPUT)
    }

    override fun getInvName(): String {
        return "SHVSolarArray"
    }
}