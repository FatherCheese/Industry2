package baboon.industry.gui.generator;

import baboon.industry.block.generator.entity.TileEntitySolarBase;
import net.minecraft.core.player.inventory.InventoryPlayer;

public class GuiGeneratorSolar extends GuiSolarBase {

    public GuiGeneratorSolar(InventoryPlayer inventory, TileEntitySolarBase tileEntity) {
        super(inventory, tileEntity);
    }

    @Override
    protected void drawGuiContainerForegroundLayer() {
        super.drawGuiContainerForegroundLayer();
        int scrnX = xSize / 2;
        drawStringCenteredNoShadow(fontRenderer, "Solar Generator", scrnX, 6, 4210752);
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 4210752);
    }
}
