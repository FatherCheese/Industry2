package baboon.industry.block.reactor.entity;

import baboon.industry.IndustryConfig;
import sunsetsatellite.catalyst.core.util.Connection;
import sunsetsatellite.catalyst.core.util.Direction;
import sunsetsatellite.catalyst.energy.impl.TileEntityEnergyConductor;

public class TileEntityReactorRTG extends TileEntityEnergyConductor {

    public TileEntityReactorRTG() {
        setCapacity(IndustryConfig.cfg.getInt("Energy Values.elvIO") * 2);
        setMaxProvide(IndustryConfig.cfg.getInt("Energy Values.elvIO") * 2);
        setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.elvIO") * 2);

        for (Direction dir : Direction.values())
            setConnection(dir, Connection.OUTPUT);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.energy <= this.capacity) ++this.energy;
    }
}
