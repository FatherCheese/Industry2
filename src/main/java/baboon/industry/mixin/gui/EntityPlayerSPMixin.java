package baboon.industry.mixin.gui;

import baboon.industry.block.reactor.entity.TileEntityReactor;
import baboon.industry.gui.IPlayerDisplay;
import baboon.industry.gui.reactor.GuiReactor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.EntityPlayerSP;
import net.minecraft.core.player.inventory.InventoryPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = EntityPlayerSP.class, remap = false)
public class EntityPlayerSPMixin implements IPlayerDisplay {
    @Shadow protected Minecraft mc;

    @Override
    public void displayGuiReactor(InventoryPlayer playerInventory, TileEntityReactor reactor, int initialChamberCount) {
        reactor.chamberCount = initialChamberCount;
        this.mc.displayGuiScreen(new GuiReactor(playerInventory, reactor));
    }
}
