package baboon.industry.recipe.fuel;

import baboon.industry.block.IndustryBlocks;
import baboon.industry.item.IndustryItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;

import java.util.HashMap;

public class GeneratorFuel {
    private static final HashMap<Object, Object> fuelList = new HashMap<>();

    public GeneratorFuel() {
        addFuel(Block.cactus, 8);
        addFuel(Block.sugarcane, 8);
        addFuel(Block.saplingBirch, 16);
        addFuel(Block.saplingCherry, 16);
        addFuel(Block.saplingEucalyptus, 16);
        addFuel(IndustryBlocks.saplingRubberWood, 16);
        addFuel(Block.saplingOak, 16);
        addFuel(Block.saplingOakRetro, 16);
        addFuel(Item.stick, 16);
        addFuel(Block.logBirch, 24);
        addFuel(Block.logCherry, 24);
        addFuel(Block.logEucalyptus, 24);
        addFuel(Block.logOak, 24);
        addFuel(Block.logOakMossy, 24);
        addFuel(Block.logPine, 24);
        addFuel(Block.saplingPine, 24);
        addFuel(Block.planksOak, 24);
        addFuel(Block.planksOakPainted, 24);
        addFuel(IndustryItems.scrap, 32);
        addFuel(Item.coal, 64);
    }

    public static void addFuel(Item itemInput, int fuelYield) {
        fuelList.put(itemInput.id, fuelYield);
    }

    public static void addFuel(Block blockInput, int fuelYield) {
        fuelList.put(blockInput, fuelYield);
    }

    public static int getResult(int i) {
        return (int) fuelList.get(i);
    }

    public static HashMap<Object, Object> getFuelList() {
        return fuelList;
    }
}
