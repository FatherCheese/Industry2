package baboon.industry.block.reactor.entity;

import baboon.industry.IndustryConfig;
import baboon.industry.block.IndustryBlocks;
import baboon.industry.item.IndustryItems;
import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.util.helper.Side;
import sunsetsatellite.energyapi.impl.TileEntityEnergyConductor;
import sunsetsatellite.sunsetutils.util.Connection;
import sunsetsatellite.sunsetutils.util.Direction;

import java.util.Random;

public class TileEntityReactorNew extends TileEntityEnergyConductor implements IInventory {
    private ItemStack[] contents;
    private int uraniumCell = 0;
    private int coolantCell = 0;
    private int uraniumTimer = 0;
    private int coolantTimer = 0;
    public int heat = 0;
    public int maxHeat = 1000;
    public boolean isDisabled = false;

    public TileEntityReactorNew() {
        contents = new ItemStack[9 * 6];
        setCapacity(IndustryConfig.cfg.getInt("Energy Values.ehvIO"));
        setMaxProvide(IndustryConfig.cfg.getInt("Energy Values.ehvIO"));

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
        return "IndustryReactor";
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this)
            return false;

        return entityPlayer.distanceToSqr(xCoord + 0.5f, yCoord + 0.5f, zCoord + 0.5f) <= 64;
    }
    private static boolean sendInventoryPacket = true;
    @Override
    public void onInventoryChanged() {
        uraniumCell = 0;
        coolantCell = 0;
        maxHeat = 1000;

        for (ItemStack content : contents) {
            if (content != null) {
                if (content.getItem() == IndustryItems.cellUranium)
                    uraniumCell += 1;

                if (content.getItem() == IndustryItems.cellCoolant)
                    coolantCell += 1;

                if (content.getItem() == IndustryItems.reactorPlate)
                    maxHeat += 250;
            }
        }
        if (sendInventoryPacket){
            super.onInventoryChanged();
        }
    }

    public boolean isAssembled() {
        Side[] sides = new Side[]{Side.NORTH, Side.SOUTH, Side.EAST, Side.WEST, Side.BOTTOM, Side.TOP};
        for (Side side : sides) {
            int x = xCoord + side.getOffsetX();
            int y = yCoord + side.getOffsetY();
            int z = zCoord + side.getOffsetZ();
            if (worldObj.getBlockId(x, yCoord, z) != IndustryBlocks.nuclearChamber.id &&
                    worldObj.getBlockId(xCoord, y, zCoord) != IndustryBlocks.nuclearIO.id)
                return false;
        }
        return true;
    }

    private void overHeat() {
        Random random = new Random();
        double x = xCoord + random.nextDouble() * 2;
        double y = yCoord + random.nextDouble();
        double z = zCoord + random.nextDouble() * 2;
        worldObj.spawnParticle("smoke", x, y + 0.22, z, 0.0, 0.0, 0.0);
        worldObj.spawnParticle("flame", x, y + 0.22, z, 0.0, 0.0, 0.0);

        if (heat >= maxHeat)
            for (int exploX = (int) (x - 1); exploX < x + 1; exploX++)
                for (int exploY = (int) (y - 1); exploY < y + 1; exploY++)
                    for (int exploZ = (int) (z - 1); exploZ < z + 1; exploZ++) {
                        worldObj.createExplosion(null, x, y, z, 6.0f);
                        worldObj.setBlock(xCoord, yCoord, zCoord, 0);
                    }
    }

    private int adjacentUranium(int id) {
        int num = 0;
        int[] offsets = new int[]{-1, 1, 9, -9};
        for (int i : offsets) {
            if (isUranium(id + i)) num++;
        }
        return num;
    }

    private boolean isUranium(int id) {
        if (id < 0) return false;
        if (id >= contents.length) return false;
        if (contents[id] == null) return false;
        return contents[id].getItem() == IndustryItems.cellUranium;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!worldObj.isClientSide && isAssembled() && !isDisabled) {
            ++coolantTimer;
            ++uraniumTimer;

            boolean damageUranium = false;
            if (uraniumTimer >= 20) {
                uraniumTimer = 0;
                damageUranium = true;
            }

            boolean damageCoolant = false;
            if (coolantTimer >= 20) {
                coolantTimer = 0;
                damageCoolant = true;
            }

            for (int i = 0; i < contents.length; i++) {
                ItemStack stack = contents[i];
                if (stack == null)
                    continue;

                if (damageUranium && stack.getItem() == IndustryItems.cellUranium) {
                    heat += 4;
                    stack.damageItem(1, null);
                }

                if (damageCoolant && stack.getItem() == IndustryItems.cellCoolant) {
                    int adjUranium = adjacentUranium(i);
                    stack.damageItem(adjUranium, null);
                    heat -= adjUranium;
                    heat = Math.max(heat, 0);
                }

                if (stack.stackSize <= 0) {
                    contents[i] = null;
                }
            }

            if (energy + 5 * uraniumCell <= capacity)
                energy += 5 * uraniumCell;
        }

        if ((uraniumCell <= 0  || isDisabled) && heat - 1 >= 0)
            --heat;

        if (heat >= maxHeat / 2)
            overHeat();
    }

    @Override
    public void writeToNBT(CompoundTag compoundTag) {
        super.writeToNBT(compoundTag);
        compoundTag.putInt("Heat", heat);
        compoundTag.putInt("MaxHeat", maxHeat);
        compoundTag.putInt("Energy", energy);
        compoundTag.putInt("UraniumCells", uraniumCell);
        compoundTag.putInt("CoolantCells", coolantCell);
        compoundTag.putBoolean("Disabled", isDisabled);

        ListTag nbtTagList = new ListTag();
        for (int i = 0; i < this.contents.length; ++i) {
            if (this.contents[i] == null) continue;
            CompoundTag nbtTagCompound = new CompoundTag();
            nbtTagCompound.putByte("Slot", (byte)i);
            this.contents[i].writeToNBT(nbtTagCompound);
            nbtTagList.addTag(nbtTagCompound);
        }
        compoundTag.put("Items", nbtTagList);
    }

    @Override
    public void readFromNBT(CompoundTag compoundTag) {
        super.readFromNBT(compoundTag);
        heat = compoundTag.getInteger("Heat");
        maxHeat = compoundTag.getInteger("MaxHeat");
        energy = compoundTag.getInteger("Energy");
        uraniumCell = compoundTag.getInteger("UraniumCells");
        coolantCell = compoundTag.getInteger("CoolantCells");
        isDisabled = compoundTag.getBoolean("Disabled");

        ListTag nbtTagList = compoundTag.getList("Items");
        this.contents = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nbtTagList.tagCount(); ++i) {
            CompoundTag nbtCompoundTag = (CompoundTag) nbtTagList.tagAt(i);
            int j = nbtCompoundTag.getByte("Slot") & 0xFF;
            if (j >= contents.length) continue;
            this.contents[j] = ItemStack.readItemStackFromNbt(nbtCompoundTag);
        }
    }
}
