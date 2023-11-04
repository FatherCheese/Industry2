package baboon.industry.block.storage.entity;

import baboon.industry.IndustryConfig;

public class TileEntityTransformerHVtoMV extends TileEntityTransformerBase {

    public TileEntityTransformerHVtoMV() {
        setCapacity(IndustryConfig.cfg.getInt("Energy Values.mvStorage"));
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.mediumVoltage"));
        setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.highVoltage"));
    }
}
