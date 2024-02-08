package baboon.industry.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.sound.SoundType;
import net.minecraft.core.world.World;

import java.util.Random;

public class BlockAlarm extends Block {
    public BlockAlarm(String key, int id) {
        super(key, id, Material.metal);
        setTicking(true);
    }

    @Override
    public int tickRate() {
        return 50;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
        if (world.isBlockGettingPowered(x, y, z) || world.isBlockIndirectlyGettingPowered(x, y, z)) {
            world.setBlockMetadataWithNotify(x, y, z, 1);
            world.scheduleBlockUpdate(x, y, z, this.id, 0);
        } else {
            world.setBlockMetadataWithNotify(x, y, z, 0);
        }
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        world.scheduleBlockUpdate(x, y, z, this.id, tickRate());
        if (world.getBlockMetadata(x, y, z) == 1) {
            world.playSoundEffect(SoundType.WORLD_SOUNDS, x, y, z, "industry.alarm", 1.0F, 1.0F);
        }
    }
}
