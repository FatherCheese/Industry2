package turniplabs.industry.blocks.entities

import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.item.ItemStack
import net.minecraft.core.player.inventory.IInventory
import net.minecraft.core.world.Dimension
import net.minecraft.core.world.type.WorldTypes
import net.minecraft.core.world.weather.Weather
import sunsetsatellite.energyapi.impl.ItemEnergyContainer
import sunsetsatellite.energyapi.impl.TileEntityEnergyConductor
import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import kotlin.math.min

class TileEntityWindmill : TileEntityEnergyConductor(), IInventory {
    private var contents: Array<ItemStack?> = arrayOfNulls(2)
    var generatedEnergy = 0

    init {
        setCapacity(1024)
        setTransfer(16)
        setMaxReceive(16)

        for (dir in Direction.values())
            setConnection(dir, Connection.OUTPUT)
    }

    override fun getSizeInventory(): Int {
        return contents.size
    }

    override fun getStackInSlot(i: Int): ItemStack? {
        return contents[i]
    }

    override fun decrStackSize(i: Int, j: Int): ItemStack? {
        return if (contents[i] != null) {
            if (contents[i]!!.stackSize <= j) {
                val itemStack: ItemStack? = contents[i]

                contents[i] = null
                onInventoryChanged()
                return itemStack!!
            }
            val itemStack: ItemStack = contents[i]!!.splitStack(j)

            if (contents[i]!!.stackSize == 0) {
                contents[i] = null
            }

            onInventoryChanged()
            itemStack
        } else
            return null
    }

    override fun setInventorySlotContents(i: Int, itemStack: ItemStack?) {
        contents[i] = itemStack
        if (itemStack != null && itemStack.stackSize > inventoryStackLimit)
            itemStack.stackSize = inventoryStackLimit

        onInventoryChanged()
    }

    override fun getInvName(): String {
        return "Windmill"
    }

    override fun getInventoryStackLimit(): Int {
        return 64
    }

    override fun canInteractWith(entityPlayer: EntityPlayer?): Boolean {
        if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this)
            return false

        return entityPlayer!!.distanceToSqr(
            (xCoord + 0.5f).toDouble(),
            (yCoord + 0.5f).toDouble(),
            (zCoord + 0.5f).toDouble()
        ) <= 64.0f
    }

    override fun updateEntity() {
        super.updateEntity()

        if (getStackInSlot(0) != null && getStackInSlot(0)?.item is ItemEnergyContainer) {
            provide(getStackInSlot(0), getMaxProvide(), false)
            onInventoryChanged()
        }

        if (getStackInSlot(1) != null && getStackInSlot(1)?.item is ItemEnergyContainer) {
            receive(getStackInSlot(1), getMaxReceive(), false)
            onInventoryChanged()
        }

        if (energy < capacity) {

            if (yCoord > 96)
                generatedEnergy = 1 * (yCoord / 64)

            if (worldObj.getCurrentWeather() == Weather.overworldRain ||
                worldObj.getCurrentWeather() == Weather.overworldRainBlood ||
                worldObj.getCurrentWeather() == Weather.overworldStorm ||
                worldObj.getCurrentWeather() == Weather.overworldSnow
            ) {
                generatedEnergy * 2
            }

            if (worldObj.dimension == Dimension.paradise)
                generatedEnergy * 2

            if (worldObj.dimension == Dimension.nether || worldObj.worldType == WorldTypes.OVERWORLD_HELL)
                generatedEnergy = 0

            for (xSides in xCoord - 1..xCoord + 1)
                for (zSides in zCoord - 1..zCoord + 1)
                    if (worldObj.getBlockId(xSides, yCoord, zSides) != 0)
                        generatedEnergy = 0

            if (generatedEnergy > 0)
                energy = min(energy + generatedEnergy, capacity)
        }
    }
}