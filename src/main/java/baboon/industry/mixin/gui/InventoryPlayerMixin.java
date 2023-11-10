package baboon.industry.mixin.gui;

import baboon.industry.item.ItemArmorIridium;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.util.helper.DamageType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = InventoryPlayer.class, remap = false)
public class InventoryPlayerMixin {

    @Shadow public ItemStack[] armorInventory;

    @Inject(method = "getTotalArmourPoints", at = @At("TAIL"), cancellable = true)
    public void industry_electricArmourPoints(CallbackInfoReturnable<Integer> cir) {
        float maxEnergy = 0.0F;
        InventoryPlayer inventoryPlayer = (InventoryPlayer) (Object) this;

        for(int armorPiece = 0; armorPiece < inventoryPlayer.armorInventory.length; ++armorPiece) {
            ItemStack itemStack = inventoryPlayer.armorInventory[armorPiece];
            if (itemStack != null && itemStack.getItem() instanceof ItemArmorIridium) {
                ItemArmorIridium armor = (ItemArmorIridium)itemStack.getItem();
                maxEnergy += (float)armor.getEnergy(itemStack);
            }
        }

        cir.setReturnValue ((int)(20.0F * maxEnergy));
    }

    @Inject(method = "getTotalProtectionAmount", at = @At("TAIL"), cancellable = true)
    public void industry_electricProtectionAmount(DamageType damageType, CallbackInfoReturnable<Float> cir) {
        float protectionPercentage = 0.0F;
        InventoryPlayer inventoryPlayer = (InventoryPlayer) (Object) this;

        for(int invLength = 0; invLength < inventoryPlayer.armorInventory.length; ++invLength) {
            if (inventoryPlayer.armorInventory[invLength] != null && inventoryPlayer.armorInventory[invLength].getItem() instanceof ItemArmorIridium) {
                ItemArmorIridium armor = (ItemArmorIridium)inventoryPlayer.armorInventory[invLength].getItem();
                if (armor.getEnergy(armorInventory[invLength]) > 0)
                 protectionPercentage += armor.material.getProtection(damageType);
            }
        }

        cir.setReturnValue(protectionPercentage);
    }

    @Inject(method = "damageArmor(I)V", at = @At("TAIL"))
    public void industry_damageElectricArmor(int damage, CallbackInfo ci) {
        InventoryPlayer inventoryPlayer = (InventoryPlayer) (Object) this;

        for(int invLength = 0; invLength < inventoryPlayer.armorInventory.length; ++invLength) {
            if (inventoryPlayer.armorInventory[invLength] != null && inventoryPlayer.armorInventory[invLength].getItem() instanceof ItemArmorIridium) {
                ItemArmorIridium armor = ((ItemArmorIridium) inventoryPlayer.armorInventory[invLength].getItem());

                if (armor.getEnergy(armorInventory[invLength]) - 8 >= 0)
                    armor.modifyEnergy(armorInventory[invLength], -8);

                if (inventoryPlayer.armorInventory[invLength].stackSize <= 0) {
                    inventoryPlayer.armorInventory[invLength] = null;
                }
            }
        }
    }
}
