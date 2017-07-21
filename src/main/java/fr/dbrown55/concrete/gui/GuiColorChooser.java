package fr.dbrown55.concrete.gui;

import java.awt.Color;
import java.io.IOException;

import fr.dbrown55.concrete.Main;
import fr.dbrown55.concrete.entities.EntityConcreteBug;
import fr.dbrown55.concrete.items.ItemHandler;
import fr.dbrown55.concrete.net.MessageSetPaint;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiPageButtonList.GuiResponder;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlider;
import net.minecraft.client.gui.GuiSlider.FormatHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;

public class GuiColorChooser extends GuiScreen {

	public static final int ID = 0;
	private int color = MapColor.SNOW.colorValue;
	
	private static final EntityConcreteBug bug = new EntityConcreteBug(Minecraft.getMinecraft().world);
	
	private static GuiResponder res = new GuiResponder() {
		@Override
		public void setEntryValue(int id, String value) {}
		
		@Override
		public void setEntryValue(int id, float value) {}
		
		@Override
		public void setEntryValue(int id, boolean value) {}
	};
	
	private static FormatHelper help = new FormatHelper() {
		@Override
		public String getText(int id, String name, float value) {
			return name + (int) value;
		}
	};
	
	private static GuiSlider slidR, slidG, slidB;
	
	public GuiColorChooser() {

	}
	
	@Override
	public void initGui() {
		super.initGui();
		bug.rotationYaw = 0.0F;
		bug.setSolid(true);
		this.buttonList.clear();
		for(EnumDyeColor col : EnumDyeColor.values()){
			this.buttonList.add(new GuiColorButton(col.getMetadata(), (this.width/2) - 200 + (col.getMetadata() * 25), 10, col.getMapColor().colorValue));
		}
		Color col = Color.WHITE;
		EntityPlayer pl = Minecraft.getMinecraft().player;
		ItemStack stack = pl.getHeldItemMainhand();
		if(stack != null && stack.hasTagCompound()) {
			NBTTagCompound nbt = stack.getTagCompound();
			if(nbt.hasKey("color")) {
				col = new Color(nbt.getInteger("color"));
			}
		}
		this.buttonList.add(slidR = new GuiSlider(res, 20, 10, 80, "Red: ", 0.0F, 255.0F, col.getRed(), help));
		this.buttonList.add(slidG = new GuiSlider(res, 21, 10, 105, "Green: ", 0.0F, 255.0F, col.getGreen(), help));
		this.buttonList.add(slidB = new GuiSlider(res, 22, 10, 130, "Blue: ", 0.0F, 255.0F, col.getBlue(), help));
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button instanceof GuiColorButton){
			this.color = ((GuiColorButton)button).getColor();
			Color col = new Color(this.color);
			this.slidR.setSliderValue(col.getRed(), false);
			this.slidG.setSliderValue(col.getGreen(), false);
			this.slidB.setSliderValue(col.getBlue(), false);
			this.bug.setColor(this.color);
		}
		super.actionPerformed(button);
	}
	
	@Override
	public void updateScreen() {
		super.updateScreen();
		this.updateColor();
		EntityPlayer pl = Minecraft.getMinecraft().player;
		ItemStack stack = pl.getHeldItemMainhand();
		if(stack == null || stack.getItem() != ItemHandler.BRUSH){
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		bug.rotationYaw += 3.0F*partialTicks;
		drawEntityOnScreen(this.width - 100, this.height/2 + 20, 90, bug);
	}
	
	public static void drawEntityOnScreen(int posX, int posY, int scale, EntityLivingBase ent) {
		GlStateManager.enableColorMaterial();
		GlStateManager.pushMatrix();
		GlStateManager.translate((float)posX, (float)posY, 50.0F);
		GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
		GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
		float f = ent.renderYawOffset;
		float f1 = ent.rotationYaw;
		float f2 = ent.rotationPitch;
		float f3 = ent.prevRotationYawHead;
		float f4 = ent.rotationYawHead;
		GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(22.5F, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(ent.rotationYaw, 0.0F, 1.0F, 0.0F);
		GlStateManager.translate(0.0F, 0.0F, 0.0F);
		RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
		rendermanager.setPlayerViewY(180.0F);
		rendermanager.setRenderShadow(false);
		rendermanager.doRenderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
		rendermanager.setRenderShadow(true);
		ent.renderYawOffset = f;
		ent.rotationYaw = f1;
		ent.rotationPitch = f2;
		ent.prevRotationYawHead = f3;
		ent.rotationYawHead = f4;
		GlStateManager.popMatrix();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableRescaleNormal();
		GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GlStateManager.disableTexture2D();
		GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}
	
	public void updateColor(){
		this.color = new Color((int)this.slidR.getSliderValue(), (int)this.slidG.getSliderValue(), (int)this.slidB.getSliderValue()).getRGB();
		bug.setColor(this.color);
	}
	
	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		Main.wrapper.sendToServer(new MessageSetPaint(this.color));
	}
	
	private class GuiColorButton extends GuiButton {

		private int color = MapColor.SNOW.colorValue;
		
		public GuiColorButton(int buttonId, int x, int y, int color) {
			super(buttonId, x, y, 20, 20, "");
			this.color = color;
		}
		
		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY) {
			super.drawButton(mc, mouseX, mouseY);
			if(this.visible){
				this.drawRect(this.xPosition + 2, this.yPosition + 2, this.xPosition + 18, this.yPosition + 18, 0xFF000000 + this.color);
			}
		}
		
		public int getColor(){
			return this.color;
		}
		
	}

}
