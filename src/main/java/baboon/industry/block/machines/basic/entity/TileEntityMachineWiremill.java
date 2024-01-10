package baboon.industry.block.machines.basic.entity;

import baboon.industry.block.IndustryBlocks;
import baboon.industry.block.machines.basic.BlockMachineWiremill;
import baboon.industry.recipe.RecipesWiremill;
import net.minecraft.core.item.ItemStack;

public class TileEntityMachineWiremill extends TileEntityMachineBase {
    private final RecipesWiremill recipes = new RecipesWiremill();

    @Override
    public String getInvName() {
        return "IndustryMachineWiremill";
    }

    private boolean isProducible(ItemStack itemStack) {
        return recipes.getRecipeList().containsKey(itemStack.getItem().id);
    }

    private boolean canProduce() {
        if (contents[2] != null && contents[2].getItem() != null) {
            if (isProducible(contents[2])) {
                ItemStack resultStack = recipes.getRecipeResult(contents[2].getItem().id);

                return contents[3] == null || contents[3].getItem() == resultStack.getItem() &&
                        contents[3].stackSize + resultStack.stackSize <= resultStack.getMaxStackSize();
            }
        }
        return false;
    }

    private void produceItem() {
        if (canProduce()) {
            ItemStack itemStack = recipes.getRecipeResult(contents[2].getItem().id);

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
    public void tick() {
        super.tick();
        boolean hasEnergy = energy > 0;
        boolean machineUpdated = false;

        if (!worldObj.isClientSide) {
            if (worldObj.getBlockId(x, y, z) == IndustryBlocks.machineWiremill.id &&
            currentMachineTime == 0 &&
            contents[2] == null) {
                BlockMachineWiremill.updateBlockState(true, worldObj, x, y, z);
                machineUpdated = true;
            }

            if (hasEnergy && canProduce()) {
                ++currentMachineTime;
                energy -= 2;
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

            if (active) {
                worldObj.notifyBlockChange(x, y, z, IndustryBlocks.machineWiremill.id);
            }
        }
    }
}
