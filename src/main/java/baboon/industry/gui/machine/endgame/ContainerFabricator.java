package baboon.industry.gui.machine.endgame;

import baboon.industry.block.machines.endgame.entity.TileEntityEnergyFabricator;
import net.minecraft.core.InventoryAction;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;
import net.minecraft.core.player.inventory.slot.SlotFurnace;
import sunsetsatellite.catalyst.energy.impl.ContainerEnergy;

import java.util.List;

public class ContainerFabricator extends ContainerEnergy {

    public ContainerFabricator(InventoryPlayer inventory, TileEntityEnergyFabricator tileEntity) {
        tile = tileEntity;
        addSlot(new Slot(tileEntity, 0, 8, 17));
        addSlot(new Slot(tileEntity, 1, 8, 53));
        addSlot(new Slot(tileEntity, 2, 56, 35));
        addSlot(new SlotFurnace(inventory.player, tileEntity, 3, 116, 35));
        addSlot(new Slot(tileEntity, 4, 151, 53));

        for(int xSlot = 0; xSlot < 3; ++xSlot)
            for (int ySlot = 0; ySlot < 9; ++ySlot)
                addSlot(new Slot(inventory, ySlot + xSlot * 9 + 9, 8 + ySlot * 18, 84 + xSlot * 18));

        for(int hotbar = 0; hotbar < 9; ++hotbar)
            addSlot(new Slot(inventory, hotbar, 8 + hotbar * 18, 142));
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer entityPlayer) {
        return ((TileEntityEnergyFabricator) tile).canInteractWith(entityPlayer);
    }

    @Override
    public List<Integer> getTargetSlots(InventoryAction action, Slot slot, int target, EntityPlayer player) {
        if (slot.id >= 5 && slot.id <= 43) { // Entire inventory
            if (target == 1)    // Batteries
                return getSlots(0, 1, true);

            if (target == 2)    // Scrap
                return getSlots(4, 1, false);

            if (slot.id < 32)   // Inventory > Hotbar
                return getSlots(32, 9, false);

            // Hotbar > Inventory
            return getSlots(5, 27, false);
        }
        if (slot.id < 0)
            return null;

        return getSlots(5, 36, false);
    }
}
