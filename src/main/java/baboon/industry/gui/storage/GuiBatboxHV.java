package baboon.industry.gui.storage;

import baboon.industry.block.storage.entity.TileEntityBatboxBase;
import net.minecraft.core.player.inventory.InventoryPlayer;

public class GuiBatboxHV extends GuiBatboxBase {

    public GuiBatboxHV(InventoryPlayer inventory, TileEntityBatboxBase tileEntity) {
        super(inventory, tileEntity);
    }

    @Override
    protected void drawGuiContainerForegroundLayer() {
        super.drawGuiContainerForegroundLayer();
        int scrnX = xSize / 2;
        drawStringCenteredNoShadow(fontRenderer, "HV Batbox", scrnX, 6, 4210752);
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 4210752);
    }
}
