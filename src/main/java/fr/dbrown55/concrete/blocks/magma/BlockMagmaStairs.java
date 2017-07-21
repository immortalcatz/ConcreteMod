package fr.dbrown55.concrete.blocks.magma;

import java.util.ArrayList;
import java.util.List;

import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteStairsBase;
import fr.dbrown55.concrete.items.ItemHandler;
import fr.dbrown55.utilmod.helpers.TranslationHelper;
import net.minecraft.block.BlockColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMagmaStairs extends ConcreteStairsBase {

	private EnumDyeColor color;
	
	public BlockMagmaStairs(EnumDyeColor color) {
		super(BlockHandler.MAGMA_SOLID.getDefaultState().withProperty(BlockColored.COLOR, color), new ResourceLocation("concrete", "magma_stairs_" + color.getName()));
		this.color = color;
		this.setLightLevel(0.2F);
	}
	
	@Override
	public ItemStack getStack(IBlockState state) {
		return new ItemStack(ItemHandler.MAGMA_STAIRS, 1, this.color.getMetadata());
	}

	@Override
	public boolean isCorrectStairs(ItemStack stack) {
		return stack.getMetadata() == this.color.getMetadata();
	}

	@Override
	public String getDisplayName(ItemStack stack) {
		return TranslationHelper.translateAll("tile.magma_concrete_stairs.name", "color." + EnumDyeColor.byMetadata(stack.getMetadata()).getName());
	}

}
