package turniplabs.industry.blocks.entities.solar

import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import turniplabs.industry.IndustryConfig

class TileEntitySolarHV : TileEntitySolarBase(64){

    init {
        setCapacity(16384)
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.highVoltage"))
        setMaxReceive(0)

        for (dir: Direction in Direction.values())
            setConnection(dir, Connection.OUTPUT)
    }

    override fun getInvName(): String {
        return "HVSolarArray"
    }
}