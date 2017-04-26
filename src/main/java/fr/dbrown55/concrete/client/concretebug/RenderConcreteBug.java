package fr.dbrown55.concrete.client.concretebug;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import fr.dbrown55.concrete.entities.EntityConcreteBug;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderConcreteBug extends RenderLiving<EntityConcreteBug> {
	
	private ResourceLocation TEXTURE_NORMAL = new ResourceLocation("concrete", "textures/entity/concrete_bug.png");
	private ResourceLocation TEXTURE_SOLID = new ResourceLocation("concrete", "textures/entity/concrete_bug_solid.png");
	
	protected RenderConcreteBug(RenderManager renderManager) {
		super(renderManager, new ModelConcreteBug(), 0.3F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityConcreteBug entity) {
		return entity.isSolid() ? TEXTURE_SOLID : TEXTURE_NORMAL;
	}
	
	@Override
	protected boolean canRenderName(EntityConcreteBug entity) {
		return entity.isSolid() ? false : super.canRenderName(entity);
	}

	@Override
	protected void renderModel(EntityConcreteBug entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		Color col = new Color(entitylivingbaseIn.getColor());
		GL11.glColor3f( ((float)col.getRed()) /255.0f, ((float)col.getGreen()) /255.0f, ((float)col.getBlue()) /255.0f);
		super.renderModel(entitylivingbaseIn, limbSwing, limbSwingAmount, entitylivingbaseIn.isSolid() ? 0.0f : ageInTicks, netHeadYaw, headPitch, scaleFactor);
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
	}

}
