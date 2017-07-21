package fr.dbrown55.concrete.blocks.vanilla;

import java.util.ArrayList;
import java.util.List;

import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteGateBase;
import fr.dbrown55.concrete.items.ItemHandler;
import fr.dbrown55.utilmod.helpers.TranslationHelper;
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

public class BlockVanillaGate extends ConcreteGateBase {
	
	private EnumDyeColor color;

	public BlockVanillaGate(EnumDyeColor color) {
		super(color, new ResourceLocation("concrete", "vanilla_gate_" + color.getName()));
		this.color = color;
	}

	@Override
	public ItemStack getStack(IBlockState state, boolean pickBlock) {
		return new ItemStack(ItemHandler.VANILLA_GATE, 1, this.color.getMetadata());
	}

	@Override
	public boolean isCorrectGate(ItemStack stack) {
		return stack.getMetadata() == this.color.getMetadata();
	}

	@Override
	public String getDisplayName(ItemStack stack) {
		return TranslationHelper.translateAll("tile.vanilla_gate.name", "color." + EnumDyeColor.byMetadata(stack.getMetadata()).getName());
	}

}
