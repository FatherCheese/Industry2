package turniplabs.industry.gui

import net.minecraft.core.player.inventory.InventoryPlayer
import turniplabs.industry.blocks.entities.TileEntityBatboxBase

class GuiBatboxSHV(inventory: InventoryPlayer?, tileEntity: TileEntityBatboxBase) : GuiBatboxBase(inventory, tileEntity) {

    override fun drawGuiContainerForegroundLayer() {
        super.drawGuiContainerForegroundLayer()
        fontRenderer.drawString("SHV Batbox", 64, 6, 4210752)
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 4210752)
    }
}