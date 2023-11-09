package baboon.industry.mixin.gui;

import baboon.industry.block.reactor.entity.TileEntityReactor;
import baboon.industry.gui.IPlayerDisplay;
import baboon.industry.gui.network.PacketOpenReactor;
import baboon.industry.gui.reactor.ContainerReactor;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.world.World;
import net.minecraft.server.entity.player.EntityPlayerMP;
import net.minecraft.server.net.handler.NetServerHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = EntityPlayerMP.class, remap = false)
public abstract class EntityPlayerMPMixin extends EntityPlayer implements IPlayerDisplay {
    public EntityPlayerMPMixin(World world) {
        super(world);
    }

    @Shadow protected abstract void getNextWindowId();

    @Shadow public NetServerHandler playerNetServerHandler;

    @Shadow private int currentWindowId;

    @Override
    public void displayGuiReactor(InventoryPlayer playerInventory, TileEntityReactor reactor, int initialChamberCount) {
        this.getNextWindowId();
        this.playerNetServerHandler.sendPacket(new PacketOpenReactor(this.currentWindowId, 0, reactor.getInvName(), reactor.getSizeInventory(), (byte) reactor.chamberCount));
        this.craftingInventory = new ContainerReactor(this.inventory, reactor);
        this.craftingInventory.windowId = this.currentWindowId;
        this.craftingInventory.onContainerInit((EntityPlayerMP)(Object)this);
    }
}
