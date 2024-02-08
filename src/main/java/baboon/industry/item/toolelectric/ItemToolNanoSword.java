package baboon.industry.item.toolelectric;

import baboon.industry.Industry2;
import baboon.industry.IndustryConfig;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.world.World;
import turniplabs.halplibe.helper.TextureHelper;

public class ItemToolNanoSword extends ItemToolElectric {
    private int damageTimer = 0;
    private boolean active = false;

    int[][] toolTexture = new int[][]{
            TextureHelper.getOrCreateItemTexture(Industry2.MOD_ID, "tool_nanosword_inactive.png"),
            TextureHelper.getOrCreateItemTexture(Industry2.MOD_ID, "tool_nanosword.png")
    };

    public ItemToolNanoSword(String name, int id) {
        super(name, id, BlockTags.MINEABLE_BY_SWORD, ToolMaterial.steel);
        this.baseCapacity = IndustryConfig.cfg.getInt("Energy Values.hvBatteryStorage");
        this.baseReceive = IndustryConfig.cfg.getInt("Energy Values.hvIO");
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }


    @Override
    public void inventoryTick(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
        if (!world.isClientSide) {
            if (getEnergy(itemstack) <= 0)
                active = false;

            if (active)
                damageTimer++;

            if (damageTimer == 20 && getEnergy(itemstack) > 0) {
                damageTimer = 0;
                modifyEnergy(itemstack, -10);
            }
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        world.playSoundAtEntity(entityplayer, "industry.laser", 1.0f, 1.0f);
        if (!world.isClientSide) {
            active = !active;
            if (active)
                itemstack.setMetadata(1);
            else {
                itemstack.setMetadata(0);
                modifyEnergy(itemstack, -10);
            }
        }
        return super.onItemRightClick(itemstack, world, entityplayer);
    }

    @Override
    public boolean canHarvestBlock(Block block) {
        return block.hasTag(BlockTags.MINEABLE_BY_SWORD);
    }

    @Override
    public int getDamageVsEntity(Entity entity) {
        if (active)
            return 20;
        else
            return 1;
    }

    @Override
    public int getIconFromDamage(int id) {
        if (id == 1)
            return iconCoordToIndex(toolTexture[1][0], toolTexture[1][1]);
        return iconCoordToIndex(toolTexture[0][0], toolTexture[0][1]);
    }
}
