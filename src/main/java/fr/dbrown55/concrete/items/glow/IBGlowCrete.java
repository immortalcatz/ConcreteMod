package fr.dbrown55.concrete.items.glow;

import fr.dbrown55.concrete.blocks.BlockConcrete;
import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.items.ItemBlockConcrete;

public class IBGlowCrete extends ItemBlockConcrete {

	public IBGlowCrete() {
		super(BlockHandler.glowS);
	}

	@Override
	public int getMetadata(int damage) {
		return 0;
	}

}
