package baboon.industry.gui.machine.basic;

import baboon.industry.block.machines.basic.entity.TileEntityMachineBase;
import net.minecraft.core.player.inventory.InventoryPlayer;

public class GuiMachineWiremill extends GuiMachineBase {

    public GuiMachineWiremill(InventoryPlayer inventory, TileEntityMachineBase tileEntity) {
        super(inventory, tileEntity);
    }

    @Override
    protected void drawGuiContainerForegroundLayer() {
        super.drawGuiContainerForegroundLayer();
        int scrnX = xSize / 2;
        drawStringCenteredNoShadow(fontRenderer, "Wiremill", scrnX, 6, 4210752);
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 4210752);
    }
}
