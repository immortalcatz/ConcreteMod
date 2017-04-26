package fr.dbrown55.concrete.items;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemConcreteBugDecoration extends Item {

	public ItemConcreteBugDecoration() {
		super();
		this.setRegistryName("concrete", "bugDecoration");
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

}
