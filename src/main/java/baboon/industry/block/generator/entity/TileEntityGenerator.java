package baboon.industry.block.generator.entity;

import baboon.industry.IndustryConfig;
import baboon.industry.block.IndustryBlocks;
import baboon.industry.block.generator.BlockGenerator;
import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;
import net.minecraft.core.crafting.LookupFuelFurnace;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;
import sunsetsatellite.energyapi.api.LookupFuelEnergy;
import sunsetsatellite.energyapi.impl.ItemEnergyContainer;
import sunsetsatellite.energyapi.impl.TileEntityEnergyConductor;
import sunsetsatellite.sunsetutils.util.Connection;
import sunsetsatellite.sunsetutils.util.Direction;

public class TileEntityGenerator extends TileEntityEnergyConductor implements IInventory {
    private ItemStack[] contents;
    public boolean active = false;
    public int currentBurnTime = 0;
    public int maxBurnTime = 0;
    public ItemStack currentFuel = null;

    public TileEntityGenerator() {
        contents = new ItemStack[3];

        setCapacity(IndustryConfig.cfg.getInt("Energy Values.lvStorage"));
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.lowVoltage"));
        setMaxReceive(0);

        for (Direction dir : Direction.values())
            setConnection(dir, Connection.INPUT);
    }

    @Override
    public int getSizeInventory() {
        return contents.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return contents[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        if (contents[i] == null && contents[i].stackSize <= j) {
            ItemStack itemStack = contents[i];

            contents[i] = null;
            onInventoryChanged();
            return itemStack;
        }
        ItemStack splitStack = contents[i].splitStack(j);
        if (contents[i].stackSize == 0) {
            contents[i] = null;

            onInventoryChanged();
            return splitStack;
        } else return null;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        contents[i] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
            itemStack.stackSize = getInventoryStackLimit();

        onInventoryChanged();
    }

    @Override
    public String getInvName() {
        return "IndustryGenerator";
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this)
            return false;
        else
            return entityPlayer.distanceToSqr(xCoord + 0.5f, yCoord + 0.5f, zCoord + 0.5f) <= 64;
    }

    private int energyYieldForItem(ItemStack itemStack) {
        return itemStack == null ? 0 : LookupFuelEnergy.fuelEnergy().getEnergyYield(itemStack.getItem().id);
    }

    private int burnTimeForItem(ItemStack itemStack) {
        return itemStack == null ? 0 : LookupFuelFurnace.instance.getFuelYield(itemStack.getItem().id);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        boolean machineUpdated = false;

        if (!worldObj.isClientSide) {
            if (getStackInSlot(0) != null && getStackInSlot(0).getItem() instanceof ItemEnergyContainer) {
                provide(getStackInSlot(0), getMaxProvide(), false);
                onInventoryChanged();
            }

            if (currentBurnTime > 0) {
                --currentBurnTime;
                modifyEnergy(energyYieldForItem(currentFuel));
            }

            if (currentBurnTime == 0 && energy != capacity) {
                currentBurnTime = burnTimeForItem(contents[2]) / 5;
                maxBurnTime = currentBurnTime;

                if (currentBurnTime > 0) {
                    active = true;
                    BlockGenerator.updateBlockState(true, worldObj, xCoord, yCoord, zCoord);

                    currentFuel = contents[2];
                    onInventoryChanged();
                    machineUpdated = true;

                    if (contents[2] != null) {
                        --contents[2].stackSize;
                        if (contents[2].stackSize <= 0)
                            contents[2] = null;
                    }
                } else {
                    active = false;
                    currentFuel = null;
                }
            }
            if (machineUpdated)
                onInventoryChanged();

            if (active)
                worldObj.notifyBlockChange(xCoord, yCoord, zCoord, IndustryBlocks.generator.id);
        }
    }

    @Override
    public void writeToNBT(CompoundTag CompoundTag) {
        super.writeToNBT(CompoundTag);
        CompoundTag.putInt("BurnTime", currentBurnTime);
        CompoundTag.putInt("Energy", energy);

        ListTag listTag = new ListTag();
        for (int i = 0; i < contents.length; i++) {
            if (contents[i] != null) {
                CompoundTag compoundTag2 = new CompoundTag();

                compoundTag2.putInt("Slot", i);
                contents[i].writeToNBT(compoundTag2);
                listTag.addTag(compoundTag2);
            }
        }
        CompoundTag.put("Items", listTag);
    }

    @Override
    public void readFromNBT(CompoundTag CompoundTag) {
        super.readFromNBT(CompoundTag);
        currentBurnTime = CompoundTag.getInteger("BurnTime");
        energy = CompoundTag.getInteger("Energy");

        ListTag listTag = CompoundTag.getList("Items");

        contents = new ItemStack[getSizeInventory()];
        for (int i = 0; i < listTag.tagCount(); i++) {
            CompoundTag compoundTag2 = (com.mojang.nbt.CompoundTag) listTag.tagAt(i);
            int slot = compoundTag2.getInteger("Slot");

            if (slot >= 0 && slot < contents.length)
                contents[slot] = ItemStack.readItemStackFromNbt(compoundTag2);
        }
    }
}
