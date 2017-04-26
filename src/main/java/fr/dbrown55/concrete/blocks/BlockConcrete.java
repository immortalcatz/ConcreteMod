package fr.dbrown55.concrete.blocks;

import fr.dbrown55.concrete.Main;
import fr.dbrown55.concrete.enums.EnumConcreteType;
import fr.dbrown55.concrete.tabs.ConcreteCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockConcrete extends Block {
	
	private EnumConcreteType type;
	
	public BlockConcrete(EnumConcreteType type) {
		super(Material.ROCK);
		this.type = type;
		this.setCreativeTab(ConcreteCreativeTab.instance());
		this.setRegistryName(this.type.getResourceLocation(false));
		this.setUnlocalizedName(this.type.getName(false));
		this.setHardness(1.5F);
		this.setHarvestLevel("pickaxe", 0);
		this.setResistance(10.0F);
		this.setSoundType(SoundType.STONE);
	}
	
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if(Main.AprilFools){
			if( worldIn.getBlockState(pos.north()).getMaterial() == Material.LAVA
			|| worldIn.getBlockState(pos.south()).getMaterial() == Material.LAVA
			|| worldIn.getBlockState(pos.east()).getMaterial() == Material.LAVA
			|| worldIn.getBlockState(pos.west()).getMaterial() == Material.LAVA
			|| worldIn.getBlockState(pos.up()).getMaterial() == Material.LAVA
			|| worldIn.getBlockState(pos.down()).getMaterial() == Material.LAVA ){
				worldIn.setBlockState(pos, this.getPowderState(state));
			} else {
				worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
			}
		}
	}
	
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
		if(Main.AprilFools){
			if( worldIn.getBlockState(pos.north()).getMaterial() == Material.LAVA
			|| worldIn.getBlockState(pos.south()).getMaterial() == Material.LAVA
			|| worldIn.getBlockState(pos.east()).getMaterial() == Material.LAVA
			|| worldIn.getBlockState(pos.west()).getMaterial() == Material.LAVA
			|| worldIn.getBlockState(pos.up()).getMaterial() == Material.LAVA
			|| worldIn.getBlockState(pos.down()).getMaterial() == Material.LAVA ){
				worldIn.setBlockState(pos, this.getPowderState(state));
			} else {
				worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
			}
		}
	}
	
	public abstract IBlockState getPowderState(IBlockState original);
	
}
