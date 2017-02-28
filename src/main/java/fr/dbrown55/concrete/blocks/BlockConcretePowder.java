package fr.dbrown55.concrete.blocks;

import java.util.Random;

import fr.dbrown55.concrete.entities.EntityFallingConcrete;
import fr.dbrown55.concrete.tabs.CreativeTabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockConcretePowder extends BlockFalling {

	private EnumDyeColor color;
	protected final MapColor blockMapColor;
	
	public BlockConcretePowder(EnumDyeColor color) {
		this.color = color;
		this.blockMapColor = color.getMapColor();
		this.setRegistryName("concrete_powder_" + this.color.getName());
		this.setUnlocalizedName("concrete_powder_" + this.color);
		this.setSoundType(SoundType.SAND);
		this.setCreativeTab(CreativeTabRegistry.getTab());
		this.setHardness(0.5F);
	}
	
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if( worldIn.getBlockState(pos.north()).getMaterial() == Material.WATER
		|| worldIn.getBlockState(pos.south()).getMaterial() == Material.WATER
		|| worldIn.getBlockState(pos.east()).getMaterial() == Material.WATER
		|| worldIn.getBlockState(pos.west()).getMaterial() == Material.WATER
		|| worldIn.getBlockState(pos.up()).getMaterial() == Material.WATER
		|| worldIn.getBlockState(pos.down()).getMaterial() == Material.WATER ){
			worldIn.setBlockState(pos, BlockRegistry.getSolidFromDye(this.color).getDefaultState());
		} else {
			worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
		}
	}
	
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
		if( worldIn.getBlockState(pos.north()).getMaterial() == Material.WATER
		|| worldIn.getBlockState(pos.south()).getMaterial() == Material.WATER
		|| worldIn.getBlockState(pos.east()).getMaterial() == Material.WATER
		|| worldIn.getBlockState(pos.west()).getMaterial() == Material.WATER
		|| worldIn.getBlockState(pos.up()).getMaterial() == Material.WATER
		|| worldIn.getBlockState(pos.down()).getMaterial() == Material.WATER ){
			worldIn.setBlockState(pos, BlockRegistry.getSolidFromDye(this.color).getDefaultState());
		} else {
			worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
		}
	}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if(!worldIn.isRemote) {
			this.checkFallable(worldIn, pos);
		}
	}
	
	private void checkFallable(World worldIn, BlockPos pos) {
		if((worldIn.isAirBlock(pos.down()) || canFallThrough(worldIn.getBlockState(pos.down()))) && pos.getY() >= 0) {
			int i = 32;

			if(worldIn.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32))) {
				if(!worldIn.isRemote) {
					EntityFallingConcrete entityfallingblock = new EntityFallingConcrete(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, worldIn.getBlockState(pos));
					this.onStartFalling(entityfallingblock);
					worldIn.spawnEntityInWorld(entityfallingblock);
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
			}
		}
	}
	
	protected void onStartFalling(EntityFallingBlock fallingEntity){}
	
	public static boolean canFallThrough(IBlockState state) {
		Block block = state.getBlock();
		Material material = state.getMaterial();
		return block == Blocks.FIRE || material == Material.AIR || material == Material.WATER || material == Material.LAVA;
	}
	
	public void onEndFalling(World worldIn, BlockPos pos){}

	public EnumDyeColor getColor() {
		return this.color;
	}

}
