package baboon.industry.block.energy.generator.entity;

import baboon.industry.IndustryConfig;

public class TileEntityArrayEHV extends TileEntitySolarBase {

    public TileEntityArrayEHV() {
        super(IndustryConfig.cfg.getInt("Energy Values.ehvIO"));

        setCapacity(IndustryConfig.cfg.getInt("Energy Values.ehvIO"));
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.ehvIO"));
    }

    @Override
    public String getInvName() {
        return "IndustryArrayEHV";
    }
}
