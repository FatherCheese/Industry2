package baboon.industry.block.generator.entity;

import baboon.industry.IndustryConfig;

public class TileEntityArrayMV extends TileEntitySolarBase {

    public TileEntityArrayMV() {
        super(IndustryConfig.cfg.getInt("Energy Values.mvIO"));

        setCapacity(IndustryConfig.cfg.getInt("Energy Values.mvIO"));
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.mvIO"));
    }

    @Override
    public String getInvName() {
        return "IndustryArrayMV";
    }
}
