package baboon.industry.mixin.gui;

import baboon.industry.block.reactor.entity.TileEntityReactor;
import baboon.industry.gui.IPlayerDisplay;
import baboon.industry.gui.network.ICustomPackets;
import baboon.industry.gui.network.PacketOpenReactor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.net.handler.NetClientHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = NetClientHandler.class, remap = false)
public class NetClientHandlerMixin implements ICustomPackets {
    @Shadow private Minecraft mc;

    @Override
    public void handleOpenReactor(PacketOpenReactor packetOpenReactor) {
        TileEntityReactor tileEntityReactor = new TileEntityReactor();
        ((IPlayerDisplay) mc.thePlayer).displayGuiReactor(mc.thePlayer.inventory, tileEntityReactor, packetOpenReactor.chamberCount);
        mc.thePlayer.craftingInventory.windowId = packetOpenReactor.windowId;
    }
}
