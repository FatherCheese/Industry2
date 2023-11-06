package baboon.industry.gui.reactor;

import baboon.industry.block.reactor.entity.TileEntityReactor;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;
import sunsetsatellite.energyapi.template.containers.ContainerEnergy;

public class ContainerReactor extends ContainerEnergy {

    public ContainerReactor(InventoryPlayer inventory, TileEntityReactor tileEntity) {
        tile = tileEntity;
        int numberOfRows = tileEntity.getSizeInventory() / 9;
        int rowInt = numberOfRows * 9;

        for (int reactorRowsX = 0; reactorRowsX < 9; ++reactorRowsX)
            for(int reactorRowsY = 0; reactorRowsY < numberOfRows; ++reactorRowsY)
                addSlot(new Slot(tileEntity, reactorRowsX + reactorRowsY * 9, 8 + reactorRowsX * 18, 18 + reactorRowsY * 18));

        for (int xSlot = 0; xSlot < 9; ++xSlot)
            for(int ySlot = 0; ySlot < 3; ++ySlot)
                addSlot(new Slot(inventory, xSlot + ySlot * 9 + 9, 8 + xSlot * 18, 40 + ySlot * 18 + rowInt));

        for(int hotbar = 0; hotbar < 9; ++hotbar)
            addSlot(new Slot(inventory, hotbar, 8 + hotbar * 18, 98 + rowInt));
    }
}
