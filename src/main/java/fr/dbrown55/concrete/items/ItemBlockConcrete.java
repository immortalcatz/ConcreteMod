package fr.dbrown55.concrete.items;

import fr.dbrown55.concrete.Main;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteBase;
import fr.dbrown55.utilmod.helpers.SideHelper;
import fr.dbrown55.utilmod.objects.DbrownItemBlock;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

public class ItemBlockConcrete extends DbrownItemBlock {

	private ConcreteBase base;
	
	public ItemBlockConcrete(ConcreteBase block) {
		super(block);
		this.base = block;
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		if(SideHelper.isClient()) {
			return this.base.getDisplayName(stack);
		}
		return super.getItemStackDisplayName(stack);
	}

}
