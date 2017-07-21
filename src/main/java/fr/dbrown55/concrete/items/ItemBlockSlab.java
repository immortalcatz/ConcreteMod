package fr.dbrown55.concrete.items;

import java.util.HashMap;

import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteGateBase;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteSlabBase;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteSlabBase.EnumSlabPartType;
import fr.dbrown55.utilmod.helpers.SideHelper;
import fr.dbrown55.utilmod.objects.DbrownItemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBlockSlab extends DbrownItemBlock {

	private ConcreteSlabBase blockBaseLower, blockBaseUpper, blockBaseDouble;
	
	public ItemBlockSlab(ConcreteSlabBase blockBaseLower, ConcreteSlabBase blockBaseUpper, ConcreteSlabBase blockBaseDouble, ResourceLocation name) {
		super(blockBaseLower, name);
		this.hasSubtypes = true;
		this.blockBaseLower = blockBaseLower;
		this.blockBaseUpper = blockBaseUpper;
		this.blockBaseDouble = blockBaseDouble;
	}
	
	public ItemBlockSlab(HashMap<EnumSlabPartType, ConcreteSlabBase> blocksBase, ResourceLocation name) {
		this(blocksBase.get(EnumSlabPartType.LOWER_HALF), blocksBase.get(EnumSlabPartType.UPPER_HALF), blocksBase.get(EnumSlabPartType.FULL_BLOCK), name);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = playerIn.getHeldItem(hand);
		IBlockState state = worldIn.getBlockState(pos);
		Block b = state.getBlock();
		if(stack.getCount() != 0 && playerIn.canPlayerEdit(pos.offset(facing), facing, stack)) {
			if(b == this.blockBaseLower || b == this.blockBaseUpper) {
				if((b == this.blockBaseLower && facing != EnumFacing.UP) || (b == this.blockBaseUpper && facing == EnumFacing.DOWN)) {
					pos = pos.offset(facing);
				}
				EnumDyeColor col1 = EnumDyeColor.byMetadata(stack.getMetadata());
				EnumDyeColor col2 = state.getValue(BlockColored.COLOR);
				if(((facing == EnumFacing.UP && b == this.blockBaseLower) || (facing == EnumFacing.DOWN && b == this.blockBaseUpper)) && col1 == col2) {
					IBlockState state2 = this.blockBaseDouble.getDefaultState().withProperty(BlockColored.COLOR, col1);
					AxisAlignedBB aabb = state2.getCollisionBoundingBox(worldIn, pos);
					if(aabb != Block.NULL_AABB && worldIn.checkNoEntityCollision(aabb) && worldIn.setBlockState(pos, state2, 3)) {
						SoundType sound = this.blockBaseDouble.getSoundType(state2, worldIn, pos, playerIn);
						worldIn.playSound(playerIn, pos, sound.getPlaceSound(), SoundCategory.BLOCKS, (sound.getVolume() + 1.0F) / 2.0F, sound.getPitch() * 0.8F);
						stack.setCount(stack.getCount() - 1);
					}		
					return EnumActionResult.SUCCESS;
				}
			}
			return this.tryPlace(playerIn, stack, worldIn, pos) ? EnumActionResult.SUCCESS : super.onItemUse(playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
		} else {
			return EnumActionResult.FAIL;
		}
	}
	
	private boolean tryPlace(EntityPlayer player, ItemStack stack, World worldIn, BlockPos pos) {
		IBlockState state = worldIn.getBlockState(pos);
		Block b = state.getBlock();
		if(b == this.blockBaseLower || b == this.blockBaseUpper) {
			EnumDyeColor col1 = EnumDyeColor.byMetadata(stack.getMetadata());
			EnumDyeColor col2 = state.getValue(BlockColored.COLOR);
			if(col1 == col2) {
				IBlockState state2 = this.blockBaseDouble.getDefaultState().withProperty(BlockColored.COLOR, col1);
				AxisAlignedBB aabb = state2.getCollisionBoundingBox(worldIn, pos);
				if(aabb != Block.NULL_AABB && worldIn.checkNoEntityCollision(aabb) && worldIn.setBlockState(pos, state2, 3)) {
					SoundType sound = this.blockBaseDouble.getSoundType(state2, worldIn, pos, player);
					worldIn.playSound(player, pos, sound.getPlaceSound(), SoundCategory.BLOCKS, (sound.getVolume() + 1.0F) / 2.0F, sound.getPitch() * 0.8F);
					stack.setCount(stack.getCount() - 1);
				}
				return true;
			}
		}
		return false;
	}
	
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState) {
		if((side == EnumFacing.DOWN || (side != EnumFacing.UP && hitY >= 0.5)) && newState.getBlock() == this.blockBaseLower) {
			newState = this.blockBaseLower.getUpperState(newState);
		}
		
		if(!world.setBlockState(pos, newState, 3)) {
			return false;
		}

		IBlockState state = world.getBlockState(pos);
		Block b = state.getBlock();
		if(b == this.blockBaseLower || b == this.blockBaseUpper) {
			setTileEntityNBT(world, player, pos, stack);
			b.onBlockPlacedBy(world, pos, state, player, stack);
		}
		
		return true;
	}
	
	@Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack) {
		BlockPos originalPos = pos;
		IBlockState state = worldIn.getBlockState(pos);
		Block b = state.getBlock();
		EnumDyeColor col1 = EnumDyeColor.byMetadata(stack.getMetadata());
		if(b == this.blockBaseLower || b == this.blockBaseUpper) {
			EnumDyeColor col2 = state.getValue(BlockColored.COLOR);
			boolean flag = b == this.blockBaseUpper;
			if((side == EnumFacing.UP && !flag || side == EnumFacing.DOWN && flag) && col1 == col2) {
				return true;
			}
		}
		
		pos = pos.offset(side);
		IBlockState state2 = worldIn.getBlockState(pos);
		Block b2 = state2.getBlock();
		EnumDyeColor col3 = state2.getProperties().containsKey(BlockColored.COLOR) ? state2.getValue(BlockColored.COLOR) : null;
		return (b2 == this.blockBaseLower || b2 == this.blockBaseUpper) && col1 == col3 ? true : super.canPlaceBlockOnSide(worldIn, originalPos, side, player, stack);
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		if(SideHelper.isClient()) {
			return this.blockBaseLower.getDisplayName(stack);
		}
		return super.getItemStackDisplayName(stack);
	}

	
}
