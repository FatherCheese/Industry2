package turniplabs.industry.blocks.machines

import net.minecraft.core.block.BlockTileEntityRotatable
import net.minecraft.core.block.entity.TileEntity
import net.minecraft.core.block.material.Material
import net.minecraft.core.entity.EntityItem
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.item.ItemStack
import net.minecraft.core.player.inventory.IInventory
import net.minecraft.core.world.World
import sunsetsatellite.energyapi.EnergyAPI
import turniplabs.industry.blocks.entities.TileEntityGenerator
import turniplabs.industry.gui.ContainerGenerator
import turniplabs.industry.gui.GuiGenerator

class BlockGenerator(key: String?, id: Int, material: Material?) : BlockTileEntityRotatable(key, id, material) {

    override fun getNewBlockEntity(): TileEntity {
        return TileEntityGenerator()
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
        if (!world?.isClientSide!!) {
            val tileEntity: TileEntityGenerator = world.getBlockTileEntity(x, y, z) as TileEntityGenerator

            tileEntity ?: return false
            EnergyAPI.displayGui(
                player, GuiGenerator(player?.inventory, tileEntity),
                ContainerGenerator(player?.inventory, tileEntity),
                player?.inventory
            )
        }
        return true
    }
}