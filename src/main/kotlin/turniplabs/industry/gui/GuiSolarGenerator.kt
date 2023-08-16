package turniplabs.industry.gui

import net.minecraft.client.gui.GuiContainer
import net.minecraft.client.gui.GuiTooltip
import net.minecraft.core.net.command.TextFormatting
import net.minecraft.core.player.inventory.InventoryPlayer
import org.lwjgl.opengl.GL11
import turniplabs.industry.blocks.entities.TileEntitySolarGenerator

class GuiSolarGenerator(inventory: InventoryPlayer?, private val tileEntity: TileEntitySolarGenerator) :
    GuiContainer(ContainerSolarGenerator(inventory, tileEntity)) {

    override fun drawGuiContainerBackgroundLayer(f: Float) {
        val texture: Int = mc.renderEngine.getTexture("/assets/industry/gui/generator_solar.png")
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
        mc.renderEngine.bindTexture(texture)

        val textX: Int = (width - xSize) / 2
        val textY: Int = (height - ySize) / 2
        drawTexturedModalRect(textX, textY, 0, 0, xSize, ySize)

        val power: Double = (tileEntity.energy.toFloat() / tileEntity.capacity.toFloat()).toDouble()
        drawTexturedModalRect(textX + 80, textY + 57, 176, 0, (power * 16).toInt(), 8)

        // Turns the image to the moon if it's night or blocked
        if (tileEntity.generatedEnergy > 0) drawTexturedModalRect(
            textX + 84,
            textY + 21,
            176,
            8,
            8,
            8
        ) else drawTexturedModalRect(textX + 84, textY + 21, 184, 8, 8, 8)
    }

    override fun drawGuiContainerForegroundLayer() {
        super.drawGuiContainerForegroundLayer()
        fontRenderer.drawString("Solar Generator", 46, 6, 4210752)
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 4210752)
    }

    override fun drawScreen(x: Int, y: Int, renderPartialTicks: Float) {
        val scrnX: Int = (width - xSize) / 2
        val scrnY: Int = (height - ySize) / 2
        super.drawScreen(x, y, renderPartialTicks)

        val text = StringBuilder()
        if ((x > (scrnX + 80)) && (x < (scrnX + 96))) {
            if (y > (scrnY + 57) && y < (scrnY + 66)) {
                text.append(TextFormatting.WHITE).append("Energy: ")
                    .append(TextFormatting.LIGHT_GRAY).append(tileEntity.energy)
                    .append(TextFormatting.WHITE).append(" / ")
                    .append(TextFormatting.LIGHT_GRAY).append(tileEntity.capacity)

                val guiTooltip = GuiTooltip(mc)
                GL11.glDisable(GL11.GL_LIGHTING)
                GL11.glCullFace(GL11.GL_CULL_FACE)
                guiTooltip.render(text.toString(), x, y, 8, -8)
                GL11.glEnable(GL11.GL_LIGHTING)
                GL11.glEnable(GL11.GL_CULL_FACE)
            }
        }

        // Sun/Moon generated energy description
        if (x > (scrnX + 84) && x < (scrnX + 92)) {
            if (y > (scrnY + 21) && y < (scrnY + 29)) {
                text.append(TextFormatting.WHITE).append("Generating: ")
                    .append(TextFormatting.LIGHT_GRAY).append(tileEntity.generatedEnergy)

                val guiTooltip = GuiTooltip(mc)
                GL11.glDisable(GL11.GL_LIGHTING)
                GL11.glCullFace(GL11.GL_CULL_FACE)
                guiTooltip.render(text.toString(), x, y, 8, -8)
                GL11.glEnable(GL11.GL_LIGHTING)
                GL11.glEnable(GL11.GL_CULL_FACE)
            }
        }
    }
}