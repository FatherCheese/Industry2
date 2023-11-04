package baboon.industry;

import baboon.industry.block.IndustryBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.generate.feature.WorldFeatureOre;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTree;
import useless.terrainapi.generation.overworld.ChunkDecoratorOverworldAPI;

import java.util.HashMap;

public class IndustryWorldGen {

    private final String MOD_ID = Industry2.MOD_ID;

    private void initializeWorldGenValues() {
        ChunkDecoratorOverworldAPI.oreFeatures.setOreValues(MOD_ID, IndustryBlocks.oreCopperStone, 8, 20, 1/2f);
        ChunkDecoratorOverworldAPI.oreFeatures.setOreValues(MOD_ID, IndustryBlocks.oreTinStone, 8, 20, 1/2f);
        ChunkDecoratorOverworldAPI.oreFeatures.setOreValues(MOD_ID, IndustryBlocks.oreUraniumStone, 8, 20, 1/4f);
    }

    protected void initializeWorldGen() {
        initializeWorldGenValues();

        if (IndustryConfig.cfg.getBoolean("World Gen.copperOre"))
            ChunkDecoratorOverworldAPI.oreFeatures.addManagedOreFeature(IndustryBlocks.oreCopperStone, true);

        if (IndustryConfig.cfg.getBoolean("World Gen.tinOre"))
            ChunkDecoratorOverworldAPI.oreFeatures.addManagedOreFeature(IndustryBlocks.oreTinStone, true);

        if (IndustryConfig.cfg.getBoolean("World Gen.uraniumOre"))
            ChunkDecoratorOverworldAPI.oreFeatures.addManagedOreFeature(IndustryBlocks.oreUraniumStone, true);

        if (IndustryConfig.cfg.getBoolean("World Gen.treeRubberwood"))
            ChunkDecoratorOverworldAPI.biomeFeatures.addFeatureSurface(
                    new WorldFeatureTree(IndustryBlocks.leavesRubberWood.id, IndustryBlocks.logRubberWood.id, 7),
                    3,
                    new Biome[]{Biomes.OVERWORLD_FOREST}
            );
    }
}
