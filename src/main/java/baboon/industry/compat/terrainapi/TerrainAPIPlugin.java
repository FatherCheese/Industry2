package baboon.industry.compat.terrainapi;

import baboon.industry.Industry2;
import useless.terrainapi.api.TerrainAPI;

public class TerrainAPIPlugin implements TerrainAPI {
    @Override
    public String getModID() {
        return Industry2.MOD_ID;
    }

    @Override
    public void onInitialize() {
        new OverworldInitialization().init();
    }
}
