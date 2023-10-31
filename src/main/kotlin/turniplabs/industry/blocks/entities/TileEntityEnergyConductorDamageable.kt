package turniplabs.industry.blocks.entities

import com.mojang.nbt.CompoundTag
import sunsetsatellite.energyapi.impl.TileEntityEnergyConductor
import sunsetsatellite.sunsetutils.util.Connection
import turniplabs.industry.Interfaces.IMachineCondition
import kotlin.math.min

open class TileEntityEnergyConductorDamageable : TileEntityEnergyConductor(), IMachineCondition {

    /*                             /
    / Energy Ratings:              /
    / 16RC = Low voltage           /
    / 32RC = Medium voltage       /
    / 512RC = High voltage         /
    / 1024RC = Super High voltage */

    val random = java.util.Random()

    private val maxMachineHealth = 100
    private val healAmount = 1
    private var machineHealth: Int = maxMachineHealth
    private var hasBeenDamagedInLastTick = false

    override val getMachineHealth: Int
        get() = machineHealth

    override fun setMachineHealth(newHealth: Int) {
        machineHealth = newHealth
    }

    override fun receive(dir: sunsetsatellite.sunsetutils.util.Direction?, amount: Int, test: Boolean): Int {
        if (canConnect(dir, Connection.INPUT)) {
            if (amount > maxReceive && random.nextInt(4) == 0) {
                machineHealth -= amount - maxReceive
                hasBeenDamagedInLastTick = true
            }

            val received: Int = min(capacity - energy, min(maxReceive, amount))
            if (!test)
                energy += received

            return received
        }

        return 0
    }

    override fun updateEntity() {
        super.updateEntity()

        // Destroy itself on 1 health
        if (machineHealth < 1)
            worldObj.createExplosion(null, xCoord.toDouble(), yCoord.toDouble(), zCoord.toDouble(), 1.0f)

        if (machineHealth < maxMachineHealth) {
            val x = xCoord.toDouble() + random.nextDouble()
            val y = yCoord.toDouble() + random.nextDouble()
            val z = zCoord.toDouble() + random.nextDouble()

            worldObj.spawnParticle("smoke", x, y + 0.22, z, 0.0, 0.0, 0.0)
            worldObj.spawnParticle("flame", x, y + 0.22, z, 0.0, 0.0, 0.0)
        }

        // Heal itself over time
        if (!hasBeenDamagedInLastTick && random.nextInt(4) == 0 && machineHealth + healAmount <= maxMachineHealth) {
            machineHealth += healAmount
        }

        hasBeenDamagedInLastTick = false
    }

    override fun readFromNBT(compoundTag: CompoundTag?) {
        super.readFromNBT(compoundTag)
        machineHealth = compoundTag!!.getShort("Health").toInt()
    }

    override fun writeToNBT(compoundTag: CompoundTag?) {
        super.writeToNBT(compoundTag)
        compoundTag?.putShort("Health", machineHealth.toShort())
    }
}