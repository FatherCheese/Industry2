package turniplabs.industry.blocks.entities

import com.mojang.nbt.CompoundTag
import com.mojang.nbt.ListTag
import net.minecraft.core.crafting.LookupFuelFurnace
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.item.ItemStack
import net.minecraft.core.player.inventory.IInventory
import sunsetsatellite.energyapi.api.LookupFuelEnergy
import sunsetsatellite.energyapi.impl.ItemEnergyContainer
import sunsetsatellite.energyapi.impl.TileEntityEnergyConductor
import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import turniplabs.industry.blocks.machines.BlockGenerator

class TileEntityGenerator: TileEntityEnergyConductor(), IInventory {
    var active = false
    private var contents: Array<ItemStack?>
    private var currentBurnTime = 0
    private var maxBurnTime = 0
    private var currentFuel: ItemStack? = null

    init {
        setCapacity(1024)
        setTransfer(32)
        setMaxReceive(0)
        contents = arrayOfNulls(3)

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

    // TODO - this eats fuel because there's nothing stopping it if the generator is full, also the texture won't update
    // I've tried rewriting it and it just won't work if I do
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

        if (currentBurnTime > 0) {
            --currentBurnTime
            modifyEnergy(getEnergyYieldForItem(currentFuel))
        }

        if (currentBurnTime == 0) {
            currentBurnTime = getBurnTimeFromItem(contents[2]) / 5
            maxBurnTime = currentBurnTime

            if (currentBurnTime > 0) {
                active = true
                BlockGenerator.updateBlockState(true, worldObj, xCoord, yCoord, zCoord)
                
                currentFuel = contents[2]
                onInventoryChanged()

                if (contents[2] != null) {
                    --contents[2]!!.stackSize
                    if (contents[2]?.stackSize == 0) contents[2] = null
                }
            } else  {
                active = false
                currentFuel = null
            }
        }

        if (active)
            worldObj.markBlockDirty(xCoord, yCoord, zCoord)
    }

    override fun readFromNBT(compoundTag: CompoundTag?) {
        super.readFromNBT(compoundTag)
        val nbtTagList = compoundTag!!.getList("Items")

        contents = arrayOfNulls(sizeInventory)
        for (i in 0 until nbtTagList.tagCount()) {
            val compoundTag2: CompoundTag = nbtTagList.tagAt(i) as CompoundTag
            val byte: Int = compoundTag2.getByte("Slot").toInt()

            if (byte >= 0 && byte < contents.size)
                contents[byte] = ItemStack.readItemStackFromNbt(compoundTag2)
        }

        currentBurnTime = compoundTag.getShort("CookTime").toInt()
        energy = compoundTag.getShort("Energy").toInt()
    }

    override fun writeToNBT(compoundTag: CompoundTag?) {
        super.writeToNBT(compoundTag)
        compoundTag?.putShort("CookTime", currentBurnTime.toShort())
        compoundTag?.putShort("Energy", energy.toShort())

        val listTag = ListTag()
        for (i in contents.indices) {
            if (contents[i] != null) {
                val compoundTag2 = CompoundTag()

                compoundTag2.putByte("Slot", i.toByte())
                contents[i]!!.writeToNBT(compoundTag2)
                listTag.addTag(compoundTag2)
            }
        }
        compoundTag!!.put("Items", listTag)
    }

    // Burn time, used by GuiGenerator
    fun getBurnTime(i: Int): Int {
        return if (maxBurnTime == 0) 0 else currentBurnTime * i / maxBurnTime
    }

    private fun canBurn(): Boolean {
        return energy != capacity
    }

    private fun getEnergyYieldForItem(itemStack: ItemStack?): Int {
        return if (itemStack == null) 0 else LookupFuelEnergy.fuelEnergy().getEnergyYield(itemStack.item.id)
    }

    private fun getBurnTimeFromItem(itemStack: ItemStack?): Int {
        return if (itemStack == null) 0 else LookupFuelFurnace.instance.getFuelYield(itemStack.item.id)
    }
}