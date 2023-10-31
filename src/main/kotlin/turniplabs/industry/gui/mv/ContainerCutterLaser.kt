package turniplabs.industry.gui.mv

import net.minecraft.core.InventoryAction
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.player.inventory.InventoryPlayer
import net.minecraft.core.player.inventory.slot.Slot
import net.minecraft.core.player.inventory.slot.SlotFurnace
import sunsetsatellite.energyapi.template.containers.ContainerEnergy
import turniplabs.industry.blocks.entities.mv.TileEntityLaserCutter

class ContainerCutterLaser(inventory: InventoryPlayer?, tileEntity: TileEntityLaserCutter) : ContainerEnergy() {

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

    override fun getTargetSlots(action: InventoryAction, slot: Slot, target: Int, player: EntityPlayer?): List<Int>? {
        if (slot.id in 7..43) { // Entire inventory
            return when {
                target == 1 -> getSlots(1, 1, false) // Batteries
                target == 2 -> getSlots(2, 2, false) // Ingredients
                target == 3 -> getSlots(6, 1, false) // Redstone
                slot.id < 35 -> getSlots(35, 9, false) // Inventory > Hotbar
                else -> getSlots(7, 27, false) // Hotbar > Inventory
            }
        }

        return when {
            slot.id < 0 -> null
            else -> getSlots(7, 36, false)
        }
    }
}