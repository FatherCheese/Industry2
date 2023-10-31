package turniplabs.industry.blocks.entities.lv

import com.mojang.nbt.CompoundTag
import com.mojang.nbt.ListTag
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.item.ItemStack
import net.minecraft.core.player.inventory.IInventory
import sunsetsatellite.energyapi.impl.ItemEnergyContainer
import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import turniplabs.industry.IndustryTags
import turniplabs.industry.blocks.IndustryBlocks
import turniplabs.industry.blocks.entities.TileEntityEnergyConductorDamageable
import turniplabs.industry.blocks.machines.lv.BlockRecycler
import turniplabs.industry.items.IndustryItems

class TileEntityRecycler : TileEntityEnergyConductorDamageable(), IInventory {
    var active = false
    private var contents: Array<ItemStack?>
    var currentMachineTime = 0
    val maxMachineTime = 160

    init {
        contents = arrayOfNulls(4)

        setCapacity(1024)
        setTransfer(16)
        setMaxReceive(16)

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
        return "Recycler"
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

    private fun canProduce(): Boolean {
        if (contents[2] == null || contents[2]!!.item == null)
            return false

        if (!contents[2]!!.item.hasTag(IndustryTags.PREVENT_ITEM_RECYCLING))
            return true

        return false
    }

    private fun produceItem() {
        if (canProduce()) {
            val produce = ItemStack(IndustryItems.scrap, 1) ?: return

            if (contents[3] == null) {
                if (random.nextInt(4) == 0)
                    contents[3] = produce.copy()
            } else
                if (contents[3]!!.itemID == produce.itemID)
                    if (random.nextInt(4) == 0)
                        contents[3]!!.stackSize += produce.stackSize

            --contents[2]!!.stackSize

            if (contents[2]!!.stackSize <= 0)
                contents[2] = null
        }
    }

    override fun updateEntity() {
        super.updateEntity()
        val hasEnergy: Boolean = energy > 0
        var machineUpdated = false

        if (getStackInSlot(0) != null && getStackInSlot(0)?.item is ItemEnergyContainer) {
            provide(getStackInSlot(0), getMaxProvide(), false)
            onInventoryChanged()
        }

        if (getStackInSlot(1) != null && getStackInSlot(1)?.item is ItemEnergyContainer) {
            val stack: ItemStack? = getStackInSlot(1)

            receive(stack, maxReceive, false)
            onInventoryChanged()
        }

        if (!worldObj.isClientSide) {
            if (worldObj.getBlockId(xCoord, yCoord, zCoord) == IndustryBlocks.machineRecycler.id &&
                currentMachineTime == 0 &&
                contents[2] == null
                ) {
                BlockRecycler.updateBlockState(false, worldObj, xCoord, yCoord, zCoord)
                machineUpdated = true
            }

            if (hasEnergy && canProduce()) {
                ++currentMachineTime
                --energy
                active = true

                if (currentMachineTime == maxMachineTime) {
                    currentMachineTime = 0
                    produceItem()
                    active = false
                    machineUpdated = true
                }
            }
            else {
                currentMachineTime = 0
                active = false
            }
            if (machineUpdated)
                onInventoryChanged()

            if (active)
                worldObj.markBlockDirty(xCoord, yCoord, zCoord)
        }
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

        currentMachineTime = compoundTag.getShort("Production").toInt()
        energy = compoundTag.getShort("Energy").toInt()
    }

    override fun writeToNBT(compoundTag: CompoundTag?) {
        super.writeToNBT(compoundTag)
        compoundTag?.putShort("Production", currentMachineTime.toShort())
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

    fun getProgressScaled(i: Int): Int {
        return if (currentMachineTime == 0) 0 else (currentMachineTime * i) / maxMachineTime
    }
}
