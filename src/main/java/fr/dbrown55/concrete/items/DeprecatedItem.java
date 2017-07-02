package fr.dbrown55.concrete.items;

import fr.dbrown55.utilmod.helpers.SideHelper;
import fr.dbrown55.utilmod.objects.DbrownItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class DeprecatedItem extends DbrownItem {

	private ItemStack transforms;
	
	public DeprecatedItem(ResourceLocation name, ItemStack transforms) {
		super(name);
		this.transforms = transforms;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(entityIn instanceof EntityPlayer){
			EntityPlayer pl = (EntityPlayer)entityIn;
			ItemStack copy = this.transforms.copy();
			copy.stackSize = stack.stackSize;
			pl.inventory.setInventorySlotContents(itemSlot, copy);
		}
	}
	
	@Override
	public boolean onEntityItemUpdate(EntityItem entityItem) {
		ItemStack copy = this.transforms.copy();
		copy.stackSize = entityItem.getEntityItem().stackSize;
		entityItem.setEntityItemStack(copy);
		return false; // Won't update after because different item
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		if(SideHelper.isClient()){
			return I18n.translateToLocal("item.deprecatedItem.name");
		}
		return super.getItemStackDisplayName(stack);
	}

}
