package baboon.industry.gui.machine.advanced;

import baboon.industry.block.machines.advanced.entity.TileEntityAdvancedBase;
import net.minecraft.core.player.inventory.InventoryPlayer;

public class GuiAdvancedCompressor extends GuiAdvancedBase {

    public GuiAdvancedCompressor(InventoryPlayer inventory, TileEntityAdvancedBase tileEntity) {
        super(inventory, tileEntity);
    }

    @Override
    protected void drawGuiContainerForegroundLayer() {
        super.drawGuiContainerForegroundLayer();
        int scrnX = xSize / 2;
        drawStringCenteredNoShadow(fontRenderer, "Advanced Compressor", scrnX, 6, 4210752);
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 4210752);
    }
}
