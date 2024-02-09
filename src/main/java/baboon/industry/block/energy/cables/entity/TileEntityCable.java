package baboon.industry.block.energy.cables.entity;

import baboon.industry.block.energy.entity.TileEntityEnergyConductorDamageable;
import sunsetsatellite.catalyst.core.util.Connection;
import sunsetsatellite.catalyst.core.util.Direction;

public class TileEntityCable extends TileEntityEnergyConductorDamageable {
    public TileEntityCable(){

    }

    public TileEntityCable(int capacity, int energy, int tranfer, int dangerLevel) {
        setCapacity(capacity);
        setEnergy(energy);
        setTransfer(tranfer);

        for (Direction dir : Direction.values())
            setConnection(dir, Connection.BOTH);
    }
}
