package fr.dbrown55.concrete.tabs;

import java.util.List;

import fr.dbrown55.concrete.items.ItemHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ConcreteRedstoneTab extends CreativeTabs {

	public ConcreteRedstoneTab() {
		super("concreteTab.redstone");
	}

	@Override
	public Item getTabIconItem() {
		return null;
	}
	
	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(ItemHandler.REDSTONE_POWDER, 1, 0);
	}
	
	@Override
	public void displayAllRelevantItems(List<ItemStack> list) {
		for(int i = 0; i < 4; i++) {
			Item item = ItemHandler.REDSTONE_POWDER;
			if(i == 1) {
				item = ItemHandler.REDSTONE_SOLID;
			} else if(i == 2) {
				item = ItemHandler.REDSTONE_STAIRS;
			} else if(i == 3) {
				item = ItemHandler.REDSTONE_SLAB;
			}
			for(EnumDyeColor color : EnumDyeColor.values()) {
				list.add(new ItemStack(item, 1, color.getMetadata()));
			}
		}
	}

}
