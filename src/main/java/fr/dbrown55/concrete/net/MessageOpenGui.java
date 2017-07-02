package fr.dbrown55.concrete.net;

import fr.dbrown55.concrete.gui.GuiColorChooser;
import fr.dbrown55.concrete.gui.GuiHelp;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageOpenGui implements IMessage {
	
	private GuiEnum theEnum;
	
	public MessageOpenGui() { }
	
	public MessageOpenGui(GuiEnum theEnum) {
		this.theEnum = theEnum;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.theEnum = GuiEnum.fromId(buf.readInt());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.theEnum.ID);
	}
	
	public static class TheHandler implements IMessageHandler<MessageOpenGui, IMessage> {

		@Override
		public IMessage onMessage(MessageOpenGui message, MessageContext ctx) {
			// Recieved client side so...
			switch(message.theEnum) {
				default:
				case NONE:
					break;
				case HELP:
					Minecraft.getMinecraft().displayGuiScreen(new GuiHelp());
					break;
				case PAINT:
					Minecraft.getMinecraft().displayGuiScreen(new GuiColorChooser());
					break;
			}
			return null;
		}
		
	}
	
	public static enum GuiEnum {
		NONE(-1),
		HELP(0),
		PAINT(1);
		
		private int ID;
		
		GuiEnum(int ID) {
			this.ID = ID;
		}
		
		public int getId() {
			return this.ID;
		}
		
		public static GuiEnum fromId(int id) {
			for(GuiEnum theEnum : GuiEnum.values()) {
				if(theEnum.getId() == id) {
					return theEnum;
				}
			}
			return NONE;
		}
	}

}
