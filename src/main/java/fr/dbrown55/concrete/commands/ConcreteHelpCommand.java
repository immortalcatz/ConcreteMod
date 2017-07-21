package fr.dbrown55.concrete.commands;

import fr.dbrown55.concrete.Main;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class ConcreteHelpCommand extends CommandBase {

	@Override
	public String getName() {
		return "concrete";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/concrete";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(sender instanceof EntityPlayer){
			EntityPlayer pl = (EntityPlayer) sender; 
			pl.openGui(Main.instance, 1, pl.getEntityWorld(), (int) pl.posX, (int) pl.posY, (int) pl.posZ);
		} else {
			throw new CommandException("command.concrete.notPlayer");
		}
	}
	
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender instanceof EntityPlayer;
    }

}
