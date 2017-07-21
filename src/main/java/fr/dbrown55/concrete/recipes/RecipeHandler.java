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
			
			if(dye != EnumDyeColor.WHITE) {
				GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.VANILLA_POWDER, 8, dye.getMetadata()), "ccc", "cdc", "ccc", 'c', new ItemStack(ItemHandler.VANILLA_POWDER, 1, 0), 'd', new ItemStack(Items.DYE, 1, dye.getDyeDamage()));
				GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.MAGMA_POWDER, 8, dye.getMetadata()), "ccc", "cdc", "ccc", 'c', new ItemStack(ItemHandler.MAGMA_POWDER, 1, 0), 'd', new ItemStack(Items.DYE, 1, dye.getDyeDamage()));
				GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.GLOWSTONE_POWDER, 8, dye.getMetadata()), "ccc", "cdc", "ccc", 'c', new ItemStack(ItemHandler.GLOWSTONE_POWDER, 1, 0), 'd', new ItemStack(Items.DYE, 1, dye.getDyeDamage()));
				GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.REDSTONE_POWDER, 8, dye.getMetadata()), "ccc", "cdc", "ccc", 'c', new ItemStack(ItemHandler.REDSTONE_POWDER, 1, 0), 'd', new ItemStack(Items.DYE, 1, dye.getDyeDamage()));
			}
		}
	}

	@Override
	public void init() {		
		
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
		return new String[] {};
	}

}
