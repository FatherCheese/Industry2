package baboon.industry.gui.storage;

import baboon.industry.block.storage.entity.TileEntityBatboxBase;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;
import sunsetsatellite.catalyst.energy.impl.ContainerEnergy;

public class ContainerBatboxBase extends ContainerEnergy {

    public ContainerBatboxBase(InventoryPlayer inventory, TileEntityBatboxBase tileEntity) {
        tile = tileEntity;
        addSlot(new Slot(tileEntity, 0, 80, 17));
        addSlot(new Slot(tileEntity, 1, 80, 53));

        for(int xSlot = 0; xSlot < 3; ++xSlot)
            for (int ySlot = 0; ySlot < 9; ++ySlot)
                addSlot(new Slot(inventory, ySlot + xSlot * 9 + 9, 8 + ySlot * 18, 84 + xSlot * 18));

        for(int hotbar = 0; hotbar < 9; ++hotbar)
            addSlot(new Slot(inventory, hotbar, 8 + hotbar * 18, 142));
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer entityPlayer) {
        return ((TileEntityBatboxBase) tile).canInteractWith(entityPlayer);
    }
}
