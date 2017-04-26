package fr.dbrown55.concrete.blocks.glow;

import fr.dbrown55.concrete.blocks.BlockConcrete;
import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.enums.EnumConcreteType;
import net.minecraft.block.state.IBlockState;

public class BlockGlowCrete extends BlockConcrete {

	public BlockGlowCrete() {
		super(EnumConcreteType.GLOWSTONE);
		this.setLightLevel(1.0F);
	}

	@Override
	public IBlockState getPowderState(IBlockState original) {
		return BlockHandler.glowP.getDefaultState();
	}

}
