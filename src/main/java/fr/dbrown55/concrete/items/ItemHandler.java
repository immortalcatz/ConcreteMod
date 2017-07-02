package fr.dbrown55.concrete.items;

import java.util.HashMap;

import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteGateBase;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteStairsBase;
import fr.dbrown55.concrete.items.sticks.ItemGlowstoneStick;
import fr.dbrown55.concrete.items.sticks.ItemMagmaStick;
import fr.dbrown55.concrete.items.sticks.ItemVanillaStick;
import fr.dbrown55.utilmod.Handler;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemHandler extends Handler {

	/* OLD BLOCKS */
	public static HashMap<EnumDyeColor, Item> OLD_SOLID = new HashMap<EnumDyeColor, Item>();
	public static HashMap<EnumDyeColor, Item> OLD_POWDER = new HashMap<EnumDyeColor, Item>();
	
	/* VANILLA */
	public static Item VANILLA_POWDER, VANILLA_SOLID, VANILLA_STAIRS, VANILLA_SLAB, VANILLA_FENCE, VANILLA_GATE, VANILLA_STICK;
	
	/* MAGMA */
	public static Item MAGMA_POWDER, MAGMA_SOLID, MAGMA_STAIRS, MAGMA_SLAB, MAGMA_FENCE, MAGMA_GATE, MAGMA_STICK;
	
	/* GLOWSTONE */
	public static Item GLOWSTONE_POWDER, GLOWSTONE_SOLID, GLOWSTONE_STAIRS, GLOWSTONE_SLAB, GLOWSTONE_FENCE, GLOWSTONE_GATE, GLOWSTONE_STICK;
	
	/* REDSTONE */
	public static Item REDSTONE_POWDER, REDSTONE_SOLID, REDSTONE_STAIRS, REDSTONE_SLAB;
	
	public static Item BRUSH, PALETTE, ERASER, CONCRETE_BUG;
	
	@Override
	public void preInit() {
		/* VANILLA */
		VANILLA_POWDER = new ItemBlockConcretePowder(BlockHandler.VANILLA_POWDER);
		GameRegistry.register(VANILLA_POWDER);
		
		VANILLA_SOLID = new ItemBlockConcrete(BlockHandler.VANILLA_SOLID);
		GameRegistry.register(VANILLA_SOLID);
		
		ConcreteStairsBase[] stairBasesVanilla = new ConcreteStairsBase[16];
		for(int i = 0; i < 16; i++) {
			stairBasesVanilla[i] = BlockHandler.VANILLA_STAIRS.get(EnumDyeColor.byMetadata(i));
		}
		VANILLA_STAIRS = new ItemBlockStairs(stairBasesVanilla, new ResourceLocation("concrete", "vanilla_stairs"));
		GameRegistry.register(VANILLA_STAIRS);
		
		VANILLA_SLAB = new ItemBlockSlab(BlockHandler.VANILLA_SLABS, new ResourceLocation("concrete", "vanilla_slab"));
		GameRegistry.register(VANILLA_SLAB);
		
		VANILLA_FENCE = new ItemBlockFence(BlockHandler.VANILLA_FENCE);
		GameRegistry.register(VANILLA_FENCE);
		
		ConcreteGateBase[] gateBasesVanilla = new ConcreteGateBase[16];
		for(int i = 0; i < 16; i++) {
			gateBasesVanilla[i] = BlockHandler.VANILLA_GATES.get(EnumDyeColor.byMetadata(i));
		}
		VANILLA_GATE = new ItemBlockGate(gateBasesVanilla, new ResourceLocation("concrete", "vanilla_gate"));
		GameRegistry.register(VANILLA_GATE);
		
		VANILLA_STICK = new ItemVanillaStick();
		GameRegistry.register(VANILLA_STICK);
		
		/* MAGMA */
		
		MAGMA_POWDER = new ItemBlockConcretePowder(BlockHandler.MAGMA_POWDER);
		GameRegistry.register(MAGMA_POWDER);
		
		MAGMA_SOLID = new ItemBlockConcrete(BlockHandler.MAGMA_SOLID);
		GameRegistry.register(MAGMA_SOLID);
		
		ConcreteStairsBase[] stairBasesMagma = new ConcreteStairsBase[16];
		for(int i = 0; i < 16; i++) {
			stairBasesMagma[i] = BlockHandler.MAGMA_STAIRS.get(EnumDyeColor.byMetadata(i));
		}
		MAGMA_STAIRS = new ItemBlockStairs(stairBasesMagma, new ResourceLocation("concrete", "magma_stairs"));
		GameRegistry.register(MAGMA_STAIRS);
		
		MAGMA_SLAB = new ItemBlockSlab(BlockHandler.MAGMA_SLABS, new ResourceLocation("concrete", "magma_slab"));
		GameRegistry.register(MAGMA_SLAB);
		
		MAGMA_FENCE = new ItemBlockFence(BlockHandler.MAGMA_FENCE);
		GameRegistry.register(MAGMA_FENCE);
		
		ConcreteGateBase[] gateBasesMagma = new ConcreteGateBase[16];
		for(int i = 0; i < 16; i++) {
			gateBasesMagma[i] = BlockHandler.MAGMA_GATES.get(EnumDyeColor.byMetadata(i));
		}
		MAGMA_GATE = new ItemBlockGate(gateBasesMagma, new ResourceLocation("concrete", "magma_gate"));
		GameRegistry.register(MAGMA_GATE);
		
		MAGMA_STICK = new ItemMagmaStick();
		GameRegistry.register(MAGMA_STICK);
		
		/* GLOWSTONE */
		
		GLOWSTONE_POWDER = new ItemBlockConcretePowder(BlockHandler.GLOWSTONE_POWDER);
		GameRegistry.register(GLOWSTONE_POWDER);
		
		GLOWSTONE_SOLID = new ItemBlockConcrete(BlockHandler.GLOWSTONE_SOLID);
		GameRegistry.register(GLOWSTONE_SOLID);
		
		ConcreteStairsBase[] stairBasesGlow = new ConcreteStairsBase[16];
		for(int i = 0; i < 16; i++) {
			stairBasesGlow[i] = BlockHandler.GLOWSTONE_STAIRS.get(EnumDyeColor.byMetadata(i));
		}
		GLOWSTONE_STAIRS = new ItemBlockStairs(stairBasesGlow, new ResourceLocation("concrete", "glowcrete_stairs"));
		GameRegistry.register(GLOWSTONE_STAIRS);
		
		GLOWSTONE_SLAB = new ItemBlockSlab(BlockHandler.GLOWSTONE_SLABS, new ResourceLocation("concrete", "glowcrete_slab"));
		GameRegistry.register(GLOWSTONE_SLAB);
		
		GLOWSTONE_FENCE = new ItemBlockFence(BlockHandler.GLOWSTONE_FENCE);
		GameRegistry.register(GLOWSTONE_FENCE);
		
		ConcreteGateBase[] gateBasesGlow = new ConcreteGateBase[16];
		for(int i = 0; i < 16; i++) {
			gateBasesGlow[i] = BlockHandler.GLOWSTONE_GATES.get(EnumDyeColor.byMetadata(i));
		}
		GLOWSTONE_GATE = new ItemBlockGate(gateBasesGlow, new ResourceLocation("concrete", "glowcrete_gate"));
		GameRegistry.register(GLOWSTONE_GATE);
		
		GLOWSTONE_STICK = new ItemGlowstoneStick();
		GameRegistry.register(GLOWSTONE_STICK);
		
		/* REDSTONE */
		
		REDSTONE_POWDER = new ItemBlockConcretePowder(BlockHandler.REDSTONE_POWDER);
		GameRegistry.register(REDSTONE_POWDER);
		
		REDSTONE_SOLID = new ItemBlockConcrete(BlockHandler.REDSTONE_SOLID);
		GameRegistry.register(REDSTONE_SOLID);
		
		ConcreteStairsBase[] stairBasesRed = new ConcreteStairsBase[16];
		for(int i = 0; i < 16; i++) {
			stairBasesRed[i] = BlockHandler.REDSTONE_STAIRS.get(EnumDyeColor.byMetadata(i));
		}
		REDSTONE_STAIRS = new ItemBlockStairs(stairBasesRed, new ResourceLocation("concrete", "redcrete_stairs"));
		GameRegistry.register(REDSTONE_STAIRS);
		
		REDSTONE_SLAB = new ItemBlockSlab(BlockHandler.REDSTONE_SLABS, new ResourceLocation("concrete", "redcrete_slab"));
		GameRegistry.register(REDSTONE_SLAB);
		
		/* OLD IDS FIX */
		
		for(EnumDyeColor dye : EnumDyeColor.values()){
			Item solidOld = new DeprecatedItem(new ResourceLocation("one_point_twelve_concrete", "concrete_" + dye.getName()), new ItemStack(VANILLA_SOLID, 1, dye.getMetadata()));
			Item powderOld = new DeprecatedItem(new ResourceLocation("one_point_twelve_concrete", "concrete_powder_" + dye.getName()), new ItemStack(VANILLA_POWDER, 1, dye.getMetadata()));
			OLD_SOLID.put(dye, solidOld);
			OLD_POWDER.put(dye, powderOld);
			GameRegistry.register(solidOld);
			GameRegistry.register(powderOld);
		}
		
		BRUSH = new ItemBrush();
		GameRegistry.register(BRUSH);
		
		PALETTE = new ItemPalette();
		GameRegistry.register(PALETTE);
		
		ERASER = new ItemEraser();
		GameRegistry.register(ERASER);
		
		CONCRETE_BUG = new ItemConcreteBugDecoration();
		GameRegistry.register(CONCRETE_BUG);
	}

	@Override
	public void init() { }

	@Override
	public void postInit() { }

	@Override
	public EnumHandlerPriority getPriority() {
		return EnumHandlerPriority.ITEM;
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
