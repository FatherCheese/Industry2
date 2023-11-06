package baboon.industry.block.reactor.entity;

import baboon.industry.IndustryConfig;
import baboon.industry.block.IndustryBlocks;
import baboon.industry.item.IndustryItems;
import baboon.industry.item.ItemCellUranium;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.util.helper.Side;
import sunsetsatellite.energyapi.impl.TileEntityEnergyConductor;

import java.util.Random;

public class TileEntityReactor extends TileEntityEnergyConductor implements IInventory {
    private ItemStack[] contents;
    private int chamberCount;
    private int uraniumRods = 0;
    public int heat = 0;
    public int maxHeat = 1000;

    public TileEntityReactor() {
        contents = new ItemStack[0];
        setCapacity(IndustryConfig.cfg.getInt("Energy Values.ehvStorage"));
        setMaxProvide(IndustryConfig.cfg.getInt("Energy Values.extraHighVoltage"));
        setMaxReceive(0);
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
        return false;
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
        for (Side side: sides) {
            int x = xCoord + side.getOffsetX();
            int y = yCoord + side.getOffsetY();
            int z = zCoord + side.getOffsetZ();
            if (worldObj.getBlockId(x, y, z) == IndustryBlocks.nuclearChamber.id)
                chamberCount += 1;
        }
    }
    private void handleInventoryChange(){
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
                    EntityItem entityitem = new EntityItem(worldObj, (float)xCoord + randX, (float)yCoord + randY, (float)zCoord + randZ, new ItemStack(itemstack.itemID, i1, itemstack.getMetadata()));
                    float f3 = 0.05f;
                    entityitem.xd = (float)random.nextGaussian() * f3;
                    entityitem.yd = (float)random.nextGaussian() * f3 + 0.2f;
                    entityitem.zd = (float)random.nextGaussian() * f3;
                    worldObj.entityJoinedWorld(entityitem);
                }
            }
        }
        contents = newContents;
    }

    @Override
    public void onInventoryChanged() {
        super.onInventoryChanged();
        uraniumRods = 0;
        for (int cells = 0; cells < contents.length; cells++) {
            if (contents[cells] != null) {
                if (contents[cells].getItem() instanceof ItemCellUranium)
                    uraniumRods += cells

                System.out.println(uraniumRods);

                if (contents[cells].itemID == IndustryItems.cellCoolant.id) {
                    heat -= 1;
                    if (heat > 0)
                        contents[cells].damageItem(1, null);
                }

                if (contents[cells].stackSize <= 0)
                    contents[cells] = null;
            }
        }
    }

    @Override
    public void updateEntity() {
        if (!worldObj.isClientSide) {

            checkSides();
            if (contents.length != chamberCount * 9)
                handleInventoryChange();

            uraniumRods = 0;
            for (int cells = 0; cells < contents.length; cells++) {
                if (contents[cells] != null) {
                    if (contents[cells].getItem() instanceof ItemCellUranium) {
                        heat += 3;
                        uraniumRods += cells;
                        contents[cells].damageItem(1, null);
                    }

                    System.out.println(uraniumRods);

                    if (contents[cells].itemID == IndustryItems.cellCoolant.id) {
                        heat -= 1;
                        if (heat > 0)
                            contents[cells].damageItem(1, null);
                    }

                    if (contents[cells].stackSize <= 0)
                        contents[cells] = null;
                }
            }

            if (uraniumRods <= 0)
                --heat;

            if (heat >= maxHeat / 2)
                overheat();
        }
        super.updateEntity();
    }
}
