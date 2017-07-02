package fr.dbrown55.concrete.proxies;

import fr.dbrown55.concrete.entities.EntityConcreteBug;
import fr.dbrown55.concrete.items.ItemHandler;
import fr.dbrown55.concrete.render.concretebug.RenderFactoryConcreteBug;
import fr.dbrown55.utilmod.BaseProxyClient;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends BaseProxyClient {

	@Override
	public void preInit() {
		for(EnumDyeColor dye : EnumDyeColor.values()) {
			registerItemModel(ItemHandler.OLD_POWDER.get(dye));
			registerItemModel(ItemHandler.OLD_SOLID.get(dye));
			
			registerItemModel(ItemHandler.VANILLA_POWDER, dye.getMetadata(), new ResourceLocation("minecraft", dye.getName() + "_concrete_powder"));
			registerItemModel(ItemHandler.VANILLA_SOLID, dye.getMetadata(), new ResourceLocation("minecraft", dye.getName() + "_concrete"));
			registerItemModel(ItemHandler.VANILLA_STAIRS, dye.getMetadata(), new ResourceLocation("concrete", "vanilla_stairs/" + dye.getName()));
			registerItemModel(ItemHandler.VANILLA_SLAB, dye.getMetadata(), new ResourceLocation("concrete", "vanilla_slab/" + dye.getName()));
			registerItemModel(ItemHandler.VANILLA_FENCE, dye.getMetadata(), new ResourceLocation("concrete", "vanilla_fence/" + dye.getName()));
			registerItemModel(ItemHandler.VANILLA_GATE, dye.getMetadata(), new ResourceLocation("concrete", "vanilla_gate/" + dye.getName()));
			registerItemModel(ItemHandler.VANILLA_STICK, dye.getMetadata(), new ResourceLocation("concrete", "vanilla_stick/" + dye.getName()));
			
			registerItemModel(ItemHandler.MAGMA_POWDER, dye.getMetadata(), new ResourceLocation("concrete", "magma_powder/" + dye.getName()));
			registerItemModel(ItemHandler.MAGMA_SOLID, dye.getMetadata(), new ResourceLocation("concrete", "magma_solid/" + dye.getName()));
			registerItemModel(ItemHandler.MAGMA_STAIRS, dye.getMetadata(), new ResourceLocation("concrete", "magma_stairs/" + dye.getName()));
			registerItemModel(ItemHandler.MAGMA_SLAB, dye.getMetadata(), new ResourceLocation("concrete", "magma_slab/" + dye.getName()));
			registerItemModel(ItemHandler.MAGMA_FENCE, dye.getMetadata(), new ResourceLocation("concrete", "magma_fence/" + dye.getName()));
			registerItemModel(ItemHandler.MAGMA_GATE, dye.getMetadata(), new ResourceLocation("concrete", "magma_gate/" + dye.getName()));
			registerItemModel(ItemHandler.MAGMA_STICK, dye.getMetadata(), new ResourceLocation("concrete", "magma_stick/" + dye.getName()));
			
			registerItemModel(ItemHandler.GLOWSTONE_POWDER, dye.getMetadata(), new ResourceLocation("concrete", "glowcrete_powder/" + dye.getName()));
			registerItemModel(ItemHandler.GLOWSTONE_SOLID, dye.getMetadata(), new ResourceLocation("concrete", "glowcrete_solid/" + dye.getName()));
			registerItemModel(ItemHandler.GLOWSTONE_STAIRS, dye.getMetadata(), new ResourceLocation("concrete", "glowcrete_stairs/" + dye.getName()));
			registerItemModel(ItemHandler.GLOWSTONE_SLAB, dye.getMetadata(), new ResourceLocation("concrete", "glowcrete_slab/" + dye.getName()));
			registerItemModel(ItemHandler.GLOWSTONE_FENCE, dye.getMetadata(), new ResourceLocation("concrete", "glowcrete_fence/" + dye.getName()));
			registerItemModel(ItemHandler.GLOWSTONE_GATE, dye.getMetadata(), new ResourceLocation("concrete", "glowcrete_gate/" + dye.getName()));
			registerItemModel(ItemHandler.GLOWSTONE_STICK, dye.getMetadata(), new ResourceLocation("concrete", "glowcrete_stick/" + dye.getName()));
			
			registerItemModel(ItemHandler.REDSTONE_POWDER, dye.getMetadata(), new ResourceLocation("concrete", "redcrete_powder/" + dye.getName()));
			registerItemModel(ItemHandler.REDSTONE_SOLID, dye.getMetadata(), new ResourceLocation("concrete", "redcrete_solid/" + dye.getName()));
			registerItemModel(ItemHandler.REDSTONE_STAIRS, dye.getMetadata(), new ResourceLocation("concrete", "redcrete_stairs/" + dye.getName()));
			registerItemModel(ItemHandler.REDSTONE_SLAB, dye.getMetadata(), new ResourceLocation("concrete", "redcrete_slab/" + dye.getName()));
			
			registerItemModel(ItemHandler.BRUSH);
			registerItemModel(ItemHandler.ERASER);
			registerItemModel(ItemHandler.PALETTE);
			registerItemModel(ItemHandler.CONCRETE_BUG);
			
			RenderingRegistry.registerEntityRenderingHandler(EntityConcreteBug.class, RenderFactoryConcreteBug.instance);
		}
	}

	@Override
	public void init() {
		IItemColor color = new IItemColor() {
			@Override
			public int getColorFromItemstack(ItemStack stack, int tintIndex) {
				return tintIndex == 1 && stack.hasTagCompound() && stack.getTagCompound().hasKey("color", new NBTTagInt(0).getId()) ? stack.getTagCompound().getInteger("color") : 0xFFFFFF;
			}
		};
		
		registerItemColor(color, ItemHandler.BRUSH);
		registerItemColor(color, ItemHandler.CONCRETE_BUG);
	}

	@Override
	public void postInit() { }

}
