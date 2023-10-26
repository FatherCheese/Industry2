package turniplabs.industry.gui

import net.minecraft.client.gui.GuiContainer
import net.minecraft.client.gui.GuiTooltip
import net.minecraft.core.net.command.TextFormatting
import net.minecraft.core.player.inventory.Container
import net.minecraft.core.player.inventory.InventoryPlayer
import org.lwjgl.opengl.GL11
import turniplabs.industry.blocks.entities.TileEntityWindmill

class GuiWindmill(inventory: InventoryPlayer, private val tileEntity: TileEntityWindmill, container: Container?) :
    GuiContainer(ContainerWindmill(inventory, tileEntity)) {

    override fun drawGuiContainerBackgroundLayer(f: Float) {
        val texture: Int = mc.renderEngine.getTexture("assets/industry/gui/generator_windmill.png")
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
        mc.renderEngine.bindTexture(texture)

        val textX: Int = (width - xSize) / 2
        val textY: Int = (height - ySize) / 2
        drawTexturedModalRect(textX, textY, 0, 0, xSize, ySize)

        val power: Double = (tileEntity.energy.toFloat() / tileEntity.capacity.toFloat()).toDouble()
        drawTexturedModalRect(textX + 80, textY + 39, 176, 14, (power * 16).toInt(), 8)

        val height: Double = tileEntity.yCoord.toDouble() / 256
        drawTexturedModalRect(textX + 102, textY + (27 + 32) - (32 / height).toInt(), 176, 36, 4, (32 / height).toInt())
    }

    override fun drawTexturedModalRect(x: Int, y: Int, u: Int, v: Int, width: Int, height: Int) {
        super.drawGuiContainerForegroundLayer()
        fontRenderer.drawString("Windmill", 64, 6, 4210752)
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 4210752)
    }

    override fun drawScreen(x: Int, y: Int, renderPartialTicks: Float) {
        super.drawScreen(x, y, renderPartialTicks)
        val scrnX: Int = (width - xSize) / 2
        val scrnY: Int = (height - ySize) / 2

        val text = StringBuilder()
        if ((x > (scrnX + 80)) && (x < (scrnX + 96))) {
            if (y > (scrnY + 39) && y < (scrnY + 47)) {
                text.append("${TextFormatting.WHITE}Energy: ${TextFormatting.LIGHT_GRAY}${tileEntity.energy}${TextFormatting.WHITE} / ${TextFormatting.LIGHT_GRAY}${tileEntity.capacity}")

                val guiTooltip = GuiTooltip(mc)
                GL11.glDisable(GL11.GL_LIGHTING)
                GL11.glCullFace(GL11.GL_CULL_FACE)
                guiTooltip.render(text.toString(), x, y, 8, -8)
                GL11.glEnable(GL11.GL_LIGHTING)
                GL11.glEnable(GL11.GL_CULL_FACE)
            }
        }

        if (x > (scrnX + 102) && x < (scrnX + 106)) {
            if (y > (scrnY + 27) && y < (scrnX + 59)) {
                text.append("${TextFormatting.WHITE}HEIGHT: ${TextFormatting.LIGHT_GRAY}${tileEntity.yCoord}${TextFormatting.WHITE} / ${TextFormatting.LIGHT_GRAY}256")

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