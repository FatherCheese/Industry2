package baboon.industry.block.storage.entity;

import baboon.industry.IndustryConfig;

public class TileEntityBatboxHV extends TileEntityBatboxBase {

    public TileEntityBatboxHV() {
        setCapacity(IndustryConfig.cfg.getInt("Energy Values.hvStorage"));
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.highVoltage"));
        setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.highVoltage"));
    }

    @Override
    public String getInvName() {
        return "IndustryBatboxHV";
    }
}
