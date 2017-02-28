package fr.dbrown55.concrete.recipes;

import fr.dbrown55.concrete.blocks.BlockConcrete;
import fr.dbrown55.concrete.blocks.BlockConcretePowder;
import fr.dbrown55.concrete.blocks.BlockRegistry;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class WaterConcrete implements IRecipe {

	private EnumDyeColor dye;
	
	public WaterConcrete(EnumDyeColor dye){
		this.dye = dye;
	}
	
	public BlockConcretePowder getInput(){
		return BlockRegistry.getPowderFromDye(this.dye);
	}
	
	public BlockConcrete getOutput(){
		return BlockRegistry.getSolidFromDye(this.dye);
	}
	
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		return null;
	}

	@Override
	public int getRecipeSize() {
		return 0;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return null;
	}

	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inv) {
		return null;
	}
	
	

}
