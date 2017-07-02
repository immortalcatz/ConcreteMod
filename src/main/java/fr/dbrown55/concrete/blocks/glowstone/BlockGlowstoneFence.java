package fr.dbrown55.concrete.blocks.glowstone;

import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteFenceBase;
import fr.dbrown55.utilmod.helpers.TranslationHelper;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class BlockGlowstoneFence extends ConcreteFenceBase {

	public BlockGlowstoneFence() {
		super(new ResourceLocation("concrete", "glowcrete_fence"));
		this.setLightLevel(1.0F);
	}

	@Override
	public String getDisplayName(ItemStack stack) {
		return TranslationHelper.translateAll("tile.glowstone_concrete_fence.name", "color." + EnumDyeColor.byMetadata(stack.getMetadata()).getName());
	}

}
