package turniplabs.industry.blocks.entities

import net.minecraft.core.entity.EntityLiving
import net.minecraft.core.util.helper.DamageType
import net.minecraft.core.util.phys.AABB
import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import javax.swing.text.html.parser.Entity

class TileEntityCable(capacity: Int, energy: Int, transfer: Int, private var dangerLevel: Int) :
    TileEntityEnergyConductorDamageable(){

    init {
        setCapacity(capacity)
        setEnergy(energy)
        setTransfer(transfer)

        for (dir in Direction.values())
            setConnection(dir, Connection.BOTH)
    }

    fun setDangerLevel(dangerLevel: Int) {
        this.dangerLevel = dangerLevel
    }

    override fun updateEntity() {
        if (dangerLevel > 0 && energy >= dangerLevel * 10) {
            val list = worldObj.getEntitiesWithinAABB(
                Entity::class.java, AABB.getBoundingBox(
                    xCoord.toDouble() - 0.55,
                    yCoord.toDouble() - 0.55,
                    zCoord.toDouble() - 0.55,
                    xCoord.toDouble() + 1.55,
                    yCoord.toDouble() + 1.55,
                    zCoord.toDouble() + 1.55
                )
            )

            for (entity in list) {
                if (entity is EntityLiving) {
                    val prevHealth = entity.health
                    entity.hurt(null, dangerLevel, DamageType.BLAST)

                    // Only generate particles if entity actually took damage
                    if (prevHealth != entity.health) {
                        for (run in 0 until random.nextInt(10)) {
                            val x = random.nextDouble() + entity.x
                            val y = -1 + random.nextDouble() +random.nextInt(2) + entity.y
                            val z = random.nextDouble() + entity.z
                            worldObj.spawnParticle("flame", x, y, z, 0.0, 0.0, 0.0)
                        }
                        energy -= dangerLevel * 10
                    }
                }
            }
        }
        super.updateEntity()
    }
}