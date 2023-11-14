package baboon.industry.block.generator.entity;

import baboon.industry.IndustryConfig;

public class TileEntityGeneratorSolar extends TileEntitySolarBase {
    public TileEntityGeneratorSolar() {
        super(1);

        setCapacity(IndustryConfig.cfg.getInt("Energy Values.elvMachineStorage"));
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.elvIO"));
    }

    @Override
    public String getInvName() {
        return "IndustrySolar";
    }
}
