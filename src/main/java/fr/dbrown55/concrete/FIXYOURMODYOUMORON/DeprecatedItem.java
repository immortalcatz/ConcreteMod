package fr.dbrown55.concrete.FIXYOURMODYOUMORON;

import java.util.HashMap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DeprecatedItem extends Item {

	private HashMap<Integer, ItemStack> toSwitch;
	
	public DeprecatedItem(HashMap<Integer, ItemStack> toSwitch) {
		this.setUnlocalizedName("deprecatedItem");
		this.toSwitch = toSwitch;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(!worldIn.isRemote && entityIn instanceof EntityPlayer && !((EntityPlayer)entityIn).capabilities.isCreativeMode){
			InventoryPlayer plinv = ((EntityPlayer)entityIn).inventory;
			ItemStack is = this.getNewStack(stack.getMetadata()).copy();
			is.stackSize = stack.stackSize;
			plinv.setInventorySlotContents(itemSlot, is);
		}
	}
	
	@Override
	public boolean onEntityItemUpdate(EntityItem ei) {
		if(!ei.worldObj.isRemote){
			ItemStack is = this.getNewStack(ei.getEntityItem().getMetadata()).copy();
			is.stackSize = ei.getEntityItem().stackSize;
			ei.setEntityItemStack(is);
		}
		return false;
	}
	
	public ItemStack getNewStack(int fromMeta){
		if(this.toSwitch.containsKey(fromMeta)){
			return this.toSwitch.get(fromMeta);
		} else if(this.toSwitch.containsKey(-1)){
			return this.toSwitch.get(-1);
		}
		return new ItemStack(Blocks.AIR);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
	
	// The texture looks like this:
	
	/////////////////
	//             //
	// @Deprecated //
	// this        // <-- the "this" is struck through
	// //TODO: fix //
	//             //
	/////////////////

}
