package baboon.industry.block.storage.entity;

import baboon.industry.IndustryConfig;

public class TileEntityTransformerEHVtoHV extends TileEntityTransformerBase {

    public TileEntityTransformerEHVtoHV() {
        setCapacity(IndustryConfig.cfg.getInt("Energy Values.hvStorage"));
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.highVoltage"));
        setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.extraHighVoltage"));
    }
}
