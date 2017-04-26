package fr.dbrown55.concrete.blocks;

import fr.dbrown55.concrete.blocks.colored.BlockColoredConcrete;
import fr.dbrown55.concrete.blocks.colored.BlockColoredConcretePowder;
import fr.dbrown55.concrete.blocks.glow.BlockGlowCrete;
import fr.dbrown55.concrete.blocks.glow.BlockGlowCretePowder;
import fr.dbrown55.concrete.blocks.magma.BlockMagmaConcrete;
import fr.dbrown55.concrete.blocks.magma.BlockMagmaConcretePowder;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockHandler {
	
	public static BlockConcrete coloredS, magmaS, glowS;
	public static BlockConcretePowder coloredP, magmaP, glowP;
	
	public static PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.create("color", EnumDyeColor.class);
	
	public static void init(){
		// Vanilla
		coloredS = new BlockColoredConcrete();
		coloredP = new BlockColoredConcretePowder();
		GameRegistry.register(coloredS);
		GameRegistry.register(coloredP);
		
		// Magma
		magmaS = new BlockMagmaConcrete();
		magmaP = new BlockMagmaConcretePowder();
		GameRegistry.register(magmaS);
		GameRegistry.register(magmaP);
		
		// Glowstone
		glowS = new BlockGlowCrete();
		glowP = new BlockGlowCretePowder();
		GameRegistry.register(glowS);
		GameRegistry.register(glowP);
	}
	
}
