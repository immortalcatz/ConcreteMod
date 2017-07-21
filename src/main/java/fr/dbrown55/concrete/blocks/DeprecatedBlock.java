package fr.dbrown55.concrete.blocks;

import fr.dbrown55.utilmod.helpers.SideHelper;
import fr.dbrown55.utilmod.objects.DbrownBlock;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

public class DeprecatedBlock extends DbrownBlock {

	private IBlockState transforms;
	
	public DeprecatedBlock(ResourceLocation name, IBlockState transforms) {
		super(Material.CORAL, MapColor.GRAY, name);
		this.transforms = transforms;
	}
	
	public IBlockState getState(){
		return this.transforms;
	}
	
	@Override
	public String getLocalizedName() {
		if(SideHelper.isClient()){
			return I18n.translateToLocal("item.deprecatedItem.name");
		}
		return super.getLocalizedName();
	}

}
