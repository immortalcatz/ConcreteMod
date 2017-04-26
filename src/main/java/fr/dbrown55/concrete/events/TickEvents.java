package fr.dbrown55.concrete.events;

import fr.dbrown55.concrete.Main;
import fr.dbrown55.concrete.FIXYOURMODYOUMORON.DeprecatedBlock;
import fr.dbrown55.concrete.blocks.BlockConcretePowder;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class TickEvents {

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent e){
		if(e.side == Side.SERVER){
			Chunk c = e.player.worldObj.getChunkFromBlockCoords(new BlockPos(e.player.posX, e.player.posY, e.player.posZ));
			int cx = c.xPosition * 16, cz = c.zPosition * 16;
			for(int x = cx; x < cx + 16; x++){
				for(int y = 0; y < e.player.worldObj.getHeight(); y++){
					for(int z = cz; z < cz + 16; z++){
						BlockPos pos = new BlockPos(x, y, z);
						if(e.player.worldObj.getBlockState(pos).getBlock() instanceof DeprecatedBlock){
							e.player.worldObj.setBlockState(pos, ((DeprecatedBlock)e.player.worldObj.getBlockState(pos).getBlock()).getNewBlockState(e.player.worldObj.getBlockState(pos).getBlock().getMetaFromState(e.player.worldObj.getBlockState(pos))));
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onFallingConcreteTick(WorldTickEvent e){
		for(Entity ent : e.world.loadedEntityList){
			if(ent instanceof EntityFallingBlock){
				EntityFallingBlock blk = (EntityFallingBlock) ent;
				// EntityFallingBlock#getBlock return a IBlockState.
				if(blk.getBlock() != null && blk.getBlock().getBlock() instanceof BlockConcretePowder){
					IBlockState blkstate = blk.getBlock();
					BlockConcretePowder block = (BlockConcretePowder)blkstate.getBlock();
					if(Main.vanillaBehavior){
						if(e.world.getBlockState(blk.getPosition().down()).getMaterial() == Material.WATER){
							e.world.setBlockState(blk.getPosition().down(), blk.getBlock());
							blk.setDead();
						}
					} else {
						if(e.world.getBlockState(blk.getPosition().down()).getMaterial() == Material.WATER
							|| e.world.getBlockState(blk.getPosition().north()).getMaterial() == Material.WATER
							|| e.world.getBlockState(blk.getPosition().south()).getMaterial() == Material.WATER
							|| e.world.getBlockState(blk.getPosition().east()).getMaterial() == Material.WATER
							|| e.world.getBlockState(blk.getPosition().west()).getMaterial() == Material.WATER ){
							e.world.setBlockState(blk.getPosition(), block.getSolidState(blkstate));
							blk.setDead();
						}
					}
				}
			}
		}
	}

}
