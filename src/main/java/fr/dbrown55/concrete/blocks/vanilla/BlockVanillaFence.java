package fr.dbrown55.concrete.blocks.vanilla;

import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteFenceBase;
import fr.dbrown55.utilmod.helpers.TranslationHelper;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;

public class BlockVanillaFence extends ConcreteFenceBase {

	public BlockVanillaFence() {
		super(new ResourceLocation("concrete", "vanilla_fence"));
	}

	@Override
	public String getDisplayName(ItemStack stack) {
		return TranslationHelper.translateAll("tile.vanilla_fence.name", "color." + EnumDyeColor.byMetadata(stack.getMetadata()).getName());
	}

}
