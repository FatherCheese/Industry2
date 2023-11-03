package baboon.industry.mixin;

import baboon.industry.block.cables.BlockCable;
import baboon.industry.block.cables.model.CableModel;
import net.minecraft.client.render.RenderBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.world.WorldSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = RenderBlocks.class, remap = false)
public class RenderBlocksMixin {

    @Shadow private WorldSource blockAccess;

    @Inject(method = "renderBlockByRenderType", at = @At("HEAD"), cancellable = true)
    void industry_renderCable(Block block, int renderType, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        if (renderType == 32)
            cir.setReturnValue(CableModel.renderCable((RenderBlocks) (Object) this, blockAccess, (BlockCable) block, x, y, z));
    }
}