package baboon.industry.compat.terrainapi;

import baboon.industry.Industry2;
import baboon.industry.IndustryConfig;
import baboon.industry.block.I2Blocks;
import baboon.industry.world.WorldFeatureRubberTree;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import useless.terrainapi.generation.Parameters;
import useless.terrainapi.generation.overworld.OverworldFunctions;
import useless.terrainapi.generation.overworld.api.ChunkDecoratorOverworldAPI;
import useless.terrainapi.initialization.BaseInitialization;

public class OverworldInitialization extends BaseInitialization {
    private final String MOD_ID = Industry2.MOD_ID;
    @Override
    protected void initValues() {
        if (IndustryConfig.cfg.getBoolean("World Gen.treeRubberwood"))
            ChunkDecoratorOverworldAPI.biomeFeatures.addFeature(
                    (Parameters x) -> new WorldFeatureRubberTree(I2Blocks.leavesRubberWood.id,4 + x.random.nextInt(3 - 1) + 1),
                    null,
                    OverworldFunctions::getStandardBiomesDensity,
                    new Object[]{1, new Biome[]{Biomes.OVERWORLD_FOREST}},
                    -1);
    }

    @Override
    protected void initStructure() {

    }

    @Override
    protected void initOre() {
        if (IndustryConfig.cfg.getBoolean("World Gen.copperOre"))
            ChunkDecoratorOverworldAPI.oreFeatures.addManagedOreFeature(MOD_ID, I2Blocks.oreCopperStone, 8, 20, 1/2f, true);

        if (IndustryConfig.cfg.getBoolean("World Gen.tinOre"))
            ChunkDecoratorOverworldAPI.oreFeatures.addManagedOreFeature(MOD_ID, I2Blocks.oreTinStone, 8, 20, 1/2f, true);
    }

    @Override
    protected void initRandom() {

    }

    @Override
    protected void initBiome() {

    }
}
