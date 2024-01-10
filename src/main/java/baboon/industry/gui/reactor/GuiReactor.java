package baboon.industry.gui.reactor;

import baboon.industry.block.reactor.entity.TileEntityReactorNew;
import baboon.industry.item.IndustryItems;
import net.minecraft.client.gui.GuiContainer;
import net.minecraft.core.InventoryAction;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemArmor;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;
import net.minecraft.core.player.inventory.slot.SlotCrafting;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiReactor extends GuiContainer {
    private final TileEntityReactorNew tile;
    private final InventoryPlayer inventory;

    public GuiReactor(InventoryPlayer inventory, TileEntityReactorNew tileEntity) {
        super(new ContainerReactor(inventory, tileEntity));
        this.tile = tileEntity;
        this.inventory = inventory;
        ySize = 97 + 17 + 18 * 6;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f) {
        int reactorRows = 6;
        ySize = 97 + 17 + 18 * reactorRows;

        int texture = mc.renderEngine.getTexture("/assets/industry/gui/reactor.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        mc.renderEngine.bindTexture(texture);

        int scrnX = (width - xSize) / 2;
        int scrnY = (height - ySize) / 2;
        drawTexturedModalRect(scrnX, scrnY, 0, 0, xSize, 17);

        drawTexturedModalRect(scrnX, scrnY + 17, 0, 17, xSize, 18 * reactorRows);
        drawTexturedModalRect(scrnX, scrnY + 17 + 18 * reactorRows, 0, 126, xSize, 97);

    }

    @Override
    protected void drawGuiContainerForegroundLayer() {
        super.drawGuiContainerForegroundLayer();
        drawStringCenteredNoShadow(fontRenderer, "Nuclear Reactor", xSize / 2, 6, 4210752);
//        fontRenderer.drawString("Inventory", 8, ySize / 2, 4210752);
    }
    public void clickInventory(int x, int y, int mouseButton) {
        int slotId = this.getSlotId(x, y);
        if (slotId == -1) {
            return;
        }
        if (slotId == -999) {
            InventoryAction action = InventoryAction.DROP_HELD_STACK;
            if (mouseButton == 1) {
                action = InventoryAction.DROP_HELD_SINGLE;
            }
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
        Item itemInSlot = stackInSlot != null ? stackInSlot.getItem() : null;
        int clickedItemId = stackInSlot != null ? stackInSlot.getItem().id : 0;
        ItemStack grabbedItem = this.mc.thePlayer.inventory.getHeldItemStack();
        if (mouseButton == 1) {
            action = InventoryAction.CLICK_RIGHT;
        }
        if (slot instanceof SlotCrafting) {
            if (this.mc.gameSettings.swapCraftingButtons.value) {
                if (shiftPressed && ctrlPressed) {
                    action = InventoryAction.MOVE_SIMILAR;
                } else if (shiftPressed) {
                    action = InventoryAction.MOVE_SINGLE_ITEM;
                } else if (ctrlPressed) {
                    action = InventoryAction.MOVE_STACK;
                }
            } else if (shiftPressed && ctrlPressed) {
                action = InventoryAction.MOVE_SIMILAR;
            } else if (shiftPressed) {
                action = InventoryAction.MOVE_STACK;
            } else if (ctrlPressed) {
                action = InventoryAction.MOVE_SINGLE_ITEM;
            }
        } else if (spacePressed) {
            action = InventoryAction.MOVE_ALL;
        } else if (shiftPressed && ctrlPressed) {
            action = InventoryAction.MOVE_SIMILAR;
        } else if (shiftPressed || altPressed) {
            action = InventoryAction.MOVE_STACK;
        } else if (ctrlPressed) {
            action = InventoryAction.MOVE_SINGLE_ITEM;
        }
        if (this.inventorySlots instanceof ContainerReactor) { // This is the only section that actually really matters
            if (clickedItemId == IndustryItems.cellUranium.id || clickedItemId == IndustryItems.cellCoolant.id || clickedItemId == IndustryItems.reactorPlate.id){
                target = 1;
            }
        }
        if (slot != null && itemInSlot instanceof ItemArmor && mouseButton == 1 && shiftPressed) {
            this.mc.playerController.handleInventoryMouseClick(this.inventorySlots.windowId, InventoryAction.EQUIP_ARMOR, new int[]{slot.id}, this.mc.thePlayer);
            return;
        }
        if (slot != null && slot.allowItemInteraction() && grabbedItem != null && grabbedItem.getItem().hasInventoryInteraction() && mouseButton == 1) {
            this.mc.playerController.handleInventoryMouseClick(this.inventorySlots.windowId, InventoryAction.INTERACT_GRABBED, new int[]{slot.id}, this.mc.thePlayer);
            return;
        }
        if (slot != null && stackInSlot != null && slot.allowItemInteraction() && stackInSlot.getItem().hasInventoryInteraction() && mouseButton == 1) {
            this.mc.playerController.handleInventoryMouseClick(this.inventorySlots.windowId, InventoryAction.INTERACT_SLOT, new int[]{slot.id}, this.mc.thePlayer);
            return;
        }
        int[] args = new int[]{slotId, target};
        this.mc.playerController.handleInventoryMouseClick(this.inventorySlots.windowId, action, args, this.mc.thePlayer);
    }
    private int getSlotId(int x, int y) {
        Slot slot = this.getSlotAtPosition(x, y);
        int x2 = (this.width - this.xSize) / 2;
        int y2 = (this.height - this.ySize) / 2;
        boolean flag = x < x2 || y < y2 || x >= x2 + this.xSize || y >= y2 + this.ySize;
        int slotId = -1;
        if (slot != null) {
            slotId = slot.id;
        }
        if (flag) {
            slotId = -999;
        }
        return slotId;
    }
}
