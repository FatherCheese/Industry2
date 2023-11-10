package baboon.industry.block.generator.entity;

import baboon.industry.IndustryConfig;

public class TileEntityGeneratorSolar extends TileEntitySolarBase {
    public TileEntityGeneratorSolar() {
        super(1);

        setCapacity(IndustryConfig.cfg.getInt("Energy Values.elvStorage"));
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.extraLowVoltage"));
    }

    @Override
    public String getInvName() {
        return "IndustrySolar";
    }
}
