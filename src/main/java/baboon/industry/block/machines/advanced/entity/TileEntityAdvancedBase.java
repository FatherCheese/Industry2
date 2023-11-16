package baboon.industry.block.machines.advanced.entity;

import baboon.industry.IndustryConfig;
import baboon.industry.block.entity.TileEntityEnergyConductorDamageable;
import baboon.industry.item.IndustryItems;
import baboon.industry.recipe.fuel.AdvancedRedstoneFuel;
import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.util.helper.Sides;
import sunsetsatellite.energyapi.impl.ItemEnergyContainer;
import sunsetsatellite.sunsetutils.util.Connection;
import sunsetsatellite.sunsetutils.util.Direction;
import sunsetsatellite.sunsetutils.util.IItemIO;

public class TileEntityAdvancedBase extends TileEntityEnergyConductorDamageable implements IInventory, IItemIO {
    protected ItemStack[] contents;
    protected AdvancedRedstoneFuel redstoneFuel = new AdvancedRedstoneFuel();
    protected int currentSpeed = 0;
    protected int currentEnergy = 0;
    protected int currentTransformers = 0;
    public boolean active = false;
    public int redstone = 0;
    public int currentMachineTime = 0;
    public final int maxRedstone = 8000;
    public int maxMachineTime = 200;

    public TileEntityAdvancedBase() {
        contents = new ItemStack[11];

        setCapacity(IndustryConfig.cfg.getInt("Energy Values.mvMachineStorage"));
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.mvIO"));
        setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.mvIO"));

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
        return null;
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

    @Override
    public void onInventoryChanged() {
        super.onInventoryChanged();
        for (int upgradesSize = 7; upgradesSize < 10; upgradesSize++) {
            currentSpeed = 0;
            currentEnergy = 0;
            currentTransformers = 0;
            maxMachineTime = 200;
            capacity = IndustryConfig.cfg.getInt("Energy Values.mvMachineStorage");

            if (contents[upgradesSize] != null) {
                if (contents[upgradesSize].getItem() == IndustryItems.upgradeSpeed) {
                    currentSpeed += 1;
                    maxMachineTime *= 1 - 0.3;
                }

                if (contents[upgradesSize].getItem() == IndustryItems.upgradeEnergy) {
                    currentEnergy += 1;
                    capacity += 10000;
                }

                if (contents[upgradesSize].getItem() == IndustryItems.upgradeTransformer)
                    currentTransformers += 1;
            }
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!worldObj.isClientSide) {
            if (getStackInSlot(0) != null && getStackInSlot(0).getItem() instanceof ItemEnergyContainer) {
                provide(getStackInSlot(0), getMaxProvide(), false);
                onInventoryChanged();
            }

            if (getStackInSlot(1) != null && getStackInSlot(1).getItem() instanceof ItemEnergyContainer) {
                receive(getStackInSlot(1), getMaxReceive(), false);
                onInventoryChanged();
            }

            if (currentTransformers == 1)
                setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.hvIO"));
            else if (currentTransformers == 2)
                setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.ehvIO"));

            if (contents[6] != null && redstoneFuel.getRedstoneList().containsKey(contents[6].getItem().id)) {
                int newRedstoneLevel = redstoneFuel.getYield(contents[6].getItem().id);

                if (redstoneFuel.getRedstoneFuel(contents[6].getItem().id) != 0 && redstone + newRedstoneLevel <= maxRedstone) {
                    redstone += newRedstoneLevel;
                    --contents[6].stackSize;

                    if (contents[6].stackSize <= 0)
                        contents[6] = null;
                }
            }
        }
    }

    @Override
    public void writeToNBT(CompoundTag CompoundTag) {
        super.writeToNBT(CompoundTag);
        CompoundTag.putInt("MachineTime", currentMachineTime);
        CompoundTag.putInt("Energy", energy);
        CompoundTag.putInt("Redstone", redstone);

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
        currentMachineTime = CompoundTag.getInteger("MachineTime");
        energy = CompoundTag.getInteger("Energy");
        redstone = CompoundTag.getInteger("Redstone");

        ListTag listTag = CompoundTag.getList("Items");

        contents = new ItemStack[getSizeInventory()];
        for (int i = 0; i < listTag.tagCount(); i++) {
            CompoundTag compoundTag2 = (com.mojang.nbt.CompoundTag) listTag.tagAt(i);
            int slot = compoundTag2.getInteger("Slot");

            if (slot >= 0 && slot < contents.length)
                contents[slot] = ItemStack.readItemStackFromNbt(compoundTag2);
        }
    }

    @Override
    public int getActiveItemSlotForSide(Direction direction) {
        int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        int index = Sides.orientationLookUpHorizontal[6 * meta + direction.getSide()];
        direction = Direction.getDirectionFromSide(index);

        if (direction == Direction.Y_POS) {
            for (int inputSlots = 2; inputSlots < 4; inputSlots++) {
                if (contents[inputSlots] == null || contents[inputSlots].stackSize < 64)
                    return inputSlots;
            }
            return 2;
        }

        if (direction == Direction.X_POS) {
            for (int outputSlots = 4; outputSlots < 6; outputSlots++) {
                if (contents[outputSlots] != null)
                    return outputSlots;
            }
            return 4;
        }

        if (direction == Direction.Y_NEG)
            return 6;

        return -1;
    }

    @Override
    public Connection getItemIOForSide(Direction direction) {
        int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        int index = Sides.orientationLookUpHorizontal[6 * meta + direction.getSide()];
        direction = Direction.getDirectionFromSide(index);

        switch (direction) {
            case Y_POS:
            case Y_NEG:
                return Connection.INPUT;
            case X_POS:
                return Connection.OUTPUT;
        }

        return Connection.NONE;
    }
}
