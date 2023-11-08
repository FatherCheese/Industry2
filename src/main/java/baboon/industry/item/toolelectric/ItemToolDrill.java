package baboon.industry.item.toolelectric;

import baboon.industry.IndustryConfig;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.material.ToolMaterial;

public class ItemToolDrill extends ItemToolElectric {

    public ItemToolDrill(int id, ToolMaterial toolMaterial) {
        super(id, BlockTags.MINEABLE_BY_PICKAXE, toolMaterial);
        this.baseCapacity = IndustryConfig.cfg.getInt("Energy Values.mvStorage");
        this.baseReceive = IndustryConfig.cfg.getInt("Energy Values.mediumVoltage");
        this.baseProvide = 0;
    }

    @Override
    public boolean canHarvestBlock(Block block) {
        return block.hasTag(BlockTags.MINEABLE_BY_PICKAXE);
    }
}
