package baboon.industry.gui.storage;

import baboon.industry.block.storage.entity.TileEntityBatboxBase;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.player.inventory.InventoryPlayer;

public class GuiBatboxEHV extends GuiBatboxBase {

    public GuiBatboxEHV(InventoryPlayer inventory, TileEntityBatboxBase tileEntity) {
        super(inventory, tileEntity);
    }

    @Override
    protected void drawGuiContainerForegroundLayer() {
        super.drawGuiContainerForegroundLayer();
        int scrnX = xSize / 2;
        I18n i18n = I18n.getInstance();
        drawStringCenteredNoShadow(fontRenderer, i18n.translateKey("gui.industry.batbox.ehv"), scrnX, 6, 4210752);
        fontRenderer.drawString(i18n.translateKey("gui.industry.inventory"), 8, (ySize - 96) + 2, 4210752);
    }
}
