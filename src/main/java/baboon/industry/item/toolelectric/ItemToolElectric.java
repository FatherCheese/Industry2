package baboon.industry.item.toolelectric;

import net.minecraft.core.block.Block;
import net.minecraft.core.data.tag.Tag;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.net.command.TextFormatting;
import sunsetsatellite.energyapi.impl.ItemEnergyContainer;
import sunsetsatellite.sunsetutils.util.ICustomDescription;

public class ItemToolElectric extends ItemEnergyContainer implements ICustomDescription {
    private final Tag<Block> tagEffectiveAgainst;
    private final ToolMaterial material;

    protected ItemToolElectric(int id, Tag<Block> tagEffectiveAgainst, ToolMaterial toolMaterial) {
        super(id);
        this.tagEffectiveAgainst = tagEffectiveAgainst;
        this.material = toolMaterial;
        setMaxStackSize(1);
    }

    @Override
    public float getStrVsBlock(ItemStack itemstack, Block block) {
        return getEnergy(itemstack) > 0 ? block.hasTag(this.tagEffectiveAgainst) ? this.material.getEfficiency(false) : 1.0F : 0;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack itemstack, int id, int x, int y, int z, EntityLiving entityliving) {
        Block block = Block.blocksList[id];
        if (block != null && block.getHardness() > 0.0f) {
            modifyEnergy(itemstack, -50);
        }

        return true;
    }

    @Override
    public boolean isFull3D() {
        return true;
    }

    @Override
    public String getDescription(ItemStack itemStack) {
        return TextFormatting.WHITE + "Energy: " + TextFormatting.LIGHT_GRAY + getEnergy(itemStack) + TextFormatting.WHITE + " / " + TextFormatting.LIGHT_GRAY + baseCapacity;
    }

    @Override
    public boolean isSilkTouch() {
        return material.isSilkTouch();
    }
}
