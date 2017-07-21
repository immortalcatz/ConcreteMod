package fr.dbrown55.concrete.blocks.vanilla;

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
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVanillaStairs extends ConcreteStairsBase {

	private EnumDyeColor color;
	
	public BlockVanillaStairs(EnumDyeColor color) {
		super(BlockHandler.VANILLA_SOLID.getDefaultState().withProperty(BlockColored.COLOR, color), new ResourceLocation("concrete", "vanilla_stairs_" + color.getName()));
		this.color = color;
	}

	@Override
	public ItemStack getStack(IBlockState state) {
		return new ItemStack(ItemHandler.VANILLA_STAIRS, 1, this.color.getMetadata());
	}

	@Override
	public boolean isCorrectStairs(ItemStack stack) {
		return stack.getMetadata() == this.color.getMetadata();
	}

	@Override
	public String getDisplayName(ItemStack stack) {
		return TranslationHelper.translateAll("tile.vanilla_stairs.name", "color." + EnumDyeColor.byMetadata(stack.getMetadata()).getName());
	}

}
