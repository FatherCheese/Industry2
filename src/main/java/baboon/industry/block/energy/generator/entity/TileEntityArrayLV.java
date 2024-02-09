package baboon.industry.block.energy.generator.entity;

import baboon.industry.IndustryConfig;

public class TileEntityArrayLV extends TileEntitySolarBase {

    public TileEntityArrayLV() {
        super(IndustryConfig.cfg.getInt("Energy Values.lvIO"));

        setCapacity(IndustryConfig.cfg.getInt("Energy Values.lvIO"));
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.lvIO"));
    }

    @Override
    public String getInvName() {
        return "IndustryArrayLV";
    }
}
