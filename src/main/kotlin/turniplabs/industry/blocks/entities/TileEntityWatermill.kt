package turniplabs.industry.blocks.entities

import com.mojang.nbt.CompoundTag
import com.mojang.nbt.ListTag
import net.minecraft.core.block.Block
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemStack
import net.minecraft.core.player.inventory.IInventory
import net.minecraft.core.world.Dimension
import sunsetsatellite.energyapi.impl.ItemEnergyContainer
import sunsetsatellite.energyapi.impl.TileEntityEnergyConductor
import sunsetsatellite.sunsetutils.util.Connection
import sunsetsatellite.sunsetutils.util.Direction
import turniplabs.industry.IndustryConfig
import turniplabs.industry.recipes.fuels.WatermillFuel

class TileEntityWatermill : TileEntityEnergyConductor(), IInventory {
    private var contents: Array<ItemStack?>
    var currentFuelTime = 0
    val maxFuelTime = 8000

    init {
        setCapacity(1024)
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.lowVoltage"))
        setMaxReceive(0)

        contents = arrayOfNulls(3)

        setConnection(Direction.Y_POS, Connection.OUTPUT)
        setConnection(Direction.Y_NEG, Connection.OUTPUT)
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
        return "Watermill"
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

    private fun getFuelTimeFromItem(itemStack: ItemStack?): Int {
        return if (itemStack == null) 0 else WatermillFuel.getResult(itemStack.item.id)!!
    }

    override fun updateEntity() {
        super.updateEntity()

        if (getStackInSlot(0) != null && getStackInSlot(0)?.item is ItemEnergyContainer) {
            provide(getStackInSlot(0), getMaxProvide(), false)
            onInventoryChanged()
        }

        if (worldObj.dimension == Dimension.nether)
            currentFuelTime = 0

        for (xWater in xCoord - 1..xCoord + 1)
            for (yWater in yCoord - 1..yCoord + 1)
                for (zWater in zCoord - 1..zCoord + 1)
                    if (
                        worldObj.getBlockId(xWater, yWater, zWater) == Block.fluidWaterFlowing.id ||
                        worldObj.getBlockId(xWater, yWater, zWater) == Block.fluidWaterStill.id
                    )
                        currentFuelTime = maxFuelTime

        if (currentFuelTime > 0 && energy != capacity) {
            --currentFuelTime
            ++energy
        }

        if (currentFuelTime == 0 && energy != capacity) {
            if (contents[2] != null && WatermillFuel.getFuelList().containsKey(contents[2]!!.item.id)) {
                currentFuelTime = getFuelTimeFromItem(contents[2])
                --contents[2]!!.stackSize

                if (contents[2]?.stackSize == 0)
                    contents[2] = null
            }
        }

        if (currentFuelTime in 1 until 7000 && contents[2] != null) {
            if (WatermillFuel.getFuelList().containsKey(contents[2]!!.item.id)) {
                onInventoryChanged()

                currentFuelTime += getFuelTimeFromItem(contents[2])

                if (contents[2]!!.item == Item.bucketWater) {
                    --contents[2]!!.stackSize

                    contents[2] = ItemStack(Item.bucket)
                }
                else
                    --contents[2]!!.stackSize

                if (contents[2]?.stackSize == 0)
                    contents[2] = null
            }
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

        currentFuelTime = compoundTag.getShort("FuelTime").toInt()
        energy = compoundTag.getShort("Energy").toInt()
    }

    override fun writeToNBT(compoundTag: CompoundTag?) {
        super.writeToNBT(compoundTag)
        compoundTag?.putShort("FuelTime", currentFuelTime.toShort())
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