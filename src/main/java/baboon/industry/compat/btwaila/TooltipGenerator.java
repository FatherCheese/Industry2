package baboon.industry.compat.btwaila;

import baboon.industry.block.energy.generator.entity.TileEntityGenerator;
import toufoumaster.btwaila.gui.components.AdvancedInfoComponent;
import toufoumaster.btwaila.tooltips.TileTooltip;

public class TooltipGenerator extends TileTooltip<TileEntityGenerator> {
    @Override
    public void initTooltip() {
        addClass(TileEntityGenerator.class);
    }

    @Override
    public void drawAdvancedTooltip(TileEntityGenerator generator, AdvancedInfoComponent advancedInfoComponent) {
        advancedInfoComponent.drawStringWithShadow("Stored Energy: " + generator.energy + " / " + generator.capacity, 0);

        if (generator.active)
            advancedInfoComponent.drawStringWithShadow("Machine Progress: " + generator.currentBurnTime + " / " + generator.maxBurnTime, 0);

        advancedInfoComponent.drawInventory(generator, 0);
    }
}
