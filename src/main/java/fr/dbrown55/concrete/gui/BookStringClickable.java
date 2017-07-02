package fr.dbrown55.concrete.gui;

import java.awt.Desktop;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.entity.player.EntityPlayer.EnumChatVisibility;
import net.minecraft.util.text.TextFormatting;

public class BookStringClickable extends BookString implements GuiYesNoCallback {

	private EnumClickAction action;
	private String actionResult;
	
	public BookStringClickable(String s, EnumClickAction action, String actionResult) {
		super(s);
		this.action = action;
		this.actionResult = actionResult;
	}
	
	public void click() {
		if(this.action == EnumClickAction.OPEN_URL) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiConfirmOpenLink(this, this.actionResult, 0, false));
		} else if(this.action == EnumClickAction.SET_BOOK_PAGE) {
			GuiScreen screen = Minecraft.getMinecraft().currentScreen;
			if(screen instanceof GuiHelp) {
				GuiHelp help = (GuiHelp) screen;
				if(StringUtils.isNumeric(this.actionResult)) {
					help.setPage(Integer.parseInt(this.actionResult));
				} else {
					help.setPage(this.actionResult);
				}
			}
		}
	}

	@Override
	public void confirmClicked(boolean result, int id) {
		if(id == 0 && result) {
			Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
			if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
				try {
					desktop.browse(new URL(this.actionResult).toURI());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
	}
	
	@Override
	public String toString() {
		return TextFormatting.BLUE.toString() + TextFormatting.UNDERLINE.toString() + TextFormatting.getTextWithoutFormattingCodes(super.toString());
	}
	
	public static enum EnumClickAction {
		OPEN_URL,
		SET_BOOK_PAGE;
	}
	
}
