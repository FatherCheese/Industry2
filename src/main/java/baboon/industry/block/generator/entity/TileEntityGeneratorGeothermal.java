package baboon.industry.block.generator.entity;

import baboon.industry.IndustryConfig;
import baboon.industry.block.IndustryBlocks;
import baboon.industry.block.generator.BlockGeneratorGeothermal;
import baboon.industry.recipe.fuel.GeneratorGeothermalFuel;
import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;
import sunsetsatellite.energyapi.impl.ItemEnergyContainer;
import sunsetsatellite.energyapi.impl.TileEntityEnergyConductor;
import sunsetsatellite.sunsetutils.util.Connection;
import sunsetsatellite.sunsetutils.util.Direction;

public class TileEntityGeneratorGeothermal extends TileEntityEnergyConductor implements IInventory {
    private ItemStack[] contents;
    private final GeneratorGeothermalFuel fuel = new GeneratorGeothermalFuel();
    private boolean active = false;
    public int currentFuelTime = 0;
    public final int maxFuelTime = 8000;

    public TileEntityGeneratorGeothermal() {
        contents = new ItemStack[3];

        setCapacity(IndustryConfig.cfg.getInt("Energy Values.lvStorage"));
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.lowVoltage"));
        setMaxReceive(0);

        for (Direction dir : Direction.values())
            setConnection(dir, Connection.OUTPUT);
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
        if (contents[i] != null) {
            if (contents[i].stackSize <= j) {
                ItemStack itemstack = contents[i];
                contents[i] = null;
                return itemstack;
            } else {
                ItemStack splitStack = contents[i].splitStack(j);
                if (contents[i].stackSize <= 0) {
                    contents[i] = null;
                }

                return splitStack;
            }
        } else {
            return null;
        }
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
        return "IndustryGeothermal";
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this)
            return false;

        return entityPlayer.distanceToSqr(xCoord + 0.5f, yCoord + 0.5f, zCoord + 0.5f) <= 64;
    }

    private int getFuelFromItem(ItemStack itemStack) {
        return (itemStack == null) ? 0 : fuel.getYield(itemStack.getItem().id);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!worldObj.isClientSide) {
            if (getStackInSlot(0) != null && getStackInSlot(0).getItem() instanceof ItemEnergyContainer) {
                provide(getStackInSlot(0), maxProvide, false);
                onInventoryChanged();
            }

            if (currentFuelTime > 0 && energy != capacity) {
                --currentFuelTime;
                ++energy;
            }

            if ((currentFuelTime == 0 || currentFuelTime > 0 && currentFuelTime < 7000) && contents[2] != null) {
                if (fuel.getFuelList().containsKey(contents[2].getItem().id)) {
                    active = true;
                    onInventoryChanged();
                    BlockGeneratorGeothermal.updateBlockState(true, worldObj, xCoord, yCoord, zCoord);

                    currentFuelTime += getFuelFromItem(contents[2]);
                    --contents[2].stackSize;

                    if (contents[2].getItem() == Item.bucketLava)
                        contents[2] = new ItemStack(Item.bucket);

                    if (contents[2].stackSize <= 0)
                        contents[2] = null;
                } else
                    active = false;
            }

            if (active)
                worldObj.notifyBlockChange(xCoord, yCoord, zCoord, IndustryBlocks.generatorGeothermal.id);
        }
    }

    @Override
    public void writeToNBT(CompoundTag CompoundTag) {
        super.writeToNBT(CompoundTag);
        CompoundTag.putInt("FuelTime", currentFuelTime);
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
        currentFuelTime = CompoundTag.getInteger("FuelTime");
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
