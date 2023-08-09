package turniplabs.industry.mixin;

import net.minecraft.client.render.RenderBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.world.WorldSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sunsetsatellite.energyapi.util.RenderWire;
import turniplabs.industry.blocks.BlockCable;

@Mixin(value = RenderBlocks.class, remap = false)
public class RenderBlocksMixin {

    @Shadow private WorldSource blockAccess;

    @Inject(method = "renderBlockByRenderType", at = @At("TAIL"), cancellable = true)
    private void industry_renderCables(Block block, int renderType, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        if (block instanceof BlockCable)
            cir.setReturnValue(RenderWire.render((RenderBlocks) ((Object)this), blockAccess, x, y, z, block, 0));
    }
}
