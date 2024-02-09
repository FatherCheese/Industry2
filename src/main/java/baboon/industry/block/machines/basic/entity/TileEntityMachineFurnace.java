package baboon.industry.block.machines.basic.entity;

import baboon.industry.block.I2Blocks;
import baboon.industry.block.machines.basic.BlockMachineFurnace;
import baboon.industry.item.I2Items;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryBlastFurnace;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryFurnace;
import net.minecraft.core.item.ItemStack;

import java.util.List;
import java.util.Objects;

public class TileEntityMachineFurnace extends TileEntityMachineBase {
    private final List<RecipeEntryFurnace> furnaceEntries = Registries.RECIPES.getAllFurnaceRecipes();
    private final List<RecipeEntryBlastFurnace> blastFurnaceEntries = Registries.RECIPES.getAllBlastFurnaceRecipes();
    private boolean blasting = false;
    @Override
    public String getInvName() {
        return "IndustryMachineFurnace";
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

    private void produceItem() {
        if (canProduce()) {
            ItemStack itemStack = Objects.requireNonNull(getSmeltingResult(contents[2]));

            if (contents[3] == null) contents[3] = itemStack.copy();
            else if (contents[3].itemID == itemStack.itemID) contents[3].stackSize += itemStack.stackSize;

            --contents[2].stackSize;

            if (contents[2].stackSize <= 0) contents[2] = null;
        }
    }

    private boolean canProduce() {
        if (contents[2] != null && contents[2].getItem() != null) {
            ItemStack resultStack = getSmeltingResult(contents[2]);
            if (resultStack != null) {
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
        boolean machineUpdated = false;

        if (!worldObj.isClientSide) {
            if (worldObj.getBlockId(x, y, z) == I2Blocks.machineFurnace.id &&
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
                worldObj.notifyBlockChange(x, y, z, I2Blocks.machineFurnace.id);
        }
    }
}
