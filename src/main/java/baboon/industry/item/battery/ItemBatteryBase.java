package baboon.industry.item.battery;

import baboon.industry.Industry2;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.net.command.TextFormatting;
import sunsetsatellite.energyapi.EnergyAPI;
import sunsetsatellite.energyapi.template.items.ItemBattery;
import sunsetsatellite.sunsetutils.util.ICustomDescription;
import turniplabs.halplibe.helper.TextureHelper;

public class ItemBatteryBase extends ItemBattery implements ICustomDescription {
    private final int[][] textureCoords;

    public ItemBatteryBase(int i, int capacity, int provide, int receive, String emptyTexture, String midEmptyTexture, String midTexture, String midFullTexture, String fullTexture) {
        super(i);
        setMaxStackSize(1);

        String MOD_ID = Industry2.MOD_ID;
        textureCoords = new int[][]{
                TextureHelper.getOrCreateItemTexture(MOD_ID, fullTexture),
                TextureHelper.getOrCreateItemTexture(MOD_ID, midFullTexture),
                TextureHelper.getOrCreateItemTexture(MOD_ID, midTexture),
                TextureHelper.getOrCreateItemTexture(MOD_ID, midEmptyTexture),
                TextureHelper.getOrCreateItemTexture(MOD_ID, emptyTexture)
        };
    }

    @Override
    public int getIconIndex(ItemStack itemstack) {
        int mapped = (int) EnergyAPI.map(
                ((double) getEnergy(itemstack) / getCapacity(itemstack)),
                0.0,
                1.0,
                0.0,
                4.0);

        setIconCoord(textureCoords[mapped][0], textureCoords[mapped][1]);
        return iconIndex;
    }

    @Override
    public String getDescription(ItemStack stack) {
        return TextFormatting.WHITE + "Max Transfer: " + TextFormatting.LIGHT_GRAY + "IN: " + baseReceive + TextFormatting.WHITE + " / " + TextFormatting.LIGHT_GRAY + "OUT: " + baseProvide + "\n"
                + TextFormatting.WHITE + "Energy: " + TextFormatting.LIGHT_GRAY + getEnergy(stack) + TextFormatting.WHITE + " / " + TextFormatting.LIGHT_GRAY + baseCapacity;
    }
}
