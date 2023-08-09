package turniplabs.industry.blocks

import net.minecraft.core.block.BlockTileEntity
import net.minecraft.core.block.entity.TileEntity
import net.minecraft.core.block.material.Material
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.enums.EnumDropCause
import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemStack
import net.minecraft.core.lang.I18n
import net.minecraft.core.net.command.TextFormatting
import net.minecraft.core.world.World
import sunsetsatellite.energyapi.EnergyAPI
import sunsetsatellite.sunsetutils.util.ICustomDescription
import turniplabs.industry.blocks.entities.TileEntityCable
import turniplabs.industry.items.ModItems

class BlockCable(
    key: String?,
    id: Int,
    material:
    Material?,
    private val capacity: Int,
    private val energy: Int,
    private val transfer: Int,
    private val dangerLevel: Int,
    private val breakResult: Item
) : BlockTileEntity(key, id, material), ICustomDescription {

    override fun getNewBlockEntity(): TileEntity {
        return TileEntityCable(capacity, energy, transfer, dangerLevel)
    }

    override fun getDescription(p0: ItemStack?): String {
        val text = StringBuilder()
        val trans = I18n.getInstance()
        return text.append(TextFormatting.WHITE).append("Max Transfer: ").append(TextFormatting.LIGHT_GRAY).append(100)
            .append(" ").append(EnergyAPI.ENERGY_SUFFIX).append(" IN ").append("| ").append(100).append(" ")
            .append(EnergyAPI.ENERGY_SUFFIX).append(" OUT").toString()
    }

    override fun renderAsNormalBlock(): Boolean {
        return false
    }

    override fun blockActivated(world: World?, x: Int, y: Int, z: Int, player: EntityPlayer?): Boolean {
        return false
    }

    override fun getRenderBlockPass(): Int {
        return 31
    }

    override fun isOpaqueCube(): Boolean {
        return false
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
        ModItems
        return arrayOf(ItemStack(breakResult))
    }
}