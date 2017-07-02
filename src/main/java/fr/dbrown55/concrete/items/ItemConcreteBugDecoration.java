package fr.dbrown55.concrete.items;

import fr.dbrown55.concrete.entities.EntityConcreteBug;
import fr.dbrown55.utilmod.objects.DbrownItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemConcreteBugDecoration extends DbrownItem {

	public ItemConcreteBugDecoration() {
		super(new ResourceLocation("concrete", "bugDecoration"));
		this.setMaxStackSize(1);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(!stack.hasTagCompound()){
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setInteger("color", 0xFFFFFF);
			stack.setTagCompound(nbt);
		}
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(facing == EnumFacing.UP && !worldIn.isRemote) {
			System.out.println("yeahlol");
			EntityConcreteBug bug = new EntityConcreteBug(worldIn);
			bug.setPosition(pos.getX(), pos.getY() + 1.2, pos.getZ());
			bug.setColor(stack.hasTagCompound() && stack.getTagCompound().hasKey("color") ? stack.getTagCompound().getInteger("color") : 0xFFFFFF);
			bug.setSolid(true);
			worldIn.spawnEntityInWorld(bug);
			stack.stackSize --;
		}
		return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}

}
