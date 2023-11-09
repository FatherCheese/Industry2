package baboon.industry.mixin.gui;

import baboon.industry.gui.network.ICustomPackets;
import baboon.industry.gui.network.PacketOpenReactor;
import net.minecraft.core.net.handler.NetHandler;
import net.minecraft.core.net.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = NetHandler.class, remap = false)
public abstract class NetHandlerMixin implements ICustomPackets {
    @Shadow public abstract void handleInvalidPacket(Packet packet);

    @Override
    public void handleOpenReactor(PacketOpenReactor packetOpenReactor) {
        handleInvalidPacket(packetOpenReactor);
    }
}
