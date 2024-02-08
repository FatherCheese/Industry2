package baboon.industry.item;

import net.minecraft.core.item.Item;

public class ItemTools extends Item {

    public ItemTools(String name, int id) {
        super(name, id);
        setMaxStackSize(1);
        setContainerItem(this);
    }
}
