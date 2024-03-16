package baboon.industry.gui.generator.reactor;

import baboon.industry.block.reactor.entity.TileEntityReactorNewer;
import net.minecraft.core.crafting.ICrafting;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;
import sunsetsatellite.catalyst.energy.impl.ContainerEnergy;

public class ContainerReactorNewer extends ContainerEnergy {
    private final int energy = 0;
    private final int heat = 0;
    private final int coolant = 0;
    InventoryPlayer inventory;
    TileEntityReactorNewer tileEntity;

    public ContainerReactorNewer(InventoryPlayer inventory, TileEntityReactorNewer tileEntity) {
        this.inventory = inventory;
        this.tileEntity = tileEntity;

        /*
        SLOTS IN ORDER
        00 - Battery I/O
        01 - Battery I/O
        02 - Coolant Slot
        */

        tile = tileEntity;
        addSlot(new Slot(tileEntity, 0, 14, 18));
        addSlot(new Slot(tileEntity, 1, 14, 54));
        addSlot(new Slot(tileEntity, 2, 14, 126));

        // Reactor inventory (7x7)
        for (int hor = 0; hor < 7; ++hor) {
            for (int ver = 0; ver < 7; ver++) {
                addSlot(new Slot(tileEntity, 3 + hor + ver * 7, 44 + hor * 18, 18 + ver * 18));
            }
        }

        // Player inventory
        for(int xSlot = 0; xSlot < 3; ++xSlot) {
            for (int ySlot = 0; ySlot < 9; ++ySlot) {
                addSlot(new Slot(inventory, ySlot + xSlot * 9 + 9, 8 + ySlot * 18, 158 + xSlot * 18));
            }
        }

        // Player hotbar
        for(int hotbar = 0; hotbar < 9; ++hotbar)
            addSlot(new Slot(inventory, hotbar, 8 + hotbar * 18, 216));
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return tileEntity.canInteractWith(player);
    }

    @Override
    public void updateInventory() {
        super.updateInventory();

        for (ICrafting crafter : this.crafters) {
            if (this.energy != tileEntity.energy) crafter.updateCraftingInventoryInfo(this, 0, tileEntity.energy);
            if (this.heat != tileEntity.heat) crafter.updateCraftingInventoryInfo(this, 1, tileEntity.heat);
            if (this.coolant != tileEntity.coolant) crafter.updateCraftingInventoryInfo(this, 2, tileEntity.coolant);
        }
    }

    @Override
    public void updateClientProgressBar(int id, int value) {
        tileEntity = (TileEntityReactorNewer) tile;
        if (id == 0) tileEntity.energy = value;
        if (id == 1) tileEntity.heat = value;
        if (id == 2) tileEntity.coolant = value;
    }
}
