package baboon.industry.item;

import net.minecraft.core.item.Item;

public class ItemTools extends Item {

    public ItemTools(int id) {
        super(id);
        setMaxStackSize(1);
        setContainerItem(this);
    }
}
