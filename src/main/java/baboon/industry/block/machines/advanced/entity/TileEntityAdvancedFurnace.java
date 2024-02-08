package baboon.industry.block.machines.advanced.entity;

import baboon.industry.block.I2Blocks;
import baboon.industry.block.machines.advanced.BlockAdvancedFurnace;
import baboon.industry.item.I2Items;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryBlastFurnace;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryFurnace;
import net.minecraft.core.item.ItemStack;

import java.util.List;

public class TileEntityAdvancedFurnace extends TileEntityAdvancedBase {
    private final List<RecipeEntryFurnace> furnaceEntries = Registries.RECIPES.getAllFurnaceRecipes();
    private final List<RecipeEntryBlastFurnace> blastFurnaceEntries = Registries.RECIPES.getAllBlastFurnaceRecipes();
    private boolean blasting = false;

    @Override
    public String getInvName() {
        return "IndustryAdvancedFurnace";
    }

    private ItemStack getSmeltingResult(ItemStack stack){
        if (blasting) {
            RecipeEntryBlastFurnace be = getBlastFurnaceEntry(stack);
            return be != null ? be.getOutput() : null;
        }
        RecipeEntryFurnace fe = getFurnaceEntry(stack);
        return fe != null ? fe.getOutput() : null;
    }
    private RecipeEntryFurnace getFurnaceEntry(ItemStack stack){
        for (RecipeEntryFurnace furnaceEntry : furnaceEntries){
            if (furnaceEntry.getInput().matches(stack)) return furnaceEntry;
        }
        return null;
    }
    private RecipeEntryBlastFurnace getBlastFurnaceEntry(ItemStack stack){
        for (RecipeEntryBlastFurnace entryBlastFurnace : blastFurnaceEntries){
            if (entryBlastFurnace.getInput().matches(stack)) return entryBlastFurnace;
        }
        return null;
    }

    private boolean canProduce(ItemStack input, ItemStack output) {
        if (input == null || input.getItem() == null)
            return false;

        ItemStack resultStack = getSmeltingResult(input);
        if (resultStack != null) {
            return output == null || output.getItem() == resultStack.getItem() &&
                    output.stackSize + resultStack.stackSize <= resultStack.getMaxStackSize();
        }
        return false;
    }

    private void produceFirstItem() {
        if (canProduce(contents[2], contents[4])) {
            ItemStack itemStack = getSmeltingResult(contents[2]);

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
            ItemStack itemStack = getSmeltingResult(contents[3]);

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
    public void onInventoryChanged() {
        blasting = false;
        for (int upgradesSize = 7; upgradesSize < 11; upgradesSize++) {
            if (contents[upgradesSize] != null) {
                if (contents[upgradesSize].getItem() == I2Items.upgradeBlasting)
                    blasting = true;
            }
        }
        super.onInventoryChanged();
    }

    @Override
    public void tick() {
        super.tick();
        boolean hasEnergy = energy > 0;

        if (!worldObj.isClientSide) {
            if (currentMachineTime == 0 || currentMachineTime > 0 && contents[2] != null || contents[3] != null) {
                BlockAdvancedFurnace.updateBlockState(true, worldObj, x, y, z);
                onInventoryChanged();

                if (hasEnergy && (canProduce(contents[2], contents[4]) || canProduce(contents[3], contents[5]))) {
                    ++currentMachineTime;
                    energy -= 6;
                    active = true;

                    if (currentSpeed > 0 && energy - 40 * currentSpeed >= -40)
                        energy -= 40 * currentSpeed;

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
                worldObj.notifyBlockChange(x, y, z, I2Blocks.advancedMachineFurnace.id);
        }
    }
}
