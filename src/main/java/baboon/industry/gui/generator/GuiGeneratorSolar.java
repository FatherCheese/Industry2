package baboon.industry.gui.generator;

import baboon.industry.block.generator.entity.TileEntitySolarBase;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.player.inventory.InventoryPlayer;

public class GuiGeneratorSolar extends GuiSolarBase {

    public GuiGeneratorSolar(InventoryPlayer inventory, TileEntitySolarBase tileEntity) {
        super(inventory, tileEntity);
    }

    @Override
    protected void drawGuiContainerForegroundLayer() {
        super.drawGuiContainerForegroundLayer();
        int scrnX = xSize / 2;
        I18n i18n = I18n.getInstance();
        drawStringCenteredNoShadow(fontRenderer, i18n.translateKey("gui.industry.generator.solar"), scrnX, 6, 4210752);
        fontRenderer.drawString(i18n.translateKey("gui.industry.inventory"), 8, (ySize - 96) + 2, 4210752);
    }
}
