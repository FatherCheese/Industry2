package baboon.industry.item.toolelectric;

import baboon.industry.IndustryConfig;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.data.tag.Tag;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.world.World;
import org.checkerframework.checker.units.qual.A;

public class ItemToolNanoSword extends ItemToolElectric {
    private int damageTimer = 0;
    private boolean active = false;

    public ItemToolNanoSword(int id) {
        super(id, BlockTags.MINEABLE_BY_SWORD, ToolMaterial.steel);
        this.baseCapacity = IndustryConfig.cfg.getInt("Energy Values.mvStorage");
        this.baseReceive = IndustryConfig.cfg.getInt("Energy Values.mediumVoltage");
        this.baseProvide = 0;
    }


    @Override
    public void inventoryTick(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
        super.inventoryTick(itemstack, world, entity, i, flag);
        if (getEnergy(itemstack) == 0)
            active = false;

        if (active)
            damageTimer++;

        if (damageTimer == 20 && getEnergy(itemstack) > 0) {
            damageTimer = 0;
            modifyEnergy(itemstack, -10);
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        active = !active;
        world.playSoundAtEntity(entityplayer, "industry.laser", 1.0f, 1.0f);
        return super.onItemRightClick(itemstack, world, entityplayer);
    }

    @Override
    public boolean canHarvestBlock(Block block) {
        return block.hasTag(BlockTags.MINEABLE_BY_SWORD);
    }
}
