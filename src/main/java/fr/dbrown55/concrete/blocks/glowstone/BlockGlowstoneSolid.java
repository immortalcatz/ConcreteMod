package fr.dbrown55.concrete.blocks.glowstone;

import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteBase;
import fr.dbrown55.utilmod.helpers.BlockStateHelper;
import fr.dbrown55.utilmod.helpers.TranslationHelper;
import net.minecraft.block.BlockColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class BlockGlowstoneSolid extends ConcreteBase {

	public BlockGlowstoneSolid() {
		super(new ResourceLocation("concrete", "glowcrete"));
		this.setLightLevel(1.0F);
	}

	@Override
	public IBlockState getPowderState(IBlockState original) {
		return BlockStateHelper.copyProperties(original, BlockHandler.GLOWSTONE_POWDER, BlockColored.COLOR);
	}

	@Override
	public String getDisplayName(ItemStack stack) {
		return TranslationHelper.translateAll("tile.glowstone_concrete.name", "color." + EnumDyeColor.byMetadata(stack.getMetadata()).getName());
	}

}
