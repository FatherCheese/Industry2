package baboon.industry.block.reactor;

import baboon.industry.block.reactor.entity.TileEntityReactor;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.entity.TileEntityDispenser;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

import java.util.Random;

public class BlockReactor extends BlockTileEntity {
    private final Random random = new Random();
    public BlockReactor(String key, int id, Material material) {
        super(key, id, material);
    }

    @Override
    protected TileEntity getNewBlockEntity() {
        return new TileEntityReactor();
    }

    @Override
    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        return super.blockActivated(world, x, y, z, player);
    }
    public void onBlockRemoval(World world, int x, int y, int z) {
        if (world.getBlockTileEntity(x, y, z) != null) {
            TileEntityReactor tileEntityReactor = (TileEntityReactor)world.getBlockTileEntity(x, y, z);
            for (int l = 0; l < tileEntityReactor.getSizeInventory(); ++l) {
                ItemStack itemstack = tileEntityReactor.getStackInSlot(l);
                if (itemstack == null) continue;
                float f = this.random.nextFloat() * 0.8f + 0.1f;
                float f1 = this.random.nextFloat() * 0.8f + 0.1f;
                float f2 = this.random.nextFloat() * 0.8f + 0.1f;
                while (itemstack.stackSize > 0) {
                    int i1 = this.random.nextInt(21) + 10;
                    if (i1 > itemstack.stackSize) {
                        i1 = itemstack.stackSize;
                    }
                    itemstack.stackSize -= i1;
                    EntityItem entityitem = new EntityItem(world, (float)x + f, (float)y + f1, (float)z + f2, new ItemStack(itemstack.itemID, i1, itemstack.getMetadata()));
                    float f3 = 0.05f;
                    entityitem.xd = (float)this.random.nextGaussian() * f3;
                    entityitem.yd = (float)this.random.nextGaussian() * f3 + 0.2f;
                    entityitem.zd = (float)this.random.nextGaussian() * f3;
                    world.entityJoinedWorld(entityitem);
                }
            }
        }
        super.onBlockRemoval(world, x, y, z);
    }
}
