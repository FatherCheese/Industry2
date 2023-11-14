package baboon.industry.block.storage.entity;

import baboon.industry.IndustryConfig;

public class TileEntityBatboxMV extends TileEntityBatboxBase {

    public TileEntityBatboxMV() {
        setCapacity(IndustryConfig.cfg.getInt("Energy Values.mvBatteryStorage") * 3);
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.mvIO"));
        setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.mvIO"));
    }

    @Override
    public String getInvName() {
        return "IndustryBatboxMV";
    }
}
