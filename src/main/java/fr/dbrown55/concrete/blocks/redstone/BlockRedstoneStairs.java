package fr.dbrown55.concrete.blocks.redstone;

import fr.dbrown55.concrete.Main;
import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteStairsBase;
import fr.dbrown55.concrete.items.ItemHandler;
import fr.dbrown55.utilmod.helpers.TranslationHelper;
import net.minecraft.block.BlockColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockRedstoneStairs extends ConcreteStairsBase {

	private EnumDyeColor color;
	
	public BlockRedstoneStairs(EnumDyeColor color) {
		super(BlockHandler.REDSTONE_SOLID.getDefaultState().withProperty(BlockColored.COLOR, color), new ResourceLocation("concrete", "redcrete_stairs_" + color.getName()));
		this.color = color;
	}

	@Override
	public ItemStack getStack(IBlockState state) {
		return new ItemStack(ItemHandler.REDSTONE_STAIRS, 1, this.color.getMetadata());
	}

	@Override
	public boolean isCorrectStairs(ItemStack stack) {
		return stack.getMetadata() == this.color.getMetadata();
	}

	@Override
	public String getDisplayName(ItemStack stack) {
		return TranslationHelper.translateAll("tile.redstone_concrete_stairs.name", "color." + EnumDyeColor.byMetadata(stack.getMetadata()).getName());
	}
	
	public boolean canProvidePower(IBlockState state) {
		return true;
	}

	/**
	 * Lower because it costs less redstone to craft this block (+ stairs are 3/4th of a block)
	 */
	public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		if(Main.config.isFullRsPower()) {
			return 15;
		}
		return (int) ((15.0*(8.0/9.0))*(3.0/4.0));
	}

}
