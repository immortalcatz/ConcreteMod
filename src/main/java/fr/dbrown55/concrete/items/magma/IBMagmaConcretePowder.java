package fr.dbrown55.concrete.items.magma;

import fr.dbrown55.concrete.blocks.BlockConcretePowder;
import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.items.ItemBlockConcretePowder;

public class IBMagmaConcretePowder extends ItemBlockConcretePowder {

	public IBMagmaConcretePowder() {
		super(BlockHandler.magmaP);
	}

	@Override
	public int getMetadata(int damage) {
		return 0;
	}

	
}
