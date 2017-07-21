package fr.dbrown55.concrete.items;

import java.util.Random;

import fr.dbrown55.concrete.client.SoundHandler;
import fr.dbrown55.concrete.entities.EntityConcreteBug;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;

public class ItemEraser extends Item {

	public ItemEraser() {
		this.setRegistryName("concrete", "eraser");
		this.setUnlocalizedName("eraser");
		this.setMaxStackSize(1);
		this.setMaxDamage(50);
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		if(hand == EnumHand.MAIN_HAND && target instanceof EntityConcreteBug && ((EntityConcreteBug)target).getColor() != 0xFFFFFF){
			((EntityConcreteBug)target).setColor(0xFFFFFF);
			stack.damageItem(1, playerIn);
			playerIn.world.playSound(target.posX, target.posY, target.posZ, SoundHandler.ERASER, SoundCategory.PLAYERS, 1.0F, 1.0F, false);
			return true;
		}
		return super.itemInteractionForEntity(stack, playerIn, target, hand);
	}

}
