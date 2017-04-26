package fr.dbrown55.concrete.items;

import fr.dbrown55.concrete.blocks.BlockConcrete;
import fr.dbrown55.concrete.blocks.BlockConcretePowder;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public abstract class ItemBlockConcrete extends ItemBlock {

	public ItemBlockConcrete(BlockConcrete blk){
		super(blk);
		this.setRegistryName(blk.getRegistryName());
		this.setUnlocalizedName(blk.getUnlocalizedName());
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}
	
	@Override
	public abstract int getMetadata(int damage);
}
