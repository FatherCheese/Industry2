package baboon.industry.block.storage.entity;

import baboon.industry.IndustryConfig;

public class TileEntityTransformerEHVtoHV extends TileEntityTransformerBase {

    public TileEntityTransformerEHVtoHV() {
        setCapacity(IndustryConfig.cfg.getInt("Energy Values.hvIO"));
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.hvIO"));
        setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.ehvIO"));
    }
}
