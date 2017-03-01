package fr.dbrown55.concrete.tabs;

import net.minecraft.creativetab.CreativeTabs;
import scala.concurrent.impl.Future;

public class CreativeTabRegistry {

	private static ConcreteCreativeTab tab;
	
	public static void init(){
		tab = new ConcreteCreativeTab();
	}

	public static CreativeTabs getTab() {
		return tab;
	}
	
}
