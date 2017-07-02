package fr.dbrown55.concrete.blocks.concreteBaseClasses;

import java.util.ArrayList;
import java.util.List;

import fr.dbrown55.concrete.Main;
import moze_intel.projecte.gameObjs.ObjHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ConcreteGateBase extends BlockFenceGate {
	
	private EnumDyeColor color;
	
	public ConcreteGateBase(EnumDyeColor color, ResourceLocation name) {
		super(BlockPlanks.EnumType.OAK);
		this.color = color;
		this.blockSoundType = SoundType.STONE;
		this.setRegistryName(name);
		this.setUnlocalizedName(name.getResourcePath());
		this.setCreativeTab(null);
		this.setHarvestLevel("pickaxe", 0);
		this.setHardness(1.5f);
	}

	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		ItemStack stack = player.inventory.getCurrentItem();
        if(stack != null && stack.getItem() instanceof ItemPickaxe) {
        	return true;
        }
        return false;
	}
	
	public abstract ItemStack getStack(IBlockState state, boolean pickBlock);
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		ArrayList<ItemStack> drop = new ArrayList<ItemStack>();
		drop.add(this.getStack(state, false));
		return drop;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return this.getStack(state, true);
	}
	
	/**
	 * Used to determine if the item should place this block
	 * @param stack The input stack
	 * @return If the block should be placed
	 */
	public abstract boolean isCorrectGate(ItemStack stack);
	
	@Override
	public MapColor getMapColor(IBlockState state) {
		return this.color.getMapColor();
	}
	
	/**
	 * Used to get the display name of the stack
	 * @return name - The stack display name
	 */
	@SideOnly(Side.CLIENT)
	public abstract String getDisplayName(ItemStack stack);
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(Main.config.isProjectECompatOn() && hand == EnumHand.MAIN_HAND && heldItem != null && heldItem.getItem() == ObjHandler.philosStone) {
			// Transmutation in progress, don't open the gate
			return false;
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
	}
	
}
