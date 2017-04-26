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

public class SetPaintMessage implements IMessage {

	private int color;
	
	public SetPaintMessage() {}
	
	public SetPaintMessage(int color) {
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

}
