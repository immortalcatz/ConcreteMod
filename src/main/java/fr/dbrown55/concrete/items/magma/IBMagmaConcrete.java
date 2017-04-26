package fr.dbrown55.concrete.items.magma;

import fr.dbrown55.concrete.blocks.BlockConcrete;
import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.items.ItemBlockConcrete;

public class IBMagmaConcrete extends ItemBlockConcrete {

	public IBMagmaConcrete() {
		super(BlockHandler.magmaS);
	}

	@Override
	public int getMetadata(int damage) {
		return 0;
	}

}
