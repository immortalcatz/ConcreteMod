package fr.dbrown55.concrete.items;

import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteBase;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteFenceBase;
import fr.dbrown55.utilmod.helpers.SideHelper;
import fr.dbrown55.utilmod.objects.DbrownItemBlock;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

public class ItemBlockFence extends DbrownItemBlock {

	private ConcreteFenceBase base;
	
	public ItemBlockFence(ConcreteFenceBase block) {
		super(block);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.base = block;
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		if(SideHelper.isClient()) {
			return this.base.getDisplayName(stack);
		}
		return super.getItemStackDisplayName(stack);
	}
	
}
