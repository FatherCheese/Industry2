package baboon.industry.interfaces;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.Container;
import net.minecraft.core.player.inventory.IInventory;

public interface IEntityPlayerMP {

    void displayGuiScreen_i2(GuiScreen guiScreen, Container container, IInventory inventory, int x, int y, int z);

    void displayGuiScreen_i2(GuiScreen guiScreen, TileEntity inventory, int x, int y, int z);

    void displayItemGuiScreen_i2(GuiScreen guiScreen, Container container, IInventory inventory, ItemStack stack);
}
