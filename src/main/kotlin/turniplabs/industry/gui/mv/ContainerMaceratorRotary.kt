package turniplabs.industry.gui.mv

import net.minecraft.core.player.inventory.InventoryPlayer
import net.minecraft.core.player.inventory.slot.Slot
import net.minecraft.core.player.inventory.slot.SlotFurnace
import sunsetsatellite.energyapi.template.containers.ContainerEnergy
import turniplabs.industry.blocks.entities.mv.TileEntityMaceratorRotary

class ContainerMaceratorRotary(inventory: InventoryPlayer?, tileEntity: TileEntityMaceratorRotary) : ContainerEnergy() {

    init {
        tile = tileEntity
        addSlot(Slot(tileEntity, 0, 8, 17))
        addSlot(Slot(tileEntity, 1, 8, 53))
        addSlot(Slot(tileEntity, 2, 56, 25))
        addSlot(Slot(tileEntity, 3, 56, 43))
        addSlot(SlotFurnace(inventory?.player, tileEntity, 4, 116, 25))
        addSlot(SlotFurnace(inventory?.player, tileEntity, 5, 116, 43))
        addSlot(Slot(tileEntity, 6, 151, 53))

        for (ySlot in 0..2) for (xSlot in 0..8)
            addSlot(
                Slot(
                    inventory,
                    xSlot + ySlot * 9 + 9,
                    8 + xSlot * 18,
                    84 + ySlot * 18
                )
            )
        for (hotbar in 0..8)
            addSlot(Slot(inventory, hotbar, 8 + hotbar * 18, 142))
    }
}