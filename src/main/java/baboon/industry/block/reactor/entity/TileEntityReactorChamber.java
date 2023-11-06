package baboon.industry.block.reactor.entity;

import baboon.industry.IndustryConfig;
import baboon.industry.block.IndustryBlocks;
import baboon.industry.item.IndustryItems;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;
import sunsetsatellite.energyapi.impl.TileEntityEnergyConductor;

import java.util.Random;

public class TileEntityReactorChamber extends TileEntityEnergyConductor implements IInventory {
    private ItemStack[] contents;
    private int uraniumRods = 0;
    public int heat = 0;
    public int maxHeat = 1000;

    public TileEntityReactorChamber() {
        contents = new ItemStack[9];
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
        return "IndustryReactorChamber";
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

    private void overheat() {
        Random random = new Random();
        double x = xCoord + random.nextDouble();
        double y = yCoord + random.nextDouble();
        double z = zCoord + random.nextDouble();
        worldObj.spawnParticle("smoke", x, y + 0.22, z, 0.0, 0.0, 0.0);
        worldObj.spawnParticle("flame", x, y + 0.22, z, 0.0, 0.0, 0.0);

        if (heat >= maxHeat)
            for (int explosionSize = 0; explosionSize < uraniumRods; explosionSize++)
                worldObj.createExplosion(null, x, y, z, 3.0f);
    }

    @Override
    public void updateEntity() {
        if (!worldObj.isClientSide) {

            for (int i : new int[]{xCoord - 1, xCoord + 1}) {
                if (worldObj.getBlockId(i, yCoord, zCoord) == IndustryBlocks.nuclearChamber.id)
                    contents = new ItemStack[contents.length + 9];
            }

            for (int i : new int[]{yCoord - 1, yCoord + 1}) {
                if (worldObj.getBlockId(xCoord, i, zCoord) == IndustryBlocks.nuclearChamber.id)
                    contents = new ItemStack[contents.length + 9];
            }

            for (int i : new int[]{zCoord - 1, zCoord + 1}) {
                if (worldObj.getBlockId(xCoord, yCoord, i) == IndustryBlocks.nuclearChamber.id)
                    contents = new ItemStack[contents.length + 9];
            }

            for (ItemStack content : contents) {
                if (content != null) {
                    if (content.itemID == IndustryItems.cellUranium.id) {
                        heat += 4;
                        ++energy;
                        uraniumRods += 1;
                    }

                    if (content.itemID == IndustryItems.cellCoolant.id)
                        --heat;
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
