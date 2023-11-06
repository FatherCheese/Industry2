package baboon.industry.gui.reactor;

import baboon.industry.block.reactor.entity.TileEntityReactor;
import net.minecraft.client.gui.GuiContainer;
import net.minecraft.core.player.inventory.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GuiReactor extends GuiContainer {
    private final TileEntityReactor tile;
    private final InventoryPlayer inventory;

    public GuiReactor(InventoryPlayer inventory, TileEntityReactor tileEntity) {
        super(new ContainerReactor(inventory, tileEntity));
        this.tile = tileEntity;
        this.inventory = inventory;
        ySize = 97 + 17 + 18 * (tileEntity.getSizeInventory()/9);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f) {
        int reactorRows = tile.getSizeInventory()/9;
        ySize = 97 + 17 + 18 * reactorRows;

        int texture = mc.renderEngine.getTexture("/assets/industry/gui/reactor.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        mc.renderEngine.bindTexture(texture);

        int scrnX = (width - xSize) / 2;
        int scrnY = (height - ySize) / 2;
        drawTexturedModalRect(scrnX, scrnY, 0, 0, xSize, 17);

        drawTexturedModalRect(scrnX, scrnY + 17, 0, 17, xSize, 18 * reactorRows);
        drawTexturedModalRect(scrnX, scrnY + 17 + 18 * reactorRows, 0, 126, xSize, 97);

    }

    @Override
    protected void drawGuiContainerForegroundLayer() {
        super.drawGuiContainerForegroundLayer();
        drawStringCenteredNoShadow(fontRenderer, "Nuclear Reactor", xSize / 2, 6, 4210752);
//        fontRenderer.drawString("Inventory", 8, ySize / 2, 4210752);
    }
}
