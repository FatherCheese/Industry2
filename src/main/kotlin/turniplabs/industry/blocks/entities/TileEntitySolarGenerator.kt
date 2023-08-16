package turniplabs.industry.blocks.entities

import net.minecraft.core.block.Block
import net.minecraft.core.world.season.Seasons
import sunsetsatellite.energyapi.impl.ItemEnergyContainer
import sunsetsatellite.energyapi.template.tiles.TileEntityBatteryBox
import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import kotlin.math.min

class TileEntitySolarGenerator: TileEntityBatteryBox() {
    var generatedEnergy = 0

    init {
        setCapacity(1024)
        setTransfer(1)
        setMaxReceive(0)

        for (dir: Direction in Direction.values())
            setConnection(dir, Connection.OUTPUT)
    }

    override fun getInvName(): String {
        return "SolarGenerator"
    }

    override fun updateEntity() {
        super.updateEntity()
        if (energy < capacity && isFacingSky()) {
            generatedEnergy = 4

            generatedEnergy -= worldObj.skyDarken

            if (generatedEnergy > 0)
                energy = min(energy + generatedEnergy, capacity)

            if (worldObj.getBlockTemperature(xCoord, zCoord) > 0.85f && worldObj.getBlockHumidity(xCoord, zCoord) < 0.30f)
                generatedEnergy += 2

            if (worldObj.getBlockTemperature(xCoord, zCoord) < 0.40f && worldObj.getBlockHumidity(xCoord, zCoord) < 0.30f)
                generatedEnergy -= 2

            if (yCoord > 150)
                generatedEnergy += yCoord / 100

            if (worldObj.seasonManager.currentSeason == Seasons.OVERWORLD_WINTER ||
                worldObj.seasonManager.currentSeason == Seasons.OVERWORLD_WINTER_ENDLESS ||
                worldObj.seasonManager.currentSeason == Seasons.OVERWORLD_FALL
                ) generatedEnergy -= 2
        }

        if (!isFacingSky())
            generatedEnergy = 0

        if (getStackInSlot(0) != null && getStackInSlot(0)?.item is ItemEnergyContainer) {
            provide(getStackInSlot(0), getMaxProvide(), false)
            onInventoryChanged()
        }
    }

    fun isFacingSky(): Boolean {
        for (heightCoord in yCoord + 1..255) {
            val block = Block.getBlock(worldObj.getBlockId(xCoord, heightCoord, zCoord))
            if (block != null && block.isOpaqueCube) return false
        }
        return true
    }
}