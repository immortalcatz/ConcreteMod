package fr.dbrown55.concrete.recipes;

import fr.dbrown55.concrete.items.ItemRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeHandler {
	
	private static final ItemStack _SAND_ = new ItemStack(Blocks.SAND), _GRAVEL_ = new ItemStack(Blocks.GRAVEL);
	
	public static void init(){
		for(EnumDyeColor dye : EnumDyeColor.values()){
			GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.getPowderFromDye(dye), 8), _SAND_, _SAND_, _SAND_, _SAND_, _GRAVEL_, _GRAVEL_, _GRAVEL_, _GRAVEL_, new ItemStack(Items.DYE, 1, dye.getDyeDamage()));
		}
	}
	
}
