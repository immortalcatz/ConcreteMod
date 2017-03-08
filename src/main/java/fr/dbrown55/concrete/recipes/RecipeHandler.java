package fr.dbrown55.concrete.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.dbrown55.concrete.backported.EnumDyeColor;
import fr.dbrown55.concrete.blocks.BlockRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RecipeHandler {

	private static final ItemStack _SAND_ = new ItemStack(Blocks.sand), _GRAVEL_ = new ItemStack(Blocks.gravel);
	
	public static void init() {
		for(EnumDyeColor dye : EnumDyeColor.values()){
			GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.getPowderFromDye(dye), 8), _SAND_, _SAND_, _SAND_, _SAND_, _GRAVEL_, _GRAVEL_, _GRAVEL_, _GRAVEL_, new ItemStack(Items.dye, 1, dye.getDyeDamage()));
		}
	}

}
