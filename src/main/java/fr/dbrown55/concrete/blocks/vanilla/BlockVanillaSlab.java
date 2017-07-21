package fr.dbrown55.concrete.blocks.vanilla;

import java.util.ArrayList;
import java.util.List;

import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteSlabBase;
import fr.dbrown55.concrete.items.ItemHandler;
import fr.dbrown55.utilmod.helpers.BlockStateHelper;
import fr.dbrown55.utilmod.helpers.TranslationHelper;
import net.minecraft.block.BlockColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVanillaSlab extends ConcreteSlabBase {

	public BlockVanillaSlab(EnumSlabPartType type) {
		super(type, new ResourceLocation("concrete", "vanilla_slab_" + type.getName()));
	}
	
	@Override
	public ItemStack getStack(IBlockState state, boolean pickBlock) {
		int count = 1;
		if(this.isDouble() && !pickBlock) {
			count = 2;
		}
		return new ItemStack(ItemHandler.VANILLA_SLAB, count, state.getValue(BlockColored.COLOR).getMetadata());
	}

	@Override
	public IBlockState getUpperState(IBlockState lower) {
		return BlockStateHelper.copyProperties(lower, BlockHandler.VANILLA_SLABS.get(EnumSlabPartType.UPPER_HALF), BlockColored.COLOR);
	}

	@Override
	public String getDisplayName(ItemStack stack) {
		return TranslationHelper.translateAll("tile.vanilla_slab.name", "color." + EnumDyeColor.byMetadata(stack.getMetadata()).getName());
	}

}
