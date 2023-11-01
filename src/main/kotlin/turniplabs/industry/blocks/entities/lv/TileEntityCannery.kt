package turniplabs.industry.blocks.entities.lv

import com.mojang.nbt.CompoundTag
import com.mojang.nbt.ListTag
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.item.ItemStack
import net.minecraft.core.player.inventory.IInventory
import sunsetsatellite.energyapi.impl.ItemEnergyContainer
import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import turniplabs.industry.IndustryConfig
import turniplabs.industry.blocks.IndustryBlocks
import turniplabs.industry.blocks.entities.TileEntityEnergyConductorDamageable
import turniplabs.industry.items.IndustryItems
import turniplabs.industry.recipes.RecipesCannery

class TileEntityCannery : TileEntityEnergyConductorDamageable(), IInventory {
    private var contents: Array<ItemStack?>
    var currentMachineTime = 0
    var maxMachineTime = 160

    init {
        contents = arrayOfNulls(5)

        setCapacity(1024)
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.lowVoltage"))
        setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.lowVoltage"))

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
        return "Cannery"
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

    private fun isProducible(itemStack: ItemStack?): Boolean {
        return (RecipesCannery.getRecipeList().containsKey(itemStack!!.item.id) && contents[3]!!.item == IndustryItems.canEmpty) ||
                (contents[2]!!.item == IndustryItems.uraniumIngot && contents[3]!!.item == IndustryItems.cellEmpty)
    }

    private fun canProduce(): Boolean {
        if ((contents[2] == null || contents[2]!!.item == null) || (contents[3] == null || contents[3]!!.item == null))
            return false

        if (isProducible(contents[2])) {
            val resultStack: ItemStack? = RecipesCannery.getResult(contents[2]!!.item.id)
            val canStack: Int = RecipesCannery.getResult(contents[2]!!.item.id)!!.stackSize

            if (contents[3]!!.stackSize < canStack || contents[3]!!.stackSize - canStack < 0)
                return false

            if (contents[4] == null || contents[4]!!.item == resultStack!!.item &&
                (contents[4]!!.stackSize + resultStack.stackSize <= inventoryStackLimit ||
                        contents[4]!!.stackSize + resultStack.stackSize <= contents[4]!!.maxStackSize ||
                        contents[4]!!.stackSize + resultStack.stackSize <= resultStack.maxStackSize))
                return true
        }
        return false
    }

    private fun produceItem() {
        if (canProduce()) {
            val itemStack: ItemStack = RecipesCannery.getResult(contents[2]!!.item.id) ?: return
            val canStack: Int = RecipesCannery.getResult(contents[2]!!.item.id)!!.stackSize

            if (contents[4] == null)
                contents[4] = itemStack.copy()
            else
                if (contents[4]!!.itemID == itemStack.itemID)
                    contents[4]!!.stackSize += itemStack.stackSize

            --contents[2]!!.stackSize
            contents[3]!!.stackSize -= canStack

            if (contents[2]!!.stackSize <= 0)
                contents[2] = null

            if (contents[3]!!.stackSize <= 0)
                contents[3] = null
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
            receive(getStackInSlot(1), maxReceive, false)
            onInventoryChanged()
        }

        if (!worldObj.isClientSide) {
            if (worldObj.getBlockId(xCoord, yCoord, zCoord) == IndustryBlocks.machineCannery.id &&
                currentMachineTime == 0 &&
                contents[2] == null) {
                machineUpdated = true
            }

            if (hasEnergy && canProduce()) {
                ++currentMachineTime
                --energy

                if (currentMachineTime == maxMachineTime) {
                    currentMachineTime = 0
                    produceItem()
                    machineUpdated = true
                }
            } else {
                currentMachineTime = 0
            }

            if (machineUpdated)
                onInventoryChanged()
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
        return (currentMachineTime * i) / maxMachineTime
    }
}