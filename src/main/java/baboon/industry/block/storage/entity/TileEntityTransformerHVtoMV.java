package baboon.industry.block.storage.entity;

import baboon.industry.IndustryConfig;

public class TileEntityTransformerHVtoMV extends TileEntityTransformerBase {

    public TileEntityTransformerHVtoMV() {
        setCapacity(IndustryConfig.cfg.getInt("Energy Values.mvIO"));
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.mvIO"));
        setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.hvIO"));
    }
}
