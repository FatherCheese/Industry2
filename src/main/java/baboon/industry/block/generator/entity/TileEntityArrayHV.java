package baboon.industry.block.generator.entity;

import baboon.industry.IndustryConfig;

public class TileEntityArrayHV extends TileEntitySolarBase {

    public TileEntityArrayHV() {
        super(12);

        setCapacity(IndustryConfig.cfg.getInt("Energy Values.hvIO"));
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.hvIO"));
    }

    @Override
    public String getInvName() {
        return "IndustryArrayHV";
    }
}
