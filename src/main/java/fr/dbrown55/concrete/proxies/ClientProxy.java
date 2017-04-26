package fr.dbrown55.concrete.proxies;

import fr.dbrown55.concrete.FIXYOURMODYOUMORON.CouldYouPleaseBeABitMorePolite;
import fr.dbrown55.concrete.client.concretebug.RenderFactoryConcreteBug;
import fr.dbrown55.concrete.entities.EntityConcreteBug;
import fr.dbrown55.concrete.items.ItemHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void onPreInit() {		
		for(int i = 0; i < 16; i++){
			registerRender(ItemHandler.coloredP, i, "minecraft:" + EnumDyeColor.byMetadata(i).getName() + "_concrete_powder");
			registerRender(ItemHandler.coloredS, i, "minecraft:" + EnumDyeColor.byMetadata(i).getName() + "_concrete");
		}
		registerRender(ItemHandler.magmaP);
		registerRender(ItemHandler.magmaS);
		registerRender(ItemHandler.glowP);
		registerRender(ItemHandler.glowS);
		
		registerRender(ItemHandler.palette);
		registerRender(ItemHandler.brush);
		registerRender(ItemHandler.eraser);
		registerRender(ItemHandler.concreteBug);
		
		for(EnumDyeColor dye : EnumDyeColor.values()){
			registerRender(CouldYouPleaseBeABitMorePolite.itemsSolidToFix.get(dye), 0, "one_point_twelve_concrete:randomStringOfCharactersIncoming");
			registerRender(CouldYouPleaseBeABitMorePolite.itemsPowderToFix.get(dye), 0, "one_point_twelve_concrete:randomStringOfCharactersIncoming");
		}
		
		RenderingRegistry.registerEntityRenderingHandler(EntityConcreteBug.class, RenderFactoryConcreteBug.instance);
	}

	@Override
	public void onInit() {
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor(){

			@Override
			public int getColorFromItemstack(ItemStack stack, int tintIndex) {
				return tintIndex == 1 && stack.hasTagCompound() && stack.getTagCompound().hasKey("color", new NBTTagInt(0).getId()) ? stack.getTagCompound().getInteger("color") : 0xFFFFFF;
			}
			
		}, ItemHandler.brush, ItemHandler.concreteBug);
	}
	
	@Override
	public void onPostInit() {
		
	}

	@SideOnly(Side.CLIENT)
	private static void registerRender(Item item){
		registerRender(item, 0, item.getRegistryName());
	}
	
	@SideOnly(Side.CLIENT)
	private static void registerRender(Item item, int meta, Object loc){
		String location = "";
		if(loc instanceof String){
			location = (String)loc;
		} else if(loc instanceof ResourceLocation){
			location = ((ResourceLocation)loc).toString();
		}
		if(location == ""){
			throw new IllegalArgumentException(loc + " isn't a String or ResourceLocation");
		}
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(location, "normal"));
	}
	
}
