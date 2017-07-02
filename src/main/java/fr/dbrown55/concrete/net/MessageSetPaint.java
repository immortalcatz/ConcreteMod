package fr.dbrown55.concrete.net;

import fr.dbrown55.concrete.items.ItemHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSetPaint implements IMessage {

	private int color;
	
	public MessageSetPaint() {}
	
	public MessageSetPaint(int color) {
		this.color = color;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.color = ByteBufUtils.readVarInt(buf, 5);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeVarInt(buf, this.color, 5);
	}
	
	public int getColor(){
		return this.color;
	}

	public static class TheHandler implements IMessageHandler<MessageSetPaint, IMessage> {
		
		@Override
		public IMessage onMessage(MessageSetPaint message, MessageContext ctx) {
			EntityPlayer pl = ctx.getServerHandler().playerEntity;
			ItemStack is = pl.getHeldItemMainhand();
			if(is != null && is.getItem() == ItemHandler.BRUSH && is.hasTagCompound()){
				is.getTagCompound().setInteger("color", message.getColor());
			}
			return null;
		}
		
	}
	
}
