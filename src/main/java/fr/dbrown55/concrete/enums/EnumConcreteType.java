package fr.dbrown55.concrete.enums;

import fr.dbrown55.concrete.items.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public enum EnumConcreteType {
	COLORED("minecraft", "concrete", ItemHandler.coloredP, new ItemStack(Items.DYE, 1, 15), new ItemStack(Items.DYE, 1, 14), new ItemStack(Items.DYE, 1, 13), new ItemStack(Items.DYE, 1, 12), new ItemStack(Items.DYE, 1, 11), new ItemStack(Items.DYE, 1, 10), new ItemStack(Items.DYE, 1, 9), new ItemStack(Items.DYE, 1, 8), new ItemStack(Items.DYE, 1, 7), new ItemStack(Items.DYE, 1, 6), new ItemStack(Items.DYE, 1, 5), new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 3), new ItemStack(Items.DYE, 1, 2), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 0)),
	MAGMA("concrete", "magma_concrete", ItemHandler.magmaP, new ItemStack(Items.MAGMA_CREAM)),
	GLOWSTONE("concrete", "glowcrete", ItemHandler.glowP, new ItemStack(Items.GLOWSTONE_DUST));
	
	private String modid, name;
	private Item result;
	private ItemStack[] toCraft; // Beside sand and gravel ofc
	
	EnumConcreteType(String modid, String name, Item result, ItemStack... toCraft){
		this.modid = modid;
		this.name = name;
		this.result = result;
		this.toCraft = toCraft;
	}
	
	public ResourceLocation getResourceLocation(boolean powder){
		return new ResourceLocation(this.modid, this.getName(powder));
	}
	
	public String getName(boolean powder){
		return this.name + (powder ? "_powder" : "");
	}
	
	public Item getResult(){
		return this.result;
	}
	
	public ItemStack[] getCraftStacks(){
		return this.toCraft;
	}
}
