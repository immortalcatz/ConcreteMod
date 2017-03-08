package fr.dbrown55.concrete.compat;

import cpw.mods.fml.common.Loader;
import fr.dbrown55.concrete.backported.EnumDyeColor;
import fr.dbrown55.concrete.blocks.BlockRegistry;
import moze_intel.projecte.utils.MetaBlock;
import moze_intel.projecte.utils.WorldTransmutations;

public class ProjectECompat {
	
	public static void init(){
		if(Loader.isModLoaded("ProjectE")){
			for(int i = 0; i < 16; i++){
				EnumDyeColor dye = EnumDyeColor.byDyeDamage(i);
				EnumDyeColor dyeNext = EnumDyeColor.byDyeDamage(i == 0 ? 15 : i - 1);
				EnumDyeColor dyePrev = EnumDyeColor.byDyeDamage(i == 15 ? 0 : i + 1);
				WorldTransmutations.register(new MetaBlock(BlockRegistry.getPowderFromDye(dye)), new MetaBlock(BlockRegistry.getPowderFromDye(dyeNext)), new MetaBlock(BlockRegistry.getPowderFromDye(dyePrev)));
				WorldTransmutations.register(new MetaBlock(BlockRegistry.getSolidFromDye(dye)), new MetaBlock(BlockRegistry.getSolidFromDye(dyeNext)), new MetaBlock(BlockRegistry.getSolidFromDye(dyePrev)));
			}
		}
	}

}
