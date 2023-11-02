package baboon.industry.block.entity;

import com.mojang.nbt.CompoundTag;
import sunsetsatellite.energyapi.impl.TileEntityEnergyConductor;
import sunsetsatellite.sunsetutils.util.Connection;
import sunsetsatellite.sunsetutils.util.Direction;

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
    private int machineHealth = maxMachineHealth;
    private Boolean lastTickDamage = false;

    public void setMachineHealth(int newHealth) {
        machineHealth = newHealth;
    }

    @Override
    public int receive(Direction dir, int amount, boolean test) {
        if (canConnect(dir, Connection.INPUT)) {
            if (amount > maxReceive && random.nextInt(4) == 0) {
                machineHealth -= amount - maxReceive;
                lastTickDamage = true;
            }

            int received = Math.min(capacity - energy, Math.min(maxReceive, amount));
            if (!test)
                energy += received;
        }
        return 0;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (machineHealth < 0)
            worldObj.createExplosion(null, xCoord, yCoord, zCoord, 1.0f);

        if (machineHealth < maxMachineHealth) {
            double x = xCoord + random.nextDouble();
            double y = yCoord + random.nextDouble();
            double z = zCoord + random.nextDouble();

            worldObj.spawnParticle("smoke", x, y + 0.22, z, 0.0, 0.0, 0.0);
            worldObj.spawnParticle("flame", x, y + 0.22, z, 0.0, 0.0, 0.0);
        }

        if (!lastTickDamage && random.nextInt(4) == 0  && machineHealth + healAmount <= maxMachineHealth)
            machineHealth += healAmount;

        lastTickDamage = false;
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
