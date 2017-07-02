package fr.dbrown55.concrete.blocks.concreteBaseClasses;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import fr.dbrown55.utilmod.objects.DbrownBlock;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ConcreteFenceBase extends BlockFence {
	
	public ConcreteFenceBase(ResourceLocation name) {
		super(Material.ROCK, MapColor.SNOW);
		this.setRegistryName(name);
		this.setUnlocalizedName(name.getResourcePath());
		this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)).withProperty(BlockColored.COLOR, EnumDyeColor.WHITE));
		this.setHarvestLevel("pickaxe", 0);
		this.setHardness(1.5f);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, BlockColored.COLOR, BlockFence.NORTH, BlockFence.SOUTH, BlockFence.EAST, BlockFence.WEST);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(BlockColored.COLOR).getMetadata();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.byMetadata(meta));
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(BlockColored.COLOR, EnumDyeColor.byMetadata(stack.getMetadata())));
	}
	
	@Override
	public MapColor getMapColor(IBlockState state) {
		return state.getValue(BlockColored.COLOR).getMapColor();
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		drops.add(new ItemStack(this, 1, state.getValue(BlockColored.COLOR).getMetadata()));
		return drops;
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(this, 1, state.getValue(BlockColored.COLOR).getMetadata());
	}
	
	/**
	 * Used to get the display name of the stack
	 * @return name - The stack display name
	 */
	@SideOnly(Side.CLIENT)
	public abstract String getDisplayName(ItemStack stack);
	
}
