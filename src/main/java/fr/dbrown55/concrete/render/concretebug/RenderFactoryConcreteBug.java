package fr.dbrown55.concrete.render.concretebug;

import fr.dbrown55.concrete.entities.EntityConcreteBug;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderFactoryConcreteBug implements IRenderFactory<EntityConcreteBug> {

	public static final RenderFactoryConcreteBug instance = new RenderFactoryConcreteBug();
	
	@Override
	public Render<? super EntityConcreteBug> createRenderFor(RenderManager manager) {
		return new RenderConcreteBug(manager);
	}

}
