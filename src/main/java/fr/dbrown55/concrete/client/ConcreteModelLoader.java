package fr.dbrown55.concrete.client;

import java.util.Collection;
import java.util.HashMap;

import com.google.common.base.Function;

import fr.dbrown55.concrete.Main;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.model.IModelState;

public class ConcreteModelLoader implements ICustomModelLoader {
	
	private final IModel DEPRECATED_BLOCK_MODEL, DEPRECATED_ITEM_MODEL;
	private final HashMap<EnumDyeColor, IModel> concreteModels = new HashMap<EnumDyeColor, IModel>();
	private final HashMap<EnumDyeColor, IModel> concretePowderModels = new HashMap<EnumDyeColor, IModel>();
	
	public ConcreteModelLoader(){
		this.DEPRECATED_BLOCK_MODEL = ModelLoaderRegistry.getModelOrMissing(new ResourceLocation("one_point_twelve_concrete", "block/DEPRECATED"));
		this.DEPRECATED_ITEM_MODEL = ModelLoaderRegistry.getModelOrMissing(new ResourceLocation("one_point_twelve_concrete", "item/DEPRECATED"));
		for(EnumDyeColor dye : EnumDyeColor.values()){
			concreteModels.put(dye, ModelLoaderRegistry.getModelOrMissing(new ResourceLocation("minecraft", "block/" + dye.getName() + "_concrete")));
			concretePowderModels.put(dye, ModelLoaderRegistry.getModelOrMissing(new ResourceLocation("minecraft", "block/" + dye.getName() + "_concrete_powder")));
		}
	}
	
	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {
		// Config isn't updated with F3+T, so do nothing I guess...
	}

	@Override
	public boolean accepts(ResourceLocation modelLocation) {
		return modelLocation.getResourceDomain().equalsIgnoreCase("one_point_twelve_concrete");
	}

	@Override
	public IModel loadModel(ResourceLocation modelLocation) throws Exception {
		if(Main.config.usesDeprTextures()){
			if(modelLocation instanceof ModelResourceLocation && ((ModelResourceLocation)modelLocation).getVariant().equals("normal")){
				return DEPRECATED_BLOCK_MODEL;
			} else {
				return DEPRECATED_ITEM_MODEL;
			}
		} else {
			for(EnumDyeColor dye : EnumDyeColor.values()){
				if(modelLocation.getResourcePath().equals("concrete_" + dye.getName())) {
					return concreteModels.get(dye);
				} else if(modelLocation.getResourcePath().equals("concrete_powder_" + dye.getName())){
					return concretePowderModels.get(dye);
				}
			}
		}
		return null;
	}

}
