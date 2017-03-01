package fr.dbrown55.concrete.entities;

import fr.dbrown55.concrete.blocks.BlockConcretePowder;
import fr.dbrown55.concrete.blocks.BlockRegistry;
import net.minecraft.block.BlockColored;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.world.World;

public class EntityFallingConcrete extends EntityFallingBlock {

	private IBlockState fallTile;
	
	public EntityFallingConcrete(World worldIn) {
		super(worldIn);
	}
	
	public EntityFallingConcrete(World worldIn, double x, double y, double z, IBlockState fallingBlockState) {
		super(worldIn, x, y, z, fallingBlockState);
		this.fallTile = fallingBlockState;
	}	

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(this.world.getBlockState(this.getPosition()).getMaterial() == Material.WATER
			|| this.world.getBlockState(this.getPosition().north()).getMaterial() == Material.WATER
			|| this.world.getBlockState(this.getPosition().south()).getMaterial() == Material.WATER
			|| this.world.getBlockState(this.getPosition().east()).getMaterial() == Material.WATER
			|| this.world.getBlockState(this.getPosition().west()).getMaterial() == Material.WATER){
			EnumDyeColor color = ((BlockConcretePowder)this.fallTile.getBlock()).getColor();
			if(color != null){
				IBlockState state = BlockRegistry.getSolidFromDye(color).getDefaultState();
				this.world.setBlockState(this.getPosition(), state);
			}
			this.setDead();
		}
	}
	
}
