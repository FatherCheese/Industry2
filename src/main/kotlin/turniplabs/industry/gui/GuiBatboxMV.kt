package turniplabs.industry.gui

import net.minecraft.core.player.inventory.InventoryPlayer
import turniplabs.industry.blocks.entities.TileEntityBatboxBase

class GuiBatboxMV(inventory: InventoryPlayer?, tileEntity: TileEntityBatboxBase) : GuiBatboxBase(inventory, tileEntity) {

    override fun drawGuiContainerForegroundLayer() {
        super.drawGuiContainerForegroundLayer()
        fontRenderer.drawString("MV Batbox", 64, 6, 4210752)
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 4210752)
    }
}