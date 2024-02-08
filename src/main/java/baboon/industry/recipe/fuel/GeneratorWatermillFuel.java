package baboon.industry.recipe.fuel;

import baboon.industry.item.I2Items;
import net.minecraft.core.item.Item;

import java.util.HashMap;

public class GeneratorWatermillFuel {
    private static final HashMap<Integer, Integer> fuelList = new HashMap<>();

    public GeneratorWatermillFuel() {
        addFuel(Item.bucketWater.id, 1000);
        addFuel(I2Items.cellWater.id, 1000);
    }

    public static void addFuel(int inputItem, int outputYield) {
        fuelList.put(inputItem, outputYield);
    }

    public int getYield(int i) {
        return fuelList.get(i);
    }

    public HashMap<Integer, Integer> getFuelList() {
        return fuelList;
    }
}
