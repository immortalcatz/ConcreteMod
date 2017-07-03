package fr.dbrown55.concrete.recipes;

import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.items.ItemHandler;
import fr.dbrown55.utilmod.Handler;
import net.minecraft.block.BlockColored;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import team.chisel.api.carving.CarvingUtils;
import team.chisel.common.carving.Carving;
import team.chisel.common.init.ChiselBlocks;

public class ChiselRecipeHandler extends Handler {

	@Override
	public void preInit() {
		
	}

	@Override
	public void init() {
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
		return new String[] {"chisel"};
	}

}
