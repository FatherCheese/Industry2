package baboon.industry.block.generator.entity;

import net.minecraft.core.block.Block;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;
import sunsetsatellite.energyapi.impl.TileEntityEnergyConductor;

public class TileEntitySolarBase extends TileEntityEnergyConductor implements IInventory {
    protected ItemStack[] contents;
    public int generatedEnergy = 0;
    private final int solarVoltage;

    public TileEntitySolarBase(int solarVoltage) {
        contents = new ItemStack[2];
        this.solarVoltage = solarVoltage;
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

    private boolean isFacingSky() {
        for (int heightCoords = yCoord + 1; heightCoords < 255; heightCoords++) {
            Block block = Block.getBlock(worldObj.getBlockId(xCoord, heightCoords, zCoord));
            if (block != null && block.isOpaqueCube())
                return false;
        }
        return true;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!worldObj.isClientSide) {
            if (!isFacingSky())
                generatedEnergy = 0;

            if (energy < capacity && isFacingSky()) {
                generatedEnergy = 2 * solarVoltage;

                generatedEnergy -= worldObj.skyDarken * solarVoltage;

                if (generatedEnergy > 0)
                    energy = Math.min(energy + generatedEnergy, capacity);
            }
        }
    }
}
