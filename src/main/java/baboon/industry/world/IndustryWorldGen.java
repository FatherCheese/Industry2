package baboon.industry.world;

import baboon.industry.Industry2;
import baboon.industry.IndustryConfig;
import baboon.industry.block.IndustryBlocks;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import useless.terrainapi.generation.overworld.api.ChunkDecoratorOverworldAPI;

import java.util.Random;

public class IndustryWorldGen {

    private final String MOD_ID = Industry2.MOD_ID;

    private void initializeWorldGenValues() {
        ChunkDecoratorOverworldAPI.oreFeatures.config.setOreValues(MOD_ID, IndustryBlocks.oreCopperStone, 8, 20, 1/2f);
        ChunkDecoratorOverworldAPI.oreFeatures.config.setOreValues(MOD_ID, IndustryBlocks.oreTinStone, 8, 20, 1/2f);
        ChunkDecoratorOverworldAPI.oreFeatures.config.setOreValues(MOD_ID, IndustryBlocks.oreUraniumStone, 2, 10, 1/4f);
    }

    public void initializeWorldGen() {
        Random random = new Random();
        initializeWorldGenValues();

        if (IndustryConfig.cfg.getBoolean("World Gen.copperOre"))
            ChunkDecoratorOverworldAPI.oreFeatures.addManagedOreFeature(IndustryBlocks.oreCopperStone, true);

        if (IndustryConfig.cfg.getBoolean("World Gen.tinOre"))
            ChunkDecoratorOverworldAPI.oreFeatures.addManagedOreFeature(IndustryBlocks.oreTinStone, true);

        if (IndustryConfig.cfg.getBoolean("World Gen.uraniumOre"))
            ChunkDecoratorOverworldAPI.oreFeatures.addManagedOreFeature(IndustryBlocks.oreUraniumStone, true);

        if (IndustryConfig.cfg.getBoolean("World Gen.treeRubberwood"))
            ChunkDecoratorOverworldAPI.biomeFeatures.addFeatureSurface(
                    new WorldFeatureRubberTree(IndustryBlocks.leavesRubberWood.id,4 + random.nextInt(3 - 1) + 1),
                    1,
                    new Biome[]{Biomes.OVERWORLD_FOREST}
            );
    }
}
