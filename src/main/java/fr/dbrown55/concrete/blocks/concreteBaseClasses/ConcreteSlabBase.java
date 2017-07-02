package fr.dbrown55.concrete.blocks.concreteBaseClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.dbrown55.utilmod.objects.DbrownBlock;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ConcreteSlabBase extends DbrownBlock {

	private static AxisAlignedBB LOWER = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5, 1.0);
	private static AxisAlignedBB UPPER = new AxisAlignedBB(0.0, 0.5, 0.0, 1.0, 1.0, 1.0);
	
	private EnumSlabPartType type;
	
	public ConcreteSlabBase(EnumSlabPartType type, ResourceLocation name) {
		super(Material.ROCK, MapColor.SNOW, name);
		this.setDefaultState(this.blockState.getBaseState().withProperty(BlockColored.COLOR, EnumDyeColor.WHITE));
		this.setHarvestLevel("pickaxe", 0);
		this.setHardness(1.5f);
		this.type = type;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, BlockColored.COLOR);
	}
	
	@Override
	public MapColor getMapColor(IBlockState state) {
		return state.getValue(BlockColored.COLOR).getMapColor();
	}
	
	public boolean isDouble(){
		return this.type == EnumSlabPartType.FULL_BLOCK;
	}
	
	public abstract IBlockState getUpperState(IBlockState lower);
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		EnumDyeColor color = EnumDyeColor.byMetadata(stack.getItemDamage());
		worldIn.setBlockState(pos, state.withProperty(BlockColored.COLOR, color));
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.byMetadata(meta));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(BlockColored.COLOR).getMetadata();
	}
	
	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		return this.isDouble() ? 2 : 1;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return this.type == EnumSlabPartType.LOWER_HALF ? LOWER : (this.type == EnumSlabPartType.UPPER_HALF ? UPPER : FULL_BLOCK_AABB);
	}
	
	public boolean isFullCube(IBlockState state) {
		return this.isDouble();
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return this.isDouble();
	}
	
	@Override
	public boolean isFullyOpaque(IBlockState state) {
		return this.isDouble();
	}
	
	@Override
	public boolean isVisuallyOpaque() {
		return this.isDouble();
	}

	public boolean isOpaqueCube(IBlockState state) {
		return this.isDouble();
    }
	
	public abstract ItemStack getStack(IBlockState state, boolean pickBlock);
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		ArrayList<ItemStack> drop = new ArrayList<ItemStack>();
		drop.add(this.getStack(state, false));
		return drop;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return this.getStack(state, true);
	}
	
	/**
	 * Used to get the display name of the stack
	 * @return name - The stack display name
	 */
	@SideOnly(Side.CLIENT)
	public abstract String getDisplayName(ItemStack stack);
	
	public static enum EnumSlabPartType implements IStringSerializable {
		LOWER_HALF("lower"),
		UPPER_HALF("upper"),
		FULL_BLOCK("double");
		
		private String name;
		
		EnumSlabPartType(String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return this.name;
		}
		
	}

}
