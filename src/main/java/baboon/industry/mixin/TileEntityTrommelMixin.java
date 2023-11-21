package baboon.industry.mixin;

import baboon.industry.item.IndustryItems;
import net.minecraft.core.WeightedRandomBag;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.block.entity.TileEntityTrommel;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = TileEntityTrommel.class, remap = false)
public class TileEntityTrommelMixin {

    @Shadow @Final private static WeightedRandomBag<WeightedRandomLootObject> trommelDropsDirt;

    @Shadow @Final private static WeightedRandomBag<WeightedRandomLootObject> trommelDropsGravel;

    @Shadow @Final private static WeightedRandomBag<WeightedRandomLootObject> trommelDropsRichDirt;

    @Shadow @Final private static WeightedRandomBag<WeightedRandomLootObject> trommelDropsSoulSand;

    @Shadow @Final private static WeightedRandomBag<WeightedRandomLootObject> trommelDropsClay;

    @Shadow @Final private static WeightedRandomBag<WeightedRandomLootObject> trommelDropsSand;

    static {
        trommelDropsDirt.addEntry(new WeightedRandomLootObject(new ItemStack(IndustryItems.oreRawTin), 1), 0.5f);
        trommelDropsDirt.addEntry(new WeightedRandomLootObject(new ItemStack(IndustryItems.oreRawCopper), 1), 0.5f);

        trommelDropsClay.addEntry(new WeightedRandomLootObject(new ItemStack(IndustryItems.oreRawUranium), 1), 1.0);

        trommelDropsGravel.addEntry(new WeightedRandomLootObject(new ItemStack(IndustryItems.oreRawTin), 1, 2), 10.0f);
        trommelDropsGravel.addEntry(new WeightedRandomLootObject(new ItemStack(IndustryItems.oreRawCopper), 1, 2), 10.0f);

        trommelDropsRichDirt.addEntry(new WeightedRandomLootObject(new ItemStack(IndustryItems.oreRawTin), 1, 2), 15.0f);
        trommelDropsRichDirt.addEntry(new WeightedRandomLootObject(new ItemStack(IndustryItems.oreRawCopper), 1, 2), 15.0f);
        trommelDropsRichDirt.addEntry(new WeightedRandomLootObject(new ItemStack(IndustryItems.oreRawUranium), 1), 1.0);

        trommelDropsSand.addEntry(new WeightedRandomLootObject(new ItemStack(IndustryItems.oreRawUranium), 1), 1.0);

        trommelDropsSoulSand.addEntry(new WeightedRandomLootObject(new ItemStack(IndustryItems.oreRawTin), 1), 1.0f);
        trommelDropsSoulSand.addEntry(new WeightedRandomLootObject(new ItemStack(IndustryItems.oreRawCopper), 1), 1.0f);
        trommelDropsSoulSand.addEntry(new WeightedRandomLootObject(new ItemStack(IndustryItems.oreRawUranium), 1), 2.0);
    }
}
