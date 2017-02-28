package fr.dbrown55.concrete.blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockRegistry {

	private static HashMap<EnumDyeColor, BlockConcretePowder> concretePowders = new HashMap<EnumDyeColor, BlockConcretePowder>();
	private static HashMap<EnumDyeColor, BlockConcrete> concretes = new HashMap<EnumDyeColor, BlockConcrete>();
	
	public static void init(){
		for(EnumDyeColor color : EnumDyeColor.values()){
			concretePowders.put(color, new BlockConcretePowder(color));
			GameRegistry.register(concretePowders.get(color));
			concretes.put(color, new BlockConcrete(color));
			GameRegistry.register(concretes.get(color));
		}
	}
	
	public static BlockConcretePowder getPowderFromDye(EnumDyeColor dye){
		return concretePowders.get(dye);
	}
	
	public static BlockConcrete getSolidFromDye(EnumDyeColor dye){
		return concretes.get(dye);
	}
	
}
