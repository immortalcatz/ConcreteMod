package fr.dbrown55.concrete;

import java.io.File;
import java.util.Calendar;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;

public class ConcreteConf extends Configuration {

	private boolean USE_VANILLA_BEHAVIOR, APRIL_FOOLS, PROJECTE_COMPAT, CHISEL_COMPAT, USE_DEPR_TEXTURES, FULL_RS_POWDER;
	
	public ConcreteConf(File file){
		super(file);
		
		this.USE_VANILLA_BEHAVIOR = this.getBoolean("vanillaBehavior", "misc", false, "Whenever the falling concrete should act like in vanilla (might not be EXACTLY like in vanilla)");
		
		Calendar c = Calendar.getInstance();
		boolean confOption = this.getBoolean("aprilFools", "misc", false, "Whenever we should enable the AprilFools update features ?");
		if(c.get(Calendar.DAY_OF_MONTH) == 1 && c.get(Calendar.MONTH) == 3){
			this.APRIL_FOOLS = true;
		} else {
			this.APRIL_FOOLS = confOption;
		}
		
		this.PROJECTE_COMPAT = this.getBoolean("projecte", "modcompat", true, "Whenever we should enable ProjectE compatibility") && Loader.isModLoaded("projecte");
		
		this.CHISEL_COMPAT = this.getBoolean("chisel", "modcompat", true, "Whenever we should enable Chisel compatibility") && Loader.isModLoaded("chisel");
		
		this.USE_DEPR_TEXTURES = this.getBoolean("useDeprecatedTextures", "client", true, "Whenever we should use the Java pun texture");
		
		this.FULL_RS_POWDER = this.getBoolean("fullRedstonePower", "misc", false, "Whenever the redstone concrete blocks should output 15 no matter what");
		
		this.save();
	}

	public boolean usesVanillaHehavior() {
		return USE_VANILLA_BEHAVIOR;
	}

	public boolean isAprilFools() {
		return APRIL_FOOLS;
	}

	public boolean isProjectECompatOn() {
		return PROJECTE_COMPAT;
	}

	public boolean isChiselCompatOn() {
		return CHISEL_COMPAT;
	}

	public boolean usesDeprTextures() {
		return USE_DEPR_TEXTURES;
	}
	
	public boolean isFullRsPower() {
		return FULL_RS_POWDER;
	}
	
}
