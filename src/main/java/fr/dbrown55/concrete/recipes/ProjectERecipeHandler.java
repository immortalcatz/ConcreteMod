package fr.dbrown55.concrete.recipes;

import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteSlabBase.EnumSlabPartType;
import fr.dbrown55.utilmod.Handler;
import fr.dbrown55.utilmod.helpers.BlockStateHelper;
import moze_intel.projecte.utils.WorldTransmutations;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStairs.EnumHalf;
import net.minecraft.block.BlockStairs.EnumShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumFacing;

public class ProjectERecipeHandler extends Handler {

	public boolean[] boolValues = new boolean[] {true, false};
	
	@Override
	public void preInit() {

	}

	@Override
	public void init() {
		for(int i = 0; i < 16; i++) {
			EnumDyeColor dyeFrom = EnumDyeColor.byMetadata(i);
			EnumDyeColor dyeResult = EnumDyeColor.byMetadata(i == 15 ? 0 : i + 1);
			EnumDyeColor dyeAltResult = EnumDyeColor.byMetadata(i == 0 ? 15 : i - 1);
			
			IBlockState from, result, altResult;
			
			/* VANILLA POWDER */
			from = BlockHandler.VANILLA_POWDER.getDefaultState().withProperty(BlockColored.COLOR, dyeFrom);
			result = BlockHandler.VANILLA_POWDER.getDefaultState().withProperty(BlockColored.COLOR,dyeResult);
			altResult = BlockHandler.VANILLA_POWDER.getDefaultState().withProperty(BlockColored.COLOR, dyeAltResult);
			WorldTransmutations.register(from, result, altResult);
			
			/* VANILLA SOLID */
			from = BlockHandler.VANILLA_SOLID.getDefaultState().withProperty(BlockColored.COLOR, dyeFrom);
			result = BlockHandler.VANILLA_SOLID.getDefaultState().withProperty(BlockColored.COLOR,dyeResult);
			altResult = BlockHandler.VANILLA_SOLID.getDefaultState().withProperty(BlockColored.COLOR, dyeAltResult);
			WorldTransmutations.register(from, result, altResult);
			
			/* VANILLA STAIRS */
			for(EnumFacing facing : EnumFacing.HORIZONTALS) {
				for(EnumHalf half : EnumHalf.values()) {
					for(EnumShape shape : EnumShape.values()) {
						from = BlockHandler.VANILLA_STAIRS.get(dyeFrom).getDefaultState().withProperty(BlockStairs.FACING, facing).withProperty(BlockStairs.HALF, half).withProperty(BlockStairs.SHAPE, shape);
						result = BlockStateHelper.copyProperties(from, BlockHandler.VANILLA_STAIRS.get(dyeResult), BlockStairs.FACING, BlockStairs.HALF, BlockStairs.SHAPE);
						altResult = BlockStateHelper.copyProperties(from, BlockHandler.VANILLA_STAIRS.get(dyeAltResult), BlockStairs.FACING, BlockStairs.HALF, BlockStairs.SHAPE);
						WorldTransmutations.register(from, result, altResult);
					}
				}
			}
			
			/* VANILLA SLAB */
			for(EnumSlabPartType part : EnumSlabPartType.values()) {
				from = BlockHandler.VANILLA_SLABS.get(part).getDefaultState().withProperty(BlockColored.COLOR, dyeFrom);
				result = BlockHandler.VANILLA_SLABS.get(part).getDefaultState().withProperty(BlockColored.COLOR, dyeResult);
				altResult = BlockHandler.VANILLA_SLABS.get(part).getDefaultState().withProperty(BlockColored.COLOR, dyeAltResult);
				WorldTransmutations.register(from, result, altResult);
			}
			
			/* VANILLA FENCE */
			from = BlockHandler.VANILLA_FENCE.getDefaultState().withProperty(BlockColored.COLOR, dyeFrom);
			result = BlockHandler.VANILLA_FENCE.getDefaultState().withProperty(BlockColored.COLOR,dyeResult);
			altResult = BlockHandler.VANILLA_FENCE.getDefaultState().withProperty(BlockColored.COLOR, dyeAltResult);
			WorldTransmutations.register(from, result, altResult);
			
			/* VANILLA GATE */
			for(boolean open : boolValues) {
				for(boolean powered : boolValues) {
					for(boolean inWall : boolValues) {
						from = BlockHandler.VANILLA_GATES.get(dyeFrom).getDefaultState().withProperty(BlockFenceGate.OPEN, open).withProperty(BlockFenceGate.POWERED, powered).withProperty(BlockFenceGate.IN_WALL, inWall);
						result = BlockStateHelper.copyProperties(from, BlockHandler.VANILLA_GATES.get(dyeResult), BlockFenceGate.OPEN, BlockFenceGate.POWERED, BlockFenceGate.IN_WALL);
						altResult = BlockStateHelper.copyProperties(from, BlockHandler.VANILLA_GATES.get(dyeAltResult), BlockFenceGate.OPEN, BlockFenceGate.POWERED, BlockFenceGate.IN_WALL);
						WorldTransmutations.register(from, result, altResult);
					}
				}
			}
			
			/* MAGMA POWDER */
			from = BlockHandler.MAGMA_POWDER.getDefaultState().withProperty(BlockColored.COLOR, dyeFrom);
			result = BlockHandler.MAGMA_POWDER.getDefaultState().withProperty(BlockColored.COLOR,dyeResult);
			altResult = BlockHandler.MAGMA_POWDER.getDefaultState().withProperty(BlockColored.COLOR, dyeAltResult);
			WorldTransmutations.register(from, result, altResult);
			
			/* MAGMA SOLID */
			from = BlockHandler.MAGMA_SOLID.getDefaultState().withProperty(BlockColored.COLOR, dyeFrom);
			result = BlockHandler.MAGMA_SOLID.getDefaultState().withProperty(BlockColored.COLOR,dyeResult);
			altResult = BlockHandler.MAGMA_SOLID.getDefaultState().withProperty(BlockColored.COLOR, dyeAltResult);
			WorldTransmutations.register(from, result, altResult);
			
			/* MAGMA STAIRS */
			for(EnumFacing facing : EnumFacing.HORIZONTALS) {
				for(EnumHalf half : EnumHalf.values()) {
					for(EnumShape shape : EnumShape.values()) {
						from = BlockHandler.MAGMA_STAIRS.get(dyeFrom).getDefaultState().withProperty(BlockStairs.FACING, facing).withProperty(BlockStairs.HALF, half).withProperty(BlockStairs.SHAPE, shape);
						result = BlockStateHelper.copyProperties(from, BlockHandler.MAGMA_STAIRS.get(dyeResult), BlockStairs.FACING, BlockStairs.HALF, BlockStairs.SHAPE);
						altResult = BlockStateHelper.copyProperties(from, BlockHandler.MAGMA_STAIRS.get(dyeAltResult), BlockStairs.FACING, BlockStairs.HALF, BlockStairs.SHAPE);
						WorldTransmutations.register(from, result, altResult);
					}
				}
			}
			
			/* MAGMA SLAB */
			for(EnumSlabPartType part : EnumSlabPartType.values()) {
				from = BlockHandler.MAGMA_SLABS.get(part).getDefaultState().withProperty(BlockColored.COLOR, dyeFrom);
				result = BlockHandler.MAGMA_SLABS.get(part).getDefaultState().withProperty(BlockColored.COLOR, dyeResult);
				altResult = BlockHandler.MAGMA_SLABS.get(part).getDefaultState().withProperty(BlockColored.COLOR, dyeAltResult);
				WorldTransmutations.register(from, result, altResult);
			}
			
			/* MAGMA FENCE */
			from = BlockHandler.MAGMA_FENCE.getDefaultState().withProperty(BlockColored.COLOR, dyeFrom);
			result = BlockHandler.MAGMA_FENCE.getDefaultState().withProperty(BlockColored.COLOR,dyeResult);
			altResult = BlockHandler.MAGMA_FENCE.getDefaultState().withProperty(BlockColored.COLOR, dyeAltResult);
			WorldTransmutations.register(from, result, altResult);
			
			/* MAGMA GATE */
			for(boolean open : boolValues) {
				for(boolean powered : boolValues) {
					for(boolean inWall : boolValues) {
						from = BlockHandler.MAGMA_GATES.get(dyeFrom).getDefaultState().withProperty(BlockFenceGate.OPEN, open).withProperty(BlockFenceGate.POWERED, powered).withProperty(BlockFenceGate.IN_WALL, inWall);
						result = BlockStateHelper.copyProperties(from, BlockHandler.MAGMA_GATES.get(dyeResult), BlockFenceGate.OPEN, BlockFenceGate.POWERED, BlockFenceGate.IN_WALL);
						altResult = BlockStateHelper.copyProperties(from, BlockHandler.MAGMA_GATES.get(dyeAltResult), BlockFenceGate.OPEN, BlockFenceGate.POWERED, BlockFenceGate.IN_WALL);
						WorldTransmutations.register(from, result, altResult);
					}
				}
			}
			
			/* GLOWSTONE POWDER */
			from = BlockHandler.GLOWSTONE_POWDER.getDefaultState().withProperty(BlockColored.COLOR, dyeFrom);
			result = BlockHandler.GLOWSTONE_POWDER.getDefaultState().withProperty(BlockColored.COLOR,dyeResult);
			altResult = BlockHandler.GLOWSTONE_POWDER.getDefaultState().withProperty(BlockColored.COLOR, dyeAltResult);
			WorldTransmutations.register(from, result, altResult);
			
			/* GLOWSTONE SOLID */
			from = BlockHandler.GLOWSTONE_SOLID.getDefaultState().withProperty(BlockColored.COLOR, dyeFrom);
			result = BlockHandler.GLOWSTONE_SOLID.getDefaultState().withProperty(BlockColored.COLOR,dyeResult);
			altResult = BlockHandler.GLOWSTONE_SOLID.getDefaultState().withProperty(BlockColored.COLOR, dyeAltResult);
			WorldTransmutations.register(from, result, altResult);
			
			/* GLOWSTONE STAIRS */
			for(EnumFacing facing : EnumFacing.HORIZONTALS) {
				for(EnumHalf half : EnumHalf.values()) {
					for(EnumShape shape : EnumShape.values()) {
						from = BlockHandler.GLOWSTONE_STAIRS.get(dyeFrom).getDefaultState().withProperty(BlockStairs.FACING, facing).withProperty(BlockStairs.HALF, half).withProperty(BlockStairs.SHAPE, shape);
						result = BlockStateHelper.copyProperties(from, BlockHandler.GLOWSTONE_STAIRS.get(dyeResult), BlockStairs.FACING, BlockStairs.HALF, BlockStairs.SHAPE);
						altResult = BlockStateHelper.copyProperties(from, BlockHandler.GLOWSTONE_STAIRS.get(dyeAltResult), BlockStairs.FACING, BlockStairs.HALF, BlockStairs.SHAPE);
						WorldTransmutations.register(from, result, altResult);
					}
				}
			}
			
			/* GLOWSTONE SLAB */
			for(EnumSlabPartType part : EnumSlabPartType.values()) {
				from = BlockHandler.GLOWSTONE_SLABS.get(part).getDefaultState().withProperty(BlockColored.COLOR, dyeFrom);
				result = BlockHandler.GLOWSTONE_SLABS.get(part).getDefaultState().withProperty(BlockColored.COLOR, dyeResult);
				altResult = BlockHandler.GLOWSTONE_SLABS.get(part).getDefaultState().withProperty(BlockColored.COLOR, dyeAltResult);
				WorldTransmutations.register(from, result, altResult);
			}
			
			/* GLOWSTONE FENCE */
			from = BlockHandler.GLOWSTONE_FENCE.getDefaultState().withProperty(BlockColored.COLOR, dyeFrom);
			result = BlockHandler.GLOWSTONE_FENCE.getDefaultState().withProperty(BlockColored.COLOR,dyeResult);
			altResult = BlockHandler.GLOWSTONE_FENCE.getDefaultState().withProperty(BlockColored.COLOR, dyeAltResult);
			WorldTransmutations.register(from, result, altResult);
			
			/* GLOWSTONE GATE */
			for(boolean open : boolValues) {
				for(boolean powered : boolValues) {
					for(boolean inWall : boolValues) {
						from = BlockHandler.GLOWSTONE_GATES.get(dyeFrom).getDefaultState().withProperty(BlockFenceGate.OPEN, open).withProperty(BlockFenceGate.POWERED, powered).withProperty(BlockFenceGate.IN_WALL, inWall);
						result = BlockStateHelper.copyProperties(from, BlockHandler.GLOWSTONE_GATES.get(dyeResult), BlockFenceGate.OPEN, BlockFenceGate.POWERED, BlockFenceGate.IN_WALL);
						altResult = BlockStateHelper.copyProperties(from, BlockHandler.GLOWSTONE_GATES.get(dyeAltResult), BlockFenceGate.OPEN, BlockFenceGate.POWERED, BlockFenceGate.IN_WALL);
						WorldTransmutations.register(from, result, altResult);
					}
				}
			}
			
			/* REDSTONE POWDER */
			from = BlockHandler.REDSTONE_POWDER.getDefaultState().withProperty(BlockColored.COLOR, dyeFrom);
			result = BlockHandler.REDSTONE_POWDER.getDefaultState().withProperty(BlockColored.COLOR,dyeResult);
			altResult = BlockHandler.REDSTONE_POWDER.getDefaultState().withProperty(BlockColored.COLOR, dyeAltResult);
			WorldTransmutations.register(from, result, altResult);
			
			/* REDSTONE SOLID */
			from = BlockHandler.REDSTONE_SOLID.getDefaultState().withProperty(BlockColored.COLOR, dyeFrom);
			result = BlockHandler.REDSTONE_SOLID.getDefaultState().withProperty(BlockColored.COLOR,dyeResult);
			altResult = BlockHandler.REDSTONE_SOLID.getDefaultState().withProperty(BlockColored.COLOR, dyeAltResult);
			WorldTransmutations.register(from, result, altResult);
			
			/* REDSTONE STAIRS */
			for(EnumFacing facing : EnumFacing.HORIZONTALS) {
				for(EnumHalf half : EnumHalf.values()) {
					for(EnumShape shape : EnumShape.values()) {
						from = BlockHandler.REDSTONE_STAIRS.get(dyeFrom).getDefaultState().withProperty(BlockStairs.FACING, facing).withProperty(BlockStairs.HALF, half).withProperty(BlockStairs.SHAPE, shape);
						result = BlockStateHelper.copyProperties(from, BlockHandler.REDSTONE_STAIRS.get(dyeResult), BlockStairs.FACING, BlockStairs.HALF, BlockStairs.SHAPE);
						altResult = BlockStateHelper.copyProperties(from, BlockHandler.REDSTONE_STAIRS.get(dyeAltResult), BlockStairs.FACING, BlockStairs.HALF, BlockStairs.SHAPE);
						WorldTransmutations.register(from, result, altResult);
					}
				}
			}
			
			/* REDSTONE SLAB */
			for(EnumSlabPartType part : EnumSlabPartType.values()) {
				from = BlockHandler.REDSTONE_SLABS.get(part).getDefaultState().withProperty(BlockColored.COLOR, dyeFrom);
				result = BlockHandler.REDSTONE_SLABS.get(part).getDefaultState().withProperty(BlockColored.COLOR, dyeResult);
				altResult = BlockHandler.REDSTONE_SLABS.get(part).getDefaultState().withProperty(BlockColored.COLOR, dyeAltResult);
				WorldTransmutations.register(from, result, altResult);
			}
		}
	}

	@Override
	public void postInit() {

	}

	@Override
	public EnumHandlerPriority getPriority() {
		return EnumHandlerPriority.RECIPE;
	}

	@Override
	public String[] getDependencies(EnumHandlerPhase phase) {
		return new String[] {};
	}

	@Override
	public String[] getRequirements(EnumHandlerPhase phase) {
		return new String[] {"ProjectE"};
	}

}
