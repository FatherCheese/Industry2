package turniplabs.industry.blocks.entities

import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction

class TileEntityCable(capacity: Int, energy: Int, transfer: Int, var cableDangerLevel: Int) :
    TileEntityEnergyConductorDamageable() {

    init {
        setCapacity(capacity)
        setEnergy(energy)
        setTransfer(transfer)

        for (dir in Direction.values())
            setConnection(dir, Connection.BOTH)
    }
}