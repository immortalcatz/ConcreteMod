package fr.dbrown55.concrete.net;

import fr.dbrown55.concrete.items.ItemHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SetPaintMessageHandler implements IMessageHandler<SetPaintMessage, IMessage> {

	@Override
	public IMessage onMessage(SetPaintMessage message, MessageContext ctx) {
		EntityPlayer pl = ctx.getServerHandler().playerEntity;
		ItemStack is = pl.getHeldItem(EnumHand.MAIN_HAND);
		if(is != null && is.getItem() == ItemHandler.brush && is.hasTagCompound()){
			is.getTagCompound().setInteger("color", message.getColor());
		}
		return null;
	}
	
}