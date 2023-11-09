package baboon.industry.mixin.gui;

import baboon.industry.block.reactor.entity.TileEntityReactor;
import baboon.industry.gui.IPlayerDisplay;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.InventoryPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = EntityPlayer.class, remap = false)
public class EntityPlayerMixin implements IPlayerDisplay {
    @Override
    public void displayGuiReactor(InventoryPlayer playerInventory, TileEntityReactor reactor, int initialChamberCount) {

    }
}
