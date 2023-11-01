package turniplabs.industry.blocks.machines.mv

import net.minecraft.core.block.BlockTileEntityRotatable
import net.minecraft.core.block.entity.TileEntity
import net.minecraft.core.block.material.Material
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.enums.EnumDropCause
import net.minecraft.core.item.ItemStack
import net.minecraft.core.util.helper.Side
import net.minecraft.core.util.helper.Sides
import net.minecraft.core.world.World
import net.minecraft.core.world.WorldSource
import sunsetsatellite.energyapi.interfaces.mixins.IEntityPlayer
import turniplabs.halplibe.helper.TextureHelper
import turniplabs.industry.Industry2
import turniplabs.industry.blocks.IndustryBlocks
import turniplabs.industry.blocks.entities.mv.TileEntityLaserCutter

class BlockCutterLaser(key: String?, id: Int, material: Material?) : BlockTileEntityRotatable(key, id, material) {
    private var keepInventory = false

    private val machineTexture: Array<IntArray> = arrayOf(
        TextureHelper.getOrCreateBlockTexture(Industry2.MOD_ID, "advanced_machine_cutter.png"),
        TextureHelper.getOrCreateBlockTexture(Industry2.MOD_ID, "advanced_machine_cutter_on.png"),
        TextureHelper.getOrCreateBlockTexture(Industry2.MOD_ID, "machine_casing_advanced.png")
    )

    init {
        setupInstance(this)
    }

    override fun getNewBlockEntity(): TileEntity {
        return TileEntityLaserCutter()
    }

    override fun blockActivated(world: World?, x: Int, y: Int, z: Int, player: EntityPlayer?): Boolean {
        if (!world!!.isClientSide) {
            val tileEntity: TileEntityLaserCutter = world.getBlockTileEntity(x, y, z) as TileEntityLaserCutter

            tileEntity ?: return false
            (player as IEntityPlayer).displayGuiScreen_energyapi(tileEntity)
        }
        return true
    }

    override fun getBlockTexture(blockAccess: WorldSource?, x: Int, y: Int, z: Int, side: Side): Int {

        /*
        0 = bottom
        1 = top
        2 = north
        3 = south
        4 = west
        5 = east
         */

        val tileEntity: TileEntityLaserCutter = blockAccess?.getBlockTileEntity(x, y, z) as TileEntityLaserCutter
        val metadata: Int = blockAccess.getBlockMetadata(x, y, z)
        val index = Sides.orientationLookUpHorizontal[6 * metadata + side.id]
        if (index != 2)
            texCoordToIndex(machineTexture[2][0], machineTexture[2][1]).also { atlasIndices[index] = it }

        if (index == 2)
            if (tileEntity.active) texCoordToIndex(machineTexture[1][0], machineTexture[1][1]).also { atlasIndices[index] = it }
            else texCoordToIndex(machineTexture[0][0], machineTexture[0][1]).also { atlasIndices[index] = it }

        return atlasIndices[index]
    }

    override fun getBreakResult(
        world: World?,
        dropCause: EnumDropCause?,
        x: Int,
        y: Int,
        z: Int,
        meta: Int,
        tileEntity: TileEntity?
    ): Array<ItemStack> {
        return when (dropCause) {
            EnumDropCause.SILK_TOUCH, EnumDropCause.PICK_BLOCK, EnumDropCause.PROPER_TOOL -> arrayOf(ItemStack(this))
            else ->  arrayOf(ItemStack(IndustryBlocks.machineCasing))
        }
    }

    companion object {
        private var instance: BlockCutterLaser? = null

        private fun setupInstance(machine: BlockCutterLaser) {
            instance = machine
        }

        private fun getInstance(): BlockCutterLaser {
            return instance ?: throw NullPointerException("Instance of BlockCutterLaser hasn't been setup!")
        }

        fun updateBlockState(active: Boolean, world: World, x: Int, y: Int, z: Int) {
            val metaData: Int = world.getBlockMetadata(x, y, z)
            val tileEntity: TileEntity? = world.getBlockTileEntity(x, y, z)

            if (tileEntity == null) world.setBlockWithNotify(x, y, z, 0)
            else {
                getInstance().keepInventory = true
                if (active) world.setBlockMetadataWithNotify(x, y, z, 1)
                if (!active) world.setBlockMetadataWithNotify(x, y, z, 0)

                getInstance().keepInventory = false
                world.setBlockMetadataWithNotify(x, y, z, metaData)
                tileEntity.validate()
                world.setBlockTileEntity(x, y, z, tileEntity)
            }
        }
    }
}