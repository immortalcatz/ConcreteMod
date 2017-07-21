package fr.dbrown55.concrete;

import java.util.HashMap;
import java.util.logging.Logger;

import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.client.SoundHandler;
import fr.dbrown55.concrete.commands.ConcreteHelpCommand;
import fr.dbrown55.concrete.entities.EntityHandler;
import fr.dbrown55.concrete.events.ConcreteEvents;
import fr.dbrown55.concrete.events.EMCEvent;
import fr.dbrown55.concrete.gui.GuiHandler;
import fr.dbrown55.concrete.items.ItemHandler;
import fr.dbrown55.concrete.net.MessageSetPaint;
import fr.dbrown55.concrete.net.MessageSetPaintHandler;
import fr.dbrown55.concrete.recipes.ChiselRecipeHandler;
import fr.dbrown55.concrete.recipes.ProjectERecipeHandler;
import fr.dbrown55.concrete.recipes.RecipeHandler;
import fr.dbrown55.concrete.tabs.ConcreteGlowstoneTab;
import fr.dbrown55.concrete.tabs.ConcreteItemsTab;
import fr.dbrown55.concrete.tabs.ConcreteMagmaTab;
import fr.dbrown55.concrete.tabs.ConcreteRedstoneTab;
import fr.dbrown55.concrete.tabs.ConcreteVanillaTab;
import fr.dbrown55.utilmod.BaseProxy;
import fr.dbrown55.utilmod.Handler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION, dependencies = Main.DEPS)
public class Main {

	public static final String MODID = "concrete";
	public static final String NAME = "1.12 Concrete";
	public static final String VERSION = "1.2";
	public static final String DEPS = "required-after:dbrownutil";
	
	public static final Logger logger = Logger.getLogger(MODID);
	
	public static ConcreteConf config;
	
	@SidedProxy(clientSide = "fr.dbrown55.concrete.proxies.ClientProxy", serverSide = "fr.dbrown55.concrete.proxies.CommonProxy")
	public static BaseProxy proxy;
	
	@Instance
	public static Main instance;
	
	public static SimpleNetworkWrapper wrapper;
	
	public static HashMap<String, CreativeTabs> concreteTabs = new HashMap<String, CreativeTabs>();
	public static CreativeTabs concreteTabItems;
	
	@EventHandler
	public static void onPreInit(FMLPreInitializationEvent e) {
		config = new ConcreteConf(e.getSuggestedConfigurationFile());
		
		Handler.addHandler(new BlockHandler(), MODID);
		Handler.addHandler(new ItemHandler(), MODID);
		Handler.addHandler(new RecipeHandler(), MODID);
		if(config.isProjectECompatOn()) {
			Handler.addHandler(new ProjectERecipeHandler(), MODID);
		}
		if(config.isChiselCompatOn()) {
			Handler.addHandler(new ChiselRecipeHandler(), MODID);
		}
		Handler.addHandler(new EntityHandler(), MODID);
		Handler.addHandler(new SoundHandler(), MODID);
		
		Handler.onPreInit(MODID);
		
		proxy.preInit();
		
		MinecraftForge.EVENT_BUS.register(new ConcreteEvents());
		if(Main.config.isProjectECompatOn()) {
			MinecraftForge.EVENT_BUS.register(new EMCEvent());
		}
		
		wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
		wrapper.registerMessage(MessageSetPaintHandler.class, MessageSetPaint.class, 0, Side.SERVER);
		
		concreteTabs.put("vanilla", new ConcreteVanillaTab());
		concreteTabs.put("magma", new ConcreteMagmaTab());
		concreteTabs.put("glowstone", new ConcreteGlowstoneTab());
		concreteTabs.put("redstone", new ConcreteRedstoneTab());
		concreteTabItems = new ConcreteItemsTab();
	}
	
	@EventHandler
	public static void onInit(FMLInitializationEvent e) {		
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
		
		Handler.onInit(MODID);
		
		proxy.init();
	}
	
	@EventHandler
	public static void onPostInit(FMLPostInitializationEvent e) {
		Handler.onPostInit(MODID);
		
		proxy.postInit();
	}
	
	@EventHandler
	public static void onServerStarted(FMLServerStartingEvent e){
		e.registerServerCommand(new ConcreteHelpCommand());
	}
	
}
