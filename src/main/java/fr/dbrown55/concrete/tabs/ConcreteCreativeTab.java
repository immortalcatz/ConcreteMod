package fr.dbrown55.concrete.tabs;

import java.util.List;

import fr.dbrown55.concrete.items.ItemHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ConcreteCreativeTab extends CreativeTabs {
	
	private static ConcreteCreativeTab INSTANCE = new ConcreteCreativeTab();
	
	public ConcreteCreativeTab() {
		super("concreteTab");
	}
	
	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(ItemHandler.coloredP, 1, 0);
	}
	
	@Override
	public void displayAllRelevantItems(List<ItemStack> list) {
		for(int i = 0; i < 32; i++){
			list.add(new ItemStack(i < 16 ? ItemHandler.coloredP : ItemHandler.coloredS, 1, i < 16 ? i : i - 16));
		}
		
		list.add(new ItemStack(ItemHandler.magmaP));
		list.add(new ItemStack(ItemHandler.magmaS));
		
		list.add(new ItemStack(ItemHandler.glowP));
		list.add(new ItemStack(ItemHandler.glowS));
		
		list.add(new ItemStack(ItemHandler.brush));
		list.add(new ItemStack(ItemHandler.palette));
		list.add(new ItemStack(ItemHandler.eraser));
	}
	
	public static ConcreteCreativeTab instance(){
		return INSTANCE;
	}

	@Override
	public Item getTabIconItem() {
		return ItemHandler.coloredP;
	}
}