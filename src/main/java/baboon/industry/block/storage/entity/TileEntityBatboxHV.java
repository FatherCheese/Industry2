package baboon.industry.block.storage.entity;

import baboon.industry.IndustryConfig;

public class TileEntityBatboxHV extends TileEntityBatboxBase {

    public TileEntityBatboxHV() {
        setCapacity(IndustryConfig.cfg.getInt("Energy Values.hvBatteryStorage") * 3);
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.hvIO"));
        setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.hvIO"));
    }

    @Override
    public String getInvName() {
        return "IndustryBatboxHV";
    }
}
