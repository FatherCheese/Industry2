package baboon.industry.block.storage.entity;

import baboon.industry.IndustryConfig;

public class TileEntityTransformerMVtoLV extends TileEntityTransformerBase {

    public TileEntityTransformerMVtoLV() {
        setCapacity(IndustryConfig.cfg.getInt("Energy Values.lvStorage"));
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.lowVoltage"));
        setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.mediumVoltage"));
    }
}
