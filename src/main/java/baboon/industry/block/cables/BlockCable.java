package baboon.industry.block.cables;

import baboon.industry.block.cables.entity.TileEntityCable;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;
import sunsetsatellite.energyapi.api.IEnergy;

import java.util.Random;

public class BlockCable extends BlockTileEntity {
    public int capacity;
    public int transfer;
    private final int dangerLevel;

    public BlockCable(String key, int id, Material material, int capacity, int transfer, int dangerLevel) {
        super(key, id, material);

        this.capacity = capacity;
        this.transfer = transfer;
        this.dangerLevel = dangerLevel;

        setBlockBounds(0.25f, 0.25f, 0.25f, 0.75f, 0.75f, 0.75f);
    }

    @Override
    protected TileEntity getNewBlockEntity() {
        return new TileEntityCable(capacity, 0, transfer, dangerLevel);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    private AABB cableBoundingBox(World blockAccess, BlockCable blockCable, int x, int y, int z) {
        double boundMin = 0.375;
        double boundMax = 0.625;
        AABB defaultBox = new AABB(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);

        boolean aPosX = blockAccess.getBlockId(x + 1, y, z) == blockCable.id ||
                blockAccess.getBlockTileEntity(x + 1, y, z) instanceof IEnergy;

        boolean aNegX = blockAccess.getBlockId(x - 1, y, z) == blockCable.id ||
                blockAccess.getBlockTileEntity(x - 1, y, z) instanceof IEnergy;

        boolean aPosY = blockAccess.getBlockId(x, y + 1, z) == blockCable.id ||
                blockAccess.getBlockTileEntity(x, y + 1, z) instanceof IEnergy;

        boolean aNegY = blockAccess.getBlockId(x, y - 1, z) == blockCable.id ||
                blockAccess.getBlockTileEntity(x, y - 1, z) instanceof IEnergy;

        boolean aPosZ = blockAccess.getBlockId(x, y, z + 1) == blockCable.id ||
                blockAccess.getBlockTileEntity(x, y, z + 1) instanceof IEnergy;

        boolean aNegZ = blockAccess.getBlockId(x, y, z - 1) == blockCable.id ||
                blockAccess.getBlockTileEntity(x, y, z - 1) instanceof IEnergy;

        // Thanks to Nuke from the modding discord for help translating to Java!
        double offset = boundMax / 2;
        defaultBox.setBounds(
                boundMin - (aNegX ? offset : 0),
                boundMin - (aNegY ? offset : 0),
                boundMin - (aNegZ ? offset : 0),
                boundMax + (aPosX ? offset : 0),
                boundMax + (aPosY ? offset : 0),
                boundMax + (aPosZ ? offset : 0)
        );

        return AABB.getBoundingBoxFromPool(
                x + defaultBox.minX,
                y + defaultBox.minY,
                z + defaultBox.minZ,
                x + defaultBox.maxX,
                y + defaultBox.maxY,
                z + defaultBox.maxZ);
    }

    @Override
    public AABB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        return cableBoundingBox(world, this, x, y, z);
    }

    @Override
    public AABB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return cableBoundingBox(world, this, x, y, z);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        super.onEntityCollidedWithBlock(world, x, y, z, entity);
        IEnergy tile = (IEnergy) world.getBlockTileEntity(x, y, z);
        if (entity instanceof EntityLiving && dangerLevel > 0) {
            if (tile.getEnergy() > 0 && tile.getEnergy() - dangerLevel * 10 > 0) {
                tile.setEnergy(-dangerLevel * 10);

                entity.hurt(null, dangerLevel, DamageType.GENERIC);

                Random random = new Random();
                double randX = random.nextDouble() + entity.x;
                double randY = random.nextDouble() + entity.y;
                double randZ = random.nextDouble() + entity.z;
                world.spawnParticle("flame", randX, randY, randZ, 0.0, 0.0, 0.0);
                world.playSoundAtEntity(entity, "industry.zap", 0.1f, 0.8f);
            }
        }
    }
}
