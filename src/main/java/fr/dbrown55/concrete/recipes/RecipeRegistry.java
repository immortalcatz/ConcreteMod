package fr.dbrown55.concrete.recipes;

import java.util.ArrayList;
import java.util.List;

import fr.dbrown55.concrete.Main;
import fr.dbrown55.concrete.blocks.BlockRegistry;
import fr.dbrown55.concrete.items.ItemRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;

public class RecipeRegistry {

	private static ItemStack S = new ItemStack(Blocks.SAND), G = new ItemStack(Blocks.GRAVEL);
	private static ArrayList<WaterConcrete> waterRecipes;
	
	public static void init() {
		for(EnumDyeColor color : EnumDyeColor.values()){
			ItemStack D = new ItemStack(Items.DYE, 1, BlockRegistry.getPowderFromDye(color).getColor().getDyeDamage());
			GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.getPowderFromDye(color), 8), new Object[] {S, S, S, S, G, G, G, G, D});
		}
		
		// JEI Integration
		RecipeSorter.register(Main.modid + "_water_concrete", WaterConcrete.class, Category.SHAPELESS, "after:minecraft:shapeless");
		waterRecipes = new ArrayList<WaterConcrete>();
		for(EnumDyeColor color : EnumDyeColor.values()){
			waterRecipes.add(new WaterConcrete(color));
		}
	}
	
	public static List<WaterConcrete> getWaterRecipes(){
		return waterRecipes;
	}

}
