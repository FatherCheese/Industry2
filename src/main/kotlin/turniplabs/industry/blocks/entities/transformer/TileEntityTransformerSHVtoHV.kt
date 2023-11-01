package turniplabs.industry.blocks.entities.transformer

import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import turniplabs.industry.IndustryConfig
import turniplabs.industry.blocks.entities.TileEntityEnergyConductorDamageable

class TileEntityTransformerSHVtoHV : TileEntityEnergyConductorDamageable() {

    init {
        this.setCapacity(16384)
        this.setTransfer(IndustryConfig.cfg.getInt("Energy Values.highVoltage"))
        this.setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.superHighVoltage"))

        setConnection(Direction.Y_POS, Connection.INPUT)
        setConnection(Direction.Y_NEG, Connection.OUTPUT)
    }
}