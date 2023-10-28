package turniplabs.industry.gui.batbox

import net.minecraft.core.player.inventory.InventoryPlayer
import turniplabs.industry.blocks.entities.batbox.TileEntityBatboxBase

class GuiBatboxLV(inventory: InventoryPlayer?, tileEntity: TileEntityBatboxBase) : GuiBatboxBase(inventory, tileEntity) {

    override fun drawGuiContainerForegroundLayer() {
        super.drawGuiContainerForegroundLayer()
        fontRenderer.drawString("LV Batbox", 64, 6, 4210752)
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 4210752)
    }
}