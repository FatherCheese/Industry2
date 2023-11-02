package baboon.industry.block.cables.entity;

import baboon.industry.block.entity.TileEntityEnergyConductorDamageable;
import sunsetsatellite.sunsetutils.util.Connection;
import sunsetsatellite.sunsetutils.util.Direction;

public class TileEntityCable extends TileEntityEnergyConductorDamageable {

    public TileEntityCable(int capacity, int energy, int transfer, int cableDangerLevel) {
        setCapacity(capacity);
        setEnergy(energy);
        setTransfer(transfer);

        for (Direction dir : Direction.values())
            setConnection(dir, Connection.BOTH);
    }
}
