package baboon.industry.compat.btwaila;

import baboon.industry.block.reactor.entity.TileEntityReactorNew;
import toufoumaster.btwaila.gui.components.AdvancedInfoComponent;
import toufoumaster.btwaila.tooltips.TileTooltip;

public class TooltipReactor extends TileTooltip<TileEntityReactorNew> {
    @Override
    public void initTooltip() {
        addClass(TileEntityReactorNew.class);
    }
    @Override
    public void drawAdvancedTooltip(TileEntityReactorNew tile, AdvancedInfoComponent advancedInfoComponent) {
        advancedInfoComponent.drawStringWithShadow("Current Heat: " + tile.heat + " / " + tile.maxHeat, 0);
        advancedInfoComponent.drawStringWithShadow("Stored Energy: " + tile.energy + " / " + tile.capacity, 0);
        advancedInfoComponent.drawStringWithShadow("Disabled: " + tile.isDisabled, 0);
        advancedInfoComponent.drawInventory(tile, 0);
    }
}
