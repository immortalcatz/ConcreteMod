package fr.dbrown55.concrete.entities;

import fr.dbrown55.concrete.Main;
import fr.dbrown55.utilmod.Handler;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityHandler extends Handler {

	@Override
	public void preInit() {
		ResourceLocation bug = new ResourceLocation(Main.MODID, "bug");
		EntityRegistry.registerModEntity(bug, EntityConcreteBug.class, "ConcreteBug", 0, Main.instance, 64, 3, false);
		EntityRegistry.registerEgg(bug, 0xFFFFFF, 0xF5F5F5);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void postInit() {
		
	}

	@Override
	public EnumHandlerPriority getPriority() {
		return EnumHandlerPriority.ENTITY;
	}

	@Override
	public String[] getDependencies(EnumHandlerPhase phase) {
		return new String[] {};
	}

	@Override
	public String[] getRequirements(EnumHandlerPhase phase) {
		return new String[] {};
	}

}
