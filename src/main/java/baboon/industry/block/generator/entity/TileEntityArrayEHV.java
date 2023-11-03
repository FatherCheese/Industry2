package baboon.industry.block.generator.entity;

import baboon.industry.IndustryConfig;

public class TileEntityArrayEHV extends TileEntitySolarBase {

    public TileEntityArrayEHV() {
        super(16);

        setCapacity(IndustryConfig.cfg.getInt("Energy Values.ehvStorage"));
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.extraHighVoltage"));
    }

    @Override
    public String getInvName() {
        return "IndustryArrayEHV";
    }
}
