package fr.dbrown55.concrete.items;

import fr.dbrown55.utilmod.objects.DbrownItem;
import net.minecraft.util.ResourceLocation;

public class ItemPalette extends DbrownItem {

	public ItemPalette() {
		super(new ResourceLocation("concrete", "palette"));
		this.setMaxStackSize(1);
	}
	
}
