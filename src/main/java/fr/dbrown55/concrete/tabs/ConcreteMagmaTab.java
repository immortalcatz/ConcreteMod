package fr.dbrown55.concrete.tabs;

import java.util.List;

import fr.dbrown55.concrete.items.ItemHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ConcreteMagmaTab extends CreativeTabs {

	public ConcreteMagmaTab() {
		super("concreteTab.magma");
	}

	@Override
	public Item getTabIconItem() {
		return null;
	}
	
	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(ItemHandler.MAGMA_POWDER, 1, 0);
	}
	
	@Override
	public void displayAllRelevantItems(List<ItemStack> list) {
		for(int i = 0; i < 7; i++) {
			Item item = ItemHandler.MAGMA_POWDER;
			if(i == 1) {
				item = ItemHandler.MAGMA_SOLID;
			} else if(i == 2) {
				item = ItemHandler.MAGMA_STAIRS;
			} else if(i == 3) {
				item = ItemHandler.MAGMA_SLAB;
			} else if(i == 4) {
				item = ItemHandler.MAGMA_FENCE;
			} else if(i == 5) {
				item = ItemHandler.MAGMA_GATE;
			} else if(i == 6) {
				item = ItemHandler.MAGMA_STICK;
			}
			for(EnumDyeColor color : EnumDyeColor.values()) {
				list.add(new ItemStack(item, 1, color.getMetadata()));
			}
		}
	}

}
