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
        ySize =  inventory.getSizeInventory() + tile.getSizeInventory() * 9;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f) {
        int texture = mc.renderEngine.getTexture("/assets/industry/gui/reactor.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        mc.renderEngine.bindTexture(texture);

        int scrnX = (width - xSize) / 2;
        int scrnY = (height - ySize) / 2;
        drawTexturedModalRect(scrnX, scrnY, 0, 0, xSize, ySize);

        int reactorRows = tile.getSizeInventory() / 9;
        int reactorMin1 = Math.min(reactorRows, 6) * 9;
        this.drawTexturedModalRect(scrnX, scrnY, 0, 0, this.xSize, reactorMin1);

        int reactorMin2 = 6 * 9;
        for(int rows = reactorRows; rows > 6; reactorMin1 += reactorMin2) {
            this.drawTexturedModalRect(scrnX, scrnY + reactorMin1, 0, reactorRows, xSize, reactorMin2);
            rows -= 6;
        }

        int inventoryRows = inventory.getSizeInventory() / 9;
        drawTexturedModalRect(scrnX, scrnY + inventoryRows * 9 + reactorRows - 2, 0, 126, xSize, 96);
    }

    @Override
    protected void drawGuiContainerForegroundLayer() {
        super.drawGuiContainerForegroundLayer();
        drawStringCenteredNoShadow(fontRenderer, "Nuclear Reactor", xSize / 2, 6, 4210752);
//        fontRenderer.drawString("Inventory", 8, ySize / 2, 4210752);
    }
}
