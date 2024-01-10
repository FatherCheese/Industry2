package baboon.industry.gui.machine.basic;

import baboon.industry.block.machines.basic.entity.TileEntityMachineCannery;
import baboon.industry.item.IndustryItems;
import baboon.industry.recipe.RecipesCannery;
import net.minecraft.client.gui.GuiContainer;
import net.minecraft.client.gui.GuiTooltip;
import net.minecraft.core.InventoryAction;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.net.command.TextFormatting;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;
import net.minecraft.core.player.inventory.slot.SlotCrafting;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import sunsetsatellite.catalyst.energy.impl.ItemEnergyContainer;

public class GuiMachineCannery extends GuiContainer {
    private final TileEntityMachineCannery tileEntity;

    public GuiMachineCannery(InventoryPlayer inventory, TileEntityMachineCannery tileEntity) {
        super(new ContainerMachineCannery(inventory, tileEntity));
        this.tileEntity = tileEntity;
        this.xSize = 208;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f) {
        int texture = mc.renderEngine.getTexture("/assets/industry/gui/machine_double.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        mc.renderEngine.bindTexture(texture);

        int scrnX = (width - xSize) / 2;
        int scrnY = (height - ySize) / 2;
        drawTexturedModalRect(scrnX, scrnY, 0, 0, xSize, ySize);

        float power = (float) tileEntity.energy / tileEntity.capacity;
        drawTexturedModalRect(scrnX + 8, scrnY + 39, 176, 0, (int) (power * 16), 8);

        int machineTime = tileEntity.currentMachineTime * 23 / tileEntity.maxMachineTime;
        drawTexturedModalRect(scrnX + 79, scrnY + 35, 176, 8, machineTime + 1, 23);

        // Upgrades slots
        drawTexturedModalRect(scrnX + 176, scrnY, 0, 166, 32, 86);
    }

    @Override
    protected void drawGuiContainerForegroundLayer() {
        super.drawGuiContainerForegroundLayer();
        int scrnX = xSize / 2 - 16;
        drawStringCenteredNoShadow(fontRenderer, "Canning Machine", scrnX, 6, 4210752);
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

    private int getSlotID(int x, int y) {
        Slot slot = getSlotAtPosition(x, y);
        int scrnX = (width - xSize) / 2;
        int scrnY = (height - ySize) / 2;
        boolean isInvalidSlot = x < scrnX || y < scrnY || x > scrnX + xSize || y > scrnY + ySize;
        int slotID = -1;

        if (slot != null)
            slotID = slot.id;

        if (isInvalidSlot)
            slotID = -999;

        return slotID;
    }

    // This is copied from "Better Than Better Than Adventure", so shout out to them for figuring this out.
    @Override
    public void clickInventory(int x, int y, int mouseButton) {
        int slotId = this.getSlotID(x, y);
        if (slotId == -1) {
            return;
        }
        if (slotId == -999) {
            InventoryAction action = InventoryAction.DROP_HELD_STACK;
            if (mouseButton == 1)
                action = InventoryAction.DROP_HELD_SINGLE;

            this.mc.playerController.handleInventoryMouseClick(this.inventorySlots.windowId, action, null, this.mc.thePlayer);
            return;
        }
        if (!this.mc.thePlayer.getGamemode().consumeBlocks() && mouseButton == 2) {
            this.mc.playerController.handleInventoryMouseClick(this.inventorySlots.windowId, InventoryAction.CREATIVE_GRAB, new int[]{slotId, 64}, this.mc.thePlayer);
            return;
        }

        InventoryAction action = InventoryAction.CLICK_LEFT;
        boolean shiftPressed = Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54);
        boolean ctrlPressed = Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157);
        boolean altPressed = Keyboard.isKeyDown(56) || Keyboard.isKeyDown(184);
        boolean spacePressed = Keyboard.isKeyDown(57);
        if (mouseButton == 10) {
            shiftPressed = true;
            mouseButton = 0;
        }

        int target = 0;
        Slot slot = this.inventorySlots.getSlot(slotId);
        ItemStack stackInSlot = slot != null ? slot.getStack() : null;
        int clickedItemId = stackInSlot != null ? stackInSlot.getItem().id : 0;
        if (mouseButton == 1) {
            action = InventoryAction.CLICK_RIGHT;
        }

        if (slot instanceof SlotCrafting) {
            if (this.mc.gameSettings.swapCraftingButtons.value) {
                if (shiftPressed && ctrlPressed)
                    action = InventoryAction.MOVE_SIMILAR;
                else if (shiftPressed)
                    action = InventoryAction.MOVE_SINGLE_ITEM;
                else if (ctrlPressed)
                    action = InventoryAction.MOVE_STACK;
            } else if (shiftPressed && ctrlPressed)
                action = InventoryAction.MOVE_SIMILAR;
            else if (shiftPressed)
                action = InventoryAction.MOVE_STACK;
            else if (ctrlPressed)
                action = InventoryAction.MOVE_SINGLE_ITEM;
        } else if (spacePressed)
            action = InventoryAction.MOVE_ALL;
        else if (shiftPressed && ctrlPressed)
            action = InventoryAction.MOVE_SIMILAR;
        else if (shiftPressed || altPressed)
            action = InventoryAction.MOVE_STACK;
        else if (ctrlPressed)
            action = InventoryAction.MOVE_SINGLE_ITEM;

        if (this.inventorySlots instanceof ContainerMachineCannery) { // This is the only section that actually really matters
            boolean recipes = new RecipesCannery().getRecipeList().containsKey(clickedItemId);

            if (Item.itemsList[clickedItemId] instanceof ItemEnergyContainer)
                target = 1;
            else if (recipes)
                target = 2;
            else if (clickedItemId == IndustryItems.canEmpty.id || clickedItemId == IndustryItems.cellEmpty.id)
                target = 3;
        }

        if (slot != null && stackInSlot != null && slot.allowItemInteraction() && stackInSlot.getItem().hasInventoryInteraction() && mouseButton == 1) {
            this.mc.playerController.handleInventoryMouseClick(this.inventorySlots.windowId, InventoryAction.INTERACT_SLOT, new int[]{slot.id}, this.mc.thePlayer);
            return;
        }

        int[] args = new int[]{slotId, target};
        this.mc.playerController.handleInventoryMouseClick(this.inventorySlots.windowId, action, args, this.mc.thePlayer);
    }
}
