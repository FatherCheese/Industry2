package baboon.industry.block.machines.basic.entity;

import baboon.industry.IndustryConfig;
import baboon.industry.block.entity.TileEntityEnergyConductorDamageable;
import baboon.industry.item.IndustryItems;
import baboon.industry.recipe.RecipesCannery;
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

public class TileEntityMachineCannery extends TileEntityEnergyConductorDamageable implements IInventory, IItemIO {
    private ItemStack[] contents;
    private final RecipesCannery recipes = new RecipesCannery();
    private int currentSpeed = 0;
    private int currentEnergy = 0;
    private int currentTransformers = 0;
    public boolean active = false;
    public int currentMachineTime = 0;
    public int maxMachineTime = 200;

    public TileEntityMachineCannery() {
        contents = new ItemStack[9];

        setCapacity(IndustryConfig.cfg.getInt("Energy Values.lvMachineStorage"));
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.lvIO"));
        setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.lvIO"));

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
        return "IndustryMachineCannery";
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

    private boolean isProducible(ItemStack itemStack) {
        return (recipes.getRecipeList().containsKey(itemStack.getItem().id) && contents[2].getItem() != IndustryItems.ingotUranium && contents[3].getItem() == IndustryItems.canEmpty) ||
                (contents[2].getItem() == IndustryItems.ingotUranium && contents[3].getItem() == IndustryItems.cellEmpty && contents[3].getItem() != IndustryItems.canEmpty);
    }

    private boolean canProduce() {
        if ((contents[2] == null || contents[2].getItem() == null) || (contents[3] == null || contents[3].getItem() == null)) {
            return false;
        }

        if (isProducible(contents[2])) {
            ItemStack resultStack = recipes.getRecipeResult(contents[2].getItem().id);
            int canStack = recipes.getRecipeResult(contents[2].getItem().id).stackSize;

            if (contents[3].stackSize < canStack || contents[3].stackSize - canStack < 0)
                return false;

            return contents[4] == null || contents[4].getItem() == resultStack.getItem() && contents[4].stackSize + resultStack.stackSize <= resultStack.getMaxStackSize();
        }

        return false;
    }

    private void produceItem() {
        if (canProduce()) {
            ItemStack resultStack = recipes.getRecipeResult(contents[2].getItem().id);
            if (resultStack == null)
                return;

            int canStack = recipes.getRecipeResult(contents[2].getItem().id).stackSize;
            if (contents[4] == null)
                contents[4] = resultStack.copy();
            else
                if (contents[4].getItem().id == resultStack.getItem().id)
                    contents[4].stackSize += resultStack.stackSize;

                --contents[2].stackSize;
                contents[3].stackSize -= canStack;

                if (contents[2].stackSize <= 0)
                    contents[2] = null;

                if (contents[3].stackSize <= 0)
                    contents[3] = null;
        }
    }

    @Override
    public void onInventoryChanged() {
        currentSpeed = 0;
        currentEnergy = 0;
        currentTransformers = 0;
        maxMachineTime = 200;
        capacity = IndustryConfig.cfg.getInt("Energy Values.lvMachineStorage");

        for (int upgradesSize = 4; upgradesSize < contents.length; upgradesSize++) {
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
        super.onInventoryChanged();
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        boolean hasEnergy = energy > 0;

        if (!worldObj.isClientSide) {
            if (getStackInSlot(0) != null && getStackInSlot(0).getItem() instanceof ItemEnergyContainer) {
                provide(getStackInSlot(0), getMaxProvide(), false);
                onInventoryChanged();
            }

            if (getStackInSlot(1) != null && getStackInSlot(1).getItem() instanceof ItemEnergyContainer) {
                receive(getStackInSlot(1), getMaxReceive(), false);
                onInventoryChanged();
            }

            if (energy > capacity)
                energy = 0;

            switch (currentTransformers) {
                case 1:
                    setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.mvIO"));
                    break;
                case 2:
                    setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.hvIO"));
                    break;
                case 3:
                case 4:
                    setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.ehvIO"));
                    break;
            }

            if (currentMachineTime == 0 || currentMachineTime > 0 && contents[4] == null) {
                onInventoryChanged();

                if (hasEnergy && canProduce()) {
                    ++currentMachineTime;
                    --energy;

                    if (currentSpeed > 0 && energy - 6 * currentSpeed >= 0)
                        energy -= 6 * currentSpeed;

                    if (currentMachineTime == maxMachineTime) {
                        currentMachineTime = 0;
                        produceItem();
                        onInventoryChanged();
                    }
                } else
                    currentMachineTime = 0;
            }
        }
    }

    @Override
    public void writeToNBT(CompoundTag CompoundTag) {
        super.writeToNBT(CompoundTag);
        CompoundTag.putInt("MachineTime", currentMachineTime);
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
        int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        int index = Sides.orientationLookUpHorizontal[6 * meta + direction.getSide()];
        direction = Direction.getDirectionFromSide(index);

        switch (direction) {
            case Y_POS:
                return 2;
            case Y_NEG:
                return 3;
            case X_POS:
                return 4;
        }

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
