package turniplabs.industry.blocks.entities.mv

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
import turniplabs.industry.blocks.machines.mv.BlockCutterLaser
import turniplabs.industry.recipes.RecipesCutter
import turniplabs.industry.recipes.fuels.AdvancedRedstoneFuel

class TileEntityLaserCutter : TileEntityEnergyConductorDamageable(), IInventory {
    var active = false
    var redstone = 0
    private var contents: Array<ItemStack?>
    var currentMachineTime = 0
    val maxMachineTime = 160
    val maxRedstone = 8192

    init {
        contents = arrayOfNulls(7)

        setCapacity(4096)
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.mediumVoltage"))
        setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.mediumVoltage"))

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
        return "LaserCutter"
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
        return RecipesCutter.getRecipeList().containsKey(itemStack!!.item.id)
    }

    private fun canProduceFirst(): Boolean {
        if (contents[2] == null || contents[2]!!.item == null)
            return false

        if (isProducible(contents[2])) {
            val resultStack: ItemStack? = RecipesCutter.getResult(contents[2]!!.item.id)

            if (contents[4] == null || contents[4]!!.item == resultStack!!.item &&
                contents[4]!!.stackSize + resultStack.stackSize <= resultStack.maxStackSize)
                return true
        }
        return false
    }

    private fun canProduceSecond(): Boolean {
        if (contents[3] == null || contents[3]!!.item == null)
            return false

        if (isProducible(contents[3])) {
            val resultStack: ItemStack? = RecipesCutter.getResult(contents[3]!!.item.id)

            if (contents[5] == null || contents[5]!!.item == resultStack!!.item &&
                contents[5]!!.stackSize + resultStack.stackSize <= resultStack.maxStackSize)
                return true
        }
        return false
    }

    private fun produceFirstItem() {
        if (canProduceFirst()) {
            val itemStack: ItemStack = RecipesCutter.getResult(contents[2]!!.item.id) ?: return

            if (contents[4] == null)
                contents[4] = itemStack.copy()
            else
                if (contents[4]!!.itemID == itemStack.itemID)
                    contents[4]!!.stackSize += itemStack.stackSize

            --contents[2]!!.stackSize

            if (contents[2]!!.stackSize <= 0)
                contents[2] = null
        }
    }

    private fun produceSecondItem() {
        if (canProduceSecond()) {
            val itemStack: ItemStack = RecipesCutter.getResult(contents[3]!!.item.id) ?: return

            if (contents[5] == null)
                contents[5] = itemStack.copy()
            else
                if (contents[5]!!.itemID == itemStack.itemID)
                    contents[5]!!.stackSize += itemStack.stackSize

            --contents[3]!!.stackSize

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
            val stack: ItemStack? = getStackInSlot(1)
            receive(stack, maxReceive, false)
            onInventoryChanged()
        }

        if (!worldObj.isClientSide) {
            if (worldObj.getBlockId(xCoord, yCoord, zCoord) != IndustryBlocks.advancedMachineCutter.id &&
                currentMachineTime == 0 &&
                (contents[2] == null || contents[3] == null)) {
                BlockCutterLaser.updateBlockState(true, worldObj, xCoord, yCoord, zCoord)
                machineUpdated = true
            }
        }

        if (contents[6] != null && AdvancedRedstoneFuel.getRedstoneFuelList().containsKey(contents[6]!!.item.id)) {
            val newRedstoneLevel = AdvancedRedstoneFuel.getYield(contents[6]!!.item.id)

            if (AdvancedRedstoneFuel.getRedstoneFuel(contents[6]!!.item.id) != null && redstone + newRedstoneLevel!! <= maxRedstone) {

                redstone += newRedstoneLevel
                --contents[6]!!.stackSize

                if (contents[6]!!.stackSize <= 0)
                    contents[6] = null
            }
        }

        if (hasEnergy && (canProduceFirst() || canProduceSecond())) {
            ++currentMachineTime
            energy -= 2
            active = true

            if (redstone > 0) {
                currentMachineTime *= (redstone / 2048)

                if (canProduceFirst()) {
                    redstone -= 16
                    energy -= 4
                }

                if (canProduceSecond()) {
                    redstone -= 16
                    energy -= 4
                }
            }

            if (currentMachineTime >= maxMachineTime) {
                currentMachineTime = 0
                active = false
                machineUpdated = true

                if (contents[2] != null)
                    produceFirstItem()

                if (contents[3] != null)
                    produceSecondItem()
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

        currentMachineTime = compoundTag.getShort("MachineTime").toInt()
        energy = compoundTag.getShort("Energy").toInt()
        redstone = compoundTag.getShort("Redstone").toInt()
    }

    override fun writeToNBT(compoundTag: CompoundTag?) {
        super.writeToNBT(compoundTag)
        compoundTag?.putShort("MachineTime", currentMachineTime.toShort())
        compoundTag?.putShort("Energy", energy.toShort())
        compoundTag?.putShort("Redstone", redstone.toShort())

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