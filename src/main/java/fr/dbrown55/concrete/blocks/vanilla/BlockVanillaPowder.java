package fr.dbrown55.concrete.blocks.vanilla;

import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcretePowderBase;
import fr.dbrown55.utilmod.helpers.BlockStateHelper;
import fr.dbrown55.utilmod.helpers.TranslationHelper;
import net.minecraft.block.BlockColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

public class BlockVanillaPowder extends ConcretePowderBase {

	public BlockVanillaPowder() {
		super(new ResourceLocation("minecraft", "concrete_powder"));
	}

	@Override
	public IBlockState getSolidState(IBlockState original) {
		return BlockStateHelper.copyProperties(original, BlockHandler.VANILLA_SOLID, BlockColored.COLOR);
	}

	@Override
	public String getDisplayName(ItemStack stack) {
		return TranslationHelper.translateAll("tile.concrete_powder.name", "color." + EnumDyeColor.byMetadata(stack.getMetadata()).getName());
	}

}
