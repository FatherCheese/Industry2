package turniplabs.industry.blocks.entities.transformer

import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import turniplabs.industry.IndustryConfig
import turniplabs.industry.blocks.entities.TileEntityEnergyConductorDamageable

class TileEntityTransformerMVtoLV : TileEntityEnergyConductorDamageable() {

    init {
        this.setCapacity(2048)
        this.setTransfer(IndustryConfig.cfg.getInt("Energy Values.lowVoltage"))
        this.setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.mediumVoltage"))

        setConnection(Direction.Y_POS, Connection.INPUT)
        setConnection(Direction.Y_NEG, Connection.OUTPUT)
    }
}