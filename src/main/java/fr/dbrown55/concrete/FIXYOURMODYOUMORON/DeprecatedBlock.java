package fr.dbrown55.concrete.FIXYOURMODYOUMORON;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class DeprecatedBlock extends Block {

	private HashMap<Integer, IBlockState> toSwitch;
	
	public DeprecatedBlock(HashMap<Integer, IBlockState> toSwitch) {
		super(Material.CORAL); // Just because I don't care
		this.setBlockUnbreakable();
		this.setUnlocalizedName("deprecatedItem");
		this.toSwitch = toSwitch;
	}
	
	public IBlockState getNewBlockState(int fromMeta){
		if(this.toSwitch.containsKey(fromMeta)){
			return this.toSwitch.get(fromMeta);
		} else if(this.toSwitch.containsKey(-1)){
			return this.toSwitch.get(-1);
		}
		return Blocks.AIR.getDefaultState();
	}
	
}
