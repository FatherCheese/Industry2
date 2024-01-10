package baboon.industry.compat.btwaila;

import baboon.industry.block.machines.basic.entity.TileEntityMachineBase;
import baboon.industry.block.machines.basic.entity.TileEntityMachineCompressor;
import baboon.industry.block.machines.basic.entity.TileEntityMachineExtractor;
import baboon.industry.block.machines.basic.entity.TileEntityMachineFurnace;
import baboon.industry.block.machines.basic.entity.TileEntityMachineMacerator;
import baboon.industry.block.machines.basic.entity.TileEntityMachineRecycler;
import baboon.industry.block.machines.basic.entity.TileEntityMachineWiremill;
import toufoumaster.btwaila.gui.components.AdvancedInfoComponent;
import toufoumaster.btwaila.tooltips.TileTooltip;

public class TooltipMachineBasic extends TileTooltip<TileEntityMachineBase> {
    @Override
    public void initTooltip() {
            addClass(TileEntityMachineFurnace.class);
            addClass(TileEntityMachineMacerator.class);
            addClass(TileEntityMachineCompressor.class);
            addClass(TileEntityMachineWiremill.class);
            addClass(TileEntityMachineRecycler.class);
            addClass(TileEntityMachineExtractor.class);
    }

    @Override
    public void drawAdvancedTooltip(TileEntityMachineBase tile, AdvancedInfoComponent advancedInfoComponent) {
        advancedInfoComponent.drawStringWithShadow("Current Health: " + tile.machineHealth, 0);
        advancedInfoComponent.drawStringWithShadow("Stored Energy: " + tile.energy + " / " + tile.capacity, 0);

        if (tile.active)
            advancedInfoComponent.drawStringWithShadow("Machine Progress: " + tile.currentMachineTime + " / " + tile.maxMachineTime, 0);

        advancedInfoComponent.drawInventory(tile, 0);
    }
}
