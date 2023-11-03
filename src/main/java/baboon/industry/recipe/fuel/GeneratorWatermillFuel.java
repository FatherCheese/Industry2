package baboon.industry.recipe.fuel;

import baboon.industry.item.IndustryItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntityFurnace;
import net.minecraft.core.crafting.LookupFuelFurnace;
import net.minecraft.core.item.Item;

import java.util.HashMap;

public class GeneratorWatermillFuel {
    private static final HashMap<Integer, Integer> fuelList = new HashMap<>();

    public GeneratorWatermillFuel() {
        addFuel(Item.bucketWater.id, 1000);
        addFuel(IndustryItems.cellWater.id, 1000);
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
