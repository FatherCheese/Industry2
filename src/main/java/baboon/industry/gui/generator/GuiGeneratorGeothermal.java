package baboon.industry.gui.generator;

import baboon.industry.block.generator.entity.TileEntityGeneratorGeothermal;
import net.minecraft.client.gui.GuiContainer;
import net.minecraft.client.gui.GuiTooltip;
import net.minecraft.core.net.command.TextFormatting;
import net.minecraft.core.player.inventory.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GuiGeneratorGeothermal extends GuiContainer {
    private final TileEntityGeneratorGeothermal tileEntity;

    public GuiGeneratorGeothermal(InventoryPlayer inventory, TileEntityGeneratorGeothermal tileEntity) {
        super(new ContainerGeneratorGeothermal(inventory, tileEntity));
        this.tileEntity = tileEntity;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f) {
        int texture = mc.renderEngine.getTexture("/assets/industry/gui/generator_fluid.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        mc.renderEngine.bindTexture(texture);

        int scrnX = (width - xSize) / 2;
        int scrnY = (height - ySize) / 2;
        drawTexturedModalRect(scrnX, scrnY, 0, 0, xSize, ySize);

        float power = (float) tileEntity.energy / tileEntity.capacity;
        drawTexturedModalRect(scrnX + 8, scrnY + 39, 176, 0, (int) (power * 16), 8);

        int fuelTime = tileEntity.currentFuelTime * 16 / tileEntity.maxFuelTime;
        drawTexturedModalRect(scrnX + 80, (scrnY + 17) + (16 - fuelTime), 192, 23 - fuelTime, 16, fuelTime);
    }

    @Override
    protected void drawGuiContainerForegroundLayer() {
        int scrnX = xSize / 2;
        drawStringCenteredNoShadow(fontRenderer, "Geothermal Generator", scrnX, 6, 4210752);
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 4210752);
    }

    @Override
    public void drawScreen(int x, int y, float renderPartialTicks) {
        super.drawScreen(x, y, renderPartialTicks);
        int scrnX = (width - xSize) / 2;
        int scrnY = (height - ySize) / 2;

        if (x > (scrnX + 8) && x < (scrnX + 24))
            if (y > (scrnY + 39) && y < (scrnY + 47)) {
                String text = TextFormatting.WHITE + "Energy: " + TextFormatting.LIGHT_GRAY + tileEntity.energy + TextFormatting.WHITE + " / " + TextFormatting.LIGHT_GRAY + tileEntity.capacity;

                GuiTooltip tooltip = new GuiTooltip(mc);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glCullFace(GL11.GL_CULL_FACE);
                tooltip.render(text, x, y, 8, -8);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_CULL_FACE);
            }

        if (x > (scrnX + 83) && x < (scrnX + 93))
            if (y > (scrnY + 20) && y < (scrnY + 31)) {
                String text = TextFormatting.WHITE + "Lava: " + TextFormatting.LIGHT_GRAY + tileEntity.currentFuelTime + TextFormatting.WHITE + " / " + TextFormatting.LIGHT_GRAY + tileEntity.maxFuelTime;

                GuiTooltip tooltip = new GuiTooltip(mc);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glCullFace(GL11.GL_CULL_FACE);
                tooltip.render(text, x, y, 8, -8);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_CULL_FACE);
            }
    }
}
