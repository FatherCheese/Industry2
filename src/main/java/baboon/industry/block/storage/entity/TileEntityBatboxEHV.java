package baboon.industry.block.storage.entity;

import baboon.industry.IndustryConfig;

public class TileEntityBatboxEHV extends TileEntityBatboxBase {

    public TileEntityBatboxEHV() {
        setCapacity(IndustryConfig.cfg.getInt("Energy Values.ehvStorage") * 3);
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.extraHighVoltage"));
        setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.extraHighVoltage"));
    }

    @Override
    public String getInvName() {
        return "IndustryBatboxEHV";
    }
}
