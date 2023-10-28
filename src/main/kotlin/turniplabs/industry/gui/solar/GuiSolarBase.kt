package turniplabs.industry.gui.solar

import net.minecraft.client.gui.GuiContainer
import net.minecraft.client.gui.GuiTooltip
import net.minecraft.core.net.command.TextFormatting
import net.minecraft.core.player.inventory.InventoryPlayer
import net.minecraft.core.world.World
import org.lwjgl.opengl.GL11
import turniplabs.industry.blocks.entities.solar.TileEntitySolarBase

open class GuiSolarBase(inventory: InventoryPlayer?, private var tileEntity: TileEntitySolarBase) :
GuiContainer(ContainerSolarBase(inventory, tileEntity)) {
    // Thanks to MartinSVK12 for fixing the generated energy stuff!
    override fun drawGuiContainerBackgroundLayer(f: Float) {
        val world: World = mc.theWorld

        val texture: Int = mc.renderEngine.getTexture("/assets/industry/gui/generator_solar.png")
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
        mc.renderEngine.bindTexture(texture)

        val textX: Int = (width - xSize) / 2
        val textY: Int = (height - ySize) / 2
        drawTexturedModalRect(textX, textY, 0, 0, xSize, ySize)

        val power: Double = (tileEntity.energy.toFloat() / tileEntity.capacity.toFloat()).toDouble()
        drawTexturedModalRect(textX + 8, textY + 39, 176, 8, (power * 16).toInt(), 8)

        // Checks the generated energy, if it's above 0 display a sun, else it displays a moon
        if (tileEntity.generatedEnergy > 0) drawTexturedModalRect(
            textX + 84,
            textY + 33,
            176,
            0,
            8,
            8
        ) else drawTexturedModalRect(textX + 84, textY + 33, 184, 0, 8, 8)

        // Daylight bar(?)
        drawTexturedModalRect(textX + 76, textY + 47, 176, 16, (world.worldTime % world.worldType.dayNightCycleLengthTicks / 1000).toInt(), 4)
    }

    override fun drawScreen(x: Int, y: Int, renderPartialTicks: Float) {
        val scrnX: Int = (width - xSize) / 2
        val scrnY: Int = (height - ySize) / 2
        super.drawScreen(x, y, renderPartialTicks)

        val text = StringBuilder()
        if ((x > (scrnX + 8)) && (x < (scrnX + 24))) {
            if (y > (scrnY + 39) && y < (scrnY + 47)) {
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
        if (x > (scrnX + 84) && x < (scrnX + 92)) {
            if (y > (scrnY + 33) && y < (scrnY + 41)) {
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