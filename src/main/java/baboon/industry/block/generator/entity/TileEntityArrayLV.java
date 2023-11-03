package baboon.industry.block.generator.entity;

import baboon.industry.IndustryConfig;

public class TileEntityArrayLV extends TileEntitySolarBase {

    public TileEntityArrayLV() {
        super(4);

        setCapacity(IndustryConfig.cfg.getInt("Energy Values.lvStorage"));
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.lowVoltage"));
    }

    @Override
    public String getInvName() {
        return "IndustryArrayLV";
    }
}
