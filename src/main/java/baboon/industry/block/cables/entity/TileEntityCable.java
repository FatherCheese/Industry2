package baboon.industry.block.cables.entity;

import baboon.industry.block.entity.TileEntityEnergyConductorDamageable;
import sunsetsatellite.sunsetutils.util.Connection;
import sunsetsatellite.sunsetutils.util.Direction;

public class TileEntityCable extends TileEntityEnergyConductorDamageable {

    public TileEntityCable(int capacity, int energy, int tranfer, int dangerLevel) {
        setCapacity(capacity);
        setEnergy(energy);
        setTransfer(tranfer);

        for (Direction dir : Direction.values())
            setConnection(dir, Connection.BOTH);
    }
}
