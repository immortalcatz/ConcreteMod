package fr.dbrown55.concrete.blocks.vanilla;

import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteBase;
import fr.dbrown55.utilmod.helpers.BlockStateHelper;
import fr.dbrown55.utilmod.helpers.TranslationHelper;
import net.minecraft.block.BlockColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

public class BlockVanillaSolid extends ConcreteBase {

	public BlockVanillaSolid() {
		super(new ResourceLocation("minecraft", "concrete"));
	}

	@Override
	public IBlockState getPowderState(IBlockState original) {
		return BlockStateHelper.copyProperties(original, BlockHandler.VANILLA_POWDER, BlockColored.COLOR);
	}

	@Override
	public String getDisplayName(ItemStack stack) {
		return TranslationHelper.translateAll("tile.concrete.name", "color." + EnumDyeColor.byMetadata(stack.getMetadata()).getName());
	}

}
