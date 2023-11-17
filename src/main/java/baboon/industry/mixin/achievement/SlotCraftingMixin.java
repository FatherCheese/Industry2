package baboon.industry.mixin.achievement;

import baboon.industry.IndustryAchievements;
import baboon.industry.block.IndustryBlocks;
import baboon.industry.item.IndustryItems;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.slot.SlotCrafting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SlotCrafting.class, remap = false)
public abstract class SlotCraftingMixin {

    @Shadow private EntityPlayer thePlayer;

    @Inject(method = "onPickupFromSlot", at = @At("TAIL"))
    private void industry_pickupFromSlot(ItemStack itemStack, CallbackInfo ci) {
        Item item = itemStack.getItem();
        if (item.id == IndustryItems.toolHammer.id)
            thePlayer.addStat(IndustryAchievements.TOOL1, 1);

        if (item.id == IndustryItems.toolCutters.id)
            thePlayer.addStat(IndustryAchievements.TOOL2, 1);

        if (item.id == IndustryItems.itemInsulatedCableTin.id ||
                item.id == IndustryItems.itemInsulatedCableCopper.id ||
                item.id == IndustryItems.itemInsulatedCableGold.id ||
                item.id == IndustryItems.itemInsulatedCableSteel.id)
            thePlayer.addStat(IndustryAchievements.CABLES, 1);

        if (item.id == IndustryBlocks.generator.id)
            thePlayer.addStat(IndustryAchievements.GENERATOR, 1);

        if (item.id == IndustryBlocks.machineFurnace.id)
            thePlayer.addStat(IndustryAchievements.FURNACE, 1);

        if (item.id == IndustryBlocks.machineMacerator.id)
            thePlayer.addStat(IndustryAchievements.MACERATOR, 1);

        if (item.id == IndustryBlocks.machineCompressor.id)
            thePlayer.addStat(IndustryAchievements.COMPRESSOR, 1);

        if (item.id == IndustryBlocks.machineWiremill.id)
            thePlayer.addStat(IndustryAchievements.WIREMILL, 1);

        if (item.id == IndustryBlocks.machineExtractor.id)
            thePlayer.addStat(IndustryAchievements.EXTRACTOR, 1);

        if (item.id == IndustryBlocks.machineRecycler.id)
            thePlayer.addStat(IndustryAchievements.RECYCLER, 1);

        if (item.id == IndustryBlocks.machineCannery.id)
            thePlayer.addStat(IndustryAchievements.CANNERY, 1);

        if (item.id == IndustryBlocks.machineTrommel.id)
            thePlayer.addStat(IndustryAchievements.TROMMEL, 1);

        if (item.id == IndustryBlocks.generatorWatermill.id)
            thePlayer.addStat(IndustryAchievements.WATERMILL, 1);

        if (item.id == IndustryBlocks.generatorWindmill.id)
            thePlayer.addStat(IndustryAchievements.WINDMILL, 1);

        if (item.id == IndustryBlocks.generatorGeothermal.id)
            thePlayer.addStat(IndustryAchievements.GEOTHERMAL, 1);

        if (item.id == IndustryBlocks.generatorSolar.id)
            thePlayer.addStat(IndustryAchievements.SOLAR, 1);

        if (item.id == IndustryBlocks.generatorSolar.id)
            thePlayer.addStat(IndustryAchievements.SOLAR, 1);

        if (item.id == IndustryBlocks.solarArrayLV.id)
            thePlayer.addStat(IndustryAchievements.ARRAY1, 1);

        if (item.id == IndustryBlocks.solarArrayMV.id)
            thePlayer.addStat(IndustryAchievements.ARRAY2, 1);

        if (item.id == IndustryBlocks.solarArrayHV.id)
            thePlayer.addStat(IndustryAchievements.ARRAY3, 1);

        if (item.id == IndustryBlocks.solarArrayEHV.id)
            thePlayer.addStat(IndustryAchievements.ARRAY4, 1);

        if (item.id == IndustryBlocks.batboxLV.id)
            thePlayer.addStat(IndustryAchievements.BATBOX1, 1);

        if (item.id == IndustryItems.toolDrill.id)
            thePlayer.addStat(IndustryAchievements.DRILL1, 1);

        if (item.id == IndustryItems.toolDrillGold.id)
            thePlayer.addStat(IndustryAchievements.DRILL2, 1);

        if (item.id == IndustryItems.toolDrillDiamond.id)
            thePlayer.addStat(IndustryAchievements.DRILL3, 1);

        if (item.id == IndustryItems.toolChainsaw.id)
            thePlayer.addStat(IndustryAchievements.CHAINSAW, 1);

        if (item.id == IndustryItems.toolNanoSword.id)
            thePlayer.addStat(IndustryAchievements.NANOSWORD, 1);

        if (item.id == IndustryBlocks.energyFabricator.id)
            thePlayer.addStat(IndustryAchievements.FABRICATOR, 1);

        if (item.id == IndustryItems.ingotIridium.id)
            thePlayer.addStat(IndustryAchievements.IRIDIUM, 1);

        if (item.id == IndustryItems.armorHelmetIridium.id ||
                item.id == IndustryItems.armorChestplateIridium.id ||
                item.id == IndustryItems.armorLeggingsIridium.id ||
                item.id == IndustryItems.armorBootsIridium.id)
            thePlayer.addStat(IndustryAchievements.IRIDIUMARMOR, 1);
    }
}
