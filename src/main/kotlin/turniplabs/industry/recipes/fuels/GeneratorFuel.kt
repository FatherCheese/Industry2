package turniplabs.industry.recipes.fuels

import net.minecraft.core.block.Block
import net.minecraft.core.item.Item
import turniplabs.industry.blocks.IndustryBlocks
import turniplabs.industry.items.IndustryItems

object GeneratorFuel {
    private val fuelList = HashMap<Any?, Any?>()

    init {
        addFuel(Item.stick.id, 128)
        addFuel(Item.boat.id, 256)
        addFuel(Item.bowl.id, 256)
        addFuel(Item.sign.id, 256)
        addFuel(Item.toolCalendar.id, 256)
        addFuel(Item.toolAxeWood.id, 256)
        addFuel(Item.toolHoeWood.id, 256)
        addFuel(Item.toolPickaxeWood.id, 256)
        addFuel(Item.toolShovelWood.id, 256)
        addFuel(Item.toolSwordWood.id, 256)
        addFuel(IndustryItems.treeTap.id, 256)
        addFuel(IndustryItems.scrap.id, 512)
        addFuel(Item.coal.id, 2048)

        addFuel(Block.cactus.id, 64)
        addFuel(Block.sugarcane.id, 64)
        addFuel(IndustryBlocks.rubberSapling.id, 128)
        addFuel(Block.saplingBirch.id, 128)
        addFuel(Block.saplingCherry.id, 128)
        addFuel(Block.saplingEucalyptus.id, 128)
        addFuel(Block.saplingOak.id, 128)
        addFuel(Block.saplingOakRetro.id, 128)
        addFuel(Block.saplingPine.id, 128)
        addFuel(Block.saplingShrub.id, 128)
        addFuel(Block.bookshelfPlanksOak.id, 256)
        addFuel(Block.chestPlanksOak.id, 256)
        addFuel(Block.chestPlanksOakPainted.id, 256)
        addFuel(Block.fencegatePlanksOak.id, 256)
        addFuel(Block.fencegatePlanksOakPainted.id, 256)
        addFuel(Block.fencePlanksOak.id, 256)
        addFuel(Block.fencePlanksOakPainted.id, 256)
        addFuel(Block.logBirch.id, 256)
        addFuel(Block.logCherry.id, 256)
        addFuel(Block.logEucalyptus.id, 256)
        addFuel(Block.logOak.id, 256)
        addFuel(Block.logOakMossy.id, 256)
        addFuel(Block.logPine.id, 256)
        addFuel(IndustryBlocks.rubberLog.id, 256)
        addFuel(Block.noteblock.id, 256)
        addFuel(Block.jukebox.id, 256)
        addFuel(Block.planksOak.id, 256)
        addFuel(Block.planksOakPainted.id, 256)
        addFuel(Block.slabPlanksOak.id, 256)
        addFuel(Block.slabPlanksOakPainted.id, 256)
        addFuel(Block.stairsPlanksOak.id, 256)
        addFuel(Block.stairsPlanksOakPainted.id, 256)
        addFuel(Block.trapdoorPlanksOak.id, 256)
        addFuel(Block.workbench.id, 256)
    }

    fun addFuel(input: Int, yield: Int) {
        fuelList[input] = yield
    }

    fun getResult(i: Int): Int? {
        return if (fuelList[i] == null) 0 else fuelList[i] as Int?
    }

    fun getFuelList(): HashMap<Any?, Any?> {
        return fuelList
    }
}