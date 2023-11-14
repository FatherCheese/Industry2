package baboon.industry.block.generator.entity;

import baboon.industry.IndustryConfig;
import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.world.Dimension;
import net.minecraft.core.world.type.WorldTypes;
import net.minecraft.core.world.weather.Weather;
import sunsetsatellite.energyapi.impl.ItemEnergyContainer;
import sunsetsatellite.energyapi.impl.TileEntityEnergyConductor;
import sunsetsatellite.sunsetutils.util.Connection;
import sunsetsatellite.sunsetutils.util.Direction;

public class TileEntityGeneratorWindmill extends TileEntityEnergyConductor implements IInventory {
    private ItemStack[] contents;
    public int generatedEnergy = 0;

    public TileEntityGeneratorWindmill() {
        contents = new ItemStack[2];

        setCapacity(IndustryConfig.cfg.getInt("Energy Values.elvMachineStorage"));
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.lvIO"));
        setMaxReceive(0);

        setConnection(Direction.X_POS, Connection.OUTPUT);
        setConnection(Direction.Y_NEG, Connection.OUTPUT);
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
        return "IndustryWindmill";
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
    public void updateEntity() {
        super.updateEntity();

        if (!worldObj.isClientSide) {

            if (getStackInSlot(0) != null && getStackInSlot(0).getItem() instanceof ItemEnergyContainer) {
                provide(getStackInSlot(0), getMaxProvide(), false);
            }

            if (energy < capacity) {
                if (worldObj.getWorldType() != WorldTypes.OVERWORLD_EXTENDED || worldObj.getWorldType() != WorldTypes.OVERWORLD_AMPLIFIED) {
                    if (yCoord > 48)
                        generatedEnergy += 5 * (yCoord / 24);
                } else
                    if (yCoord > 96)
                        generatedEnergy += 5 * (yCoord / 48);

                if (worldObj.getCurrentWeather() == Weather.overworldRain ||
                        worldObj.getCurrentWeather() == Weather.overworldRainBlood ||
                        worldObj.getCurrentWeather() == Weather.overworldStorm ||
                        worldObj.getCurrentWeather() == Weather.overworldSnow)
                    generatedEnergy *= 2;

                if (worldObj.dimension == Dimension.paradise)
                    generatedEnergy *= 2;

                if (worldObj.dimension == Dimension.nether || worldObj.worldType == WorldTypes.OVERWORLD_HELL)
                    generatedEnergy = 0;

                if (generatedEnergy > 0)
                    energy = Math.min(energy + generatedEnergy, capacity);
            }
        }
    }

    @Override
    public void writeToNBT(CompoundTag CompoundTag) {
        super.writeToNBT(CompoundTag);
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
