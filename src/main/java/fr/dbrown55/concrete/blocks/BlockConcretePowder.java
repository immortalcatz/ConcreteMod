package fr.dbrown55.concrete.blocks;

import fr.dbrown55.concrete.Main;
import fr.dbrown55.concrete.enums.EnumConcreteType;
import fr.dbrown55.concrete.tabs.ConcreteCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockConcretePowder extends BlockFalling {
	
	private EnumConcreteType type;
	
	public BlockConcretePowder(EnumConcreteType type) {
		super(Material.SAND);
		this.type = type;
		this.setCreativeTab(ConcreteCreativeTab.instance());
		this.setRegistryName(this.type.getResourceLocation(true));
		this.setUnlocalizedName(this.type.getName(true));
		this.setHardness(0.5F);
		this.setSoundType(SoundType.SAND);
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if(!Main.vanillaBehavior){
			if( worldIn.getBlockState(pos.north()).getMaterial() == Material.WATER
			|| worldIn.getBlockState(pos.south()).getMaterial() == Material.WATER
			|| worldIn.getBlockState(pos.east()).getMaterial() == Material.WATER
			|| worldIn.getBlockState(pos.west()).getMaterial() == Material.WATER
			|| worldIn.getBlockState(pos.up()).getMaterial() == Material.WATER
			|| worldIn.getBlockState(pos.down()).getMaterial() == Material.WATER ){
				worldIn.setBlockState(pos, this.getSolidState(state));
				return;
			}
		}
		super.onBlockAdded(worldIn, pos, state);
	}
	
	@Override
	public int tickRate(World worldIn) {
		return 2;
	}
	
	private void checkFallable(World worldIn, BlockPos pos) {
		System.out.println("Called");
		if((worldIn.isAirBlock(pos.down()) || canFallThrough(worldIn.getBlockState(pos.down()))) && pos.getY() >= 0) {
			int i = 32;

			if(!fallInstantly && worldIn.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32))) {
				if(!worldIn.isRemote) {
					EntityFallingBlock entityfallingblock = new EntityFallingBlock(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, worldIn.getBlockState(pos));
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
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
		if(!Main.vanillaBehavior){
			if( worldIn.getBlockState(pos.north()).getMaterial() == Material.WATER
			|| worldIn.getBlockState(pos.south()).getMaterial() == Material.WATER
			|| worldIn.getBlockState(pos.east()).getMaterial() == Material.WATER
			|| worldIn.getBlockState(pos.west()).getMaterial() == Material.WATER
			|| worldIn.getBlockState(pos.up()).getMaterial() == Material.WATER
			|| worldIn.getBlockState(pos.down()).getMaterial() == Material.WATER ){
				worldIn.setBlockState(pos, this.getSolidState(state));
				return;
			}
		}
		super.neighborChanged(state, worldIn, pos, blockIn);
	}

	public abstract IBlockState getSolidState(IBlockState original);

}
