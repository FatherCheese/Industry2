package baboon.industry.block.generator.entity;

import baboon.industry.IndustryConfig;
import sunsetsatellite.energyapi.impl.ItemEnergyContainer;

public class TileEntityGeneratorSolar extends TileEntitySolarBase {
    public TileEntityGeneratorSolar() {
        super(1);

        setCapacity(IndustryConfig.cfg.getInt("Energy Values.elvStorage"));
        setTransfer(IndustryConfig.cfg.getInt("Energy Values.extraLowVoltage"));
        setMaxReceive(0);
    }

    @Override
    public String getInvName() {
        return "IndustrySolar";
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!worldObj.isClientSide) {
            if (getStackInSlot(0) != null && getStackInSlot(0).getItem() instanceof ItemEnergyContainer) {
                provide(getStackInSlot(0), maxProvide, false);
                onInventoryChanged();
            }
        }
    }
}
