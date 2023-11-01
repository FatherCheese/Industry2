package turniplabs.industry.blocks.machines.transformer

import net.minecraft.core.block.BlockTileEntity
import net.minecraft.core.block.entity.TileEntity
import net.minecraft.core.block.material.Material
import turniplabs.industry.blocks.entities.transformer.TileEntityTransformerMVtoLV

class BlockTransformerMVtoLV(key: String?, id: Int, material: Material?) : BlockTileEntity(key, id, material) {

    override fun getNewBlockEntity(): TileEntity {
        return TileEntityTransformerMVtoLV()
    }
}