package fr.dbrown55.concrete.blocks;

import fr.dbrown55.concrete.tabs.CreativeTabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumDyeColor;

public class BlockConcrete extends Block {

	private EnumDyeColor color;
	protected final MapColor blockMapColor;
	
	public BlockConcrete(EnumDyeColor color) {
		super(Material.ROCK);
		this.color = color;
		this.blockMapColor = color.getMapColor();
		this.setRegistryName("concrete_" + this.color.getName());
		this.setUnlocalizedName("concrete_" + this.color);
		this.setCreativeTab(CreativeTabRegistry.getTab());
		this.setHardness(1.5F);
		this.setHarvestLevel("pickaxe", 0);
		this.setResistance(10.0F);
	}

	public EnumDyeColor getColor() {
		return this.color;
	}
	
}
