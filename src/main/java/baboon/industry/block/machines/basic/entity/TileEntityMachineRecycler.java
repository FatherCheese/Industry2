package baboon.industry.block.machines.basic.entity;

import baboon.industry.IndustryTags;
import baboon.industry.block.IndustryBlocks;
import baboon.industry.item.IndustryItems;
import net.minecraft.core.item.ItemStack;

import java.util.Random;

public class TileEntityMachineRecycler extends TileEntityMachineBase {

    @Override
    public String getInvName() {
        return "IndustryMachineRecycler";
    }


    private boolean canProduce() {
        if (contents[2] == null || contents[2].getItem() == null)
            return false;
        else
            return !contents[2].getItem().hasTag(IndustryTags.PREVENT_ITEM_RECYCLING) && contents[2].stackSize <= 64;
    }

    private void produceItem() {
        if (canProduce()) {
            Random random = new Random();
            ItemStack produceStack = new ItemStack(IndustryItems.scrap, 1);

            if (contents[3] == null) {
                if (random.nextInt(4) == 0)
                    contents[3] = produceStack.copy();
            } else
                if (contents[3].itemID == produceStack.itemID)
                    if (random.nextInt(4) == 0)
                        contents[3].stackSize += produceStack.stackSize;

                --contents[2].stackSize;

                if (contents[2].stackSize <= 0)
                    contents[2] = null;
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        boolean hasEnergy = energy > 0;
        boolean machineUpdated = false;

        if (!worldObj.isClientSide) {
            if (worldObj.getBlockId(xCoord, yCoord, zCoord) == IndustryBlocks.machineRecycler.id &&
                    currentMachineTime == 0 &&
                    contents[2] == null) {
                machineUpdated = true;
            }

            if (hasEnergy && canProduce()) {
                ++currentMachineTime;
                --energy;

                if (currentMachineTime == maxMachineTime) {
                    currentMachineTime = 0;
                    produceItem();
                    machineUpdated = true;
                }
            } else {
                currentMachineTime = 0;
                active = false;
            }

            if (machineUpdated)
                onInventoryChanged();
        }
    }
}
