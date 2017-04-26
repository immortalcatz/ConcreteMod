package fr.dbrown55.concrete.items.colored;

import fr.dbrown55.concrete.Main;
import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.entities.EntityConcreteBug;
import fr.dbrown55.concrete.items.ItemBlockConcretePowder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class IBColoredConcretePowder extends ItemBlockConcretePowder {

	public IBColoredConcretePowder() {
		super(BlockHandler.coloredP);
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		if(FMLCommonHandler.instance().getSide() == Side.SERVER){
			return "tile.concrete_powder." + EnumDyeColor.byMetadata(stack.getMetadata()) + ".name";
		}
		return I18n.translateToLocalFormatted("tile.concrete_powder.name", I18n.translateToLocal("color." + EnumDyeColor.byMetadata(stack.getMetadata())));
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote && worldIn.getBlockState(pos).getBlock() == Blocks.MONSTER_EGG && !playerIn.isSneaking() && Main.AprilFools){
			worldIn.setBlockToAir(pos);
			EntityConcreteBug bug = new EntityConcreteBug(worldIn, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, (float) playerIn.rotationYawHead - 180.0F);
			bug.setColor(EnumDyeColor.byMetadata(stack.getMetadata()).getMapColor().colorValue);
			worldIn.spawnEntityInWorld(bug);
			stack.stackSize --;
			return EnumActionResult.SUCCESS;
		}
		return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		// TODO Auto-generated method stub
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}
	
}
