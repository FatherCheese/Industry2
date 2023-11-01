package turniplabs.industry.gui.solar

import net.minecraft.core.player.inventory.InventoryPlayer
import turniplabs.industry.blocks.entities.solar.TileEntitySolarGenerator

class GuiSolarGenerator(inventory: InventoryPlayer?, tileEntity: TileEntitySolarGenerator) :
GuiSolarBase(inventory, tileEntity) {

    override fun drawGuiContainerForegroundLayer() {
        super.drawGuiContainerForegroundLayer()
        fontRenderer.drawString("Solar Generator", 46, 6, 4210752)
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 4210752)
    }
}