package fr.dbrown55.concrete.tabs;

import fr.dbrown55.concrete.items.ItemHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ConcreteGlowstoneTab extends CreativeTabs {

	public ConcreteGlowstoneTab() {
		super("concreteTab.glowstone");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ItemHandler.GLOWSTONE_POWDER, 1, 0);
	}
	
	@Override
	public void displayAllRelevantItems(NonNullList<ItemStack> list) {
		for(int i = 0; i < 7; i++) {
			Item item = ItemHandler.GLOWSTONE_POWDER;
			if(i == 1) {
				item = ItemHandler.GLOWSTONE_SOLID;
			} else if(i == 2) {
				item = ItemHandler.GLOWSTONE_STAIRS;
			} else if(i == 3) {
				item = ItemHandler.GLOWSTONE_SLAB;
			} else if(i == 4) {
				item = ItemHandler.GLOWSTONE_FENCE;
			} else if(i == 5) {
				item = ItemHandler.GLOWSTONE_GATE;
			} else if(i == 6) {
				item = ItemHandler.GLOWSTONE_STICK;
			}
			for(EnumDyeColor color : EnumDyeColor.values()) {
				list.add(new ItemStack(item, 1, color.getMetadata()));
			}
		}
	}

}
