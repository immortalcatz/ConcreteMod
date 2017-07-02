package fr.dbrown55.concrete.blocks.concreteBaseClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.dbrown55.concrete.Main;
import fr.dbrown55.utilmod.helpers.LiquidHelper;
import fr.dbrown55.utilmod.objects.DbrownBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ConcretePowderBase extends DbrownBlock {
	
	public static boolean fallInstantly;
	
	public ConcretePowderBase(ResourceLocation name) {
		super(Material.SAND, MapColor.SNOW, name);
		this.setSoundType(SoundType.SAND);
		this.setHardness(1.5f);
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
	
	// I give a delay to be sure that if you have April fools mode on and you place water and lava next to
	// concrete it will flash one then the other, back and forth.
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn)); 
	}
	
	@Override
	public void neighborChangedFuture(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos neighbor) {
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		IBlockState stateSolid = this.getSolidState(state);
		if(!worldIn.isRemote && stateSolid != null){
			if(!this.checkFallable(worldIn, pos)){
				EnumFacing[] whiteList = EnumFacing.values();
				if(Main.config.usesVanillaHehavior()){
					whiteList = new EnumFacing[] {EnumFacing.DOWN};
				}
				if(LiquidHelper.isTouchingWater(worldIn, pos, whiteList)){
					BlockPos newPos = new BlockPos(pos);
					if(Main.config.usesVanillaHehavior()){
						newPos = newPos.down();
					}
					worldIn.setBlockState(newPos, stateSolid);
				}
			}
		}
	}
	
	@Override
	public int tickRate(World worldIn) {
		return 2;
	}
	
	private boolean checkFallable(World worldIn, BlockPos pos) { // Returns true if did fall
		boolean falling = false;
		if((worldIn.isAirBlock(pos.down()) || canFallThrough(worldIn.getBlockState(pos.down()))) && pos.getY() >= 0) {
			int i = 32;

			if(!fallInstantly && worldIn.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32))) {
				if(!worldIn.isRemote) {
					EntityFallingBlock entityfallingblock = new EntityFallingBlock(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, worldIn.getBlockState(pos));
					this.onStartFalling(entityfallingblock);
					worldIn.spawnEntityInWorld(entityfallingblock);
					falling = true;
				}
			} else {
				IBlockState state = worldIn.getBlockState(pos);
				worldIn.setBlockToAir(pos);
				BlockPos blockpos;

				for(blockpos = pos.down(); (worldIn.isAirBlock(blockpos) || canFallThrough(worldIn.getBlockState(blockpos))) && blockpos.getY() > 0; blockpos = blockpos.down()) {
					;
				}

				if(blockpos.getY() > 0) {
					worldIn.setBlockState(blockpos.up(), state); //Forge: Fix loss of state information during world gen.
				}
				falling = true;
			}
		}
		return falling;
	}

	public abstract IBlockState getSolidState(IBlockState original);

	protected void onStartFalling(EntityFallingBlock fallingEntity) {}
	
	public static boolean canFallThrough(IBlockState state) {
		Block block = state.getBlock();
		Material material = state.getMaterial();
		return block == Blocks.FIRE || material == Material.AIR || material == Material.WATER || material == Material.LAVA;
	}
	
	/**
	 * Used to get the display name of the stack
	 * @return name - The stack display name
	 */
	@SideOnly(Side.CLIENT)
	public abstract String getDisplayName(ItemStack stack);
	
}
