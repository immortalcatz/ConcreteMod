package fr.dbrown55.concrete.items;

import fr.dbrown55.concrete.blocks.BlockConcrete;
import fr.dbrown55.concrete.blocks.BlockConcretePowder;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class ItemBlockAuto extends ItemBlock {

	public ItemBlockAuto(Block block) {
		super(block);
		this.setRegistryName(block.getRegistryName());
		this.setUnlocalizedName(block.getUnlocalizedName());
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		if(FMLCommonHandler.instance().getSide() == Side.CLIENT){
			if(this.getBlock() instanceof BlockConcretePowder){
				return I18n.format("tile.concrete_powder.name", new Object[] {I18n.format("color." + ((BlockConcretePowder)this.getBlock()).getColor().getUnlocalizedName())});
			} else if(this.getBlock() instanceof BlockConcrete){
				return I18n.format("tile.concrete.name", new Object[] {I18n.format("color." + ((BlockConcrete)this.getBlock()).getColor().getUnlocalizedName())});
			} else {
				return I18n.format(this.getBlock().getUnlocalizedName(), new Object[0]);
			}
		}
		return "";
	}
	
}
