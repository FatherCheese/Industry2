package baboon.industry.block.machines.basic.entity;

import baboon.industry.block.IndustryBlocks;
import baboon.industry.block.machines.basic.BlockMachineFurnace;
import baboon.industry.item.IndustryItems;
import net.minecraft.core.crafting.legacy.recipe.RecipesBlastFurnace;
import net.minecraft.core.crafting.legacy.recipe.RecipesFurnace;
import net.minecraft.core.item.ItemStack;

public class TileEntityMachineFurnace extends TileEntityMachineBase {
    private boolean blasting = false;
    @Override
    public String getInvName() {
        return "IndustryMachineFurnace";
    }
    private boolean isProducible(ItemStack itemStack) {
        if (blasting)
            return RecipesBlastFurnace.getInstance().getSmeltingList().containsKey(itemStack.getItem().id);
        return RecipesFurnace.getInstance().getSmeltingList().containsKey(itemStack.getItem().id);
    }
    private void produceItem() {
        if (canProduce()) {

            ItemStack itemStack;

            if (blasting)
                itemStack = RecipesBlastFurnace.getInstance().getSmeltingResult(contents[2].getItem().id);
            else
                itemStack = RecipesFurnace.getInstance().getSmeltingResult(contents[2].getItem().id);

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

    private boolean canProduce() {
        if (contents[2] != null && contents[2].getItem() != null) {
            if (isProducible(contents[2])) {
                ItemStack resultStack;

                if (blasting)
                    resultStack = RecipesBlastFurnace.getInstance().getSmeltingResult(contents[2].getItem().id);
                else
                    resultStack = RecipesFurnace.getInstance().getSmeltingResult(contents[2].getItem().id);

                return contents[3] == null || contents[3].getItem() == resultStack.getItem() &&
                        contents[3].stackSize + resultStack.stackSize <= resultStack.getMaxStackSize();
            }
        }
        return false;
    }

    @Override
    public void onInventoryChanged() {
        blasting = false;
        for (int upgradesSize = 4; upgradesSize < contents.length; upgradesSize++) {
            if (contents[upgradesSize] != null) {
                if (contents[upgradesSize].getItem() == IndustryItems.upgradeBlasting)
                    blasting = true;
            }
        }
        super.onInventoryChanged();
    }

    @Override
    public void tick() {
        super.tick();
        boolean hasEnergy = energy > 0;
        boolean machineUpdated = false;

        if (!worldObj.isClientSide) {
            if (worldObj.getBlockId(x, y, z) == IndustryBlocks.machineFurnace.id &&
            currentMachineTime == 0 &&
            contents[2] == null) {
                BlockMachineFurnace.updateBlockState(true, worldObj, x, y, z);
                machineUpdated = true;
            }

            if (hasEnergy && canProduce()) {
                ++currentMachineTime;
                energy -= 3;
                active = true;

                if (currentSpeed > 0 && energy - 20 * currentSpeed >= -20)
                    energy -= 20 * currentSpeed;

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
                worldObj.notifyBlockChange(x, y, z, IndustryBlocks.machineFurnace.id);
        }
    }
}
