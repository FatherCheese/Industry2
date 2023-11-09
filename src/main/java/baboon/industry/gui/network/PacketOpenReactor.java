package baboon.industry.gui.network;

import net.minecraft.core.net.handler.NetHandler;
import net.minecraft.core.net.packet.Packet100OpenWindow;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketOpenReactor extends Packet100OpenWindow {
    public byte chamberCount;
    public PacketOpenReactor(){

    }
    public PacketOpenReactor(int windowId, int inventoryType, String windowTitle, int slotsCount, byte chamberCount) {
        super(windowId, inventoryType, windowTitle, slotsCount);
        this.chamberCount = chamberCount;
    }
    @Override
    public void readPacketData(DataInputStream dataInputStream) throws IOException {
        super.readPacketData(dataInputStream);
        this.chamberCount = dataInputStream.readByte();
    }

    @Override
    public void writePacketData(DataOutputStream dataOutputStream) throws IOException {
        super.writePacketData(dataOutputStream);
        dataOutputStream.writeByte(chamberCount);
    }

    @Override
    public void processPacket(NetHandler netHandler) {
        ((ICustomPackets)netHandler).handleOpenReactor(this);
    }

    @Override
    public int getPacketSize() {
        return super.getPacketSize() + 1;
    }
}
