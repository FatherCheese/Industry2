package baboon.industry.compat.btwaila;

import baboon.industry.block.reactor.entity.TileEntityReactorIO;
import toufoumaster.btwaila.gui.components.AdvancedInfoComponent;
import toufoumaster.btwaila.tooltips.TileTooltip;

public class TooltipReactorIO extends TileTooltip<TileEntityReactorIO> {
    @Override
    public void initTooltip() {
        addClass(TileEntityReactorIO.class);
    }
    @Override
    public void drawAdvancedTooltip(TileEntityReactorIO tile, AdvancedInfoComponent advancedInfoComponent) {
        advancedInfoComponent.drawStringWithShadow("Stored Energy: " + tile.energy + " / " + tile.capacity, 0);
    }
}
