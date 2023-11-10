package baboon.industry.gui.generator;

import baboon.industry.block.generator.entity.TileEntityGeneratorWindmill;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;
import sunsetsatellite.energyapi.template.containers.ContainerEnergy;

public class ContainerGeneratorWindmill extends ContainerEnergy {

    public ContainerGeneratorWindmill(InventoryPlayer inventory, TileEntityGeneratorWindmill tileEntity) {
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
        return ((TileEntityGeneratorWindmill) tile).canInteractWith(entityPlayer);
    }
}
