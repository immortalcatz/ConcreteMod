package fr.dbrown55.concrete.blocks;

import java.util.HashMap;

import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteBase;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteFenceBase;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteGateBase;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcretePowderBase;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteSlabBase;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteSlabBase.EnumSlabPartType;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteStairsBase;
import fr.dbrown55.concrete.blocks.glowstone.BlockGlowstoneFence;
import fr.dbrown55.concrete.blocks.glowstone.BlockGlowstoneGate;
import fr.dbrown55.concrete.blocks.glowstone.BlockGlowstonePowder;
import fr.dbrown55.concrete.blocks.glowstone.BlockGlowstoneSlab;
import fr.dbrown55.concrete.blocks.glowstone.BlockGlowstoneSolid;
import fr.dbrown55.concrete.blocks.glowstone.BlockGlowstoneStairs;
import fr.dbrown55.concrete.blocks.magma.BlockMagmaFence;
import fr.dbrown55.concrete.blocks.magma.BlockMagmaGate;
import fr.dbrown55.concrete.blocks.magma.BlockMagmaPowder;
import fr.dbrown55.concrete.blocks.magma.BlockMagmaSlab;
import fr.dbrown55.concrete.blocks.magma.BlockMagmaSolid;
import fr.dbrown55.concrete.blocks.magma.BlockMagmaStairs;
import fr.dbrown55.concrete.blocks.redstone.BlockRedstonePowder;
import fr.dbrown55.concrete.blocks.redstone.BlockRedstoneSlab;
import fr.dbrown55.concrete.blocks.redstone.BlockRedstoneSolid;
import fr.dbrown55.concrete.blocks.redstone.BlockRedstoneStairs;
import fr.dbrown55.concrete.blocks.vanilla.BlockVanillaFence;
import fr.dbrown55.concrete.blocks.vanilla.BlockVanillaGate;
import fr.dbrown55.concrete.blocks.vanilla.BlockVanillaPowder;
import fr.dbrown55.concrete.blocks.vanilla.BlockVanillaSlab;
import fr.dbrown55.concrete.blocks.vanilla.BlockVanillaSolid;
import fr.dbrown55.concrete.blocks.vanilla.BlockVanillaStairs;
import fr.dbrown55.utilmod.Handler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockHandler extends Handler {
	
	/* OLD BLOCKS */
	public static HashMap<EnumDyeColor, Block> OLD_SOLID = new HashMap<EnumDyeColor, Block>();
	public static HashMap<EnumDyeColor, Block> OLD_POWDER = new HashMap<EnumDyeColor, Block>();
	
	/* VANILLA */
	public static ConcretePowderBase VANILLA_POWDER;
	public static ConcreteBase VANILLA_SOLID;
	public static HashMap<EnumDyeColor, ConcreteStairsBase> VANILLA_STAIRS = new HashMap<EnumDyeColor, ConcreteStairsBase>();
	public static HashMap<EnumSlabPartType, ConcreteSlabBase> VANILLA_SLABS = new HashMap<EnumSlabPartType, ConcreteSlabBase>();
	public static ConcreteFenceBase VANILLA_FENCE;
	public static HashMap<EnumDyeColor, ConcreteGateBase> VANILLA_GATES = new HashMap<EnumDyeColor, ConcreteGateBase>();
	
	/* MAGMA */
	public static ConcretePowderBase MAGMA_POWDER;
	public static ConcreteBase MAGMA_SOLID;
	public static HashMap<EnumDyeColor, ConcreteStairsBase> MAGMA_STAIRS = new HashMap<EnumDyeColor, ConcreteStairsBase>();
	public static HashMap<EnumSlabPartType, ConcreteSlabBase> MAGMA_SLABS = new HashMap<EnumSlabPartType, ConcreteSlabBase>();
	public static ConcreteFenceBase MAGMA_FENCE;
	public static HashMap<EnumDyeColor, ConcreteGateBase> MAGMA_GATES = new HashMap<EnumDyeColor, ConcreteGateBase>();
	
	/* GLOWSTONE */
	public static ConcretePowderBase GLOWSTONE_POWDER;
	public static ConcreteBase GLOWSTONE_SOLID;
	public static HashMap<EnumDyeColor, ConcreteStairsBase> GLOWSTONE_STAIRS = new HashMap<EnumDyeColor, ConcreteStairsBase>();
	public static HashMap<EnumSlabPartType, ConcreteSlabBase> GLOWSTONE_SLABS = new HashMap<EnumSlabPartType, ConcreteSlabBase>();
	public static ConcreteFenceBase GLOWSTONE_FENCE;
	public static HashMap<EnumDyeColor, ConcreteGateBase> GLOWSTONE_GATES = new HashMap<EnumDyeColor, ConcreteGateBase>();
	
	/* REDSTONE */
	public static ConcretePowderBase REDSTONE_POWDER;
	public static ConcreteBase REDSTONE_SOLID;
	public static HashMap<EnumDyeColor, ConcreteStairsBase> REDSTONE_STAIRS = new HashMap<EnumDyeColor, ConcreteStairsBase>();
	public static HashMap<EnumSlabPartType, ConcreteSlabBase> REDSTONE_SLABS = new HashMap<EnumSlabPartType, ConcreteSlabBase>();
	
	@Override
	public void preInit() {
		VANILLA_POWDER = new BlockVanillaPowder();
		GameRegistry.register(VANILLA_POWDER);
		
		VANILLA_SOLID = new BlockVanillaSolid();
		GameRegistry.register(VANILLA_SOLID);
		
		VANILLA_FENCE = new BlockVanillaFence();
		GameRegistry.register(VANILLA_FENCE);
		
		MAGMA_POWDER = new BlockMagmaPowder();
		GameRegistry.register(MAGMA_POWDER);
		
		MAGMA_SOLID = new BlockMagmaSolid();
		GameRegistry.register(MAGMA_SOLID);
		
		MAGMA_FENCE = new BlockMagmaFence();
		GameRegistry.register(MAGMA_FENCE);
		
		GLOWSTONE_POWDER = new BlockGlowstonePowder();
		GameRegistry.register(GLOWSTONE_POWDER);
		
		GLOWSTONE_SOLID = new BlockGlowstoneSolid();
		GameRegistry.register(GLOWSTONE_SOLID);
		
		GLOWSTONE_FENCE = new BlockGlowstoneFence();
		GameRegistry.register(GLOWSTONE_FENCE);
		
		REDSTONE_POWDER = new BlockRedstonePowder();
		GameRegistry.register(REDSTONE_POWDER);
		
		REDSTONE_SOLID = new BlockRedstoneSolid();
		GameRegistry.register(REDSTONE_SOLID);
		
		for(EnumDyeColor dye : EnumDyeColor.values()) {
			Block solidOld = new DeprecatedBlock(new ResourceLocation("one_point_twelve_concrete", "concrete_" + dye.getName()), VANILLA_SOLID.getDefaultState().withProperty(BlockColored.COLOR, dye));
			Block powderOld = new DeprecatedBlock(new ResourceLocation("one_point_twelve_concrete", "concrete_powder_" + dye.getName()), VANILLA_POWDER.getDefaultState().withProperty(BlockColored.COLOR, dye));
			OLD_SOLID.put(dye, solidOld);
			OLD_POWDER.put(dye, powderOld);
			GameRegistry.register(solidOld);
			GameRegistry.register(powderOld);
			
			ConcreteStairsBase stairsVanilla = new BlockVanillaStairs(dye);
			VANILLA_STAIRS.put(dye, stairsVanilla);
			GameRegistry.register(stairsVanilla);
			
			ConcreteGateBase gateVanilla = new BlockVanillaGate(dye);
			VANILLA_GATES.put(dye, gateVanilla);
			GameRegistry.register(gateVanilla);
			
			ConcreteStairsBase stairsMagma = new BlockMagmaStairs(dye);
			MAGMA_STAIRS.put(dye, stairsMagma);
			GameRegistry.register(stairsMagma);
			
			ConcreteGateBase gateMagma = new BlockMagmaGate(dye);
			MAGMA_GATES.put(dye, gateMagma);
			GameRegistry.register(gateMagma);
			
			ConcreteStairsBase stairsGlow = new BlockGlowstoneStairs(dye);
			GLOWSTONE_STAIRS.put(dye, stairsGlow);
			GameRegistry.register(stairsGlow);
			
			ConcreteGateBase gateGlow = new BlockGlowstoneGate(dye);
			GLOWSTONE_GATES.put(dye, gateGlow);
			GameRegistry.register(gateGlow);
			
			ConcreteStairsBase stairsRed = new BlockRedstoneStairs(dye);
			REDSTONE_STAIRS.put(dye, stairsRed);
			GameRegistry.register(stairsRed);
		}
		
		for(EnumSlabPartType type : EnumSlabPartType.values()) {
			ConcreteSlabBase slabVabilla = new BlockVanillaSlab(type);
			VANILLA_SLABS.put(type, slabVabilla);
			GameRegistry.register(slabVabilla);
			
			ConcreteSlabBase slabMagma = new BlockMagmaSlab(type);
			MAGMA_SLABS.put(type, slabMagma);
			GameRegistry.register(slabMagma);
			
			ConcreteSlabBase slabGlow = new BlockGlowstoneSlab(type);
			GLOWSTONE_SLABS.put(type, slabGlow);
			GameRegistry.register(slabGlow);
			
			ConcreteSlabBase slabRed = new BlockRedstoneSlab(type);
			REDSTONE_SLABS.put(type, slabRed);
			GameRegistry.register(slabRed);
		}
	}

	@Override
	public void init() {}

	@Override
	public void postInit() {}

	@Override
	public EnumHandlerPriority getPriority() {
		return EnumHandlerPriority.BLOCK;
	}

	@Override
	public String[] getDependencies(EnumHandlerPhase phase) {
		return new String[] {};
	}

	@Override
	public String[] getRequirements(EnumHandlerPhase phase) {
		return new String[] {};
	}

}
