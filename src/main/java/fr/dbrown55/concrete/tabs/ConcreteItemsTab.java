package fr.dbrown55.concrete.tabs;

import java.util.List;

import fr.dbrown55.concrete.items.ItemHandler;
import fr.dbrown55.utilmod.helpers.TranslationHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;

public class ConcreteItemsTab extends CreativeTabs {

	public ConcreteItemsTab() {
		super("concreteTab.items");
	}
	
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ItemHandler.BRUSH);
	}
	
	@Override
	public void displayAllRelevantItems(NonNullList<ItemStack> list) {
		for(EnumDyeColor dye : EnumDyeColor.values()) {
			ItemStack brush = new ItemStack(ItemHandler.BRUSH);
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setInteger("color", dye.getMapColor().colorValue);
			brush.setTagCompound(nbt);
			brush.setStackDisplayName(TranslationHelper.translateAll("item.brush.preset_color.name", "color." + dye.getName()));
			list.add(brush);
		}
		list.add(new ItemStack(ItemHandler.PALETTE));
		list.add(new ItemStack(ItemHandler.ERASER));
		for(EnumDyeColor dye : EnumDyeColor.values()) {
			ItemStack bug = new ItemStack(ItemHandler.CONCRETE_BUG);
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setInteger("color", dye.getMapColor().colorValue);
			bug.setTagCompound(nbt);
			bug.setStackDisplayName(TranslationHelper.translateAll("item.bugDecoration.preset_color.name", "color." + dye.getName()));
			list.add(bug);
		}
	}
	
}
