package baboon.industry.gui.generator.reactor;

import baboon.industry.block.reactor.entity.TileEntityReactorNewer;
import net.minecraft.client.gui.GuiContainer;
import net.minecraft.client.gui.GuiTooltip;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.net.command.TextFormatting;
import net.minecraft.core.player.inventory.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GuiReactorNewer extends GuiContainer {
    private final InventoryPlayer inventory;
    private final TileEntityReactorNewer tile;
    I18n i18n = I18n.getInstance();

    public GuiReactorNewer(InventoryPlayer inventory, TileEntityReactorNewer tileEntity) {
        super(new ContainerReactorNewer(inventory, tileEntity));
        this.inventory = inventory;
        this.tile = tileEntity;
        this.xSize = 175;
        this.ySize = 239;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f) {
        int texture = mc.renderEngine.getTexture("/assets/industry/gui/reactor2.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        mc.renderEngine.bindTexture(texture);

        int scrnX = (width - xSize) / 2;
        int scrnY = (height - ySize) / 2;
        drawTexturedModalRect(scrnX, scrnY, 0, 0, xSize, ySize);

        float power = (float) tile.energy / tile.capacity;
        drawTexturedModalRect(scrnX + 14, scrnY + 40, 176, 0, (int) (power * 16), 8);

        float heat = (float) tile.heat / tile.maxHeat;
        drawTexturedModalRect(scrnX + 8, scrnY + 99, 193, 1, (int) (heat * 28), 8);

        float coolant = (float) tile.coolant * 16 / tile.maxCoolant;
        drawTexturedModalRect(scrnX + 14, (int) ((scrnY + 108) + (16 - coolant)), 176, (int) (24 - coolant), 16, (int) coolant);
    }

    @Override
    protected void drawGuiContainerForegroundLayer() {
        int scrnX = xSize / 2;
        drawStringCenteredNoShadow(fontRenderer, i18n.translateKey("gui.industry.reactor"), scrnX, 6, 4210752);
        fontRenderer.drawString(i18n.translateKey("gui.industry.inventory"), 8, (ySize - 96) + 2, 4210752);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTick) {
        super.drawScreen(mouseX, mouseY, partialTick);
        int scrnX = (width - xSize) / 2;
        int scrnY = (height - ySize) / 2;

        // Energy
        if (mouseX > (scrnX + 13) && mouseX < (scrnX + 30)) {
            if (mouseY > (scrnY + 39) && mouseY < (scrnY + 48)) {
                String text = TextFormatting.WHITE + i18n.translateKey("gui.industry.energy") + ": " + TextFormatting.LIGHT_GRAY + tile.energy + TextFormatting.WHITE + " / " + TextFormatting.LIGHT_GRAY + tile.capacity;

                GuiTooltip tooltip = new GuiTooltip(mc);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glCullFace(GL11.GL_CULL_FACE);
                tooltip.render(text, mouseX, mouseY, 8, -8);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_CULL_FACE);
            }
        }

        // Heat
        if (mouseX > (scrnX + 7) && mouseX < (scrnX + 36)) {
            if (mouseY > (scrnY + 98) && mouseY < (scrnY + 107)) {
                String text = TextFormatting.WHITE + i18n.translateKey("gui.industry.heat") + ": " + TextFormatting.LIGHT_GRAY + tile.heat + TextFormatting.WHITE + " / " + TextFormatting.LIGHT_GRAY + tile.maxHeat;

                GuiTooltip tooltip = new GuiTooltip(mc);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glCullFace(GL11.GL_CULL_FACE);
                tooltip.render(text, mouseX, mouseY, 8, -8);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_CULL_FACE);
            }
        }

        // Coolant
        if (mouseX > (scrnX + 16) && mouseX < (scrnX + 27)) {
            if (mouseY > (scrnY + 110) && mouseY < (scrnY + 122)) {
                String text = TextFormatting.WHITE + i18n.translateKey("gui.industry.water") + ": " + TextFormatting.LIGHT_GRAY + tile.coolant + TextFormatting.WHITE + " / " + TextFormatting.LIGHT_GRAY + tile.maxCoolant;

                GuiTooltip tooltip = new GuiTooltip(mc);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glCullFace(GL11.GL_CULL_FACE);
                tooltip.render(text, mouseX, mouseY, 8, -8);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_CULL_FACE);
            }
        }
    }
}
