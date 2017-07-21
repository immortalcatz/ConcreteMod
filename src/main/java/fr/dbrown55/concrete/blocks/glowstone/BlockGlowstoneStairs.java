package fr.dbrown55.concrete.blocks.glowstone;

import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteStairsBase;
import fr.dbrown55.concrete.items.ItemHandler;
import fr.dbrown55.utilmod.helpers.TranslationHelper;
import net.minecraft.block.BlockColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class BlockGlowstoneStairs extends ConcreteStairsBase {

	private EnumDyeColor color;
	
	public BlockGlowstoneStairs(EnumDyeColor color) {
		super(BlockHandler.GLOWSTONE_SOLID.getDefaultState().withProperty(BlockColored.COLOR, color), new ResourceLocation("concrete", "glowcrete_stairs_" + color.getName()));
		this.color = color;
	}

	@Override
	public ItemStack getStack(IBlockState state) {
		return new ItemStack(ItemHandler.GLOWSTONE_STAIRS, 1, this.color.getMetadata());
	}

	@Override
	public boolean isCorrectStairs(ItemStack stack) {
		return stack.getMetadata() == this.color.getMetadata();
	}

	@Override
	public String getDisplayName(ItemStack stack) {
		return TranslationHelper.translateAll("tile.glowstone_concrete_stairs.name", "color." + EnumDyeColor.byMetadata(stack.getMetadata()).getName());
	}

}
