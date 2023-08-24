package turniplabs.industry.blocks.entities

import net.minecraft.core.block.Block
import net.minecraft.core.world.season.Seasons
import sunsetsatellite.energyapi.impl.ItemEnergyContainer
import sunsetsatellite.energyapi.template.tiles.TileEntityBatteryBox
import kotlin.math.min

open class TileEntitySolarBase(private val voltage: Int): TileEntityBatteryBox() {
    var generatedEnergy = 0

    override fun updateEntity() {
        super.updateEntity()
        if (energy < capacity && isFacingSky()) {
            generatedEnergy = 2 * voltage

            generatedEnergy -= worldObj.skyDarken

            if (generatedEnergy > 0)
                energy = min(energy + generatedEnergy, capacity)

            if (worldObj.getBlockTemperature(xCoord, zCoord) > 0.85f && worldObj.getBlockHumidity(xCoord, zCoord) < 0.30f)
                generatedEnergy += 2 * voltage

            if (worldObj.getBlockTemperature(xCoord, zCoord) < 0.40f && worldObj.getBlockHumidity(xCoord, zCoord) < 0.30f)
                generatedEnergy -= 2 * voltage

            if (yCoord > 150)
                generatedEnergy += yCoord / 100

            if (worldObj.seasonManager != Seasons.OVERWORLD_SPRING ||
                worldObj.seasonManager != Seasons.OVERWORLD_SUMMER ||
                worldObj.seasonManager != Seasons.OVERWORLD_FALL
                )
                generatedEnergy -= 2 * voltage
        }

        if (!isFacingSky())
            generatedEnergy = 0

        if (getStackInSlot(0) != null && getStackInSlot(0)?.item is ItemEnergyContainer) {
            provide(getStackInSlot(0), getMaxProvide(), false)
            onInventoryChanged()
        }
    }

    private fun isFacingSky(): Boolean {
        for (heightCoord in yCoord + 1..255) {
            val block = Block.getBlock(worldObj.getBlockId(xCoord, heightCoord, zCoord))
            if (block != null && block.isOpaqueCube) return false
        }
        return true
    }
}