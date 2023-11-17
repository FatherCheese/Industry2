package baboon.industry.gui.machine.basic;

import baboon.industry.block.machines.basic.entity.TileEntityMachineBase;
import net.minecraft.core.InventoryAction;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;
import net.minecraft.core.player.inventory.slot.SlotFurnace;
import sunsetsatellite.energyapi.template.containers.ContainerEnergy;

import java.util.List;

public class ContainerMachineBase extends ContainerEnergy {
    TileEntityMachineBase tileEntity;
    InventoryPlayer inventory;

    public ContainerMachineBase(InventoryPlayer inventory, TileEntityMachineBase tileEntity) {
        this.inventory = inventory;
        this.tileEntity = tileEntity;
        tile = tileEntity;
        addSlot(new Slot(tileEntity, 0, 8, 17));
        addSlot(new Slot(tileEntity, 1, 8, 53));
        addSlot(new Slot(tileEntity, 2, 56, 35));
        addSlot(new SlotFurnace(inventory.player, tileEntity, 3, 116, 35));
        addSlot(new Slot(tileEntity, 4, 184, 8));
        addSlot(new Slot(tileEntity, 5, 184, 26));
        addSlot(new Slot(tileEntity, 6, 184, 44));
        addSlot(new Slot(tileEntity, 7, 184, 62));

        for(int xSlot = 0; xSlot < 3; ++xSlot)
            for (int ySlot = 0; ySlot < 9; ++ySlot)
                addSlot(new Slot(inventory, ySlot + xSlot * 9 + 9, 8 + ySlot * 18, 84 + xSlot * 18));

        for(int hotbar = 0; hotbar < 9; ++hotbar)
            addSlot(new Slot(inventory, hotbar, 8 + hotbar * 18, 142));
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer entityPlayer) {
        return ((TileEntityMachineBase) tile).canInteractWith(entityPlayer);
    }

    @Override
    public List<Integer> getTargetSlots(InventoryAction action, Slot slot, int target, EntityPlayer player) {
        if (slot.id >= 8 && slot.id <= 44) { // Entire inventory
            if (target == 1)    // Batteries
                return getSlots(0, 1, true);

            if (target == 2)    // Ingredients
                return getSlots(2, 1, false);

            if (slot.id < 36)   // Inventory > Hotbar
                return getSlots(36, 9, false);

            // Hotbar > Inventory
            return getSlots(8, 27, false);
        }
        if (slot.id < 0)
            return null;

        return getSlots(8, 36, false);
    }

    @Override
    public void updateClientProgressBar(int id, int value) {
        tileEntity = (TileEntityMachineBase) tile;
        if (id == 0)
            tileEntity.currentMachineTime = value;
    }
}
