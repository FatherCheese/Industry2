package baboon.industry.block.reactor.entity;

import baboon.industry.IndustryConfig;
import com.mojang.nbt.CompoundTag;
import sunsetsatellite.energyapi.impl.TileEntityEnergyConductor;
import sunsetsatellite.sunsetutils.util.Connection;
import sunsetsatellite.sunsetutils.util.Direction;

public class TileEntityReactorIO extends TileEntityEnergyConductor {

    public TileEntityReactorIO() {
        setCapacity(IndustryConfig.cfg.getInt("Energy Values.ehvIO"));
        setMaxProvide(IndustryConfig.cfg.getInt("Energy Values.ehvIO"));
        setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.ehvIO"));

        for (Direction dir : Direction.values())
            setConnection(dir, Connection.OUTPUT);

        setConnection(Direction.Y_POS, Connection.INPUT);
        setConnection(Direction.Y_NEG, Connection.INPUT);
    }

    @Override
    public void writeToNBT(CompoundTag compoundTag) {
        super.writeToNBT(compoundTag);
        compoundTag.putInt("Energy", energy);
    }

    @Override
    public void readFromNBT(CompoundTag compoundTag) {
        super.readFromNBT(compoundTag);
        energy = compoundTag.getInteger("Energy");
    }
}
