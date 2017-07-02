package fr.dbrown55.concrete.commands;

import fr.dbrown55.concrete.Main;
import fr.dbrown55.concrete.net.MessageOpenGui;
import fr.dbrown55.concrete.net.MessageOpenGui.GuiEnum;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class ConcreteHelpCommand extends CommandBase {

	@Override
	public String getCommandName() {
		return "concrete";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/concrete";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(sender instanceof EntityPlayerMP){
			Main.wrapper.sendTo(new MessageOpenGui(GuiEnum.HELP), (EntityPlayerMP)sender);
		} else {
			throw new CommandException("command.concrete.notPlayer");
		}
	}
	
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender instanceof EntityPlayer;
    }

}
