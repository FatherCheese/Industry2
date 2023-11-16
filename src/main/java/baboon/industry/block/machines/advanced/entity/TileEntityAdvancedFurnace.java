package baboon.industry.block.machines.advanced.entity;

import baboon.industry.block.IndustryBlocks;
import baboon.industry.block.machines.advanced.BlockAdvancedFurnace;
import net.minecraft.core.crafting.recipe.RecipesFurnace;
import net.minecraft.core.item.ItemStack;

public class TileEntityAdvancedFurnace extends TileEntityAdvancedBase {

    @Override
    public String getInvName() {
        return "IndustryAdvancedFurnace";
    }

    private boolean isProducible(ItemStack itemStack) {
        return RecipesFurnace.smelting().getSmeltingList().containsKey(itemStack.getItem().id);
    }

    private boolean canProduce(ItemStack input, ItemStack output) {
        if (input == null || input.getItem() == null)
            return false;

        if (isProducible(input)) {
            ItemStack resultStack = RecipesFurnace.smelting().getSmeltingResult(input.getItem().id);

            return output == null || output.getItem() == resultStack.getItem() &&
                    output.stackSize + resultStack.stackSize <= resultStack.getMaxStackSize();
        }
        return false;
    }

    private void produceFirstItem() {
        if (canProduce(contents[2], contents[4])) {
            ItemStack itemStack = RecipesFurnace.smelting().getSmeltingResult(contents[2].getItem().id);
            if (itemStack == null)
                return;

            if (contents[4] == null)
                contents[4] = itemStack.copy();
            else
                if (contents[4].getItem().id == itemStack.getItem().id)
                    contents[4].stackSize += itemStack.stackSize;

                --contents[2].stackSize;

                if (contents[2].stackSize <= 0)
                    contents[2] = null;
        }
    }

    private void produceSecondItem() {
        if (canProduce(contents[3], contents[5])) {
            ItemStack itemStack = RecipesFurnace.smelting().getSmeltingResult(contents[3].getItem().id);
            if (itemStack == null)
                return;

            if (contents[5] == null)
                contents[5] = itemStack.copy();
            else
            if (contents[5].getItem().id == itemStack.getItem().id)
                contents[5].stackSize += itemStack.stackSize;

            --contents[3].stackSize;

            if (contents[3].stackSize <= 0)
                contents[3] = null;
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        boolean hasEnergy = energy > 0;

        if (!worldObj.isClientSide) {
            if (currentMachineTime == 0 || currentMachineTime > 0 && contents[2] != null || contents[3] != null) {
                BlockAdvancedFurnace.updateBlockState(true, worldObj, xCoord, yCoord, zCoord);
                onInventoryChanged();

                if (hasEnergy && (canProduce(contents[2], contents[4]) || canProduce(contents[3], contents[5]))) {
                    ++currentMachineTime;
                    energy -= 6;
                    active = true;

                    if (currentSpeed > 0 && energy - 36 * currentSpeed >= 0)
                        energy -= 36 * currentSpeed;

                    if (redstone > 0) {
                        currentMachineTime *= (int) ((double) redstone / 2000);

                        if (canProduce(contents[2], contents[4])) {
                            redstone -= 10;
                            energy -= 6;
                        }

                        if (canProduce(contents[3], contents[5])) {
                            redstone -= 10;
                            energy -= 6;
                        }
                    }

                    if (currentMachineTime >= maxMachineTime) {
                        currentMachineTime = 0;
                        active = false;
                        onInventoryChanged();

                        if (contents[2] != null)
                            produceFirstItem();

                        if (contents[3] != null)
                            produceSecondItem();
                    }
                }
            } else {
                currentMachineTime = 0;
                active = false;
            }

            if (active)
                worldObj.notifyBlockChange(xCoord, yCoord, zCoord, IndustryBlocks.advancedMachineFurnace.id);
        }
    }
}
