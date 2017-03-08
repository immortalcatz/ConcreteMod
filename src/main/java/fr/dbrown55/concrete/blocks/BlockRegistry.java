package fr.dbrown55.concrete.blocks;

import java.util.HashMap;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.dbrown55.concrete.backported.EnumDyeColor;
import fr.dbrown55.concrete.items.ItemBlockConcrete;

public class BlockRegistry {

	private static HashMap<EnumDyeColor, BlockConcretePowder> concretePowders = new HashMap<EnumDyeColor, BlockConcretePowder>();
	private static HashMap<EnumDyeColor, BlockConcrete> concretes = new HashMap<EnumDyeColor, BlockConcrete>();
	
	public static void init(){
		for(EnumDyeColor color : EnumDyeColor.values()){
			concretePowders.put(color, new BlockConcretePowder(color));
			GameRegistry.registerBlock(concretePowders.get(color), ItemBlockConcrete.class, "concrete_powder_" + color.getUnlocalizedName());
			concretes.put(color, new BlockConcrete(color));
			GameRegistry.registerBlock(concretes.get(color), ItemBlockConcrete.class, "concrete_" + color.getUnlocalizedName());
		}
	}
	
	public static BlockConcretePowder getPowderFromDye(EnumDyeColor dye){
		return concretePowders.get(dye);
	}
	
	public static BlockConcrete getSolidFromDye(EnumDyeColor dye){
		return concretes.get(dye);
	}
	
}
