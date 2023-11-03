package baboon.industry.block.generator.entity;

import baboon.industry.IndustryConfig;

public class TileEntityArrayHV extends TileEntitySolarBase {

    public TileEntityArrayHV() {
        super(12);

        setCapacity(IndustryConfig.cfg.getInt("Energy Values.hvStorage"));
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.highVoltage"));
    }

    @Override
    public String getInvName() {
        return "IndustryArrayHV";
    }
}
