package turniplabs.industry.blocks.entities.solar

import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import turniplabs.industry.IndustryConfig

class TileEntitySolarSHV : TileEntitySolarBase(256) {

    init {
        setCapacity(32768)
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.superHighVoltage"))
        setMaxReceive(0)

        for (dir: Direction in Direction.values())
            setConnection(dir, Connection.OUTPUT)
    }

    override fun getInvName(): String {
        return "SHVSolarArray"
    }
}