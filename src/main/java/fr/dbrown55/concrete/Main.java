package fr.dbrown55.concrete;

import java.util.Calendar;

import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.client.GuiHandler;
import fr.dbrown55.concrete.client.SoundHandler;
import fr.dbrown55.concrete.compat.ChiselModCompat;
import fr.dbrown55.concrete.compat.ProjectECompat;
import fr.dbrown55.concrete.entities.EntityHandler;
import fr.dbrown55.concrete.events.TickEvents;
import fr.dbrown55.concrete.items.ItemHandler;
import fr.dbrown55.concrete.net.SetPaintMessage;
import fr.dbrown55.concrete.net.SetPaintMessageHandler;
import fr.dbrown55.concrete.proxies.CommonProxy;
import fr.dbrown55.concrete.recipes.RecipeHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)
public class Main {

	public static final String MODID = "concrete";
	public static final String NAME = "1.12 Concrete";
	public static final String VERSION = "1.1";
	
	public static Configuration conf;
	
	public static boolean PECompat, ChiselCompat, AprilFools, vanillaBehavior;
	
	@Instance
	public static Main instance;
	
	@SidedProxy(serverSide="fr.dbrown55.concrete.proxies.CommonProxy",clientSide="fr.dbrown55.concrete.proxies.ClientProxy")
	public static CommonProxy proxy;
	
	public static SimpleNetworkWrapper wrapper;
	
	@EventHandler
	public static void onPreInit(FMLPreInitializationEvent e){
		BlockHandler.init();
		ItemHandler.init();
		RecipeHandler.preInit();
		EntityHandler.init();
		SoundHandler.init();
		
		Calendar c = Calendar.getInstance();
		conf = new Configuration(e.getSuggestedConfigurationFile());
		PECompat = conf.getBoolean("projecte", "modCompat", true, "Whenever we should enable ProjectE compatibility") && Loader.isModLoaded("ProjectE") ;
		ChiselCompat = conf.getBoolean("chisel", "modCompat", true, "Whenever we should enable Chisel compatibility") && Loader.isModLoaded("chisel");
		AprilFools = conf.getBoolean("aprilFools", "misc", false, "Whenever we should enable the AprilFools update features ?") || (c.get(Calendar.DAY_OF_MONTH) == 1 && c.get(Calendar.MONTH) == 3);
		vanillaBehavior = conf.getBoolean("vanillaBehavior", "misc", false, "Whenever the falling concrete should act like in vanilla (might not be EXACTLY like in vanilla)");
		conf.save();
		
		wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
		wrapper.registerMessage(SetPaintMessageHandler.class, SetPaintMessage.class, 0, Side.SERVER);
		
		MinecraftForge.EVENT_BUS.register(new TickEvents());
		
		proxy.onPreInit();
	}
	
	@EventHandler
	public static void onInit(FMLInitializationEvent e){
		proxy.onInit();
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
		RecipeHandler.init();
	}
	
	@EventHandler
	public static void onPostInit(FMLPostInitializationEvent e){
		// ProjectE compatibility
		if(PECompat){
			ProjectECompat.init();
		}
		
		// Chisel compatibility
		if(ChiselCompat){
			ChiselModCompat.init();
		}
		
		proxy.onPostInit();
		
	}
	
}
