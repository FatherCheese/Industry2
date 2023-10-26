package turniplabs.industry.blocks.entities

import com.mojang.nbt.CompoundTag
import com.mojang.nbt.ListTag
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemStack
import net.minecraft.core.player.inventory.IInventory
import sunsetsatellite.energyapi.impl.ItemEnergyContainer
import sunsetsatellite.energyapi.impl.TileEntityEnergyConductor
import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import turniplabs.industry.blocks.machines.BlockGenerator
import turniplabs.industry.recipes.fuels.GeothermalFuel

class TileEntityGeothermal : TileEntityEnergyConductor(), IInventory {
    var active = false
    private var contents: Array<ItemStack?>
    var currentFuelTime = 0
    val maxFuelTime = 8000

    init {
        setCapacity(1024)
        setTransfer(16)
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
        return "Geothermal"
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

    private fun getFuelTimeForItem(itemStack: ItemStack?): Int {
        return if (itemStack == null) 0 else GeothermalFuel.getFuelResult(itemStack.item.id)!!
    }

    override fun updateEntity() {
        super.updateEntity()

        if (getStackInSlot(0) != null && getStackInSlot(0)?.item is ItemEnergyContainer) {
            provide(getStackInSlot(0), getMaxProvide(), false)
            onInventoryChanged()
        }

        if (currentFuelTime > 0 && energy != capacity) {
            --currentFuelTime
            ++energy
        }

        if (currentFuelTime == 0 && energy != capacity) {
            if (contents[2] != null && GeothermalFuel.getFuelList().containsKey(contents[2]!!.item.id)) {
                currentFuelTime = getFuelTimeForItem(contents[2])
                --contents[2]!!.stackSize

                if (contents[2]?.stackSize == 0)
                    contents[2] = null
            }
        }

        if (currentFuelTime in 1 until 6000 && contents[2] != null) {
            if (GeothermalFuel.getFuelList().containsKey(contents[2]!!.item.id)) {
                onInventoryChanged()
                active = true
                BlockGenerator.updateBlockState(true, worldObj, xCoord, yCoord, zCoord)

                currentFuelTime += getFuelTimeForItem(contents[2])

                if (contents[2]!!.item == Item.bucketLava) {
                    --contents[2]!!.stackSize

                    contents[2] = ItemStack(Item.bucket)
                }
                else
                    --contents[2]!!.stackSize

                if (contents[2]?.stackSize == 0)
                    contents[2] = null
            }
            else
                active = false
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

        currentFuelTime = compoundTag.getShort("CookTime").toInt()
        energy = compoundTag.getShort("Energy").toInt()
    }

    override fun writeToNBT(compoundTag: CompoundTag?) {
        super.writeToNBT(compoundTag)
        compoundTag?.putShort("CookTime", currentFuelTime.toShort())
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

    fun getFuelTime(i: Int): Int {
        return currentFuelTime * i / maxFuelTime
    }
}