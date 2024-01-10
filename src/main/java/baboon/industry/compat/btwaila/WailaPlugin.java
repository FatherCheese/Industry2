package baboon.industry.compat.btwaila;

import org.slf4j.Logger;
import toufoumaster.btwaila.entryplugins.waila.BTWailaCustomTooltipPlugin;
import toufoumaster.btwaila.tooltips.TooltipRegistry;

public class WailaPlugin implements BTWailaCustomTooltipPlugin {
    public static TooltipBatbox BATBOX = new TooltipBatbox();
    public static TooltipCable CABLE = new TooltipCable();
    public static TooltipFabricator FABRICATOR = new TooltipFabricator();
    public static TooltipGenerator GENERATOR = new TooltipGenerator();
    public static TooltipGeneratorGeothermal GENERATOR_GEOTHERMAL = new TooltipGeneratorGeothermal();
    public static TooltipGeneratorWatermill GENERATOR_WATERMILL = new TooltipGeneratorWatermill();
    public static TooltipGeneratorWindmill GENERATOR_WINDMILL = new TooltipGeneratorWindmill();
    public static TooltipMachineAdvanced MACHINE_ADVANCED = new TooltipMachineAdvanced();
    public static TooltipMachineBasic MACHINE_BASIC = new TooltipMachineBasic();
    public static TooltipMachineCannery MACHINE_CANNERY = new TooltipMachineCannery();
    public static TooltipMachineTrommel MACHINE_TROMMEL = new TooltipMachineTrommel();
    public static TooltipReactor REACTOR = new TooltipReactor();
    public static TooltipReactorIO REACTOR_IO = new TooltipReactorIO();
    public static TooltipSolar SOLAR = new TooltipSolar();
    public static TooltipTransformer TRANSFORMER = new TooltipTransformer();

    @Override
    public void initializePlugin(TooltipRegistry tooltipRegistry, Logger logger) {
        logger.info("Adding tooltips for: Industry2");
        tooltipRegistry.register(BATBOX);
        tooltipRegistry.register(CABLE);
        tooltipRegistry.register(FABRICATOR);
        tooltipRegistry.register(GENERATOR);
        tooltipRegistry.register(GENERATOR_GEOTHERMAL);
        tooltipRegistry.register(GENERATOR_WATERMILL);
        tooltipRegistry.register(GENERATOR_WINDMILL);
        tooltipRegistry.register(MACHINE_ADVANCED);
        tooltipRegistry.register(MACHINE_BASIC);
        tooltipRegistry.register(MACHINE_CANNERY);
        tooltipRegistry.register(MACHINE_TROMMEL);
        tooltipRegistry.register(REACTOR);
        tooltipRegistry.register(REACTOR_IO);
        tooltipRegistry.register(SOLAR);
        tooltipRegistry.register(TRANSFORMER);
    }
}
