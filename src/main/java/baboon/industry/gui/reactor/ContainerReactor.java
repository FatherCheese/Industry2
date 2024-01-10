package baboon.industry.gui.reactor;

import baboon.industry.block.reactor.entity.TileEntityReactorNew;
import net.minecraft.core.InventoryAction;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;
import sunsetsatellite.catalyst.energy.impl.ContainerEnergy;

import java.util.ArrayList;
import java.util.List;

public class ContainerReactor extends ContainerEnergy {
    private final int inventoryStart;
    private final int hotbarStart;

    public ContainerReactor(InventoryPlayer inventory, TileEntityReactorNew tileEntity) {
        tile = tileEntity;
        int numberOfRows = 6;
        int rowInt = numberOfRows * 18;

        for(int reactorRowsY = 0; reactorRowsY < numberOfRows; ++reactorRowsY)
            for (int reactorRowsX = 0; reactorRowsX < 9; ++reactorRowsX)
                addSlot(new Slot(tileEntity, reactorRowsX + reactorRowsY * 9, 8 + reactorRowsX * 18, 18 + reactorRowsY * 18));

        inventoryStart = this.inventorySlots.size();
        for(int ySlot = 0; ySlot < 3; ++ySlot)
            for (int xSlot = 0; xSlot < 9; ++xSlot)
                addSlot(new Slot(inventory, xSlot + ySlot * 9 + 9, 8 + xSlot * 18, 18 + 13 + rowInt + ySlot * 18));

        hotbarStart = this.inventorySlots.size();
        for(int hotbar = 0; hotbar < 9; ++hotbar)
            addSlot(new Slot(inventory, hotbar, 8 + hotbar * 18, 18 + 17 + rowInt + 3 * 18));
    }
    public List<Integer> getMoveSlots(InventoryAction action, Slot slot, int target, EntityPlayer player) {
        if (slot.id < inventoryStart){ // Quick stack inside the container
            return getSlots(0, inventoryStart, false);
        }
        if (target == 1){
            // Quick move from Inventory -> Reactor
            if (slot.id < hotbarStart){
                return moveCellsOnly(getSlots(inventoryStart,27, false), slot.id);

            }
            // Quick move from Hotbar -> Reactor
            return moveCellsOnly(getSlots(hotbarStart, 9, false), slot.id);
        }
        // Quick move from Inventory -> Hotbar
        if (slot.id < hotbarStart){
            return getSlots(inventoryStart,27, false);

        }
        // Quick move from Hotbar -> Inventory
        return getSlots(hotbarStart, 9, false);
    }

    public List<Integer> getTargetSlots(InventoryAction action, Slot slot, int target, EntityPlayer player) {
        if (slot.id < inventoryStart){  // Reactor slot -> inventory
            List<Integer> listOut = getSlots(hotbarStart,9, false); // Hotbar first
            listOut.addAll(getSlots(inventoryStart,27, false)); // Then Inventory
            return listOut;
        }
        if (target == 1){
            return getSlots(0, inventoryStart, false);
        }
        if (slot.id < hotbarStart) {// Main inventory -> Hotbar
            return getSlots(hotbarStart, 9, false);
        }
        return getSlots(inventoryStart, 27, false); // Hotbar -> Main Inventory
    }

    private List<Integer> moveCellsOnly(List<Integer> list, int slotId){
        List<Integer> returnSlots = new ArrayList<>();
        ItemStack clickedStack = inventorySlots.get(slotId).getStack();
        for (int id: list) {
            ItemStack stack = inventorySlots.get(id).getStack();
            if (stack == null) continue;
            if (stack.itemID == clickedStack.itemID){
                returnSlots.add(id);
            }
        }
        return returnSlots;
    }

    public boolean isUsableByPlayer(EntityPlayer entityPlayer) {
        return ((TileEntityReactorNew)tile).canInteractWith(entityPlayer);
    }
}
