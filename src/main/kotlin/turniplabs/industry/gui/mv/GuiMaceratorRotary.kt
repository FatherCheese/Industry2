package turniplabs.industry.gui.mv

import net.minecraft.client.gui.GuiContainer
import net.minecraft.client.gui.GuiTooltip
import net.minecraft.core.net.command.TextFormatting
import net.minecraft.core.player.inventory.InventoryPlayer
import org.lwjgl.opengl.GL11
import turniplabs.industry.blocks.entities.mv.TileEntityMaceratorRotary

class GuiMaceratorRotary(inventory: InventoryPlayer, private val tileEntity: TileEntityMaceratorRotary) :
    GuiContainer(ContainerMaceratorRotary(inventory, tileEntity))
{

    override fun drawGuiContainerBackgroundLayer(f: Float) {
        val texture: Int = mc.renderEngine.getTexture("/assets/industry/gui/advanced_machine.png")
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
        mc.renderEngine.bindTexture(texture)

        val textX: Int = (width - xSize) / 2
        val textY: Int = (height - ySize) / 2
        drawTexturedModalRect(textX, textY, 0, 0, xSize, ySize)

        val power: Double = (tileEntity.energy.toFloat() / tileEntity.capacity.toFloat()).toDouble()
        drawTexturedModalRect(textX + 8, textY + 39, 176, 0, (power * 16).toInt(), 8)

        val progress: Int = tileEntity.getProgressScaled(23)
        drawTexturedModalRect(textX + 79, textY + 35, 176, 8, progress + 1, 20)

        val redstone: Int = tileEntity.redstone / 341
        drawTexturedModalRect(textX + 157, (textY + 23 + 24) - redstone, 176, 47 - redstone, 4, redstone)
    }

    override fun drawGuiContainerForegroundLayer() {
        super.drawGuiContainerForegroundLayer()
        fontRenderer.drawString("Rotary Macerator", 64, 6, 4210752)
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 4210752)
    }

    override fun drawScreen(x: Int, y: Int, renderPartialTicks: Float) {
        val scrnX: Int = (width - xSize) / 2
        val scrnY: Int = (height - ySize) / 2
        super.drawScreen(x, y, renderPartialTicks)

        val text = StringBuilder()
        if ((x > (scrnX + 8)) && (x < (scrnX + 24))) {
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

        if (x > (scrnX + 157) && x < (scrnX + 161))
            if (y > (scrnY + 23) && y < (scrnY + 47)) {
                text.append("${TextFormatting.WHITE}Redstone: ${TextFormatting.LIGHT_GRAY}${tileEntity.redstone}${TextFormatting.WHITE} / ${TextFormatting.LIGHT_GRAY}8192")

                val guiTooltip = GuiTooltip(mc)
                GL11.glDisable(GL11.GL_LIGHTING)
                GL11.glCullFace(GL11.GL_CULL_FACE)
                guiTooltip.render(text.toString(), x, y, 8, -8)
                GL11.glEnable(GL11.GL_LIGHTING)
                GL11.glEnable(GL11.GL_CULL_FACE)
            }
    }
}