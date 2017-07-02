package fr.dbrown55.concrete.items;

import fr.dbrown55.concrete.Main;
import fr.dbrown55.concrete.client.SoundHandler;
import fr.dbrown55.concrete.entities.EntityConcreteBug;
import fr.dbrown55.concrete.net.MessageOpenGui;
import fr.dbrown55.concrete.net.MessageOpenGui.GuiEnum;
import net.minecraft.block.material.MapColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemBrush extends Item {

	public ItemBrush(){
		this.setRegistryName("concrete", "brush");
		this.setUnlocalizedName("brush");
		this.setMaxStackSize(1);
		this.setMaxDamage(50);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(!stack.hasTagCompound()){
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setInteger("color", MapColor.SNOW.colorValue);
			stack.setTagCompound(nbt);
		}
	}
	
	public void setPaintColor(ItemStack stack, int color){
		if(!stack.hasTagCompound()){
			NBTTagCompound nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
		}
		stack.getTagCompound().setInteger("color", color);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(hand == EnumHand.MAIN_HAND && playerIn.isSneaking()){
			if(playerIn.getHeldItemOffhand() == null){
				playerIn.inventory.armorInventory[3] = itemStackIn.copy();
				itemStackIn.stackSize = 0;
			} else if(playerIn.getHeldItemOffhand().getItem() == ItemHandler.PALETTE && !worldIn.isRemote){
				Main.wrapper.sendTo(new MessageOpenGui(GuiEnum.PAINT), (EntityPlayerMP) playerIn);
			}
			return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
		}
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		if(hand == EnumHand.MAIN_HAND && target instanceof EntityConcreteBug){
			((EntityConcreteBug)target).addColor(stack.getTagCompound().getInteger("color"));
			stack.damageItem(1, playerIn);
			playerIn.worldObj.playSound(target.posX, target.posY, target.posZ, SoundHandler.PAINT, SoundCategory.PLAYERS, 1.0F, 1.0F, false);
			return true;
		}
		return super.itemInteractionForEntity(stack, playerIn, target, hand);
	}
	
}