package fr.dbrown55.concrete.recipes;

import fr.dbrown55.concrete.Main;
import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.enums.EnumConcreteType;
import fr.dbrown55.concrete.items.ItemBlockConcretePowder;
import fr.dbrown55.concrete.items.ItemHandler;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipeHandler {
	
	public static void preInit(){
		OreDictionary.registerOre("concSand", new ItemStack(Blocks.SAND, 1, 0));
		OreDictionary.registerOre("concSand", new ItemStack(Blocks.SAND, 1, 1));
		OreDictionary.registerOre("concGrav", new ItemStack(Blocks.GRAVEL, 1, 0));
	}
	
	public static void init(){		
		for(EnumConcreteType type : EnumConcreteType.values()){
			for(int i = 0; i < type.getCraftStacks().length; i++){
				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(type.getResult(), 8, i), new Object[] {"concSand", "concSand", "concSand", "concSand", "concGrav", "concGrav", "concGrav", "concGrav", type.getCraftStacks()[i]}));
			}
		}
		
		if(Main.AprilFools){
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.palette), new Object[] {"rg", "pb", 'r', new ItemStack(Items.DYE, 1, EnumDyeColor.RED.getDyeDamage()), 'g', new ItemStack(Items.DYE, 1, EnumDyeColor.GREEN.getDyeDamage()), 'b', new ItemStack(Items.DYE, 1, EnumDyeColor.BLUE.getDyeDamage()), 'p', new ItemStack(Blocks.WOODEN_PRESSURE_PLATE)});
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.brush), new Object[] {"  w", " s ", "s  ", 'w', new ItemStack(Blocks.WOOL, 1, 0), 's', new ItemStack(Items.STICK)});
			GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.eraser), new Object[] {"ppb", 'p', new ItemStack(Blocks.WOOL, 1, EnumDyeColor.PINK.getMetadata()), 'b', new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.BLUE.getMetadata())});
		}
		
		if(Main.ChiselCompat){
			try {
				Block concretePowderChisel = CommandBase.getBlockByText(null, "chisel:concrete_powder");
				Block concreteChisel = CommandBase.getBlockByText(null, "chisel:concrete");
				for(int i = 0; i < 16; i++){
					GameRegistry.addShapelessRecipe(new ItemStack(BlockHandler.coloredP, 1, i), new ItemStack(concretePowderChisel, 1, i));
					GameRegistry.addShapelessRecipe(new ItemStack(concretePowderChisel, 1, i), new ItemStack(BlockHandler.coloredP, 1, i));
					GameRegistry.addShapelessRecipe(new ItemStack(BlockHandler.coloredS, 1, i), new ItemStack(concreteChisel, 1, i));
					GameRegistry.addShapelessRecipe(new ItemStack(concreteChisel, 1, i), new ItemStack(BlockHandler.coloredS, 1, i));
				}
			} catch (NumberInvalidException e) {}
		}
	}
	
}
