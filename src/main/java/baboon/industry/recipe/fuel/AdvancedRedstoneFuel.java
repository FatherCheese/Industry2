package baboon.industry.recipe.fuel;

import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;

import java.util.HashMap;

public class AdvancedRedstoneFuel {
    private static final HashMap<Integer, Integer> redstoneList = new HashMap<>();

    public AdvancedRedstoneFuel() {
        addRedstoneFuel(Item.dustRedstone.id, 100);
        addRedstoneFuel(Block.blockRedstone.id, 900);
    }

    public static void addRedstoneFuel(int inputItem, int outputYield) {
        redstoneList.put(inputItem, outputYield);
    }

    public int getYield(int i) {
        return redstoneList.get(i);
    }

    public int getRedstoneFuel(int i) {
        return redstoneList.get(i) == null ? 0 : redstoneList.get(i);
    }

    public HashMap<Integer, Integer> getRedstoneList() {
        return redstoneList;
    }
}
