package baboon.industry.item.toolelectric;

import baboon.industry.IndustryConfig;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;

public class ItemToolChainsaw extends ItemToolElectric {

    public ItemToolChainsaw(String name, int id) {
        super(name, id, BlockTags.MINEABLE_BY_AXE, ToolMaterial.iron);
        this.baseCapacity = IndustryConfig.cfg.getInt("Energy Values.lvBatteryStorage");
        this.baseReceive = IndustryConfig.cfg.getInt("Energy Values.lvIO");
    }

    @Override
    public boolean canHarvestBlock(Block block) {
        return block.hasTag(BlockTags.MINEABLE_BY_AXE);
    }

    @Override
    public boolean hitEntity(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
        modifyEnergy(itemstack, -100);
        return true;
    }
}
