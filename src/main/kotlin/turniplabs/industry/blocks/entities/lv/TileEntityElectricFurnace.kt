package turniplabs.industry.blocks.entities.lv

import com.mojang.nbt.CompoundTag
import com.mojang.nbt.ListTag
import net.minecraft.core.crafting.recipe.RecipesFurnace
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.item.ItemStack
import net.minecraft.core.player.inventory.IInventory
import sunsetsatellite.energyapi.impl.ItemEnergyContainer
import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import turniplabs.industry.blocks.IndustryBlocks
import turniplabs.industry.blocks.entities.TileEntityEnergyConductorDamageable
import turniplabs.industry.blocks.machines.lv.BlockElectricFurnace

class TileEntityElectricFurnace: TileEntityEnergyConductorDamageable(), IInventory {
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
        return "ElectricFurnace"
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

    private fun canSmelt(): Boolean {
        if (contents[2] == null)
            return false

        if (contents[2]!!.item == null)
            return false

        val itemStack: ItemStack = RecipesFurnace.smelting().getSmeltingResult(contents[2]!!.item.id) ?: return false

        return when {
            contents[3] == null -> true
            !contents[3]!!.isItemEqual(itemStack) -> false
            contents[3]!!.stackSize < inventoryStackLimit && contents[3]!!.stackSize < contents[3]!!.maxStackSize -> true
            else -> contents[3]!!.stackSize < itemStack.maxStackSize
        }
    }

    private fun smeltItem() {
        if (canSmelt()) {
            val itemStack = RecipesFurnace.smelting().getSmeltingResult(contents[2]!!.item.id)

            if (contents[3] == null)
                contents[3] = itemStack.copy()
            else
                if (contents[3]!!.itemID == itemStack.itemID)
                    contents[3]!!.stackSize += itemStack.stackSize

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
            if (worldObj.getBlockId(xCoord, yCoord, zCoord) == IndustryBlocks.machineElectricFurnace.id &&
                currentMachineTime == 0 &&
                contents[2] == null
                ) {
                BlockElectricFurnace.updateBlockState(true, worldObj, xCoord, yCoord, zCoord)
                machineUpdated = true
            }
        }

        if (hasEnergy && canSmelt()) {
            ++currentMachineTime
            --energy
            active = true

            if (currentMachineTime == maxMachineTime) {
                currentMachineTime = 0
                smeltItem()
                active = false
                machineUpdated = true
            }
        } else {
            currentMachineTime = 0
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

        currentMachineTime = compoundTag.getShort("CookTime").toInt()
        energy = compoundTag.getShort("Energy").toInt()
    }

    override fun writeToNBT(compoundTag: CompoundTag?) {
        super.writeToNBT(compoundTag)
        compoundTag?.putShort("CookTime", currentMachineTime.toShort())
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
        return if (maxMachineTime == 0) 0
        else (currentMachineTime * i) / maxMachineTime
    }
}