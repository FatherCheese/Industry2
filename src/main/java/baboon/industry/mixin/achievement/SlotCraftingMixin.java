package baboon.industry.mixin.achievement;

import baboon.industry.IndustryAchievements;
import baboon.industry.block.I2Blocks;
import baboon.industry.item.I2Items;
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
        if (item.id == I2Items.toolHammer.id)
            thePlayer.addStat(IndustryAchievements.TOOL1, 1);

        if (item.id == I2Items.toolCutters.id)
            thePlayer.addStat(IndustryAchievements.TOOL2, 1);

        if (item.id == I2Items.itemInsulatedCableTin.id ||
                item.id == I2Items.itemInsulatedCableCopper.id ||
                item.id == I2Items.itemInsulatedCableGold.id ||
                item.id == I2Items.itemInsulatedCableSteel.id)
            thePlayer.addStat(IndustryAchievements.CABLES, 1);

        if (item.id == I2Blocks.generator.id)
            thePlayer.addStat(IndustryAchievements.GENERATOR, 1);

        if (item.id == I2Blocks.machineFurnace.id)
            thePlayer.addStat(IndustryAchievements.FURNACE, 1);

        if (item.id == I2Blocks.machineMacerator.id)
            thePlayer.addStat(IndustryAchievements.MACERATOR, 1);

        if (item.id == I2Blocks.machineCompressor.id)
            thePlayer.addStat(IndustryAchievements.COMPRESSOR, 1);

        if (item.id == I2Blocks.machineWiremill.id)
            thePlayer.addStat(IndustryAchievements.WIREMILL, 1);

        if (item.id == I2Blocks.machineExtractor.id)
            thePlayer.addStat(IndustryAchievements.EXTRACTOR, 1);

        if (item.id == I2Blocks.machineRecycler.id)
            thePlayer.addStat(IndustryAchievements.RECYCLER, 1);

        if (item.id == I2Blocks.machineCannery.id)
            thePlayer.addStat(IndustryAchievements.CANNERY, 1);

        if (item.id == I2Blocks.machineTrommel.id)
            thePlayer.addStat(IndustryAchievements.TROMMEL, 1);

        if (item.id == I2Blocks.generatorWatermill.id)
            thePlayer.addStat(IndustryAchievements.WATERMILL, 1);

        if (item.id == I2Blocks.generatorWindmill.id)
            thePlayer.addStat(IndustryAchievements.WINDMILL, 1);

        if (item.id == I2Blocks.generatorGeothermal.id)
            thePlayer.addStat(IndustryAchievements.GEOTHERMAL, 1);

        if (item.id == I2Blocks.generatorSolar.id)
            thePlayer.addStat(IndustryAchievements.SOLAR, 1);

        if (item.id == I2Blocks.generatorSolar.id)
            thePlayer.addStat(IndustryAchievements.SOLAR, 1);

        if (item.id == I2Blocks.solarArrayLV.id)
            thePlayer.addStat(IndustryAchievements.ARRAY1, 1);

        if (item.id == I2Blocks.solarArrayMV.id)
            thePlayer.addStat(IndustryAchievements.ARRAY2, 1);

        if (item.id == I2Blocks.solarArrayHV.id)
            thePlayer.addStat(IndustryAchievements.ARRAY3, 1);

        if (item.id == I2Blocks.solarArrayEHV.id)
            thePlayer.addStat(IndustryAchievements.ARRAY4, 1);

        if (item.id == I2Blocks.batboxLV.id)
            thePlayer.addStat(IndustryAchievements.BATBOX1, 1);

        if (item.id == I2Items.toolDrill.id)
            thePlayer.addStat(IndustryAchievements.DRILL1, 1);

        if (item.id == I2Items.toolDrillGold.id)
            thePlayer.addStat(IndustryAchievements.DRILL2, 1);

        if (item.id == I2Items.toolDrillDiamond.id)
            thePlayer.addStat(IndustryAchievements.DRILL3, 1);

        if (item.id == I2Items.toolChainsaw.id)
            thePlayer.addStat(IndustryAchievements.CHAINSAW, 1);

        if (item.id == I2Items.toolNanoSword.id)
            thePlayer.addStat(IndustryAchievements.NANOSWORD, 1);

        if (item.id == I2Blocks.nuclearReactor.id)
            thePlayer.addStat(IndustryAchievements.REACTOR, 1);

        if (item.id == I2Blocks.energyFabricator.id)
            thePlayer.addStat(IndustryAchievements.FABRICATOR, 1);

        if (item.id == I2Items.ingotIridium.id)
            thePlayer.addStat(IndustryAchievements.IRIDIUM, 1);

        if (item.id == I2Items.armorHelmetIridium.id ||
                item.id == I2Items.armorChestplateIridium.id ||
                item.id == I2Items.armorLeggingsIridium.id ||
                item.id == I2Items.armorBootsIridium.id)
            thePlayer.addStat(IndustryAchievements.IRIDIUMARMOR, 1);
    }
}
