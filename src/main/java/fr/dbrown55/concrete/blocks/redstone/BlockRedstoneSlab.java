package fr.dbrown55.concrete.blocks.redstone;

import fr.dbrown55.concrete.Main;
import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteSlabBase;
import fr.dbrown55.concrete.items.ItemHandler;
import fr.dbrown55.utilmod.helpers.BlockStateHelper;
import fr.dbrown55.utilmod.helpers.TranslationHelper;
import net.minecraft.block.BlockColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockRedstoneSlab extends ConcreteSlabBase {

	public BlockRedstoneSlab(EnumSlabPartType type) {
		super(type, new ResourceLocation("concrete", "redcrete_slab_" + type.getName()));
	}

	@Override
	public IBlockState getUpperState(IBlockState lower) {
		return BlockStateHelper.copyProperties(lower, BlockHandler.REDSTONE_SLABS.get(EnumSlabPartType.UPPER_HALF), BlockColored.COLOR);
	}

	@Override
	public ItemStack getStack(IBlockState state, boolean pickBlock) {
		int count = 1;
		if(this.isDouble() && !pickBlock) {
			count = 2;
		}
		return new ItemStack(ItemHandler.REDSTONE_SLAB, count, state.getValue(BlockColored.COLOR).getMetadata());
	}

	@Override
	public String getDisplayName(ItemStack stack) {
		return TranslationHelper.translateAll("tile.redstone_concrete_stairs.name", "color." + EnumDyeColor.byMetadata(stack.getMetadata()).getName());
	}

	public boolean canProvidePower(IBlockState state) {
		return true;
	}

	/**
	 * Lower because it costs less redstone to craft this block
	 */
	public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		if(Main.config.isFullRsPower()) {
			return 15;
		}
		if(this.isDouble()) {
			return (int) (15.0*(8.0/9.0));
		} else {
			return (int) ((15.0*(8.0/9.0))*(1.0/2.0));
		}
	}
	
}
