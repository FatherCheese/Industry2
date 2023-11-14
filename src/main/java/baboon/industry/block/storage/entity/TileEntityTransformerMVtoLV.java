package baboon.industry.block.storage.entity;

import baboon.industry.IndustryConfig;

public class TileEntityTransformerMVtoLV extends TileEntityTransformerBase {

    public TileEntityTransformerMVtoLV() {
        setCapacity(IndustryConfig.cfg.getInt("Energy Values.lvIO"));
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.lvIO"));
        setMaxReceive(IndustryConfig.cfg.getInt("Energy Values.mvIO"));
    }
}
