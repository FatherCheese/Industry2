package turniplabs.industry.blocks.entities

import com.mojang.nbt.CompoundTag
import com.mojang.nbt.ListTag
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.item.ItemStack
import net.minecraft.core.player.inventory.IInventory
import sunsetsatellite.energyapi.impl.ItemEnergyContainer
import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import turniplabs.industry.Industry2
import turniplabs.industry.blocks.machines.BlockMacerator
import turniplabs.industry.recipes.RecipesMacerator

class TileEntityMacerator: TileEntityEnergyConductorDamageable(), IInventory {
    var active = false
    private var contents: Array<ItemStack?>
    private var currentCrushTime = 0
    private val maxCrushTime = 128

    init {
        setCapacity(1024)
        setTransfer(0)
        setMaxReceive(32)
        contents = arrayOfNulls(3)
        for (dir in Direction.values())
            setConnection(dir, Connection.INPUT)
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
        return "Macerator"
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
        val hasEnergy = energy > 0
        var machineUpdated = false

        if (getStackInSlot(1) != null && getStackInSlot(1)?.item is ItemEnergyContainer) {
            val stack: ItemStack? = getStackInSlot(1)

            receive(stack, maxReceive, false)
            onInventoryChanged()
        }

        if (!worldObj.isClientSide) {
            if (worldObj.getBlockId(xCoord, yCoord, zCoord) == Industry2.machineMacerator.id &&
                currentCrushTime == 0 &&
                contents[0] == null
            ) {
                BlockMacerator.updateBlockState(true, worldObj, xCoord, yCoord, zCoord)
                machineUpdated = true
            }
        }

        if (hasEnergy && canCrush()) {
            ++currentCrushTime
            --energy
            active = true

            if (currentCrushTime == maxCrushTime) {
                currentCrushTime = 0
                crushItem()
                active = false
                machineUpdated = true
            }
        } else {
            currentCrushTime = 0
            active = false
        }

        if (machineUpdated)
            onInventoryChanged()

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

        currentCrushTime = compoundTag.getShort("CookTime").toInt()
        energy = compoundTag.getShort("Energy").toInt()
    }

    override fun writeToNBT(compoundTag: CompoundTag?) {
        super.writeToNBT(compoundTag)
        compoundTag?.putShort("CookTime", currentCrushTime.toShort())
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


    private fun canCrush(): Boolean {
        if (contents[0] == null) return false

        val itemStack: ItemStack = RecipesMacerator.getResult(contents[0]!!.item.id) ?: return false

        return when {
            contents[2] == null -> true
            !contents[2]!!.isItemEqual(itemStack) -> false
            contents[2]!!.stackSize < inventoryStackLimit && contents[2]!!.stackSize < contents[2]!!.maxStackSize -> true
            else -> contents[2]!!.stackSize < itemStack.maxStackSize
        }
    }

    private fun crushItem() {
        if (canCrush()) {
            val itemStack: ItemStack = RecipesMacerator.getResult(contents[0]!!.item.id)

            if (contents[2] == null)
                contents[2] = itemStack.copy()
            else
                if (contents[2]!!.itemID == itemStack.itemID)
                    ++contents[2]!!.stackSize

            --contents[0]!!.stackSize

            if (contents[0]!!.stackSize <= 0)
                contents[0] = null
        }
    }

    fun getProgressScaled(i: Int): Int {
        return if (maxCrushTime == 0) 0 else (currentCrushTime * i) / maxCrushTime
    }
}