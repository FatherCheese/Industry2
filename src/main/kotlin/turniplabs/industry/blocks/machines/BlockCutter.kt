package turniplabs.industry.blocks.machines

import net.minecraft.core.block.BlockTileEntityRotatable
import net.minecraft.core.block.entity.TileEntity
import net.minecraft.core.block.material.Material
import net.minecraft.core.entity.EntityItem
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.item.ItemStack
import net.minecraft.core.player.inventory.IInventory
import net.minecraft.core.util.helper.Side
import net.minecraft.core.util.helper.Sides
import net.minecraft.core.world.World
import net.minecraft.core.world.WorldSource
import sunsetsatellite.energyapi.EnergyAPI
import turniplabs.halplibe.helper.TextureHelper
import turniplabs.industry.Industry2
import turniplabs.industry.blocks.entities.TileEntityCutter
import turniplabs.industry.gui.ContainerCutter
import turniplabs.industry.gui.GuiCutter

class BlockCutter(key: String?, id: Int, material: Material?) : BlockTileEntityRotatable(key, id, material) {
    private var keepInventory = false

    private val machineTexture: Array<IntArray> = arrayOf(
        TextureHelper.getOrCreateBlockTexture(Industry2.MOD_ID, "machine_cutter.png"),
        TextureHelper.getOrCreateBlockTexture(Industry2.MOD_ID, "machine_cutter_on.png"),
        TextureHelper.getOrCreateBlockTexture(Industry2.MOD_ID, "machine_casing_basic.png")
    )

    init {
        setupInstance(this)
    }

    override fun getNewBlockEntity(): TileEntity {
        return TileEntityCutter()
    }

    override fun onBlockRemoval(world: World?, x: Int, y: Int, z: Int) {
        val inventory: IInventory = world?.getBlockTileEntity(x, y, z) as IInventory

        label0@
        for (invSize in 0 until inventory.sizeInventory) {
            val itemStack: ItemStack = inventory.getStackInSlot(invSize) ?: continue

            val fX: Float = (world.rand.nextFloat() * 0.8f) + 0.1f
            val fY: Float = (world.rand.nextFloat() * 0.8f) + 0.1f
            val fZ: Float = (world.rand.nextFloat() * 0.8f) + 0.1f
            do {
                if (itemStack.stackSize <= 0)
                    continue@label0

                var worldRand: Int = world.rand.nextInt(21) + 10
                if (worldRand > itemStack.stackSize)
                    worldRand = itemStack.stackSize
                itemStack.stackSize -= worldRand

                val item = EntityItem(
                    world,
                    (x.toFloat() + fX).toDouble(),
                    (y.toFloat() + fY).toDouble(),
                    (z.toFloat() + fZ).toDouble(),
                    ItemStack(itemStack.itemID, worldRand, itemStack.metadata, itemStack.tag)
                )
                item.x = world.rand.nextGaussian() * 0.05f
                item.y = world.rand.nextGaussian() * 0.05f
                item.z = world.rand.nextGaussian() * 0.05f
                world.entityJoinedWorld(item)
            } while (true)
        }
        super.onBlockRemoval(world, x, y, z)
    }

    override fun blockActivated(world: World?, x: Int, y: Int, z: Int, player: EntityPlayer?): Boolean {
        if (!world!!.isClientSide) {
            val tileEntity: TileEntityCutter = world.getBlockTileEntity(x, y, z) as TileEntityCutter

            tileEntity ?: return false
            EnergyAPI.displayGui(
                player,
                GuiCutter(player?.inventory, tileEntity),
                ContainerCutter(player?.inventory, tileEntity),
                player?.inventory
            )
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

        val tileEntity: TileEntityCutter = blockAccess?.getBlockTileEntity(x, y, z) as TileEntityCutter
        val metadata: Int = blockAccess.getBlockMetadata(x, y, z)
        val index = Sides.orientationLookUpHorizontal[6 * metadata + side.id]
        if (index != 2)
            texCoordToIndex(machineTexture[2][0], machineTexture[2][1]).also { atlasIndices[index] = it }

        if (index == 2)
            if (tileEntity.active) texCoordToIndex(machineTexture[1][0], machineTexture[1][1]).also { atlasIndices[index] = it }
            else texCoordToIndex(machineTexture[0][0], machineTexture[0][1]).also { atlasIndices[index] = it }

        return atlasIndices[index]
    }

    companion object {
        private var instance: BlockCutter? = null

        fun setupInstance(machine: BlockCutter) {
            instance = machine
        }

        private fun getInstance(): BlockCutter {
            return instance ?: throw NullPointerException("Instance of BlockCutter hasn't been setup!")
        }

        fun updateBlockState(active: Boolean, world: World, x: Int, y: Int, z: Int) {
            val metadata: Int = world.getBlockMetadata(x, y, z)
            val tileEntity: TileEntity? = world.getBlockTileEntity(x, y, z)

            if (tileEntity == null)
                world.setBlockWithNotify(x, y, z, 0)
            else {
                getInstance().keepInventory = true
                if (active) world.setBlockMetadataWithNotify(x, y, z, 1)
                if (!active) world.setBlockMetadataWithNotify(x, y, z, 0)

                getInstance().keepInventory = false
                world.setBlockMetadataWithNotify(x, y, z, metadata)
                tileEntity.validate()
                world.setBlockTileEntity(x, y, z, tileEntity)
            }
        }
    }
}