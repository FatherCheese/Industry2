package baboon.industry.item.toolelectric;

import net.minecraft.core.block.Block;
import net.minecraft.core.data.tag.Tag;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.net.command.TextFormatting;
import net.minecraft.core.world.World;
import sunsetsatellite.catalyst.core.util.ICustomDescription;
import sunsetsatellite.catalyst.energy.api.IEnergyItem;

public class ItemToolElectric extends Item implements IEnergyItem, ICustomDescription {
    private final Tag<Block> tagEffectiveAgainst;
    private final ToolMaterial material;
    public int baseCapacity = 0;
    public int baseProvide = 0;
    public int baseReceive = 0;

    protected ItemToolElectric(String name, int id, Tag<Block> tagEffectiveAgainst, ToolMaterial toolMaterial) {
        super(name, id);
        this.tagEffectiveAgainst = tagEffectiveAgainst;
        this.material = toolMaterial;
        setMaxStackSize(1);
    }

    @Override
    public float getStrVsBlock(ItemStack itemstack, Block block) {
        return getEnergy(itemstack) > 0 ? block.hasTag(this.tagEffectiveAgainst) ? this.material.getEfficiency(false) : 1.0F : 0;
    }

    @Override
    public boolean onBlockDestroyed(World world, ItemStack itemstack, int id, int x, int y, int z, EntityLiving entityliving) {
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

    public int provide(ItemStack stack, int amount, boolean test) {
        int provided = Math.min(this.getEnergy(stack), Math.min(this.getMaxProvide(stack), amount));
        if (!test) {
            this.modifyEnergy(stack, -provided);
        }

        return provided;
    }

    public int receive(ItemStack stack, int amount, boolean test) {
        int received = Math.min(this.getCapacity(stack) - this.getEnergy(stack), Math.min(this.getMaxReceive(stack), amount));
        if (!test) {
            this.modifyEnergy(stack, received);
        }

        return received;
    }

    public int getEnergy(ItemStack stack) {
        return stack.getData().getInteger("energy");
    }

    public int getCapacity(ItemStack stack) {
        if (!stack.getData().containsKey("capacity")) {
            stack.getData().putInt("capacity", this.baseCapacity);
            return this.baseCapacity;
        } else {
            return stack.getData().getInteger("capacity");
        }
    }

    public int getMaxReceive(ItemStack stack) {
        if (!stack.getData().containsKey("maxReceive")) {
            stack.getData().putInt("maxReceive", this.baseReceive);
            return this.baseReceive;
        } else {
            return stack.getData().getInteger("maxReceive");
        }
    }

    public int getMaxProvide(ItemStack stack) {
        if (!stack.getData().containsKey("maxProvide")) {
            stack.getData().putInt("maxProvide", this.baseProvide);
            return this.baseProvide;
        } else {
            return stack.getData().getInteger("maxProvide");
        }
    }

    public void modifyEnergy(ItemStack stack, int amount) {
        if (stack.getData().getInteger("energy") + amount > this.getCapacity(stack)) {
            stack.getData().putInt("energy", this.getCapacity(stack));
        } else if (stack.getData().getInteger("energy") + amount < 0) {
            stack.getData().putInt("energy", 0);
        } else {
            stack.getData().putInt("energy", this.getEnergy(stack) + amount);
        }
    }
}
