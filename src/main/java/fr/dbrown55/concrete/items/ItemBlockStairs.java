package fr.dbrown55.concrete.items;

import javax.annotation.Nullable;

import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteStairsBase;
import fr.dbrown55.utilmod.helpers.SideHelper;
import fr.dbrown55.utilmod.objects.DbrownItem;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBlockStairs extends DbrownItem {

	private ConcreteStairsBase[] blockList;
	
	public ItemBlockStairs(ConcreteStairsBase[] blocks, ResourceLocation name) {
		super(name);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.blockList = blocks;
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = playerIn.getHeldItem(hand);
		IBlockState iblockstate = worldIn.getBlockState(pos);
		Block block = iblockstate.getBlock();

		if(!block.isReplaceable(worldIn, pos)) {
			pos = pos.offset(facing);
		}
		
		Block toPlace = null;
		for(ConcreteStairsBase placable : this.blockList) {
			if(placable.isCorrectStairs(stack)) {
				toPlace = placable;
			}
		}

		if(toPlace != null && stack.getCount() != 0 && playerIn.canPlayerEdit(pos, facing, stack) && worldIn.mayPlace(toPlace, pos, false, facing, (Entity)null)) {
			int i = this.getMetadata(stack.getMetadata());
			IBlockState iblockstate1 = toPlace.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, i, playerIn);

			if(placeBlockAt(stack, playerIn, worldIn, pos, facing, hitX, hitY, hitZ, iblockstate1)) {
				SoundType soundtype = worldIn.getBlockState(pos).getBlock().getSoundType(worldIn.getBlockState(pos), worldIn, pos, playerIn);
				worldIn.playSound(playerIn, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
				stack.setCount(stack.getCount() - 1);
			}

			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.FAIL;
		}
	}
	
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState) {
		if(!world.setBlockState(pos, newState, 3)) {
			return false;
		}
		
		ConcreteStairsBase toPlace = null;
		for(ConcreteStairsBase placable : this.blockList) {
			if(placable.isCorrectStairs(stack)) {
				toPlace = placable;
			}
		}
		
		IBlockState state = world.getBlockState(pos);
		if(state.getBlock() == toPlace) {
			setTileEntityNBT(world, player, pos, stack);
			toPlace.onBlockPlacedBy(world, pos, state, player, stack);
		}

		return true;
	}

	public static boolean setTileEntityNBT(World worldIn, @Nullable EntityPlayer player, BlockPos pos, ItemStack stackIn) {
		MinecraftServer minecraftserver = worldIn.getMinecraftServer();

		if(minecraftserver == null) {
			return false;
		} else {
			if(stackIn.hasTagCompound() && stackIn.getTagCompound().hasKey("BlockEntityTag", 10)) {
				TileEntity tileentity = worldIn.getTileEntity(pos);

				if(tileentity != null) {
					if(!worldIn.isRemote && tileentity.onlyOpsCanSetNbt() && (player == null || !player.canUseCommandBlock())) {
						return false;
					}

					NBTTagCompound nbttagcompound = tileentity.writeToNBT(new NBTTagCompound());
					NBTTagCompound nbttagcompound1 = nbttagcompound.copy();
					NBTTagCompound nbttagcompound2 = (NBTTagCompound)stackIn.getTagCompound().getTag("BlockEntityTag");
					nbttagcompound.merge(nbttagcompound2);
					nbttagcompound.setInteger("x", pos.getX());
					nbttagcompound.setInteger("y", pos.getY());
					nbttagcompound.setInteger("z", pos.getZ());

					if(!nbttagcompound.equals(nbttagcompound1)) {
						tileentity.readFromNBT(nbttagcompound);
						tileentity.markDirty();
						return true;
					}
				}
			}

			return false;
		}
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		ConcreteStairsBase toPlace = null;
		for(ConcreteStairsBase placable : this.blockList) {
			if(placable.isCorrectStairs(stack)) {
				toPlace = placable;
			}
		}
		if(SideHelper.isClient() && toPlace != null) {
			return toPlace.getDisplayName(stack);
		}
		return super.getItemStackDisplayName(stack);
	}
	
}
