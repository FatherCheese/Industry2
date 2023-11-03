package baboon.industry.gui.generator;

import baboon.industry.block.generator.entity.TileEntitySolarBase;
import net.minecraft.client.gui.GuiContainer;
import net.minecraft.client.gui.GuiTooltip;
import net.minecraft.core.net.command.TextFormatting;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.world.World;
import org.lwjgl.opengl.GL11;

public class GuiSolarBase extends GuiContainer {
    private final TileEntitySolarBase tileEntity;

    public GuiSolarBase(InventoryPlayer inventory, TileEntitySolarBase tileEntity) {
        super(new ContainerSolarBase(inventory, tileEntity));
        this.tileEntity = tileEntity;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f) {
        int texture = mc.renderEngine.getTexture("/assets/industry/gui/generator_solar.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        mc.renderEngine.bindTexture(texture);

        int scrnX = (width - xSize) / 2;
        int scrnY = (height - ySize) / 2;
        drawTexturedModalRect(scrnX, scrnY, 0, 0, xSize, ySize);

        float power = (float) tileEntity.energy / tileEntity.capacity;
        drawTexturedModalRect(scrnX + 8, scrnY + 39, 176, 8, (int) (power * 16), 8);

        // Displays a moon if generated energy is 0 or below
        // Else it displays a sun
        if (tileEntity.generatedEnergy > 0)
            drawTexturedModalRect(scrnX + 84, scrnY + 33, 176, 0, 8, 8);
        else drawTexturedModalRect(scrnX + 84, scrnY + 33, 184, 0, 8, 8);

        // Daylight Bar
        World world = mc.theWorld;
        drawTexturedModalRect(scrnX + 76, scrnY + 47, 176, 16, (int) (world.getWorldTime() % world.worldType.getDayNightCycleLengthTicks() / 1000), 4);
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

        if (x > (scrnX + 84) && x < (scrnX + 92))
            if (y > (scrnY + 33) && y < (scrnY + 41)) {
                String text = TextFormatting.WHITE + "Generating: " + TextFormatting.LIGHT_GRAY + tileEntity.generatedEnergy;

                GuiTooltip tooltip = new GuiTooltip(mc);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glCullFace(GL11.GL_CULL_FACE);
                tooltip.render(text, x, y, 8, -8);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_CULL_FACE);
            }
    }
}
