package baboon.industry.gui;

import baboon.industry.block.reactor.entity.TileEntityReactor;
import net.minecraft.core.player.inventory.InventoryPlayer;

public interface IPlayerDisplay {
    void displayGuiReactor(InventoryPlayer playerInventory, TileEntityReactor reactor, int initialChamberCount);
}
