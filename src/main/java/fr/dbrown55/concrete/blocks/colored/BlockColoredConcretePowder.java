package fr.dbrown55.concrete.blocks.colored;

import fr.dbrown55.concrete.blocks.BlockConcretePowder;
import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.enums.EnumConcreteType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockColoredConcretePowder extends BlockConcretePowder {
	
	public BlockColoredConcretePowder() {
		super(EnumConcreteType.COLORED);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, BlockHandler.COLOR);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(BlockHandler.COLOR).getMetadata();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(BlockHandler.COLOR, EnumDyeColor.byMetadata(meta));
	}
	
	@Override
	public IBlockState getSolidState(IBlockState original) {
		return BlockHandler.coloredS.getDefaultState().withProperty(BlockHandler.COLOR, original.getValue(BlockHandler.COLOR));
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(BlockHandler.COLOR).getMetadata();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(this, 1, state.getValue(BlockHandler.COLOR).getMetadata());
	}
	
	@Override
	public MapColor getMapColor(IBlockState state) {
		return state.getValue(BlockHandler.COLOR).getMapColor();
	}

}
