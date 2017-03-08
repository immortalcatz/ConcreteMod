package fr.dbrown55.concrete.blocks;

import fr.dbrown55.concrete.Main;
import fr.dbrown55.concrete.backported.EnumDyeColor;
import fr.dbrown55.concrete.tabs.CreativeTabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockConcrete extends Block {

	private EnumDyeColor color;
	
	public BlockConcrete(EnumDyeColor color) {
		super(Material.rock);
		this.color = color;
		this.setBlockName("concrete_" + this.color);
		this.setCreativeTab(CreativeTabRegistry.getTab());
		this.setHardness(1.5F);
		this.setHarvestLevel("pickaxe", 0);
		this.setResistance(10.0F);
		this.setBlockTextureName(Main.modid + ":concrete_" + this.color.getName());
	}
	
	public EnumDyeColor getColor() {
		return this.color;
	}
	
}
