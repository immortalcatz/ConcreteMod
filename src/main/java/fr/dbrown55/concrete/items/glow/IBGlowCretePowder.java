package fr.dbrown55.concrete.items.glow;

import fr.dbrown55.concrete.blocks.BlockConcretePowder;
import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.items.ItemBlockConcretePowder;

public class IBGlowCretePowder extends ItemBlockConcretePowder {

	public IBGlowCretePowder() {
		super(BlockHandler.glowP);
	}

	@Override
	public int getMetadata(int damage) {
		return 0;
	}

}
