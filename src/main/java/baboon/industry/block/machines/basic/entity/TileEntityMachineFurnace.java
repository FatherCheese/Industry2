package baboon.industry.block.machines.basic.entity;

import baboon.industry.block.IndustryBlocks;
import baboon.industry.block.machines.basic.BlockMachineFurnace;
import baboon.industry.item.IndustryItems;
import net.minecraft.core.crafting.recipe.RecipesBlastFurnace;
import net.minecraft.core.crafting.recipe.RecipesFurnace;
import net.minecraft.core.item.ItemStack;

public class TileEntityMachineFurnace extends TileEntityMachineBase {
    private final RecipesFurnace recipesFurnace = RecipesFurnace.smelting();
    private final RecipesBlastFurnace recipesBlastFurnace = RecipesBlastFurnace.smelting();
    private boolean blasting = false;
    @Override
    public String getInvName() {
        return "IndustryMachineFurnace";
    }

    private boolean isProducible(ItemStack itemStack) {
        if (blasting)
            return recipesBlastFurnace.getSmeltingList().containsKey(itemStack.getItem().id);
        return recipesFurnace.getSmeltingList().containsKey(itemStack.getItem().id);
    }

    private boolean canProduce() {
        if (contents[2] != null && contents[2].getItem() != null) {
            if (isProducible(contents[2])) {
                ItemStack resultStack;

                if (blasting)
                    resultStack = recipesBlastFurnace.getSmeltingResult(contents[2].getItem().id);
                else
                    resultStack = recipesFurnace.getSmeltingResult(contents[2].getItem().id);

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

    private void produceItem() {
        if (canProduce()) {

            ItemStack itemStack;

            if (blasting)
                itemStack = recipesBlastFurnace.getSmeltingResult(contents[2].getItem().id);
            else
                itemStack = recipesFurnace.getSmeltingResult(contents[2].getItem().id);

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
                worldObj.notifyBlockChange(xCoord, yCoord, zCoord, IndustryBlocks.machineFurnace.id);
        }
    }
}
