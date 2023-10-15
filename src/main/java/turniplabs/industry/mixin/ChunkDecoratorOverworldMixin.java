package turniplabs.industry.mixin;

import net.minecraft.core.block.Block;
import net.minecraft.core.world.World;
import net.minecraft.core.world.chunk.Chunk;
import net.minecraft.core.world.generate.chunk.perlin.overworld.ChunkDecoratorOverworld;
import net.minecraft.core.world.generate.feature.WorldFeatureOre;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import turniplabs.industry.Industry2;
import turniplabs.industry.blocks.IndustryBlocks;

import java.util.Random;

@Mixin(value = ChunkDecoratorOverworld.class, remap = false)
public class ChunkDecoratorOverworldMixin {

    @Shadow @Final private World world;

    @Inject(method = "decorate", at = @At("TAIL"))
    void industry_decorateOverworldOres(Chunk chunk, CallbackInfo ci) {
        // Duplicated functions because Local Captures are utterly stupid
        int ind_minY = world.getWorldType().getMinY();
        int ind_maxY = world.getWorldType().getMaxY();
        int rangeY = ind_maxY + 1 - ind_minY;
        float ind_oreHeight = (float)rangeY / 128.0F;

        int ind_chunkX = chunk.xPosition;
        int ind_chunkZ = chunk.zPosition;
        int ind_x = ind_chunkX * 16;
        int ind_z = ind_chunkZ * 16;

        Random ind_rand = new Random(this.world.getRandomSeed());

        for (int copper = 0; (float) copper < 20.0f * ind_oreHeight; ++copper) {
            int copX = ind_x + ind_rand.nextInt(16);
            int copY = ind_minY + ind_rand.nextInt(rangeY / 2);
            int copZ = ind_z + ind_rand.nextInt(16);
            new WorldFeatureOre(IndustryBlocks.INSTANCE.getOreCopperStone().id, 8, true)
                    .generate(world, ind_rand, copX, copY, copZ);
        }

        for (int tin = 0; (float) tin < 20.0f * ind_oreHeight; ++tin) {
            int tinX = ind_x + ind_rand.nextInt(16);
            int tinY = ind_minY + ind_rand.nextInt(rangeY / 2);
            int tinZ = ind_z + ind_rand.nextInt(16);
            new WorldFeatureOre(IndustryBlocks.INSTANCE.getOreTinStone().id, 8, true)
                    .generate(world, ind_rand, tinX, tinY, tinZ);
        }

        for (int uranium = 0; (float) uranium < 8.0f * ind_oreHeight; ++uranium) {
            int uraniumX = ind_x + ind_rand.nextInt(16);
            int uraniumY = ind_minY + ind_rand.nextInt(rangeY / 8);
            int uraniumZ = ind_z + ind_rand.nextInt(16);
            new WorldFeatureOre(IndustryBlocks.INSTANCE.getOreUraniumStone().id, 2, true)
                    .generate(world, ind_rand, uraniumX, uraniumY, uraniumZ);
        }
    }
}
