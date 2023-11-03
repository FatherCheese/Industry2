package baboon.industry.recipe.fuel;

import baboon.industry.item.IndustryItems;
import net.minecraft.core.item.Item;

import java.util.HashMap;

public class GeneratorGeothermalFuel {
    private static final HashMap<Integer, Integer> fuelList = new HashMap<>();

    public GeneratorGeothermalFuel() {
        addFuel(Item.bucketLava.id, 1000);
        addFuel(IndustryItems.cellLava.id, 1000);
        addFuel(Item.nethercoal.id, 250);
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
