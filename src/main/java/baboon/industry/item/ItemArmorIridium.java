package baboon.industry.item;

import baboon.industry.IndustryConfig;
import net.minecraft.core.item.ItemArmor;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ArmorMaterial;
import net.minecraft.core.net.command.TextFormatting;
import sunsetsatellite.energyapi.api.IEnergyItem;
import sunsetsatellite.sunsetutils.util.ICustomDescription;

public class ItemArmorIridium extends ItemArmor implements ICustomDescription, IEnergyItem {

    public int baseCapacity;
    public int baseProvide;
    public int baseReceive;

    public ItemArmorIridium(String name, int id, ArmorMaterial material, int armorPiece) {
        super(name, id, material, armorPiece);
        this.baseCapacity = IndustryConfig.cfg.getInt("Energy Values.ehvStorage");
        this.baseProvide = IndustryConfig.cfg.getInt("Energy Values.extraHighVoltage");
        this.baseReceive = IndustryConfig.cfg.getInt("Energy Values.extraHighVoltage");
    }

    @Override
    public String getDescription(ItemStack itemStack) {
        return TextFormatting.WHITE + "Energy: " + TextFormatting.LIGHT_GRAY + getEnergy(itemStack) + TextFormatting.WHITE + " / " + getCapacity(itemStack);
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
        stack.getData().putInt("energy", this.getEnergy(stack) + amount);
        if (stack.getData().getInteger("energy") > this.getCapacity(stack)) {
            stack.getData().putInt("energy", this.getCapacity(stack));
        }

        if (stack.getData().getInteger("energy") < 0) {
            stack.getData().putInt("energy", 0);
        }
    }
}
