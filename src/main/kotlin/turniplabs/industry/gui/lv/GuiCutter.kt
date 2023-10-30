package turniplabs.industry.gui.lv

import net.minecraft.client.gui.GuiContainer
import net.minecraft.client.gui.GuiTooltip
import net.minecraft.core.InventoryAction
import net.minecraft.core.item.Item
import net.minecraft.core.net.command.TextFormatting
import net.minecraft.core.player.inventory.InventoryPlayer
import net.minecraft.core.player.inventory.slot.SlotCrafting
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.GL11
import turniplabs.industry.blocks.entities.lv.TileEntityCutter
import turniplabs.industry.items.ItemBatteryBase
import turniplabs.industry.recipes.RecipesCutter

class GuiCutter(inventory: InventoryPlayer?, private val tileEntity: TileEntityCutter) :
    GuiContainer(ContainerCutter(inventory, tileEntity)) {

    override fun drawGuiContainerBackgroundLayer(f: Float) {
        val texture: Int = mc.renderEngine.getTexture("/assets/industry/gui/machine_single.png")
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
        mc.renderEngine.bindTexture(texture)

        val textX: Int = (width - xSize) / 2
        val textY: Int = (height - ySize) / 2
        drawTexturedModalRect(textX, textY, 0, 0, xSize, ySize)

        val power: Double = (tileEntity.energy.toFloat() / tileEntity.capacity.toFloat()).toDouble()
        drawTexturedModalRect(textX + 8, textY + 39, 176, 0, (power * 16).toInt(), 8)

        val progress: Int = tileEntity.getProgressScaled(23)
        drawTexturedModalRect(textX + 79, textY + 35, 176, 8, progress + 1, 23)
    }

    override fun drawGuiContainerForegroundLayer() {
        super.drawGuiContainerForegroundLayer()
        fontRenderer.drawString("Cutting Machine", 46, 6, 4210752)
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 4210752)
    }

    override fun drawScreen(x: Int, y: Int, renderPartialTicks: Float) {
        val scrnX: Int = (width - xSize) / 2
        val scrnY: Int = (height - ySize) / 2
        super.drawScreen(x, y, renderPartialTicks)

        val text = StringBuilder()
        if ((x > (scrnX + 8)) && (x < (scrnX + 24))) {
            if (y > (scrnY + 39) && y < (scrnY + 47)) {
                text.append("${TextFormatting.WHITE}Energy: ${TextFormatting.LIGHT_GRAY}${tileEntity.energy} ${TextFormatting.WHITE} / ${TextFormatting.WHITE}${tileEntity.capacity}")

                val guiTooltip = GuiTooltip(mc)
                GL11.glDisable(GL11.GL_LIGHTING)
                GL11.glCullFace(GL11.GL_CULL_FACE)
                guiTooltip.render(text.toString(), x, y, 8, -8)
                GL11.glEnable(GL11.GL_LIGHTING)
                GL11.glEnable(GL11.GL_CULL_FACE)
            }
        }
    }

    private fun getSlotID(x: Int, y: Int): Int {
        val slot = getSlotAtPosition(x, y)
        val x2 = (width - xSize) / 2
        val y2 = (height - ySize) / 2
        val flag = x < x2 || y < y2 || x >= x2 + xSize || y >= y2 + ySize
        var slotId = -1

        if (slot != null)
            slotId = slot.id

        if (flag)
            slotId = -999

        return slotId
    }

    // Most of this is copied from BTB, so shout out to them for figuring this out.
    override fun clickInventory(x: Int, y: Int, mouseButton: Int) {
        val slotId = getSlotID(x, y)
        if (slotId == -1) {
            return
        }

        if (slotId == -999) {
            var action = InventoryAction.DROP_HELD_STACK
            if (mouseButton == 1)
                action = InventoryAction.DROP_HELD_SINGLE

            mc.playerController.doInventoryAction(inventorySlots.windowId, action, null, mc.thePlayer)
            return
        }

        if (!mc.thePlayer.getGamemode().consumeBlocks && mouseButton == 2) {
            mc.playerController.doInventoryAction(
                inventorySlots.windowId,
                InventoryAction.CREATIVE_GRAB,
                intArrayOf(slotId, 64),
                mc.thePlayer
            )
            return
        }

        var action = InventoryAction.CLICK_LEFT
        var shiftPressed = Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54)
        val ctrlPressed = Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157)
        val altPressed = Keyboard.isKeyDown(56) || Keyboard.isKeyDown(184)
        val spacePressed: Boolean = Keyboard.isKeyDown(57)
        if (mouseButton == 10)
            shiftPressed = true

        var target = 0
        val slot = inventorySlots.getSlot(slotId)
        val stackInSlot = slot?.stack
        val clickedItemId = stackInSlot?.item?.id ?: 0
        val grabbedItem = mc.thePlayer.inventory.heldItemStack
        if (mouseButton == 1)
            action = InventoryAction.CLICK_RIGHT

        when {
            slot is SlotCrafting -> {
                when {
                    mc.gameSettings.swapCraftingButtons.value -> {
                        when {
                            shiftPressed && ctrlPressed -> {
                                action = InventoryAction.MOVE_SIMILAR
                            }

                            shiftPressed -> {
                                action = InventoryAction.MOVE_SINGLE_ITEM
                            }

                            ctrlPressed -> {
                                action = InventoryAction.MOVE_STACK
                            }
                        }
                    }
                    shiftPressed && ctrlPressed -> {
                        action = InventoryAction.MOVE_SIMILAR
                    }
                    shiftPressed -> {
                        action = InventoryAction.MOVE_STACK
                    }
                    ctrlPressed -> {
                        action = InventoryAction.MOVE_SINGLE_ITEM
                    }
                }
            }
            spacePressed -> {
                action = InventoryAction.MOVE_ALL
            }
            shiftPressed && ctrlPressed -> {
                action = InventoryAction.MOVE_SIMILAR
            }
            shiftPressed || altPressed -> {
                action = InventoryAction.MOVE_STACK
            }
            ctrlPressed -> {
                action = InventoryAction.MOVE_SINGLE_ITEM
            }
        }

        val isIngredient: Boolean = RecipesCutter.getRecipeList().containsKey(clickedItemId)

        if (inventorySlots is ContainerCutter) {
            if (Item.itemsList[clickedItemId] is ItemBatteryBase)
                target = 1
            if (isIngredient)
                target = 2
        }
        if (slot != null && slot.allowItemInteraction() && grabbedItem != null && grabbedItem.item.hasInventoryInteraction() && mouseButton == 1) {
            mc.playerController.doInventoryAction(
                inventorySlots.windowId,
                InventoryAction.INTERACT_GRABBED,
                intArrayOf(slot.id),
                mc.thePlayer
            )
            return
        }

        if (slot != null && stackInSlot != null && slot.allowItemInteraction() && stackInSlot.item.hasInventoryInteraction() && mouseButton == 1) {
            mc.playerController.doInventoryAction(
                inventorySlots.windowId,
                InventoryAction.INTERACT_SLOT,
                intArrayOf(slot.id),
                mc.thePlayer
            )
            return
        }

        val args = intArrayOf(slotId, target)
        mc.playerController.doInventoryAction(inventorySlots.windowId, action, args, mc.thePlayer)
    }
}