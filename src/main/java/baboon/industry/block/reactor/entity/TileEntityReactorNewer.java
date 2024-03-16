package baboon.industry.block.reactor.entity;

import baboon.industry.IndustryConfig;
import baboon.industry.block.I2Blocks;
import baboon.industry.block.reactor.BlockReactor;
import baboon.industry.item.I2Items;
import baboon.industry.recipe.fuel.WaterFuel;
import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.sound.SoundType;
import net.minecraft.core.util.helper.Side;
import sunsetsatellite.catalyst.core.util.Connection;
import sunsetsatellite.catalyst.core.util.Direction;
import sunsetsatellite.catalyst.energy.impl.ItemEnergyContainer;
import sunsetsatellite.catalyst.energy.impl.TileEntityEnergyConductor;

import java.util.Random;

public class TileEntityReactorNewer extends TileEntityEnergyConductor implements IInventory {
    private ItemStack[] contents;
    private final WaterFuel fuel = new WaterFuel();
    private int redstoneCellsT1;
    private int redstoneCellsT2;
    private int redstoneCellsT3;
    private int reactorVents;
    private int tickTimer;
    public int heat;
    public int coolant;
    public int maxHeat = 1000;
    public final int maxCoolant = 8000;

    public TileEntityReactorNewer() {
        contents = new ItemStack[52];

        setCapacity(IndustryConfig.cfg.getInt("Energy Values.ehvMachineStorage"));
        setMaxProvide(IndustryConfig.cfg.getInt("Energy Values.ehvIO"));

        for (Direction dir : Direction.values())
            setConnection(dir, Connection.OUTPUT);
    }

