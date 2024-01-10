package baboon.industry.compat.btwaila;

import baboon.industry.block.machines.endgame.entity.TileEntityEnergyFabricator;
import toufoumaster.btwaila.gui.components.AdvancedInfoComponent;
import toufoumaster.btwaila.tooltips.TileTooltip;

public class TooltipFabricator extends TileTooltip<TileEntityEnergyFabricator> {
    @Override
    public void initTooltip() {
        addClass(TileEntityEnergyFabricator.class);
    }

    @Override
    public void drawAdvancedTooltip(TileEntityEnergyFabricator energyFabricator, AdvancedInfoComponent advancedInfoComponent) {
        advancedInfoComponent.drawStringWithShadow("Stored Energy: " + energyFabricator.energy + " / " + energyFabricator.capacity, 0);
        advancedInfoComponent.drawStringWithShadow("Stored Scrap: " + energyFabricator.scrap + " / " + energyFabricator.maxScrap, 0);
        advancedInfoComponent.drawStringWithShadow("Current Machine Time: " + energyFabricator.currentMachineTime + " / " + energyFabricator.maxMachineTime, 0);
        advancedInfoComponent.drawInventory(energyFabricator, 0);
    }
}
