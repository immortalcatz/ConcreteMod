package fr.dbrown55.concrete.compat;

import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.enums.EnumConcreteType;
import moze_intel.projecte.emc.EMCMapper;
import moze_intel.projecte.emc.SimpleStack;
import moze_intel.projecte.utils.EMCHelper;
import moze_intel.projecte.utils.WorldTransmutations;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;

public class ProjectECompat {
	
	public static void init(){
		for(int i = 0; i < 16; i++){
			// Colored powder transmutation
			IBlockState from = BlockHandler.coloredP.getDefaultState().withProperty(BlockHandler.COLOR, EnumDyeColor.byMetadata(i));
			IBlockState result = BlockHandler.coloredP.getDefaultState().withProperty(BlockHandler.COLOR, EnumDyeColor.byMetadata(i == 16 ? 0 : i + 1));
			IBlockState altResult = BlockHandler.coloredP.getDefaultState().withProperty(BlockHandler.COLOR, EnumDyeColor.byMetadata(i == 0 ? 16 : i - 1));
			WorldTransmutations.register(from, result, altResult);

			// Colored solid transmutation
			from = BlockHandler.coloredS.getDefaultState().withProperty(BlockHandler.COLOR, EnumDyeColor.byMetadata(i));
			result = BlockHandler.coloredS.getDefaultState().withProperty(BlockHandler.COLOR, EnumDyeColor.byMetadata(i == 16 ? 0 : i + 1));
			altResult = BlockHandler.coloredS.getDefaultState().withProperty(BlockHandler.COLOR, EnumDyeColor.byMetadata(i == 0 ? 16 : i - 1));
			WorldTransmutations.register(from, result, altResult);
		}
		
		// Set powder EMC to solid as well
		// Also for other concretes
		for(EnumConcreteType type : EnumConcreteType.values()){
			for(int i = 0; i < 16; i++){
				ItemStack is = new ItemStack(type.getResult(), 1, i);
				SimpleStack ss = new SimpleStack(is);
				if(EMCHelper.doesItemHaveEmc(is)){
					EMCMapper.emc.put(ss, EMCMapper.getEmcValue(ss));
				}
			}
		}
	}

}
