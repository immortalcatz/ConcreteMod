package fr.dbrown55.concrete.compat;

import fr.dbrown55.concrete.blocks.BlockRegistry;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.Loader;
import team.chisel.api.carving.CarvingUtils;
import team.chisel.common.carving.Carving;

public class ChiselModCompat {

	public static void init(){
		if(Loader.isModLoaded("chisel")){
			for(int i = 0; i < 16; i++){
				EnumDyeColor dye = EnumDyeColor.byMetadata(i);
				Carving.chisel.addVariation("concretePowder", BlockRegistry.getPowderFromDye(dye).getDefaultState(), i);
				Carving.chisel.addVariation("concreteSolid", BlockRegistry.getSolidFromDye(dye).getDefaultState(), i);
			}
			CarvingUtils.getChiselRegistry().registerOre("concretePowder", "concretePowder");
			CarvingUtils.getChiselRegistry().registerOre("concreteSolid", "concreteSolid");
		}
	}
	
}
