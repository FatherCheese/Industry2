package baboon.industry.block;

import net.minecraft.core.block.BlockRotatableHorizontal;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.sound.SoundType;
import net.minecraft.core.world.World;

import java.util.Random;

public class BlockAlarm extends BlockRotatableHorizontal {
    private boolean triggerAlarm = false;

    public BlockAlarm(String key, int id) {
        super(key, id, Material.metal);
        setTicking(true);
    }

    @Override
    public int tickRate() {
        return triggerAlarm ? 50 : 3;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
        triggerAlarm = world.isBlockGettingPowered(x, y, z) || world.isBlockIndirectlyGettingPowered(x, y, z);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        super.updateTick(world, x, y, z, rand);
        if (triggerAlarm)
            world.playSoundEffect(SoundType.WORLD_SOUNDS, x, y, z, "industry.alarm", 1.0F, 1.0F);
        world.scheduleBlockUpdate(x, y, z, this.id, tickRate());
    }
}
