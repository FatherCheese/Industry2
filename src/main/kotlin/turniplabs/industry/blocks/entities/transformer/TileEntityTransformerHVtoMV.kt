package turniplabs.industry.blocks.entities.transformer

import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import turniplabs.industry.IndustryConfig
import turniplabs.industry.blocks.entities.TileEntityEnergyConductorDamageable

class TileEntityTransformerHVtoMV : TileEntityEnergyConductorDamageable() {

    init {
        this.setCapacity(8192)
        this.setTransfer(IndustryConfig.cfg.getInt("Energy Values.mediumVoltage"))
        this.setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.highVoltage"))


        setConnection(Direction.Y_POS, Connection.INPUT)
        setConnection(Direction.Y_NEG, Connection.OUTPUT)
    }
}