package baboon.industry.gui.generator;

import baboon.industry.block.generator.entity.TileEntityGenerator;
import baboon.industry.block.machines.basic.entity.TileEntityMachineBase;
import net.minecraft.core.crafting.ICrafting;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;
import sunsetsatellite.energyapi.template.containers.ContainerEnergy;

public class ContainerGenerator extends ContainerEnergy {
    private int energy = 0;
    private int currentBurnTime = 0;
    private int maxBurnTime = 0;
    InventoryPlayer inventory;
    TileEntityGenerator tileEntity;

    public ContainerGenerator(InventoryPlayer inventory, TileEntityGenerator tileEntity) {
        this.inventory = inventory;
        this.tileEntity = tileEntity;

        tile = tileEntity;
        addSlot(new Slot(tileEntity, 0, 8, 17));
        addSlot(new Slot(tileEntity, 1, 8, 53));
        addSlot(new Slot(tileEntity, 2, 80, 35));

        for(int xSlot = 0; xSlot < 3; ++xSlot)
            for (int ySlot = 0; ySlot < 9; ++ySlot)
                addSlot(new Slot(inventory, ySlot + xSlot * 9 + 9, 8 + ySlot * 18, 84 + xSlot * 18));

        for(int hotbar = 0; hotbar < 9; ++hotbar)
            addSlot(new Slot(inventory, hotbar, 8 + hotbar * 18, 142));
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer entityPlayer) {
        return ((TileEntityGenerator) tile).canInteractWith(entityPlayer);
    }

    @Override
    public void updateInventory() {
        super.updateInventory();

        for(ICrafting crafter : this.crafters) {
            if (this.energy != tileEntity.energy)
                crafter.updateCraftingInventoryInfo(this, 0, tileEntity.energy);

            if (this.currentBurnTime != tileEntity.currentBurnTime)
                crafter.updateCraftingInventoryInfo(this, 1, tileEntity.currentBurnTime);

            if (this.maxBurnTime != tileEntity.maxBurnTime)
                crafter.updateCraftingInventoryInfo(this, 2, tileEntity.maxBurnTime);
        }
    }

    @Override
    public void updateClientProgressBar(int id, int value) {
        tileEntity = (TileEntityGenerator) tile;
        if (id == 0)
            tileEntity.energy = value;

        if (id == 1)
            tileEntity.currentBurnTime = value;

        if (id == 2)
            tileEntity.maxBurnTime = value;
    }
}
