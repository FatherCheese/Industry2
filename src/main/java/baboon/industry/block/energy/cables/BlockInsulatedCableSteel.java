package baboon.industry.block.energy.cables;

import baboon.industry.IndustryConfig;
import baboon.industry.item.I2Items;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class BlockInsulatedCableSteel extends BlockCable {
    public BlockInsulatedCableSteel(String key, int id, Material material) {
        super(key,
                id,
                material,
                IndustryConfig.cfg.getInt("Energy Values.ehvIO"),
                IndustryConfig.cfg.getInt("Energy Values.ehvIO"),
                0);
    }

    @Override
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        return new ItemStack[]{new ItemStack(I2Items.itemInsulatedCableSteel)};
    }
}
