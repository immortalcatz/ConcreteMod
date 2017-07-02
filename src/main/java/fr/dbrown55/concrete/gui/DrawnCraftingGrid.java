package fr.dbrown55.concrete.gui;

import javax.annotation.Nonnull;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class DrawnCraftingGrid extends Gui {

	protected DrawnRecipe recipe;
	
	public DrawnCraftingGrid(DrawnRecipe recipe) {
		this.recipe = recipe;
	}
	
	public void draw(Minecraft mc, int x, int y) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(GuiHelp.bookLeft);
		this.drawTexturedModalRect(x, y, 146, 0, 94, 56);
		RenderHelper.enableGUIStandardItemLighting();
		for(int slotx = 0; slotx < 3; slotx++) {
			for(int sloty = 0; sloty < 3; sloty++) {
				int sloti = (sloty*3)+slotx;
				if(this.recipe != null) {
					ItemStack stack = this.recipe.getInputStack(sloti);
					if(stack != null) {
						mc.getRenderItem().renderItemAndEffectIntoGUI(stack, x + 5 + (slotx*17), y + 4 + (sloty*17));
					}
				}
			}
		}
		if(this.recipe != null) {
			ItemStack output = this.recipe.getOutputStack();
			mc.getRenderItem().renderItemAndEffectIntoGUI(output, x + 76, y + 21);
			mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRendererObj, output, x + 76, y + 21, null);
		}
		GlStateManager.enableLighting();
	}
	
	public void setSlotContent(int sloti, ItemStack stack) {
		if(sloti >= 0 && sloti < 10) {
			if(sloti == 9) {
				
			} else {
				this.recipe.setInputStack(sloti, stack);
			}
		}
	}
	
	public static class DrawnRecipe {
		
		private ItemStack output;
		private SingleStack[] input;
		
		public DrawnRecipe(@Nonnull ItemStack output, ItemStack... input) {
			this.output = output;
			this.input = new SingleStack[9];
			for(int i = 0; i < Math.min(input.length, 9); i++) {
				if(input[i] != null) {
					this.input[i] = new SingleStack(input[i]);
				} else {
					this.input[i] = null;
				}
			}
		}

		public ItemStack getInputStack(int sloti) {
			if(sloti >= 0 && sloti < 9) {
				return this.input[sloti] != null ? this.input[sloti].toIS() : null;
			}
			return null;
		}
		
		public void setInputStack(int sloti, ItemStack stack) {
			if(sloti >= 0 && sloti < 9) {
				this.input[sloti] = stack == null ? null : new SingleStack(stack);
			}
		}
		
		public ItemStack getOutputStack() {
			return this.output;
		}
		
		public void setOutputStack(ItemStack stack) {
			this.output = stack;
		}
		
	}
	
	public static class SingleStack {
		
		private Item item;
		private int data;
		private NBTTagCompound nbt;
		
		public SingleStack(ItemStack original) {
			this.item = original.getItem();
			this.data = original.getItemDamage();
			this.nbt = original.hasTagCompound() ? original.getTagCompound() : null;
		}
		
		public Item getItem() {
			return this.item;
		}
		
		public int getItemDamage() {
			return this.data;
		}
		
		public boolean hasTagCompound() {
			return this.nbt != null;
		}
		
		public NBTTagCompound getTagCompound() {
			return this.nbt;
		}
		
		public ItemStack toIS() {
			ItemStack returned = new ItemStack(this.item, 1, this.data);
			if(this.nbt != null) {
				returned.setTagCompound(this.nbt);
			}
			return returned;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof SingleStack || obj instanceof ItemStack)){
				return false;
			}
			if(obj instanceof ItemStack) {
				return this.toIS().equals(obj);
			} else {
				SingleStack ss = (SingleStack)obj;
				boolean flag = true;
				if(this.getItem() != ss.getItem()) {
					flag = false;
				} else if(this.getItemDamage() != ss.getItemDamage()) {
					flag = false;
				} else if(this.hasTagCompound() != ss.hasTagCompound()) {
					flag = false;
				} else if(this.hasTagCompound() && !this.getTagCompound().equals(ss.getTagCompound())) {
					flag = false;
				}
				
				return flag;
			}
		}
		
	}
	
	public static class AnimatedDrawnCraftingGrid extends DrawnCraftingGrid {
	
		private DrawnRecipe[] recipes;
		private int recipeIndex, recipeTime, recipeTimer;
		
		public AnimatedDrawnCraftingGrid(DrawnRecipe[] recipes, int time) {
			super(recipes[0]);
			this.recipes = recipes;
			this.recipe = this.recipes[0];
			this.recipeIndex = 0;
			this.recipeTime = time;
			this.recipeTimer = 0;
		}
		
		public void onTick() {
			this.recipeTimer ++;
			if(this.recipeTimer == this.recipeTime) {
				this.recipeIndex ++;
				if(this.recipeIndex == this.recipes.length) {
					this.recipeIndex = 0;
				}
				this.recipe = this.recipes[this.recipeIndex];
				this.recipeTimer = 0;
			}
		}
	}
	
}
