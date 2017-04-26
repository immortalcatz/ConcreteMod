package fr.dbrown55.concrete.blocks.glow;

import fr.dbrown55.concrete.blocks.BlockConcretePowder;
import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.enums.EnumConcreteType;
import net.minecraft.block.state.IBlockState;

public class BlockGlowCretePowder extends BlockConcretePowder {
	
	public BlockGlowCretePowder() {
		super(EnumConcreteType.GLOWSTONE);
		this.setLightLevel(1.0F);
	}

	@Override
	public IBlockState getSolidState(IBlockState original) {
		return BlockHandler.glowS.getDefaultState();
	}

}
