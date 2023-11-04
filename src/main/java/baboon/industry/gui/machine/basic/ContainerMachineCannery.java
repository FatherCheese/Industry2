package baboon.industry.gui.machine.basic;

import baboon.industry.block.machines.basic.entity.TileEntityMachineCannery;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;
import net.minecraft.core.player.inventory.slot.SlotFurnace;
import sunsetsatellite.energyapi.template.containers.ContainerEnergy;

public class ContainerMachineCannery extends ContainerEnergy {

    public ContainerMachineCannery(InventoryPlayer inventory, TileEntityMachineCannery tileEntity) {
        tile = tileEntity;
        addSlot(new Slot(tileEntity, 0, 8, 17));
        addSlot(new Slot(tileEntity, 1, 8, 53));
        addSlot(new Slot(tileEntity, 2, 56, 17));
        addSlot(new Slot(tileEntity, 3, 56, 53));
        addSlot(new SlotFurnace(inventory.player, tileEntity, 4, 116, 35));

        for(int xSlot = 0; xSlot < 3; ++xSlot)
            for (int ySlot = 0; ySlot < 9; ++ySlot)
                addSlot(new Slot(inventory, ySlot + xSlot * 9 + 9, 8 + ySlot * 18, 84 + xSlot * 18));

        for(int hotbar = 0; hotbar < 9; ++hotbar)
            addSlot(new Slot(inventory, hotbar, 8 + hotbar * 18, 142));
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer entityPlayer) {
        return ((TileEntityMachineCannery) tile).canInteractWith(entityPlayer);
    }
}
