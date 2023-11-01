package turniplabs.industry.blocks.entities.solar

import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import turniplabs.industry.IndustryConfig

class TileEntitySolarMV : TileEntitySolarBase(16){

    init {
        setCapacity(8192)
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.mediumVoltage"))
        setMaxReceive(0)

        for (dir: Direction in Direction.values())
            setConnection(dir, Connection.OUTPUT)
    }

    override fun getInvName(): String {
        return "MVSolarArray"
    }
}