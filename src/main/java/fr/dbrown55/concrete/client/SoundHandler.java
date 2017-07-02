package fr.dbrown55.concrete.client;

import fr.dbrown55.concrete.Main;
import fr.dbrown55.utilmod.Handler;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SoundHandler extends Handler {

	public static SoundEvent PAINT, ERASER;
	
	@Override
	public void preInit() {
		PAINT = new BetterSoundEvent(new ResourceLocation(Main.MODID, "paint"));
		ERASER = new BetterSoundEvent(new ResourceLocation(Main.MODID, "eraser"));
	}

	@Override
	public void init() {
	
	}

	@Override
	public void postInit() {
		
	}

	@Override
	public EnumHandlerPriority getPriority() {
		return EnumHandlerPriority.SOUND;
	}

	@Override
	public String[] getDependencies(EnumHandlerPhase phase) {
		return new String[] {};
	}

	@Override
	public String[] getRequirements(EnumHandlerPhase phase) {
		return new String[] {};
	}
	
	// Just because it auto-sets its registry name
	public static class BetterSoundEvent extends SoundEvent {

		public BetterSoundEvent(ResourceLocation name) {
			super(name);
			this.setRegistryName(name);
			GameRegistry.register(this);
		}
		
	}
	
}
