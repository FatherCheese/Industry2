package baboon.industry.item;

import baboon.industry.block.cables.BlockCable;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.ItemPlaceable;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.net.command.TextFormatting;
import sunsetsatellite.sunsetutils.util.ICustomDescription;

public class ItemCable extends ItemPlaceable implements ICustomDescription {

    public ItemCable(String name, int id, Block blockToPlace) {
        super(name, id, blockToPlace);
    }

    @Override
    public String getDescription(ItemStack itemStack) {
        int capacity = ((BlockCable) blockToPlace).capacity;
        int transfer = ((BlockCable) blockToPlace).transfer;

        return TextFormatting.WHITE + "Max Transfer: " + TextFormatting.LIGHT_GRAY + "IN: " + capacity + TextFormatting.WHITE + " / " + TextFormatting.LIGHT_GRAY + "OUT: " + transfer;
    }
}
