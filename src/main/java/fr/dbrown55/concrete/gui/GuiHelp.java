package fr.dbrown55.concrete.gui;

import java.io.IOException;

import fr.dbrown55.concrete.Main;
import fr.dbrown55.concrete.gui.DrawnCraftingGrid.AnimatedDrawnCraftingGrid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

public class GuiHelp extends GuiScreen {

	public static final ResourceLocation bookLeft = new ResourceLocation(Main.MODID, "textures/gui/book_left.png");
	public static final ResourceLocation bookRight = new ResourceLocation("minecraft", "textures/gui/book.png");
	public static final ResourceLocation bookFirstPage = new ResourceLocation(Main.MODID, "textures/gui/book_first_page.png");
	public static final ResourceLocation bookLastPage = new ResourceLocation(Main.MODID, "textures/gui/book_last_page.png");
	
	private int pageWidth, pageHeight, xSize, ySize, guiTop, guiLeft;
	
	private int page;
	private BookPage thePage;
	
	private GuiButton done, nextPage, prevPage;
	
	public GuiHelp() {
		this.pageWidth = 146;
		this.pageHeight = 180;
		this.xSize = this.pageWidth*2;
		this.ySize = this.pageHeight + 40;
		
		BookPage.initPages();
		this.page = 0;
		this.thePage = BookPage.getPageFromNumber(0);
	}
	
	@Override
	public void initGui() {
		super.initGui();
		this.guiLeft = (this.width - this.xSize) / 2;
		this.guiTop = (this.height - this.ySize) / 2;
		
		this.buttonList.clear();
		this.done = this.addButton(new GuiButton(0, this.guiLeft + this.pageWidth/2, this.guiTop + 200, this.pageWidth, 20, "Close"));
		this.nextPage = this.addButton(new GuiHelp.PageButton(1, this.guiLeft + this.xSize - 43, this.pageHeight - 25, true));
		this.prevPage = this.addButton(new GuiHelp.PageButton(2, this.guiLeft + 15, this.pageHeight - 25, false));
		this.prevPage.visible = false;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		if(this.hasPrevPage()){
			this.mc.getTextureManager().bindTexture(bookLeft);
		} else {
			this.mc.getTextureManager().bindTexture(bookFirstPage);
		}
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.pageWidth, this.pageHeight);
		if(this.hasNextPage()){
			this.mc.getTextureManager().bindTexture(bookRight);
		} else {
			this.mc.getTextureManager().bindTexture(bookLastPage);
		}
		this.drawTexturedModalRect(this.guiLeft + this.pageWidth, this.guiTop, 20, 1, this.pageWidth, this.pageHeight);
		
		if(this.thePage != null){
			BookString[] leftPage = this.thePage.getLeftPageContents();
			for(int i = 0; i < leftPage.length; i++) {
				String s = leftPage[i].toString();
				this.drawStringCenteredOnPage(s, i*10, 0x000000, EnumPageSide.LEFT);
			}
			
			BookString[] rightPage = this.thePage.getRightPageContents();
			for(int i = 0; i < rightPage.length; i++) {
				String s = rightPage[i].toString();
				this.drawStringCenteredOnPage(s, i*10, 0x000000, EnumPageSide.RIGHT);
			}
			
			if(this.thePage.hasDrawnGrid()){
				this.thePage.getDrawnGrid().draw(this.mc, this.guiLeft + (this.pageWidth/2) - 47, this.guiTop + (this.ySize/2) - 50);
			}
		}

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void updateScreen() {
		if(this.thePage != null && this.thePage.hasDrawnGrid()){
			DrawnCraftingGrid grid = this.thePage.getDrawnGrid();
			if(grid instanceof AnimatedDrawnCraftingGrid) {
				((AnimatedDrawnCraftingGrid)grid).onTick();
			}
		}
		super.updateScreen();
	}
	
	public void drawStringCenteredOnPage(String text, int y, int color, EnumPageSide page){
		FontRenderer font = this.mc.fontRendererObj;
		font.drawString(text, this.guiLeft + (page == EnumPageSide.RIGHT ? this.pageWidth : 0) + 14 + (116 - font.getStringWidth(text))/2, this.guiTop + 31 + y, color);
	}
	
	public boolean hasNextPage() {
		return this.page < BookPage.getPageCount() - 1;
	}
	
	public void nextPage() {
		this.setPage(this.page + 1);
	}
	
	public boolean hasPrevPage() {
		return this.page > 0;
	}
	
	public void prevPage() {
		this.setPage(this.page - 1);
	}
	
	public void setPage(int page) {
		BookPage tmp = BookPage.getPageFromNumber(page);
		if(tmp != null) {
			this.page = page;
			this.thePage = tmp;
			this.prevPage.visible = this.hasPrevPage();
			this.nextPage.visible = this.hasNextPage();
		}
	}
	
	public void setPage(String name) {
		int pageNumber = BookPage.getPageNumberFromName(name);
		if(pageNumber != -1) {
			setPage(pageNumber);
		}
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		boolean clicked = false;
		FontRenderer font = this.mc.fontRendererObj;
		if(mouseButton == 0) {
			BookString[] leftPage = this.thePage.getLeftPageContents();
			BookString[] rightPage = this.thePage.getRightPageContents();
			for(int i = 0; i < leftPage.length; i++) {
				if(!clicked) {
					BookString line = leftPage[i];
					String text = I18n.translateToLocal(line.toString());
					int left = this.guiLeft + 14 + (116 - font.getStringWidth(text))/2;
					int top = this.guiTop + 31 + (i*10);
					if(mouseX >= left && mouseX <= left+font.getStringWidth(text) && mouseY >= top && mouseY <= top + font.FONT_HEIGHT) {
						if(line instanceof BookStringClickable) {
							((BookStringClickable)line).click();
							clicked = true;
						}
					}
				}
			}
			for(int i = 0; i < rightPage.length; i++) {
				if(!clicked) {
					BookString line = rightPage[i];
					String text = I18n.translateToLocal(line.toString());
					int left = this.guiLeft + this.pageWidth + 14 + (116 - font.getStringWidth(text))/2;
					int top = this.guiTop + 31 + (i*10);
					if(mouseX >= left && mouseX <= left+font.getStringWidth(text) && mouseY >= top && mouseY <= top + font.FONT_HEIGHT) {
						if(line instanceof BookStringClickable) {
							((BookStringClickable)line).click();
							clicked = true;
						}
					}
				}
			}
		}
		
		if(!clicked) {
			super.mouseClicked(mouseX, mouseY, mouseButton);
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button == this.done) {
			Minecraft.getMinecraft().displayGuiScreen((GuiScreen)null);
		} else if(button == this.nextPage){
			this.nextPage();
		} else if(button == this.prevPage){
			this.prevPage();
		}
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	public static class PageButton extends GuiButton {
		
		private final boolean isForward;
		
		public PageButton(int id, int x, int y, boolean isForward){
			super(id, x, y, 23, 13, "");
			this.isForward = isForward;
		}
		
		public boolean isForward(){
			return this.isForward;
		}
		
		public void drawButton(Minecraft mc, int mouseX, int mouseY) {
            if(this.visible) {
            	boolean flag = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            	GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            	mc.getTextureManager().bindTexture(GuiHelp.bookRight);
            	int i = 0;
            	int j = 192;

            	if(flag) {
            		i += 23;
            	}

            	if(!this.isForward) {
            		j += 13;
            	}

            	this.drawTexturedModalRect(this.xPosition, this.yPosition, i, j, 23, 13);
            }
		}
	}
	
	public static enum EnumPageSide {
		LEFT,
		RIGHT;
	}
	
}
