package turniplabs.industry.gui.solar

import net.minecraft.core.player.inventory.InventoryPlayer
import turniplabs.industry.blocks.entities.solar.TileEntitySolarHV

class GuiSolarArrayHV(inventory: InventoryPlayer?, tileEntity: TileEntitySolarHV) :
GuiSolarBase(inventory, tileEntity) {

    override fun drawGuiContainerForegroundLayer() {
        super.drawGuiContainerForegroundLayer()
        fontRenderer.drawString("HV Solar Array", 46, 6, 4210752)
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 4210752)
    }
}