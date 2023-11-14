package baboon.industry.recipe.fuel;

import baboon.industry.block.IndustryBlocks;
import baboon.industry.item.IndustryItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;

import java.util.HashMap;

public class GeneratorFuel {

    private static final HashMap<Integer, Integer> fuelList = new HashMap<>();

    public GeneratorFuel() {
        addFuel(Item.sugarcane.id, 12);
        addFuel(Block.cactus.id, 12);

        addFuel(Block.saplingBirch.id, 25);
        addFuel(Block.saplingCherry.id, 25);
        addFuel(Block.saplingEucalyptus.id, 25);
        addFuel(Block.saplingOak.id, 25);
        addFuel(Block.saplingOakRetro.id, 25);
        addFuel(Block.saplingPine.id, 25);
        addFuel(IndustryBlocks.saplingRubberWood.id, 25);
        addFuel(Block.saplingShrub.id, 25);
        addFuel(Item.stick.id, 25);

        addFuel(Item.toolAxeWood.id, 50);
        addFuel(Item.toolHoeWood.id, 50);
        addFuel(Item.toolPickaxeWood.id, 50);
        addFuel(Item.toolShovelWood.id, 50);
        addFuel(Item.toolSwordWood.id, 50);

        addFuel(Block.fencegatePlanksOak.id, 75);
        addFuel(Block.fencegatePlanksOakPainted.id, 75);
        addFuel(Block.fencePlanksOak.id, 75);
        addFuel(Block.fencePlanksOakPainted.id, 75);
        addFuel(Block.logBirch.id, 75);
        addFuel(Block.logCherry.id, 75);
        addFuel(Block.logEucalyptus.id, 75);
        addFuel(Block.logOak.id, 75);
        addFuel(Block.logOakMossy.id, 75);
        addFuel(IndustryBlocks.logRubberWood.id, 75);
        addFuel(IndustryBlocks.logRubberWoodResin.id, 75);
        addFuel(IndustryBlocks.logRubberWoodResinFull.id, 75);
        addFuel(Block.logPine.id, 75);
        addFuel(Block.planksOak.id, 75);
        addFuel(Block.planksOakPainted.id, 75);
        addFuel(Block.slabPlanksOak.id, 75);
        addFuel(Block.slabPlanksOakPainted.id, 75);
        addFuel(Block.stairsPlanksOak.id, 75);
        addFuel(Block.stairsPlanksOakPainted.id, 75);
        addFuel(Block.workbench.id, 75);

        addFuel(IndustryItems.scrap.id, 87);

        addFuel(Item.coal.id, 400);
    }

    public static void addFuel(int inputItem, int outputYield) {
        fuelList.put(inputItem, outputYield);
    }

    public static void removeFuel(int item) {
        fuelList.remove(item);
    }

    public int getYield(int i) {
        return fuelList.get(i);
    }

    public HashMap<Integer, Integer> getFuelList() {
        return fuelList;
    }
}
