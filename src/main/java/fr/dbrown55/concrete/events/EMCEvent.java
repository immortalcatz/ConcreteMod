package fr.dbrown55.concrete.events;

import fr.dbrown55.concrete.items.ItemHandler;
import moze_intel.projecte.api.event.EMCRemapEvent;
import moze_intel.projecte.emc.EMCMapper;
import moze_intel.projecte.emc.SimpleStack;
import moze_intel.projecte.utils.EMCHelper;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EMCEvent {

	/**
	 * This event method is responsible of setting EMC values on all the concrete stuff (besides powder)
	 * @param event
	 */
	@SubscribeEvent
	public void onEMCMapping(EMCRemapEvent event) {
		for(EnumDyeColor dye : EnumDyeColor.values()) {
			if(EMCHelper.doesItemHaveEmc(new ItemStack(ItemHandler.VANILLA_POWDER, 1, dye.getMetadata()))) {
				int baseValue = EMCHelper.getEmcValue(new ItemStack(ItemHandler.VANILLA_POWDER, 1, dye.getMetadata()));
				EMCMapper.emc.put(new SimpleStack(new ItemStack(ItemHandler.VANILLA_SOLID, 1, dye.getMetadata())), baseValue);
				EMCMapper.emc.put(new SimpleStack(new ItemStack(ItemHandler.VANILLA_SLAB, 1, dye.getMetadata())), baseValue/2);
				EMCMapper.emc.put(new SimpleStack(new ItemStack(ItemHandler.VANILLA_STAIRS, 1, dye.getMetadata())), (int) (baseValue*(3.0/4.0)));
				EMCMapper.emc.put(new SimpleStack(new ItemStack(ItemHandler.VANILLA_STICK, 1, dye.getMetadata())), baseValue/4);
				EMCMapper.emc.put(new SimpleStack(new ItemStack(ItemHandler.VANILLA_FENCE, 1, dye.getMetadata())), (int) (baseValue*1.4));
				EMCMapper.emc.put(new SimpleStack(new ItemStack(ItemHandler.VANILLA_GATE, 1, dye.getMetadata())), baseValue*3);
			}
			
			if(EMCHelper.doesItemHaveEmc(new ItemStack(ItemHandler.MAGMA_POWDER, 1, dye.getMetadata()))) {
				int baseValue = EMCHelper.getEmcValue(new ItemStack(ItemHandler.MAGMA_POWDER, 1, dye.getMetadata()));
				EMCMapper.emc.put(new SimpleStack(new ItemStack(ItemHandler.MAGMA_SOLID, 1, dye.getMetadata())), baseValue);
				EMCMapper.emc.put(new SimpleStack(new ItemStack(ItemHandler.MAGMA_SLAB, 1, dye.getMetadata())), baseValue/2);
				EMCMapper.emc.put(new SimpleStack(new ItemStack(ItemHandler.MAGMA_STAIRS, 1, dye.getMetadata())), (int) (baseValue*(3.0/4.0)));
				EMCMapper.emc.put(new SimpleStack(new ItemStack(ItemHandler.MAGMA_STICK, 1, dye.getMetadata())), baseValue/4);
				EMCMapper.emc.put(new SimpleStack(new ItemStack(ItemHandler.MAGMA_FENCE, 1, dye.getMetadata())), (int) (baseValue*1.4));
				EMCMapper.emc.put(new SimpleStack(new ItemStack(ItemHandler.MAGMA_GATE, 1, dye.getMetadata())), baseValue*3);
			}
			
			if(EMCHelper.doesItemHaveEmc(new ItemStack(ItemHandler.GLOWSTONE_POWDER, 1, dye.getMetadata()))) {
				int baseValue = EMCHelper.getEmcValue(new ItemStack(ItemHandler.GLOWSTONE_POWDER, 1, dye.getMetadata()));
				EMCMapper.emc.put(new SimpleStack(new ItemStack(ItemHandler.GLOWSTONE_SOLID, 1, dye.getMetadata())), baseValue);
				EMCMapper.emc.put(new SimpleStack(new ItemStack(ItemHandler.GLOWSTONE_SLAB, 1, dye.getMetadata())), baseValue/2);
				EMCMapper.emc.put(new SimpleStack(new ItemStack(ItemHandler.GLOWSTONE_STAIRS, 1, dye.getMetadata())), (int) (baseValue*(3.0/4.0)));
				EMCMapper.emc.put(new SimpleStack(new ItemStack(ItemHandler.GLOWSTONE_STICK, 1, dye.getMetadata())), baseValue/4);
				EMCMapper.emc.put(new SimpleStack(new ItemStack(ItemHandler.GLOWSTONE_FENCE, 1, dye.getMetadata())), (int) (baseValue*1.4));
				EMCMapper.emc.put(new SimpleStack(new ItemStack(ItemHandler.GLOWSTONE_GATE, 1, dye.getMetadata())), baseValue*3);
			}
			
			if(EMCHelper.doesItemHaveEmc(new ItemStack(ItemHandler.REDSTONE_POWDER, 1, dye.getMetadata()))) {
				int baseValue = EMCHelper.getEmcValue(new ItemStack(ItemHandler.REDSTONE_POWDER, 1, dye.getMetadata()));
				EMCMapper.emc.put(new SimpleStack(new ItemStack(ItemHandler.REDSTONE_SOLID, 1, dye.getMetadata())), baseValue);
				EMCMapper.emc.put(new SimpleStack(new ItemStack(ItemHandler.REDSTONE_SLAB, 1, dye.getMetadata())), baseValue/2);
				EMCMapper.emc.put(new SimpleStack(new ItemStack(ItemHandler.REDSTONE_STAIRS, 1, dye.getMetadata())), (int) (baseValue*(3.0/4.0)));
			}
		}
	}
	
}
