package baboon.industry.block.storage.entity;

import baboon.industry.IndustryConfig;

public class TileEntityBatboxLV extends TileEntityBatboxBase {

    public TileEntityBatboxLV() {
        setCapacity(IndustryConfig.cfg.getInt("Energy Values.lvStorage") * 3);
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.lowVoltage"));
        setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.lowVoltage"));
    }

    @Override
    public String getInvName() {
        return "IndustryBatboxLV";
    }
}
