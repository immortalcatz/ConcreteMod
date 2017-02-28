package fr.dbrown55.concrete.tabs;

import java.util.List;

import fr.dbrown55.concrete.items.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ConcreteCreativeTab extends CreativeTabs {

	private static long lastTimeChangedIcon = System.currentTimeMillis();
	private static Item[] itemsToDisplay;
	private static Item toDisplay;
	private static int toDisplayIndex = 0;
	
	public ConcreteCreativeTab() {
		super("concreteTab");
	}

	@Override
	public Item getTabIconItem() {
		return ItemRegistry.getPowderFromDye(EnumDyeColor.WHITE);
	}
	
	@Override
	public void displayAllRelevantItems(List<ItemStack> list) {
		for(int solid = 0; solid <= 1; solid++){
			for(int i = 0; i < 16; i++){
				list.add(new ItemStack(solid == 0 ? ItemRegistry.getPowderFromDye(EnumDyeColor.byDyeDamage(15 - i)) : ItemRegistry.getSolidFromDye(EnumDyeColor.byDyeDamage(15 - i))));
			}
		}
	}
}
