package turniplabs.industry.blocks.entities

import sunsetsatellite.energyapi.impl.TileEntityEnergyConductor
import sunsetsatellite.sunsetutils.util.Connection
import turniplabs.industry.Interfaces.IMachineCondition
import kotlin.math.min

open class TileEntityEnergyConductorDamageable : TileEntityEnergyConductor(), IMachineCondition {

    /*                             /
    / Energy Ratings:              /
    / 32RC = Low voltage           /
    / 256RC = Medium voltage       /
    / 512RC = High voltage         /
    / 1024RC = Super High voltage */

    val random = java.util.Random()

    private var maxMachineHealth: Int = 100
    private var healAmount: Int = 1
    private var machineHealth: Int = maxMachineHealth
    private var hasBeenDamagedInLastTick: Boolean = false

    override val getMachineHealth: Int
        get() = machineHealth

    override fun setMachineHealth(newHealth: Int) {
        machineHealth = newHealth
    }

    override fun receive(dir: sunsetsatellite.sunsetutils.util.Direction?, amount: Int, test: Boolean): Int {
        if (canConnect(dir, Connection.INPUT)) {
            val received: Int = min(capacity - energy, min(maxReceive, amount))

            if (amount > maxReceive && random.nextInt(4) == 0) {
                machineHealth -= amount - maxReceive
                hasBeenDamagedInLastTick = true
            }
            if (!test) energy += received
            return received
        }
        return 0
    }

    override fun updateEntity() {
        super.updateEntity()

        // Destroy itself on 1 health
        if (machineHealth < 1) {
            worldObj.createExplosion(null, xCoord.toDouble(), yCoord.toDouble(), zCoord.toDouble(), 0.25f)
        }

        if (machineHealth < maxMachineHealth) {
            for (run in 0 until random.nextInt(6)) {
                val x = xCoord.toDouble() * random.nextDouble()
                val y = yCoord.toDouble() * random.nextDouble()
                val z = zCoord.toDouble() * random.nextDouble()

                worldObj.spawnParticle("smoke", x, y, z, 0.0, 0.0, 0.0)
                worldObj.spawnParticle("flame", x, y, z, 0.0, 0.0, 0.0)
            }

            // Heal itself over time
            if (!hasBeenDamagedInLastTick && random.nextInt(4) == 0) machineHealth += healAmount
            hasBeenDamagedInLastTick = false
        }
    }
}