    @Override
    public int getSizeInventory() {
        return contents.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return contents[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        if (contents[i] != null) {
            if (contents[i].stackSize <= j) {
                ItemStack itemstack = contents[i];
                contents[i] = null;
                return itemstack;
            } else {
                ItemStack splitStack = contents[i].splitStack(j);
                if (contents[i].stackSize <= 0) {
                    contents[i] = null;
                }

                return splitStack;
            }
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        contents[i] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
            itemStack.stackSize = getInventoryStackLimit();

        onInventoryChanged();
    }

    @Override
    public String getInvName() {
        return "IndustryReactor";
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        if (worldObj.getBlockTileEntity(x, y, z) != this)
            return false;

        return entityPlayer.distanceToSqr(x + 0.5f, y + 0.5f, z + 0.5f) <= 64;
    }

    @Override
    public void sortInventory() {

    }

    private static final boolean sendInventoryPacket = true;

    @Override
    public void onInventoryChanged() {
        redstoneCellsT1 = 0;
        redstoneCellsT2 = 0;
        redstoneCellsT3 = 0;
        reactorVents = 0;
        maxHeat = 1000;

        for (int i = 3; i < 51; i++) {
            if (contents[i] != null) {
                if (contents[i].getItem() == I2Items.cellRedstoneT1) {
                    redstoneCellsT1 += 1;
                }

                if (contents[i].getItem() == I2Items.cellRedstoneT2) {
                    redstoneCellsT2 += 1;
                }

                if (contents[i].getItem() == I2Items.cellRedstoneT3) {
                    redstoneCellsT3 += 1;
                }

                if (contents[i].getItem() == I2Items.reactorVent) {
                    reactorVents += 1;
                }

                if (contents[i].getItem() == I2Items.reactorPlate) {
                    maxHeat += 500;
                }
            }
        }

        if (sendInventoryPacket) {
            super.onInventoryChanged();
        }
    }

    public boolean isAssembled() {
        Side[] sides = new Side[]{Side.NORTH, Side.SOUTH, Side.EAST, Side.WEST, Side.BOTTOM, Side.TOP};
        for (Side side : sides) {
            int _x = x + side.getOffsetX();
            int _y = y + side.getOffsetY();
            int _z = z + side.getOffsetZ();
            if (worldObj.getBlockId(_x, y, _z) != I2Blocks.nuclearChamber.id && worldObj.getBlockId(x, _y, z) != I2Blocks.nuclearIO.id) {
                return false;
            }
        }
        return true;
    }

    private void overHeat() {
        // Create random smoke and fire particles.
        Random random = new Random();
        double _x = x + random.nextDouble();
        double _y = y + random.nextDouble();
        double _z = z + random.nextDouble();
        worldObj.spawnParticle("smoke", _x, _y + 0.22, _z, 0.0, 0.0, 0.0);
        worldObj.spawnParticle("flame", _x, _y + 0.22, _z, 0.0, 0.0, 0.0);

        // Set the Reactor block's 'half heat' boolean to true.
        Block block = worldObj.getBlock(x, y, z);
        if (block instanceof BlockReactor)
            ((BlockReactor) block).halfHeat = true;

        // If heat is over the maximum heat it will create a 3x3 square of explosions, with a power of 6 each.
        if (heat >= maxHeat) {
            for (int exploX = (int) (_x - 1); exploX < _x + 1; exploX++) {
                for (int exploY = (int) (_y - 1); exploY < _y + 1; exploY++) {
                    for (int exploZ = (int) (_z - 1); exploZ < _z + 1; exploZ++) {
                        worldObj.createExplosion(null, _x, _y, _z, 6.0f);
                        worldObj.setBlock(x, y, z, 0);
                    }
                }
            }
        }
    }

    // This gets the fuel yield from the item-stacks specified.
    private int getCoolantFromItem(ItemStack itemStack) {
        return (itemStack == null) ? 0 : fuel.getYield(itemStack.getItem().id);
    }

    private void coolIntoEnvironment() {
        // A for loop to check around the reactor, around the lower block.
        // If the lower blocks' materials is water and removing 25 heat is above 0 then remove heat and
        // run a check to remove one random water block.
        for (int _x = x - 1; _x < x + 2; _x++) {
            for (int _z = z - 1; _z < z + 2; _z++) {
                if (worldObj.getBlock(_x, y - 1, _z) != null) {
                    Material worldMats = worldObj.getBlockMaterial(_x, y - 1, _z);
                    if (worldMats == Material.water && heat - 25 * reactorVents > 0) {
                        heat -= 25 * reactorVents;
                        switch (worldObj.rand.nextInt(4)) {
                            default:
                            case 0:
                                worldObj.setBlockWithNotify(x + 1, y - 1, _z, 0);
                                worldObj.playSoundEffect(SoundType.WORLD_SOUNDS, x + 1, y - 1, _z, "random.fizz", 0.1f, worldObj.rand.nextFloat() * 0.7F + 0.3F);
                                break;
                            case 1:
                                worldObj.setBlockWithNotify(x - 1, y - 1, _z, 0);
                                worldObj.playSoundEffect(SoundType.WORLD_SOUNDS, x - 1, y - 1, _z, "random.fizz", 0.1f, worldObj.rand.nextFloat() * 0.7F + 0.3F);

                                break;
                            case 2:
                                worldObj.setBlockWithNotify(_x, y - 1, z + 1, 0);
                                worldObj.playSoundEffect(SoundType.WORLD_SOUNDS, _x, y - 1, z + 1, "random.fizz", 0.1f, worldObj.rand.nextFloat() * 0.7F + 0.3F);

                                break;
                            case 3:
                                worldObj.setBlockWithNotify(_x, y - 1, z - 1, 0);
                                worldObj.playSoundEffect(SoundType.WORLD_SOUNDS, _x, y - 1, z - 1, "random.fizz", 0.1f, worldObj.rand.nextFloat() * 0.7F + 0.3F);

                                break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (!worldObj.isClientSide && isAssembled()) {
            if (getStackInSlot(0) != null && getStackInSlot(0).getItem() instanceof ItemEnergyContainer) {
                provide(getStackInSlot(0), maxProvide, false);
                onInventoryChanged();
            }

            // A check for both heat and coolant. If heat is above 0, minus it and 50 coolant.
            if (coolant > 50 && heat > 0) {
                coolant -= 25;
                heat -= 50;
            }

            // Then a check for the coolant and heat count. If either are below 0 they will reset back to 0.
            if (coolant < 0) coolant = 0;
            if (heat < 0) heat = 0;

            // A safeguard for coolant and its 'fuel'.
            if ((coolant == 0 || coolant + 4000 < maxCoolant) && contents[2] != null) {
                if (fuel.getFuelList().containsKey(contents[2].getItem().id)) {
                    onInventoryChanged();
                    coolant += getCoolantFromItem(contents[2]);

                    // If it's a water bucket, return an empty bucket.
                    // Otherwise, just eat the item.
                    --contents[2].stackSize;
                    if (contents[2].getItem() == Item.bucketWater)
                        contents[2] = new ItemStack(Item.bucket);

                    if (contents[2].stackSize <= 0)
                        contents[2] = null;
                }
            }

            // Damage cells every second.
            boolean shouldDamageRedstone = false;
            if (tickTimer++ >= 20) {
                tickTimer = 0;
                shouldDamageRedstone = true;

                if (reactorVents > 0) coolIntoEnvironment();
            }

            // Check the Reactor inventory for cells.
            for (int i = 3; i < 51; i++) {
                ItemStack stacks = contents[i];
                if (stacks == null) continue;

                // If the damage boolean is activated then damage the cells and increase the heat.
                // Then if stack size is below 0, replace it with its 'empty' item.
                if (shouldDamageRedstone) {
                    stacks.damageItem(1, null);

                    if (stacks.getItem() == I2Items.cellRedstoneT1) {
                        heat += 5 * redstoneCellsT1;

                        if (stacks.stackSize <= 0) contents[i] = new ItemStack(I2Items.cellRedstoneT1Empty);
                    }

                    if (stacks.getItem() == I2Items.cellRedstoneT2) {
                        heat += 25 * redstoneCellsT2;

                        if (stacks.stackSize <= 0) contents[i] = new ItemStack(I2Items.cellRedstoneT2Empty);
                    }

                    if (stacks.getItem() == I2Items.cellRedstoneT3) {
                        heat += 50 * redstoneCellsT3;

                        if (stacks.stackSize < 0) contents[i] = null;
                    }
                }
            }

            if (heat > maxHeat / 2) this.overHeat();


            if (redstoneCellsT1 > 0 && energy + 5 * redstoneCellsT1 * 2 < capacity) energy += (5 * redstoneCellsT1) * 2;
            if (redstoneCellsT2 > 0 && energy + 25 * redstoneCellsT2 * 4 < capacity) energy += (25 * redstoneCellsT2) * 4;
            if (redstoneCellsT3 > 0 && energy + 50 * redstoneCellsT3 * 6 < capacity) energy += (50 * redstoneCellsT3) * 6;

            if (redstoneCellsT1 == 0 && redstoneCellsT2 == 0 && redstoneCellsT3 == 0 && heat > 0) --heat;
        }
    }

    @Override
    public void writeToNBT(CompoundTag tag) {
        super.writeToNBT(tag);
        tag.putInt("RedstoneCellsT1", redstoneCellsT1);
        tag.putInt("RedstoneCellsT2", redstoneCellsT2);
        tag.putInt("RedstoneCellsT3", redstoneCellsT3);
        tag.putInt("ReactorVents", reactorVents);
        tag.putInt("TickTimer", tickTimer);
        tag.putInt("Heat", heat);
        tag.putInt("Coolant", coolant);

        // Inventory writer.
        ListTag nbtTagList = new ListTag();
        for (int i = 0; i < this.contents.length; ++i) {
            if (this.contents[i] == null) continue;
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.putByte("Slot", (byte)i);
            this.contents[i].writeToNBT(compoundTag);
            nbtTagList.addTag(compoundTag);
        }
        tag.put("Items", nbtTagList);
    }

    @Override
    public void readFromNBT(CompoundTag tag) {
        super.readFromNBT(tag);
        redstoneCellsT1 = tag.getInteger("RedstoneCellsT1");
        redstoneCellsT2 = tag.getInteger("RedstoneCellsT2");
        redstoneCellsT3 = tag.getInteger("RedstoneCellsT3");
        reactorVents = tag.getInteger("ReactorVents");
        tickTimer = tag.getInteger("TickTimer");
        heat = tag.getInteger("Heat");
        coolant = tag.getInteger("Coolant");

        // Inventory reader.
        ListTag nbtTagList = tag.getList("Items");
        this.contents = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nbtTagList.tagCount(); ++i) {
            CompoundTag compoundTag = (CompoundTag) nbtTagList.tagAt(i);
            int j = compoundTag.getByte("Slot") & 0xFF;
            if (j >= contents.length) continue;
            this.contents[j] = ItemStack.readItemStackFromNbt(compoundTag);
        }
    }
}
