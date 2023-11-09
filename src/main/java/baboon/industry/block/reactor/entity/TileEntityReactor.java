package baboon.industry.block.reactor.entity;

import baboon.industry.IndustryConfig;
import baboon.industry.block.IndustryBlocks;
import baboon.industry.item.IndustryItems;
import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.util.helper.Side;
import sunsetsatellite.energyapi.impl.TileEntityEnergyConductor;
import sunsetsatellite.sunsetutils.util.Connection;
import sunsetsatellite.sunsetutils.util.Direction;

import java.util.Random;

public class TileEntityReactor extends TileEntityEnergyConductor implements IInventory {
    private ItemStack[] contents = new ItemStack[6 * 9];
    public int chamberCount = 0;
    private int uraniumCell = 0;
    private int coolantCell = 0;
    private int uraniumTimer = 0;
    private int coolantTimer = 0;
    public int heat = 0;
    public int maxHeat = 1000;

    public TileEntityReactor() {
        setCapacity(IndustryConfig.cfg.getInt("Energy Values.ehvStorage"));
        setMaxProvide(IndustryConfig.cfg.getInt("Energy Values.extraHighVoltage"));
        setMaxReceive(0);

        for (sunsetsatellite.sunsetutils.util.Direction dir : Direction.values())
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
        return "IndustryReactor";
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        if (this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this) {
            return false;
        }
        return entityPlayer.distanceToSqr((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
    }

    private void overheat() {
        Random random = new Random();
        double x = xCoord + random.nextDouble();
        double y = yCoord + random.nextDouble();
        double z = zCoord + random.nextDouble();
        worldObj.spawnParticle("smoke", x, y + 0.22, z, 0.0, 0.0, 0.0);
        worldObj.spawnParticle("flame", x, y + 0.22, z, 0.0, 0.0, 0.0);

        if (heat >= maxHeat)
            for (int exploX = (int) (x - 1); exploX < x + 1; exploX++)
                for (int exploY = (int) (y - 1); exploY < y + 1; exploY++)
                    for (int exploZ = (int) (z - 1); exploZ < z + 1; exploZ++) {
                        worldObj.createExplosion(null, x, y, z, 3.0f);
                        worldObj.setBlock(xCoord, yCoord, zCoord, 0);
                    }
    }

    private void checkSides() {
        chamberCount = 0;
        Side[] sides = new Side[]{Side.NORTH, Side.SOUTH, Side.EAST, Side.WEST, Side.BOTTOM, Side.TOP};
        for (Side side : sides) {
            int x = xCoord + side.getOffsetX();
            int y = yCoord + side.getOffsetY();
            int z = zCoord + side.getOffsetZ();
            if (worldObj.getBlockId(x, y, z) == IndustryBlocks.nuclearChamber.id)
                chamberCount += 1;
        }
    }

    private void handleInventoryChange() {
        ItemStack[] newContents = new ItemStack[chamberCount * 9];
        if (newContents.length >= contents.length)
            System.arraycopy(contents, 0, newContents, 0, contents.length);
        else {
            System.arraycopy(contents, 0, newContents, 0, newContents.length);

            for (int i = newContents.length; i < contents.length; i++) {
                Random random = new Random();
                ItemStack itemstack = contents[i];
                if (itemstack == null) continue;
                float randX = random.nextFloat() * 0.8f + 0.1f;
                float randY = random.nextFloat() * 0.8f + 0.1f;
                float randZ = random.nextFloat() * 0.8f + 0.1f;
                while (itemstack.stackSize > 0) {
                    int i1 = random.nextInt(21) + 10;
                    if (i1 > itemstack.stackSize)
                        i1 = itemstack.stackSize;

                    itemstack.stackSize -= i1;
                    EntityItem entityitem = new EntityItem(worldObj, (float) xCoord + randX, (float) yCoord + randY, (float) zCoord + randZ, new ItemStack(itemstack.itemID, i1, itemstack.getMetadata()));
                    float f3 = 0.05f;
                    entityitem.xd = (float) random.nextGaussian() * f3;
                    entityitem.yd = (float) random.nextGaussian() * f3 + 0.2f;
                    entityitem.zd = (float) random.nextGaussian() * f3;
                    worldObj.entityJoinedWorld(entityitem);
                }
            }
        }
        contents = newContents;
    }

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
        System.out.println(maxHeat);
        super.onInventoryChanged();
    }

    private int adjacentUranium(int id){
        int num = 0;
        int[] offsets = new int[]{-1, 1, 9, -9};
        for (int i: offsets) {
            if (isUranium(id + i)) num++;
        }
        return num;
    }

    private boolean isUranium(int id){
        if (id < 0) return false;
        if (id >= contents.length) return false;
        if (contents[id] == null) return false;
        return contents[id].getItem() == IndustryItems.cellUranium;
    }

    @Override
    public void updateEntity() {
        if (worldObj.isClientSide) {
            return;
        }

        checkSides();
        if (contents.length != chamberCount * 9)
            handleInventoryChange();

        uraniumTimer++;
        coolantTimer++;

        boolean damageUranium = false;
        if (uraniumTimer >= 20) {
            damageUranium = true;
            uraniumTimer = 0;
        }

        boolean damageCoolant = false;
        if (coolantTimer >= 20 && heat > 0) {
            damageCoolant = true;
            coolantTimer = 0;
        }

        for (int i = 0; i < contents.length; i++) {
            ItemStack stack = contents[i];
            if (stack == null)
                continue;

            if (damageUranium && stack.getItem() == IndustryItems.cellUranium) {
                stack.damageItem(1, null);
                heat += 4;
            }

            if (damageCoolant && stack.getItem() == IndustryItems.cellCoolant) {
                int adjUranium = adjacentUranium(i);
                stack.damageItem(adjUranium, null);
                heat -= adjUranium;
                heat = Math.max(heat, 0);
            }
            if (stack.stackSize <= 0){
                contents[i] = null;
            }
        }

        if (uraniumCell <= 0 && heat - 1 >= 0)
            --heat;

        energy += uraniumCell;

        if (heat >= maxHeat / 2)
            overheat();

        super.updateEntity();
    }

    @Override
    public void writeToNBT(CompoundTag compoundTag) {
        super.writeToNBT(compoundTag);
        compoundTag.putInt("Heat", heat);
        compoundTag.putInt("Energy", energy);
        compoundTag.putInt("Chambers", chamberCount);

        ListTag nbttaglist = new ListTag();
        for (int i = 0; i < this.contents.length; ++i) {
            if (this.contents[i] == null) continue;
            CompoundTag nbttagcompound1 = new CompoundTag();
            nbttagcompound1.putByte("Slot", (byte)i);
            this.contents[i].writeToNBT(nbttagcompound1);
            nbttaglist.addTag(nbttagcompound1);
        }
        compoundTag.put("Items", nbttaglist);
    }

    @Override
    public void readFromNBT(CompoundTag compoundTag) {
        super.readFromNBT(compoundTag);
        heat = compoundTag.getInteger("Heat");
        energy = compoundTag.getInteger("Energy");
        chamberCount = compoundTag.getInteger("Chambers");
        handleInventoryChange();
        
        ListTag nbttaglist = compoundTag.getList("Items");
        this.contents = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            CompoundTag nbttagcompound1 = (CompoundTag) nbttaglist.tagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 0xFF;
            if (j >= contents.length) continue;
            this.contents[j] = ItemStack.readItemStackFromNbt(nbttagcompound1);
        }
    }
}
