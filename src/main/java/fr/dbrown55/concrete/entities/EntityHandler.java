package fr.dbrown55.concrete.entities;

import java.util.ArrayList;
import java.util.Random;

import fr.dbrown55.concrete.Main;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class EntityHandler {

	public static void init() {
		EntityRegistry.registerModEntity(EntityConcreteBug.class, "ConcreteBug", 0, Main.instance, 64, 3, false);
		EntityRegistry.registerEgg(EntityConcreteBug.class, 0xFFFFFF, 0xF5F5F5);
	}

}
