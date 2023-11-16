package baboon.industry.gui.machine.basic;

import baboon.industry.block.machines.basic.entity.TileEntityMachineTrommel;
import net.minecraft.client.gui.GuiContainer;
import net.minecraft.client.gui.GuiTooltip;
import net.minecraft.core.net.command.TextFormatting;
import net.minecraft.core.player.inventory.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GuiMachineTrommel extends GuiContainer {
    TileEntityMachineTrommel tileEntity;

    public GuiMachineTrommel(InventoryPlayer inventory, TileEntityMachineTrommel tileEntity) {
        super(new ContainerMachineTrommel(inventory, tileEntity));
        this.tileEntity = tileEntity;
        this.xSize = 208;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f) {
        int texture = mc.renderEngine.getTexture("/assets/industry/gui/machine_trommel.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        mc.renderEngine.bindTexture(texture);

        int scrnX = (width - xSize) / 2;
        int scrnY = (height - ySize) / 2;
        drawTexturedModalRect(scrnX, scrnY, 0, 0, xSize, ySize);

        float power = (float) tileEntity.energy / tileEntity.capacity;
        drawTexturedModalRect(scrnX + 8, scrnY + 39, 176, 0, (int) (power * 16), 8);

        int progress = (int) ((tileEntity.getTrommelProgressPercent(1) * 8) % 4);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(scrnX + 80), (float)(scrnY + 35), 0.0F);
        this.drawTexturedModalRect(0, 0, 176 + progress * 16, 16, 16, 16);
        GL11.glPopMatrix();

        // Upgrades slots
        drawTexturedModalRect(scrnX + 176, scrnY, 0, 166, 32, 86);
    }

    @Override
    protected void drawGuiContainerForegroundLayer() {
        super.drawGuiContainerForegroundLayer();
        int scrnX = xSize / 2 - 16;
        drawStringCenteredNoShadow(fontRenderer, "Electric Trommel", scrnX, 6, 4210752);
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
    }
}
