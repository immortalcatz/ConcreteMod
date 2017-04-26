package fr.dbrown55.concrete.client;

import fr.dbrown55.concrete.Main;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SoundHandler {

	public static SoundEvent PAINT, ERASER;
	
	public static void init() {
		PAINT = new BetterSoundEvent(new ResourceLocation(Main.MODID, "paint"));
        GameRegistry.register(PAINT);
        ERASER = new BetterSoundEvent(new ResourceLocation(Main.MODID, "eraser"));
        GameRegistry.register(ERASER);
	}
	
	
	// Just because it auto-sets its registry name
	public static class BetterSoundEvent extends SoundEvent {

		public BetterSoundEvent(ResourceLocation name) {
			super(name);
			this.setRegistryName(name);
		}
		
	}

}
