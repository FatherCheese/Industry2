package turniplabs.industry.gui

import net.minecraft.client.gui.GuiContainer
import net.minecraft.client.gui.GuiTooltip
import net.minecraft.core.net.command.TextFormatting
import net.minecraft.core.player.inventory.InventoryPlayer
import org.lwjgl.opengl.GL11
import turniplabs.industry.blocks.entities.TileEntitySolarBase

open class GuiSolarBase(inventory: InventoryPlayer?, private var tileEntity: TileEntitySolarBase) :
GuiContainer(ContainerSolarBase(inventory, tileEntity)) {

    // Thanks to MartinSVK12 for fixing the generated energy stuff!
    override fun drawGuiContainerBackgroundLayer(f: Float) {
        val texture: Int = mc.renderEngine.getTexture("/assets/industry/gui/generator_solar.png")
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
        mc.renderEngine.bindTexture(texture)

        val textX: Int = (width - xSize) / 2
        val textY: Int = (height - ySize) / 2
        drawTexturedModalRect(textX, textY, 0, 0, xSize, ySize)

        val power: Double = (tileEntity.energy.toFloat() / tileEntity.capacity.toFloat()).toDouble()
        drawTexturedModalRect(textX + 80, textY + 57, 176, 0, (power * 16).toInt(), 8)

        // Checks the generated energy, if it's above 0 display a sun, else it displays a moon
        // Cut for now!
        if (tileEntity.generatedEnergy > 0) drawTexturedModalRect(
            textX + 84,
            textY + 21,
            176,
            8,
            8,
            8
        ) else drawTexturedModalRect(textX + 84, textY + 21, 184, 8, 8, 8)
    }

    override fun drawScreen(x: Int, y: Int, renderPartialTicks: Float) {
        val scrnX: Int = (width - xSize) / 2
        val scrnY: Int = (height - ySize) / 2
        super.drawScreen(x, y, renderPartialTicks)

        val text = StringBuilder()
        if ((x > (scrnX + 80)) && (x < (scrnX + 96))) {
            if (y > (scrnY + 57) && y < (scrnY + 66)) {
                text.append("${TextFormatting.WHITE}Energy: ${TextFormatting.LIGHT_GRAY}${tileEntity.energy}${TextFormatting.WHITE} / ${TextFormatting.WHITE}${tileEntity.capacity}")

                val guiTooltip = GuiTooltip(mc)
                GL11.glDisable(GL11.GL_LIGHTING)
                GL11.glCullFace(GL11.GL_CULL_FACE)
                guiTooltip.render(text.toString(), x, y, 8, -8)
                GL11.glEnable(GL11.GL_LIGHTING)
                GL11.glEnable(GL11.GL_CULL_FACE)
            }
        }

        // Displays text when hovering over the celestial bodies
        // Also cut!
        if (x > (scrnX + 84) && x < (scrnX + 92)) {
            if (y > (scrnY + 21) && y < (scrnY + 29)) {
                text.append("${TextFormatting.WHITE}Generating: ${TextFormatting.LIGHT_GRAY}${tileEntity.generatedEnergy}/t")

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