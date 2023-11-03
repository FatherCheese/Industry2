package baboon.industry.block.generator.entity;

import baboon.industry.IndustryConfig;

public class TileEntityArrayMV extends TileEntitySolarBase {

    public TileEntityArrayMV() {
        super(8);

        setCapacity(IndustryConfig.cfg.getInt("Energy Values.mvStorage"));
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.mediumVoltage"));
    }

    @Override
    public String getInvName() {
        return "IndustryArrayMV";
    }
}
