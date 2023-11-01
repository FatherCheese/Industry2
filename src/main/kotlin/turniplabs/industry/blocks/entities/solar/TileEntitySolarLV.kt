package turniplabs.industry.blocks.entities.solar

import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import turniplabs.industry.IndustryConfig

class TileEntitySolarLV : TileEntitySolarBase(4) {

    init {
        setCapacity(4096)
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.lowVoltage"))
        setMaxReceive(0)

        for (dir: Direction in Direction.values())
            setConnection(dir, Connection.OUTPUT)
    }

    override fun getInvName(): String {
        return "LVSolarArray"
    }
}