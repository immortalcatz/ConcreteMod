package fr.dbrown55.concrete.net;

import fr.dbrown55.concrete.items.ItemHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSetPaintHandler implements IMessageHandler<MessageSetPaint, IMessage> {

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
