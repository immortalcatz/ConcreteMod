package fr.dbrown55.concrete;

import fr.dbrown55.concrete.blocks.BlockRegistry;
import fr.dbrown55.concrete.compat.ChiselModCompat;
import fr.dbrown55.concrete.items.ItemRegistry;
import fr.dbrown55.concrete.recipes.RecipeHandler;
import fr.dbrown55.concrete.tabs.CreativeTabRegistry;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Main.modid, name = Main.name, version = Main.version)
public class Main {

	public static final String modid = "one_point_twelve_concrete";
	public static final String name = "1.12 Concrete";
	public static final String version = "v 1.0";
	
	public static Configuration conf;
	
	private static boolean PECompat, ChiselCompat;
	
	@EventHandler
	public static void onPreInit(FMLPreInitializationEvent e){
		CreativeTabRegistry.init();
		BlockRegistry.init();
		ItemRegistry.init();
		RecipeHandler.init();
		
		conf = new Configuration(e.getSuggestedConfigurationFile());
		//PECompat = conf.getBoolean("projecte", "modCompat", true, "Whenever we should enable ProjectE compatibility");
		ChiselCompat = conf.getBoolean("chisel", "modCompat", true, "Whenever we should enable Chisel compatibility");
		conf.save();
	}
	
	@EventHandler
	public static void onInit(FMLInitializationEvent e){
		if(FMLCommonHandler.instance().getSide() == Side.CLIENT){
			ItemRegistry.registerRenders();
		}
	}
	
	@EventHandler
	public static void onPostInit(FMLPostInitializationEvent e){
		/*
		// ProjectE compatibility
		if(PECompat){
			ProjectECompat.init();
		}
		*/
		
		// Chisel compatibility
		if(ChiselCompat){
			ChiselModCompat.init();
		}
		
	}
	
}
