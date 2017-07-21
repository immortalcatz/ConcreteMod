package fr.dbrown55.concrete.items;

import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcretePowderBase;
import fr.dbrown55.concrete.entities.EntityConcreteBug;
import fr.dbrown55.utilmod.helpers.SideHelper;
import fr.dbrown55.utilmod.objects.DbrownItemBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemBlockConcretePowder extends DbrownItemBlock {

	private ConcretePowderBase base;
	
	public ItemBlockConcretePowder(ConcretePowderBase block) {
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
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = playerIn.getHeldItem(hand);
		if(this.base == BlockHandler.VANILLA_POWDER && worldIn.getBlockState(pos).getBlock() == Blocks.MONSTER_EGG && !worldIn.isRemote) {
			worldIn.setBlockToAir(pos);
			EntityConcreteBug bug = new EntityConcreteBug(worldIn);
			bug.setPositionAndRotation(pos.getX(), pos.getY(), pos.getZ(), (float) (MathHelper.clamp(MathHelper.floor((double)(playerIn.rotationYaw * 4.0F / 360.0F) + 0.5D) - 180.0, 0.0, 360.0)), 0.0f);
			bug.setColor(EnumDyeColor.byMetadata(stack.getMetadata()).getMapColor().colorValue);
			worldIn.spawnEntity(bug);
			return EnumActionResult.SUCCESS;
		}
		return super.onItemUse(playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
	
}
