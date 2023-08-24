package turniplabs.industry.gui

import net.minecraft.core.player.inventory.InventoryPlayer
import turniplabs.industry.blocks.entities.TileEntitySolarLV

class GuiSolarArrayLV(inventory: InventoryPlayer?, tileEntity: TileEntitySolarLV) :
GuiSolarBase(inventory, tileEntity) {

    override fun drawGuiContainerForegroundLayer() {
        super.drawGuiContainerForegroundLayer()
        fontRenderer.drawString("LV Solar Array", 46, 6, 4210752)
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 4210752)
    }
}