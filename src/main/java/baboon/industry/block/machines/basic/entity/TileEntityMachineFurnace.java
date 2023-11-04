package baboon.industry.block.machines.basic.entity;

import baboon.industry.block.IndustryBlocks;
import baboon.industry.block.machines.basic.BlockMachineFurnace;
import net.minecraft.core.crafting.recipe.RecipesFurnace;
import net.minecraft.core.item.ItemStack;

public class TileEntityMachineFurnace extends TileEntityMachineBase {

    @Override
    public String getInvName() {
        return "IndustryMachineFurnace";
    }

    private boolean isProducible(ItemStack itemStack) {
        return RecipesFurnace.smelting().getSmeltingList().containsKey(itemStack.getItem().id);
    }

    private boolean canProduce() {
        if (contents[2] != null && contents[2].getItem() != null) {
            if (isProducible(contents[2])) {
                ItemStack resultStack = RecipesFurnace.smelting().getSmeltingResult(contents[2].getItem().id);

                return contents[3] == null || contents[3].getItem() == resultStack.getItem() &&
                        contents[3].stackSize + resultStack.stackSize <= resultStack.getMaxStackSize();
            }
        }
        return false;
    }

    private void produceItem() {
        if (canProduce()) {
            ItemStack itemStack = RecipesFurnace.smelting().getSmeltingResult(contents[2].getItem().id);

            if (contents[3] == null)
                contents[3] = itemStack.copy();
            else
                if (contents[3].itemID == itemStack.itemID)
                    contents[3].stackSize += itemStack.stackSize;

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
            if (worldObj.getBlockId(xCoord, yCoord, zCoord) == IndustryBlocks.machineFurnace.id &&
            currentMachineTime == 0 &&
            contents[2] == null) {
                BlockMachineFurnace.updateBlockState(true, worldObj, xCoord, yCoord, zCoord);
                machineUpdated = true;
            }

            if (hasEnergy && canProduce()) {
                ++currentMachineTime;
                --energy;
                active = true;

                if (currentMachineTime == maxMachineTime) {
                    currentMachineTime = 0;
                    produceItem();
                    active = false;
                    machineUpdated = true;
                }
            } else {
                currentMachineTime = 0;
                active = false;
            }

            if (machineUpdated)
                onInventoryChanged();

            if (active)
                worldObj.notifyBlockChange(xCoord, yCoord, zCoord, IndustryBlocks.machineFurnace.id);
        }
    }
}
