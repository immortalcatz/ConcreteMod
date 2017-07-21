package fr.dbrown55.concrete.blocks.magma;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.dbrown55.concrete.blocks.BlockHandler;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteSlabBase;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcreteSlabBase.EnumSlabPartType;
import fr.dbrown55.concrete.items.ItemHandler;
import fr.dbrown55.utilmod.helpers.BlockStateHelper;
import fr.dbrown55.utilmod.helpers.TranslationHelper;
import net.minecraft.block.BlockColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMagmaSlab extends ConcreteSlabBase {

	public BlockMagmaSlab(EnumSlabPartType type) {
		super(type, new ResourceLocation("concrete", "magma_slab_" + type.getName()));
		this.setLightLevel(0.2F);
	}

	@Override
	public ItemStack getStack(IBlockState state, boolean pickBlock) {
		int count = 1;
		if(this.isDouble() && !pickBlock) {
			count = 2;
		}
		return new ItemStack(ItemHandler.MAGMA_SLAB, count, state.getValue(BlockColored.COLOR).getMetadata());
	}
	
	@Override
	public IBlockState getUpperState(IBlockState lower) {
		return BlockStateHelper.copyProperties(lower, BlockHandler.MAGMA_SLABS.get(EnumSlabPartType.UPPER_HALF), BlockColored.COLOR);
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if(!entityIn.isImmuneToFire() && entityIn instanceof EntityLivingBase && !EnchantmentHelper.hasFrostWalkerEnchantment((EntityLivingBase)entityIn)) {
			entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 1.0F);
		}

		super.onEntityWalk(worldIn, pos, entityIn);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos) {
		return 15728880;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(worldIn, pos, state, rand);
		BlockPos blockpos = pos.up();
		IBlockState iblockstate = worldIn.getBlockState(blockpos);

		if(iblockstate.getBlock() == Blocks.WATER || iblockstate.getBlock() == Blocks.FLOWING_WATER) {
			worldIn.setBlockToAir(blockpos);
			worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

			if(worldIn instanceof WorldServer) {
				((WorldServer)worldIn).spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.25D, (double)blockpos.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D, new int[0]);
			}
		}
	}

	@Override
	public boolean canEntitySpawn(IBlockState state, Entity entityIn) {
		return entityIn.isImmuneToFire();
	}
	
	@Override
	public String getDisplayName(ItemStack stack) {
		return TranslationHelper.translateAll("tile.magma_concrete_slab.name", "color." + EnumDyeColor.byMetadata(stack.getMetadata()).getName());
	}

}
