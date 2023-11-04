package baboon.industry.block.storage.entity;

import baboon.industry.block.entity.TileEntityEnergyConductorDamageable;
import com.mojang.nbt.CompoundTag;
import sunsetsatellite.sunsetutils.util.Connection;
import sunsetsatellite.sunsetutils.util.Direction;

public class TileEntityTransformerBase extends TileEntityEnergyConductorDamageable {

    public TileEntityTransformerBase() {
        setConnection(Direction.Y_POS, Connection.INPUT);
        setConnection(Direction.Y_NEG, Connection.OUTPUT);
    }

    @Override
    public void writeToNBT(CompoundTag CompoundTag) {
        super.writeToNBT(CompoundTag);
        CompoundTag.putInt("Energy", energy);
    }

    @Override
    public void readFromNBT(CompoundTag CompoundTag) {
        super.readFromNBT(CompoundTag);
        energy = CompoundTag.getInteger("Energy");
    }
}
