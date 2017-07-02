package fr.dbrown55.concrete.events;

import fr.dbrown55.concrete.Main;
import fr.dbrown55.concrete.blocks.DeprecatedBlock;
import fr.dbrown55.concrete.blocks.concreteBaseClasses.ConcretePowderBase;
import fr.dbrown55.utilmod.helpers.LiquidHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public class ConcreteEvents {

	/**
	 * This event method is responsible of turning falling powder concrete into it's solid form.
	 * @param event - The {@link WorldTickEvent} event
	 */
	@SubscribeEvent
	public void onWorldTick(WorldTickEvent event){
		for(Entity ent : event.world.loadedEntityList){
			if(ent instanceof EntityFallingBlock){
				EntityFallingBlock sand = (EntityFallingBlock)ent;
				Block b = sand.getBlock().getBlock(); 
				if(b instanceof ConcretePowderBase){
					ConcretePowderBase concrete = (ConcretePowderBase)b; 
					if(Main.config.usesVanillaHehavior()){
						BlockPos pos = sand.getPosition().down();
						IBlockState state = event.world.getBlockState(pos);
						if(LiquidHelper.isBlockWater(state)){
							event.world.setBlockState(pos, concrete.getSolidState(state));
						}
					} else {
						IBlockState state = event.world.getBlockState(sand.getPosition());
						if(LiquidHelper.isBlockWater(state) || LiquidHelper.isTouchingWater(event.world, sand.getPosition(), EnumFacing.HORIZONTALS)){
							event.world.setBlockState(sand.getPosition(), concrete.getSolidState(sand.getBlock()));
							ent.setDead();
						}
					}
				}
			}
		}
	}
	
	/**
	 * This event method is responsible of the welcome message when logging in.
	 * @param event - The {@link PlayerLoggedInEvent} event
	 */
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onPlayerJoin(PlayerLoggedInEvent event){
		ITextComponent message1 = new TextComponentTranslation("welcomemessage.part1", event.player.getName());
		ITextComponent concreteCMD = new TextComponentString("/concrete");
		ITextComponent message2 = new TextComponentTranslation("welcomemessage.part2");
		message1.setStyle(message1.getStyle().setItalic(true));
		concreteCMD.setStyle(concreteCMD.getStyle()
			.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/concrete"))
			.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentTranslation("welcomemessage.click")))
			.setColor(TextFormatting.GREEN)
			.setUnderlined(true)
		);
		message1.appendSibling(concreteCMD);
		message1.appendSibling(message2);
		
		event.player.addChatComponentMessage(message1);
	}
	
	/**
	 * This event method is responsible of turning every {@link DeprecatedBlock}s into their correct {@link IBlockState}.
	 * @param event - The {@link ChunkEvent#Load} event
	 */
	@SubscribeEvent
	public void onChunkLoaded(ChunkEvent.Load event) {
		Chunk chunk = event.getChunk();
		for(int x = 0; x < 16; x++) {
			for(int y = 0; y < event.getWorld().getActualHeight(); y++) {
				for(int z = 0; z < 16; z++) {
					BlockPos pos = new BlockPos((chunk.xPosition * 16) + x, y, (chunk.zPosition * 16) + z);
					IBlockState state = event.getWorld().getBlockState(pos);
					if(state.getBlock() instanceof DeprecatedBlock) {
						event.getWorld().setBlockState(pos, ((DeprecatedBlock)state.getBlock()).getState());
					}
				}
			}
		}
	}
	
}
