package fr.dbrown55.concrete.blocks.glowstone;

import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteSlabBase;
import fr.dbrown55.concrete.items.ItemHandler;
import fr.dbrown55.utilmod.helpers.BlockStateHelper;
import fr.dbrown55.utilmod.helpers.TranslationHelper;
import net.minecraft.block.BlockColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class BlockGlowstoneSlab extends ConcreteSlabBase {

	public BlockGlowstoneSlab(EnumSlabPartType type) {
		super(type, new ResourceLocation("concrete", "glowcrete_slab_" + type.getName()));
	}

	@Override
	public IBlockState getUpperState(IBlockState lower) {
		return BlockStateHelper.copyProperties(lower, BlockHandler.GLOWSTONE_SLABS.get(EnumSlabPartType.UPPER_HALF), BlockColored.COLOR);
	}

	@Override
	public ItemStack getStack(IBlockState state, boolean pickBlock) {
		int count = 1;
		if(this.isDouble() && !pickBlock) {
			count = 2;
		}
		return new ItemStack(ItemHandler.GLOWSTONE_SLAB, count, state.getValue(BlockColored.COLOR).getMetadata());
	}

	@Override
	public String getDisplayName(ItemStack stack) {
		return TranslationHelper.translateAll("tile.glowstone_concrete_slab.name", "color." + EnumDyeColor.byMetadata(stack.getMetadata()).getName());
	}

}
