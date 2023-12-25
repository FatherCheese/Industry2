package baboon.industry.block.machines.endgame.entity;

import baboon.industry.IndustryConfig;
import baboon.industry.IndustryTags;
import baboon.industry.item.IndustryItems;
import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.util.helper.Sides;
import sunsetsatellite.catalyst.core.util.Connection;
import sunsetsatellite.catalyst.core.util.Direction;
import sunsetsatellite.catalyst.core.util.IItemIO;
import sunsetsatellite.catalyst.energy.impl.ItemEnergyContainer;
import sunsetsatellite.catalyst.energy.impl.TileEntityEnergyConductor;

public class TileEntityEnergyFabricator extends TileEntityEnergyConductor implements IInventory, IItemIO {
    private ItemStack[] contents;
    public int scrap = 0;
    public int currentMachineTime = 0;
    public final int maxScrap = 1000;
    public final int maxMachineTime = 24000;

    public TileEntityEnergyFabricator() {
        contents = new ItemStack[5];
        setCapacity(IndustryConfig.cfg.getInt("Energy Values.ehvMachineStorage"));
        setMaxProvide(IndustryConfig.cfg.getInt("Energy Values.ehvIO"));
        setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.ehvIO"));

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
        return "IndustryFabricator";
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        if (worldObj.getBlockTileEntity(x, y, z) != this)
            return false;

        return entityPlayer.distanceToSqr(x + 0.5f, y + 0.5f, z + 0.5f) <= 64;
    }

    @Override
    public void sortInventory() {

    }

    @Override
    public void tick() {
        if (!worldObj.isClientSide) {
            if (getStackInSlot(0) != null && getStackInSlot(0).getItem() instanceof ItemEnergyContainer) {
                provide(getStackInSlot(0), getMaxProvide(), false);
                onInventoryChanged();
            }

            if (getStackInSlot(1) != null && getStackInSlot(1).getItem() instanceof ItemEnergyContainer) {
                receive(getStackInSlot(1), getMaxReceive(), false);
                onInventoryChanged();
            }

            if (contents[4] != null && contents[4].itemID == IndustryItems.scrap.id && scrap + 100 <= maxScrap) {
                --contents[4].stackSize;
                scrap += 100;

                if (contents[4].stackSize <= 0)
                    contents[4] = null;
            }

            if (contents[2] != null)
                if (!contents[2].getItem().hasTag(IndustryTags.PREVENT_FABRICATING) && energy >= 0) {
                    ++currentMachineTime;
                    if (energy - 64 >= 0)
                        energy -= 64;

                    if (currentMachineTime >= maxMachineTime) {
                        currentMachineTime = 0;

                        if (contents[3] == null)
                            contents[3] = new ItemStack(contents[2].getItem(), 1);
                        else if (contents[3].itemID == contents[2].itemID && contents[3].stackSize + 1 <= contents[3].getMaxStackSize())
                            ++contents[3].stackSize;
                    }

                    if (scrap >= 0 && scrap - 10 >= 0 && currentMachineTime + 1 <= maxMachineTime) {
                        currentMachineTime += (int) ((double) scrap / 5);
                        scrap -= 10;
                    }
                } else
                    currentMachineTime = 0;
        }
    }

    @Override
    public void writeToNBT(CompoundTag CompoundTag) {
        super.writeToNBT(CompoundTag);
        CompoundTag.putInt("MachineTime", currentMachineTime);
        CompoundTag.putInt("Scrap", scrap);
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
        currentMachineTime = CompoundTag.getInteger("MachineTime");
        scrap = CompoundTag.getInteger("Scrap");
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

    @Override
    public int getActiveItemSlotForSide(Direction direction) {
        int meta = worldObj.getBlockMetadata(x, y, z);
        int index = Sides.orientationLookUpHorizontal[6 * meta + direction.getSide()];
        direction = Direction.getDirectionFromSide(index);

        switch (direction) {
            case Y_POS:
                return 2;
            case X_POS:
                return 3;
            case Y_NEG:
                return 4;
        }

        return -1;
    }

    @Override
    public Connection getItemIOForSide(Direction direction) {
        int meta = worldObj.getBlockMetadata(x, y, z);
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
