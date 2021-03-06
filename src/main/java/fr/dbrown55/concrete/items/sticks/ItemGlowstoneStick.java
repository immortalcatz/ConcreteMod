package fr.dbrown55.concrete.items.sticks;

import fr.dbrown55.concrete.items.ConcreteStickBase;
import fr.dbrown55.utilmod.helpers.SideHelper;
import fr.dbrown55.utilmod.helpers.TranslationHelper;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemGlowstoneStick extends ConcreteStickBase {
	
	public ItemGlowstoneStick() {
		super(new ResourceLocation("concrete", "glowcrete_stick"));
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		if(SideHelper.isClient()) {
			return TranslationHelper.translateAll("item.glowstone_concrete_stick.name", "color." + EnumDyeColor.byMetadata(stack.getMetadata()).getName());
		}
		return super.getItemStackDisplayName(stack);
	}

}
