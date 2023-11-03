package baboon.industry.block.storage.entity;

import baboon.industry.IndustryConfig;

public class TileEntityBatboxMV extends TileEntityBatboxBase {

    public TileEntityBatboxMV() {
        setCapacity(IndustryConfig.cfg.getInt("Energy Values.mvStorage"));
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.mediumVoltage"));
        setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.mediumVoltage"));
    }

    @Override
    public String getInvName() {
        return "IndustryBatboxMV";
    }
}
