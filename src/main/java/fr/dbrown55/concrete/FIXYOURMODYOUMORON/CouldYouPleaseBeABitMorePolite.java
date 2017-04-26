package fr.dbrown55.concrete.FIXYOURMODYOUMORON;

import java.util.HashMap;

import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.items.ItemHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/** When a dev is still programming at 11:40 PM, he starts naming his packages/classes/methods/attributes with weird names.
* And he puts weird comments too.
*/
public class CouldYouPleaseBeABitMorePolite {

	public static HashMap<EnumDyeColor, DeprecatedBlock> blocksSolidToFix = new HashMap<EnumDyeColor, DeprecatedBlock>();
	public static HashMap<EnumDyeColor, DeprecatedBlock> blocksPowderToFix = new HashMap<EnumDyeColor, DeprecatedBlock>();
	public static HashMap<EnumDyeColor, DeprecatedItem> itemsSolidToFix = new HashMap<EnumDyeColor, DeprecatedItem>();
	public static HashMap<EnumDyeColor, DeprecatedItem> itemsPowderToFix = new HashMap<EnumDyeColor, DeprecatedItem>();
	
	public static void butImGonnaDoItAnyway(){
		for(EnumDyeColor dye : EnumDyeColor.values()){
			ResourceLocation rlp = new ResourceLocation("one_point_twelve_concrete:concrete_powder_" + dye.getName());
			ResourceLocation rls = new ResourceLocation("one_point_twelve_concrete:concrete_" + dye.getName());
			
			HashMap<Integer, IBlockState> lstBP = new HashMap<Integer, IBlockState>();
			lstBP.put(0, BlockHandler.coloredP.getDefaultState().withProperty(BlockHandler.COLOR, dye));
			HashMap<Integer, IBlockState> lstBS = new HashMap<Integer, IBlockState>();
			lstBS.put(0, BlockHandler.coloredS.getDefaultState().withProperty(BlockHandler.COLOR, dye));
			HashMap<Integer, ItemStack> lstIP = new HashMap<Integer, ItemStack>();
			lstIP.put(0, new ItemStack(ItemHandler.coloredP, 1, dye.getMetadata()));
			HashMap<Integer, ItemStack> lstIS = new HashMap<Integer, ItemStack>();
			lstIS.put(0, new ItemStack(ItemHandler.coloredS, 1, dye.getMetadata()));
			
			DeprecatedBlock blkp = (DeprecatedBlock) new DeprecatedBlock(lstBP).setRegistryName(rlp);
			DeprecatedBlock blks = (DeprecatedBlock) new DeprecatedBlock(lstBS).setRegistryName(rls);
			DeprecatedItem itemp = (DeprecatedItem) new DeprecatedItem(lstIP).setRegistryName(rlp);
			DeprecatedItem items = (DeprecatedItem) new DeprecatedItem(lstIS).setRegistryName(rls);
			
			blocksSolidToFix.put(dye, blks);
			blocksPowderToFix.put(dye, blkp);
			itemsSolidToFix.put(dye, items);
			itemsPowderToFix.put(dye, itemp);
			
			GameRegistry.register(blkp);
			GameRegistry.register(blks);
			GameRegistry.register(itemp);
			GameRegistry.register(items);
		}
	}

}
