package baboon.industry.item.toolelectric;

import baboon.industry.IndustryConfig;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.material.ToolMaterial;

public class ItemToolChainsaw extends ItemToolElectric {

    public ItemToolChainsaw(int id) {
        super(id, BlockTags.MINEABLE_BY_AXE, ToolMaterial.iron);
        this.baseCapacity = IndustryConfig.cfg.getInt("Energy Values.mvStorage");
        this.baseReceive = IndustryConfig.cfg.getInt("Energy Values.mediumVoltage");
        this.baseProvide = 0;
    }

    @Override
    public boolean canHarvestBlock(Block block) {
        return block.hasTag(BlockTags.MINEABLE_BY_AXE);
    }
}
