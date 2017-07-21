package fr.dbrown55.concrete.gui;

import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BookString {

	private String s;
	
	public BookString(String s) {
		this.s = s;
	}
	
	@Override
	public String toString() {
		return I18n.translateToLocal(this.s);
	}
	
}
