package turniplabs.industry.blocks.entities.solar

import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import turniplabs.industry.blocks.entities.solar.TileEntitySolarBase

class TileEntitySolarGenerator : TileEntitySolarBase(1) {

    init {
        setCapacity(1024)
        setTransfer(2)
        setMaxReceive(0)

        for (dir: Direction in Direction.values())
            setConnection(dir, Connection.OUTPUT)
    }

    override fun getInvName(): String {
        return "SolarGenerator"
    }
}