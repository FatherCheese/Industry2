package baboon.industry.block.entity;

import com.mojang.nbt.CompoundTag;
import sunsetsatellite.catalyst.core.util.Connection;
import sunsetsatellite.catalyst.core.util.Direction;
import sunsetsatellite.catalyst.energy.impl.TileEntityEnergyConductor;

import java.util.Random;

public class TileEntityEnergyConductorDamageable extends TileEntityEnergyConductor {

    /*                             /
    / Default Energy Ratings:      /
    / 16RC = Low voltage           /
    / 32RC = Medium voltage        /
    / 512RC = High voltage         /
    / 1024RC = Super High voltage */

    private final Random random = new Random();
    private final int maxMachineHealth = 100;
    private final int healAmount = 1;
    public int machineHealth = maxMachineHealth;
    private Boolean lastTickDamage = false;
    public TileEntityEnergyConductorDamageable() {
    }
    public int receive(Direction dir, int amount, boolean test) {
        if (canConnect(dir, Connection.INPUT)) {
            if (amount > maxReceive && random.nextInt(4) == 0) {
                machineHealth -= amount - maxReceive;
                lastTickDamage = true;
            }

            int received = Math.min(capacity - energy, Math.min(maxReceive, amount));
            if (!test)
                energy += received;

            return received;
        }

        return 0;
    }

    @Override
    public void tick() {
        super.tick();

        if (!worldObj.isClientSide) {
            if (machineHealth < 0)
                worldObj.createExplosion(null, x, y, z, 1.0f);

            if (machineHealth < maxMachineHealth) {
                double randX = x + random.nextDouble();
                double randY = y + random.nextDouble();
                double randZ = z + random.nextDouble();

                worldObj.spawnParticle("smoke", randX, randY + 0.22, randZ, 0.0, 0.0, 0.0);
                worldObj.spawnParticle("flame", randX, randY + 0.22, randZ, 0.0, 0.0, 0.0);
            }

            if (!lastTickDamage && random.nextInt(4) == 0 && machineHealth + healAmount <= maxMachineHealth)
                machineHealth += healAmount;

            lastTickDamage = false;
        }
    }

    @Override
    public void readFromNBT(CompoundTag CompoundTag) {
        super.readFromNBT(CompoundTag);
        machineHealth = CompoundTag.getInteger("Health");
    }

    @Override
    public void writeToNBT(CompoundTag CompoundTag) {
        super.writeToNBT(CompoundTag);
        CompoundTag.putInt("Health", machineHealth);
    }
}
