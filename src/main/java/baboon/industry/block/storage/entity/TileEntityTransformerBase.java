package baboon.industry.block.storage.entity;

import baboon.industry.block.energy.entity.TileEntityEnergyConductorDamageable;
import com.mojang.nbt.CompoundTag;
import sunsetsatellite.catalyst.core.util.Connection;
import sunsetsatellite.catalyst.core.util.Direction;

public class TileEntityTransformerBase extends TileEntityEnergyConductorDamageable {

    public TileEntityTransformerBase() {
        setConnection(Direction.Y_POS, Connection.INPUT);
        setConnection(Direction.Y_NEG, Connection.OUTPUT);
    }

    @Override
    public void writeToNBT(CompoundTag tag) {
        super.writeToNBT(tag);
        tag.putInt("Energy", energy);
    }

    @Override
    public void readFromNBT(CompoundTag tag) {
        super.readFromNBT(tag);
        energy = tag.getInteger("Energy");
    }
}
