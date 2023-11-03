package baboon.industry.block.generator.entity;

import baboon.industry.IndustryConfig;
import sunsetsatellite.energyapi.impl.ItemEnergyContainer;

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
