package fr.dbrown55.concrete.items;

import java.util.HashMap;

import fr.dbrown55.concrete.enums.EnumConcreteType;
import fr.dbrown55.concrete.items.colored.IBColoredConcrete;
import fr.dbrown55.concrete.items.colored.IBColoredConcretePowder;
import fr.dbrown55.concrete.items.glow.IBGlowCrete;
import fr.dbrown55.concrete.items.glow.IBGlowCretePowder;
import fr.dbrown55.concrete.items.magma.IBMagmaConcrete;
import fr.dbrown55.concrete.items.magma.IBMagmaConcretePowder;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemHandler {

	public static ItemBlockConcrete coloredS, magmaS, glowS;
	public static ItemBlockConcretePowder coloredP, magmaP, glowP;
	public static Item brush, palette, eraser, concreteBug; 
	
	private static HashMap<EnumConcreteType, ItemBlockConcretePowder> mapPowder = new HashMap<EnumConcreteType, ItemBlockConcretePowder>();
	
	public static void init(){
		// Vanilla
		coloredS = new IBColoredConcrete();
		coloredP = new IBColoredConcretePowder();
		GameRegistry.register(coloredS);
		GameRegistry.register(coloredP);
		mapPowder.put(EnumConcreteType.COLORED, coloredP);
		
		// Magma
		magmaS = new IBMagmaConcrete();
		magmaP = new IBMagmaConcretePowder();
		GameRegistry.register(magmaS);
		GameRegistry.register(magmaP);
		mapPowder.put(EnumConcreteType.MAGMA, magmaP);
		
		// Magma
		glowS = new IBGlowCrete();
		glowP = new IBGlowCretePowder();
		GameRegistry.register(glowS);
		GameRegistry.register(glowP);
		mapPowder.put(EnumConcreteType.GLOWSTONE, glowP);
		
		// Brush and palette
		palette = new Item().setRegistryName("concrete", "palette").setUnlocalizedName("palette").setMaxStackSize(1);
		brush = new ItemBrush();
		eraser = new ItemEraser();
		concreteBug = new ItemConcreteBugDecoration();
		GameRegistry.register(palette);
		GameRegistry.register(brush);
		GameRegistry.register(eraser);
		GameRegistry.register(concreteBug);
		
		// Okay, I got it, no need to yell at me
		// Note : read from here [...]                                                 [...] to here.
		//                   |                                                                    |
		//                   |                                                                    |
		//                   v                                                                    v
		fr.dbrown55.concrete.FIXYOURMODYOUMORON.CouldYouPleaseBeABitMorePolite.butImGonnaDoItAnyway();
	}

	public static ItemBlockConcretePowder getResult(EnumConcreteType type) {
		if(mapPowder.containsKey(type)){
			return mapPowder.get(type);
		} else {
			return null;
		}
	}
	
	public static void addResult(EnumConcreteType type, ItemBlockConcretePowder result) {
		if(getResult(type) == null){
			mapPowder.put(type, result);
		}
	}
	
}
