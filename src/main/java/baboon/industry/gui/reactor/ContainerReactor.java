package baboon.industry.gui.reactor;

import baboon.industry.block.reactor.entity.TileEntityReactorChamber;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;
import sunsetsatellite.energyapi.template.containers.ContainerEnergy;

public class ContainerReactor extends ContainerEnergy {

    public ContainerReactor(InventoryPlayer inventory, TileEntityReactorChamber tileEntity) {
        tile = tileEntity;
        int numberOfRows = tileEntity.getSizeInventory() / 9;

        for(int reactorRowsY = 0; reactorRowsY < numberOfRows; ++reactorRowsY)
            for (int reactorRowsX = 0; reactorRowsX < 9; ++reactorRowsX)
                addSlot(new Slot(tileEntity, reactorRowsX + reactorRowsY * 9, 8 + reactorRowsX * 18, 18 + reactorRowsY * 18));

        for(int xSlot = 0; xSlot < 3; ++xSlot)
            for (int ySlot = 0; ySlot < 9; ++ySlot)
                addSlot(new Slot(inventory, ySlot + xSlot * 9 + 9, 8 + ySlot * 18, 84 + xSlot * 18 + numberOfRows - 4 * 9));

        for(int hotbar = 0; hotbar < 9; ++hotbar)
            addSlot(new Slot(inventory, hotbar, 8 + hotbar * 18, 142 + numberOfRows - 4 * 9));
    }
}
