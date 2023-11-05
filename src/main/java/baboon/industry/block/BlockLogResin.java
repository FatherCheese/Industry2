package baboon.industry.block;

import baboon.industry.item.IndustryItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

import java.util.Random;

public class BlockLogResin extends Block {

    public BlockLogResin(String key, int id) {
        super(key, id, Material.wood);
        this.setTickOnLoad(true);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        super.updateTick(world, x, y, z, rand);

        if (rand.nextInt(300) == 0 && world.getBlockMetadata(x, y, z) == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 0);
            world.scheduleBlockUpdate(x, y, z, IndustryBlocks.logRubberWoodResin.id, tickRate());
        } else
            world.scheduleBlockUpdate(x, y, z, IndustryBlocks.logRubberWoodResin.id, tickRate());
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        if (world.getBlockMetadata(x, y, z) == 0) {
            double slimeX = x + rand.nextDouble();
            double slimeY = y + rand.nextDouble();
            double slimeZ = z + rand.nextDouble();
            world.spawnParticle("slime", slimeX, slimeY + 0.22, slimeZ, 0.0, -0.2, 0.0);
        }
    }

    @Override
    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        ItemStack itemStack = player.getHeldItem();

        if (!world.isClientSide) {
            if (world.getBlockMetadata(x, y, z) == 0 && itemStack != null && itemStack.getItem() == IndustryItems.toolTreetap) {
                world.playSoundAtEntity(player, "random.wood click", 0.4f, 1.0f);
                world.playSoundAtEntity(player, "mob.slime", 1.0f, 1.0f);
                world.setBlockMetadataWithNotify(x, y, z, 1);
                world.scheduleBlockUpdate(x, y, z, IndustryBlocks.logRubberWoodResin.id, tickRate());

                int randAmount = new Random().nextInt(4 - 1) + 1;
                player.inventory.insertItem(new ItemStack(IndustryItems.resin, randAmount), false);
                itemStack.damageItem(1, player);

                return true;
            }
        }
        return super.blockActivated(world, x, y, z, player);
    }

    @Override
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        if (dropCause == EnumDropCause.PICK_BLOCK) {
            return new ItemStack[]{new ItemStack(this)};
        }
        return new ItemStack[]{new ItemStack(IndustryBlocks.logRubberWood)};
    }
}
