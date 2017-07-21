package fr.dbrown55.concrete.blocks.concreteBaseClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.dbrown55.concrete.Main;
import fr.dbrown55.utilmod.helpers.LiquidHelper;
import fr.dbrown55.utilmod.objects.DbrownBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ConcreteBase extends DbrownBlock {

	public ConcreteBase(ResourceLocation name) {
		super(Material.ROCK, MapColor.SNOW, name);
		this.setHarvestLevel("pickaxe", 0);
		this.setHardness(1.5f);
	}
	
	@Override
	public MapColor getMapColor(IBlockState state) {
		return state.getValue(BlockColored.COLOR).getMapColor();
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, BlockColored.COLOR);
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
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if(Main.config.isAprilFools()){
			worldIn.scheduleBlockUpdate(pos, this, this.tickRate(worldIn), -1);
		}
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos neighbor) {
		if(Main.config.isAprilFools()){
			worldIn.scheduleBlockUpdate(pos, this, this.tickRate(worldIn), -1);
		}
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		IBlockState statePowder = this.getPowderState(state);
		if(!worldIn.isRemote && statePowder != null){
			EnumFacing[] whiteList = EnumFacing.values();
			if(Main.config.usesVanillaHehavior()){
				whiteList = new EnumFacing[] {EnumFacing.DOWN};
			}
			if(LiquidHelper.isTouchingLava(worldIn, pos, whiteList)){
				BlockPos newPos = new BlockPos(pos);
				if(Main.config.usesVanillaHehavior()){
					newPos = newPos.down();
				}
				worldIn.setBlockState(newPos, statePowder);
			}
		}
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
	
	/** Used when AprilFools mode is on */
	public abstract IBlockState getPowderState(IBlockState original);
	
	//Block
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		ItemStack stack = placer.getHeldItem(hand);
		return this.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.byMetadata(stack.getItemDamage()));
	}
	
	/**
	 * Used to get the display name of the stack
	 * @return name - The stack display name
	 */
	@SideOnly(Side.CLIENT)
	public abstract String getDisplayName(ItemStack stack);
	
}
