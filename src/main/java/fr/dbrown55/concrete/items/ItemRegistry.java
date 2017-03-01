package fr.dbrown55.concrete.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.dbrown55.concrete.blocks.BlockConcrete;
import fr.dbrown55.concrete.blocks.BlockConcretePowder;
import fr.dbrown55.concrete.blocks.BlockRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRegistry {

	private static HashMap<EnumDyeColor, ItemBlockAuto> concretePowders = new HashMap<EnumDyeColor, ItemBlockAuto>();
	private static HashMap<EnumDyeColor, ItemBlockAuto> concretes = new HashMap<EnumDyeColor, ItemBlockAuto>();
	
	public static void init(){
		for(EnumDyeColor color : EnumDyeColor.values()){
			concretePowders.put(color, new ItemBlockAuto(BlockRegistry.getPowderFromDye(color)));
			GameRegistry.register(concretePowders.get(color));
			concretes.put(color, new ItemBlockAuto(BlockRegistry.getSolidFromDye(color)));
			GameRegistry.register(concretes.get(color));
		}
	}

	public static ItemBlockAuto getPowderFromDye(EnumDyeColor dye){
		return concretePowders.get(dye);
	}
	
	public static List<ItemStack> getPowderStacks(){
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		for(Item powder : concretePowders.values()){
			list.add(new ItemStack(powder));
		}
		return list;
	}
	
	public static ItemBlockAuto getSolidFromDye(EnumDyeColor dye){
		return concretes.get(dye);
	}
	
	public static List<ItemStack> getSolidStacks(){
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		for(Item powder : concretes.values()){
			list.add(new ItemStack(powder));
		}
		return list;
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerRenders() {
		for(EnumDyeColor color : EnumDyeColor.values()){
			registerARender(concretePowders.get(color));
			registerARender(concretes.get(color));
		}
	}
	
	@SideOnly(Side.CLIENT)
	private static void registerARender(Item item){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
}
