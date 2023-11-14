package baboon.industry.block.storage.entity;

import baboon.industry.IndustryConfig;

public class TileEntityBatboxEHV extends TileEntityBatboxBase {

    public TileEntityBatboxEHV() {
        setCapacity(IndustryConfig.cfg.getInt("Energy Values.ehvBatteryStorage") * 3);
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.ehvIO"));
        setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.ehvIO"));
    }

    @Override
    public String getInvName() {
        return "IndustryBatboxEHV";
    }
}
