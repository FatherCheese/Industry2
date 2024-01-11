package baboon.industry.block;

import baboon.industry.item.IndustryItems;
import net.minecraft.core.block.BlockLog;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

import java.util.Random;

public class BlockLogResin extends BlockLog {

    public BlockLogResin(String key, int id) {
        super(key, id);
        this.setTicking(true);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        super.updateTick(world, x, y, z, rand);
        int meta = world.getBlockMetadata(x, y, z);

        if (rand.nextInt(100) == 0) {
            world.setBlockAndMetadataWithNotify(x, y, z, IndustryBlocks.logRubberWoodResinFull.id, meta);
        }
    }

    @Override
    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        ItemStack itemStack = player.getHeldItem();

        if (itemStack != null && itemStack.getItem() == IndustryItems.toolTreetap)
            world.playSoundAtEntity(player, "industry.tap", 0.4f, 1.0f);

        return super.blockActivated(world, x, y, z, player);
    }

    @Override
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        if (dropCause == EnumDropCause.PICK_BLOCK) {
            return new ItemStack[]{new ItemStack(IndustryBlocks.logRubberWoodResinFull)};
        }
        return new ItemStack[]{new ItemStack(IndustryBlocks.logRubberWood)};
    }
}
