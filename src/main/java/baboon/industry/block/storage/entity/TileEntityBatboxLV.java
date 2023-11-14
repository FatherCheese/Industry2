package baboon.industry.block.storage.entity;

import baboon.industry.IndustryConfig;

public class TileEntityBatboxLV extends TileEntityBatboxBase {

    public TileEntityBatboxLV() {
        setCapacity(IndustryConfig.cfg.getInt("Energy Values.lvBatteryStorage") * 3);
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.lvIO"));
        setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.lvIO"));
    }

    @Override
    public String getInvName() {
        return "IndustryBatboxLV";
    }
}
