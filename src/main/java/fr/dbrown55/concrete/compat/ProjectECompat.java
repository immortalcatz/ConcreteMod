package fr.dbrown55.concrete.compat;

public class ProjectECompat {
	
	public static void init(){
		/*
		if(Loader.isModLoaded("ProjectE")){
			for(int i = 0; i < 16; i++){
				EnumDyeColor dye = EnumDyeColor.byDyeDamage(i);
				EnumDyeColor dyeNext = EnumDyeColor.byDyeDamage(i == 0 ? 15 : i - 1);
				EnumDyeColor dyePrev = EnumDyeColor.byDyeDamage(i == 15 ? 0 : i + 1);
				WorldTransmutations.register(BlockRegistry.getPowderFromDye(dye).getDefaultState(), BlockRegistry.getPowderFromDye(dyeNext).getDefaultState(), BlockRegistry.getPowderFromDye(dyePrev).getDefaultState());
				WorldTransmutations.register(BlockRegistry.getSolidFromDye(dye).getDefaultState(), BlockRegistry.getSolidFromDye(dyeNext).getDefaultState(), BlockRegistry.getSolidFromDye(dyePrev).getDefaultState());
			}
		}
		*/
	}

}
