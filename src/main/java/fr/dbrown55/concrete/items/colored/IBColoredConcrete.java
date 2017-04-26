package fr.dbrown55.concrete.items.colored;

import fr.dbrown55.concrete.blocks.BlockConcrete;
import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.items.ItemBlockConcrete;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class IBColoredConcrete extends ItemBlockConcrete {

	public IBColoredConcrete() {
		super(BlockHandler.coloredS);
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		if(FMLCommonHandler.instance().getSide() == Side.SERVER){
			return "tile.concrete." + EnumDyeColor.byMetadata(stack.getMetadata()) + ".name";
		}
		return I18n.translateToLocalFormatted("tile.concrete.name", I18n.translateToLocal("color." + EnumDyeColor.byMetadata(stack.getMetadata())));
	}
	
}
