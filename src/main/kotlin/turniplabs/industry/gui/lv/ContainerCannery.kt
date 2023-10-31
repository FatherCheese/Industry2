package turniplabs.industry.gui.lv

import net.minecraft.core.InventoryAction
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.player.inventory.InventoryPlayer
import net.minecraft.core.player.inventory.slot.Slot
import net.minecraft.core.player.inventory.slot.SlotFurnace
import sunsetsatellite.energyapi.template.containers.ContainerEnergy
import turniplabs.industry.blocks.entities.lv.TileEntityCannery

class ContainerCannery(inventory: InventoryPlayer?, tileEntity: TileEntityCannery) : ContainerEnergy() {

    init {
        tile = tileEntity

        addSlot(Slot(tileEntity, 0, 8, 17))
        addSlot(Slot(tileEntity, 1, 8, 53))
        addSlot(Slot(tileEntity, 2, 56, 17))
        addSlot(Slot(tileEntity, 3, 56, 53))
        addSlot(SlotFurnace(inventory?.player, tileEntity, 4, 116, 35))

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

    override fun isUsableByPlayer(entityPlayer: EntityPlayer?): Boolean {
        return (tile as TileEntityCannery).canInteractWith(entityPlayer)
    }

    override fun getTargetSlots(
        action: InventoryAction?,
        slot: Slot,
        target: Int,
        player: EntityPlayer?
    ): MutableList<Int>? {
        if (slot.id in 5..41) {
            return when {
                target == 1 -> getSlots(1, 1, false) // Batteries
                target == 2 -> getSlots(2, 1, false) // Ingredients
                target == 3 -> getSlots(3, 1, false) // Cans
                slot.id < 33 -> getSlots(33, 9, false) // Inventory > Hotbar
                else -> getSlots(5, 27, false) // Hotbar > Inventory
            }
        }
        return when {
            slot.id < 0 -> null
            else -> getSlots(5, 36, false)
        }
    }
}