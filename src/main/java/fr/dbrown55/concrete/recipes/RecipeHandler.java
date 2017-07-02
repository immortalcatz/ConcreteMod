package fr.dbrown55.concrete.recipes;

import fr.dbrown55.concrete.Main;
import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteSlabBase.EnumSlabPartType;
import fr.dbrown55.concrete.items.ItemHandler;
import fr.dbrown55.utilmod.Handler;
import fr.dbrown55.utilmod.helpers.BlockStateHelper;
import moze_intel.projecte.utils.WorldTransmutations;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStairs.EnumHalf;
import net.minecraft.block.BlockStairs.EnumShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.registry.GameRegistry;
import team.chisel.api.carving.CarvingUtils;
import team.chisel.common.carving.Carving;
import team.chisel.common.init.ChiselBlocks;

public class RecipeHandler extends Handler {

	public static final ItemStack SAND = new ItemStack(Blocks.SAND), GRAVEL = new ItemStack(Blocks.GRAVEL);
	
	public boolean[] boolValues = new boolean[] {true, false};
	
	@Override
	public void preInit() {
		for(EnumDyeColor dye : EnumDyeColor.values()) {
			GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.VANILLA_POWDER, 8, dye.getMetadata()), SAND, SAND, SAND, SAND, GRAVEL, GRAVEL, GRAVEL, GRAVEL, new ItemStack(Items.DYE, 1, dye.getDyeDamage()));
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.VANILLA_STAIRS, 8, dye.getMetadata()), "b  ", "bb ", "bbb", 'b', new ItemStack(ItemHandler.VANILLA_SOLID, 1, dye.getMetadata()));
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.VANILLA_SLAB, 6, dye.getMetadata()), "bbb", 'b', new ItemStack(ItemHandler.VANILLA_SOLID, 1, dye.getMetadata()));
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.VANILLA_STICK, 8, dye.getMetadata()), "b", "b", 'b', new ItemStack(ItemHandler.VANILLA_SOLID, 1, dye.getMetadata()));
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.VANILLA_SOLID, 1, dye.getMetadata()), "ss", "ss", 's', new ItemStack(ItemHandler.VANILLA_STICK, 1, dye.getMetadata()));
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.VANILLA_FENCE, 3, dye.getMetadata()), "bsb", "bsb", 'b', new ItemStack(ItemHandler.VANILLA_SOLID, 1, dye.getMetadata()), 's', new ItemStack(ItemHandler.VANILLA_STICK, 1, dye.getMetadata()));
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.VANILLA_GATE, 1, dye.getMetadata()), "sbs", "sbs", 'b', new ItemStack(ItemHandler.VANILLA_SOLID, 1, dye.getMetadata()), 's', new ItemStack(ItemHandler.VANILLA_STICK, 1, dye.getMetadata()));
			
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.MAGMA_POWDER, 1, dye.getMetadata()), " m ", "mpm", " m ", 'm', Items.MAGMA_CREAM, 'p', new ItemStack(ItemHandler.VANILLA_POWDER, 1, dye.getMetadata()));
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.MAGMA_STAIRS, 8, dye.getMetadata()), "b  ", "bb ", "bbb", 'b', new ItemStack(ItemHandler.MAGMA_SOLID, 1, dye.getMetadata()));
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.MAGMA_SLAB, 6, dye.getMetadata()), "bbb", 'b', new ItemStack(ItemHandler.MAGMA_SOLID, 1, dye.getMetadata()));
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.MAGMA_STICK, 8, dye.getMetadata()), "b", "b", 'b', new ItemStack(ItemHandler.MAGMA_SOLID, 1, dye.getMetadata()));
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.MAGMA_SOLID, 1, dye.getMetadata()), "ss", "ss", 's', new ItemStack(ItemHandler.MAGMA_STICK, 1, dye.getMetadata()));
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.MAGMA_FENCE, 3, dye.getMetadata()), "bsb", "bsb", 'b', new ItemStack(ItemHandler.MAGMA_SOLID, 1, dye.getMetadata()), 's', new ItemStack(ItemHandler.MAGMA_STICK, 1, dye.getMetadata()));
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.MAGMA_GATE, 1, dye.getMetadata()), "sbs", "sbs", 'b', new ItemStack(ItemHandler.MAGMA_SOLID, 1, dye.getMetadata()), 's', new ItemStack(ItemHandler.MAGMA_STICK, 1, dye.getMetadata()));
			
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.GLOWSTONE_POWDER, 1, dye.getMetadata()), " g ", "gpg", " g ", 'g', Items.GLOWSTONE_DUST, 'p', new ItemStack(ItemHandler.VANILLA_POWDER, 1, dye.getMetadata()));
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.GLOWSTONE_STAIRS, 8, dye.getMetadata()), "b  ", "bb ", "bbb", 'b', new ItemStack(ItemHandler.GLOWSTONE_SOLID, 1, dye.getMetadata()));
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.GLOWSTONE_SLAB, 6, dye.getMetadata()), "bbb", 'b', new ItemStack(ItemHandler.GLOWSTONE_SOLID, 1, dye.getMetadata()));
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.GLOWSTONE_STICK, 8, dye.getMetadata()), "b", "b", 'b', new ItemStack(ItemHandler.GLOWSTONE_SOLID, 1, dye.getMetadata()));
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.GLOWSTONE_SOLID, 1, dye.getMetadata()), "ss", "ss", 's', new ItemStack(ItemHandler.GLOWSTONE_STICK, 1, dye.getMetadata()));
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.GLOWSTONE_FENCE, 3, dye.getMetadata()), "bsb", "bsb", 'b', new ItemStack(ItemHandler.GLOWSTONE_SOLID, 1, dye.getMetadata()), 's', new ItemStack(ItemHandler.GLOWSTONE_STICK, 1, dye.getMetadata()));
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.GLOWSTONE_GATE, 1, dye.getMetadata()), "sbs", "sbs", 'b', new ItemStack(ItemHandler.GLOWSTONE_SOLID, 1, dye.getMetadata()), 's', new ItemStack(ItemHandler.GLOWSTONE_STICK, 1, dye.getMetadata()));
			
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.REDSTONE_POWDER, 1, dye.getMetadata()), "rrr", "rpr", "rrr", 'r', Items.REDSTONE, 'p', new ItemStack(ItemHandler.VANILLA_POWDER, 1, dye.getMetadata()));
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.REDSTONE_STAIRS, 8, dye.getMetadata()), "b  ", "bb ", "bbb", 'b', new ItemStack(ItemHandler.REDSTONE_SOLID, 1, dye.getMetadata()));
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.REDSTONE_SLAB, 6, dye.getMetadata()), "bbb", 'b', new ItemStack(ItemHandler.REDSTONE_SOLID, 1, dye.getMetadata()));
		}
	}

	@Override
	public void init() {
		if(Main.config.isChiselCompatOn()) {
			for(EnumDyeColor dye : EnumDyeColor.values()) {
				ItemStack myPowder = new ItemStack(ItemHandler.VANILLA_POWDER, 1, dye.getMetadata());
				ItemStack theirPowder = new ItemStack(ChiselBlocks.concrete_powder, 1, dye.getMetadata());
				GameRegistry.addShapelessRecipe(myPowder, theirPowder);
				GameRegistry.addShapelessRecipe(theirPowder, myPowder);
				
				ItemStack mySolid = new ItemStack(ItemHandler.VANILLA_SOLID, 1, dye.getMetadata());
				ItemStack theirSolid = new ItemStack(ChiselBlocks.concrete, 1, dye.getMetadata());
				GameRegistry.addShapelessRecipe(mySolid, theirSolid);
				GameRegistry.addShapelessRecipe(theirSolid, mySolid);
				
				Carving.chisel.addVariation("vanillaPowder", BlockHandler.VANILLA_POWDER.getDefaultState().withProperty(BlockColored.COLOR, dye), dye.getMetadata());
				Carving.chisel.addVariation("vanillaSolid", BlockHandler.VANILLA_SOLID.getDefaultState().withProperty(BlockColored.COLOR, dye), dye.getMetadata());
				Carving.chisel.addVariation("magmaPowder", BlockHandler.MAGMA_POWDER.getDefaultState().withProperty(BlockColored.COLOR, dye), dye.getMetadata());
				Carving.chisel.addVariation("magmaSolid", BlockHandler.MAGMA_SOLID.getDefaultState().withProperty(BlockColored.COLOR, dye), dye.getMetadata());
				Carving.chisel.addVariation("glowPowder", BlockHandler.GLOWSTONE_POWDER.getDefaultState().withProperty(BlockColored.COLOR, dye), dye.getMetadata());
				Carving.chisel.addVariation("glowSolid", BlockHandler.GLOWSTONE_SOLID.getDefaultState().withProperty(BlockColored.COLOR, dye), dye.getMetadata());
				Carving.chisel.addVariation("redPowder", BlockHandler.REDSTONE_POWDER.getDefaultState().withProperty(BlockColored.COLOR, dye), dye.getMetadata());
				Carving.chisel.addVariation("redSolid", BlockHandler.REDSTONE_SOLID.getDefaultState().withProperty(BlockColored.COLOR, dye), dye.getMetadata());
			}
			
			CarvingUtils.getChiselRegistry().registerOre("vanillaPowder", "vanillaPowder");
			CarvingUtils.getChiselRegistry().registerOre("vanillaSolid", "vanillaSolid");
			CarvingUtils.getChiselRegistry().registerOre("magmaPowder", "magmaPowder");
			CarvingUtils.getChiselRegistry().registerOre("magmaSolid", "magmaSolid");
			CarvingUtils.getChiselRegistry().registerOre("glowPowder", "glowPowder");
			CarvingUtils.getChiselRegistry().registerOre("glowSolid", "glowSolid");
			CarvingUtils.getChiselRegistry().registerOre("redPowder", "redPowder");
			CarvingUtils.getChiselRegistry().registerOre("redSolid", "redSolid");
		}
		
		if(Main.config.isProjectECompatOn()) {
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
		return new String[] {"chisel", "ProjectE"};
	}

	@Override
	public String[] getRequirements(EnumHandlerPhase phase) {
		return new String[] {};
	}

}
