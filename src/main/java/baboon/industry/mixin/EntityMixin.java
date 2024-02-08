package baboon.industry.mixin;

import baboon.industry.item.I2Items;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Entity.class, remap = false)
public abstract class EntityMixin {
    @Shadow public abstract ItemStack[] getInventory();

    @Shadow protected boolean fireImmune;

    @Unique
    private boolean isArmoured(Entity entity) {
        for (int i = 0; i < 4; i++)
            if (!(entity instanceof EntityPlayer) || ((EntityPlayer) entity).inventory.armorInventory[i] == null)
                return false;

        return ((EntityPlayer) entity).inventory.armorInventory[3].itemID == I2Items.armorHelmetIridium.id     &&
                ((EntityPlayer) entity).inventory.armorInventory[2].itemID == I2Items.armorChestplateIridium.id &&
                ((EntityPlayer) entity).inventory.armorInventory[1].itemID == I2Items.armorLeggingsIridium.id   &&
                ((EntityPlayer) entity).inventory.armorInventory[0].itemID == I2Items.armorBootsIridium.id;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void industry_lavaIridiumProtection(CallbackInfo ci) {
        Entity entity = (Entity) (Object) this;
        if (isArmoured(entity))
            fireImmune = true;
        if (!isArmoured(entity))
            fireImmune = false;
    }
}
