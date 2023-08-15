package turniplabs.industry.blocks.entities

import net.minecraft.core.crafting.LookupFuelFurnace
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.item.ItemStack
import net.minecraft.core.player.inventory.IInventory
import sunsetsatellite.energyapi.api.LookupFuelEnergy
import sunsetsatellite.energyapi.impl.ItemEnergyContainer
import sunsetsatellite.energyapi.impl.TileEntityEnergyConductor
import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction

class TileEntityGenerator: TileEntityEnergyConductor(), IInventory {
    private val contents: Array<ItemStack?>
    var maxBurnTime = 0
    var currentBurnTime = 0
    private var currentFuel: ItemStack? = null

    init {
        setCapacity(1024)
        setTransfer(32)
        setMaxReceive(0)
        contents = arrayOfNulls(2)

        for (dir: Direction in Direction.values())
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
        } else return null
    }

    override fun setInventorySlotContents(i: Int, itemStack: ItemStack?) {
        contents[i] = itemStack
        if (itemStack != null && itemStack.stackSize > inventoryStackLimit)
            itemStack.stackSize = inventoryStackLimit

        onInventoryChanged()
    }

    override fun getInvName(): String {
        return "IndustryGenerator"
    }

    override fun getInventoryStackLimit(): Int {
        return 64
    }

    override fun canInteractWith(entityPlayer: EntityPlayer?): Boolean {
        if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this) return false
        return entityPlayer!!.distanceToSqr(
            (xCoord + 0.5f).toDouble(),
            (yCoord + 0.5f).toDouble(),
            (zCoord + 0.5f).toDouble()
        ) <= 64.0f
    }

    override fun updateEntity() {
        super.updateEntity()
        if (currentBurnTime > 0) {
            --currentBurnTime
            modifyEnergy(getEnergyYieldForItem(currentFuel))
        }

        if (currentBurnTime == 0) {
            currentBurnTime = getBurnTimeFromItem(contents[1]) / 5
            maxBurnTime = currentBurnTime

            if (currentBurnTime > 0) {
                currentFuel = contents[1]
                onInventoryChanged()

                if (contents[1] != null) {
                    --contents[1]!!.stackSize
                    if (contents[1]?.stackSize == 0) contents[1] = null
                }
            } else currentFuel = null
        }

        if (getStackInSlot(0) != null && getStackInSlot(0)?.item is ItemEnergyContainer) {
            provide(getStackInSlot(0), getMaxProvide(), false)
            onInventoryChanged()
        }

        if (getStackInSlot(1) != null && getStackInSlot(1)?.item is ItemEnergyContainer) {
            receive(getStackInSlot(1), getMaxReceive(), false)
            onInventoryChanged()
        }
    }

    private fun getEnergyYieldForItem(itemStack: ItemStack?): Int {
        return if (itemStack == null) 0 else LookupFuelEnergy.fuelEnergy().getEnergyYield(itemStack.item.id)
    }

    private fun getBurnTimeFromItem(itemStack: ItemStack?): Int {
        return if (itemStack == null) 0 else LookupFuelFurnace.instance.getFuelYield(itemStack.item.id)
    }
}