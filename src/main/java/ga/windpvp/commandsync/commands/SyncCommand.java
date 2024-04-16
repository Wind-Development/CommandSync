package ga.windpvp.commandsync.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import ga.windpvp.commandsync.SyncPlugin;
import org.jetbrains.annotations.NotNull;

public class SyncCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

		if (!sender.hasPermission("commandsync.admin")) {
			sender.sendMessage(Component.text("Insufficient permissions.").color(TextColor.color(200,0,0)));
			return true;
		}
		
		if (args.length == 0) {
			sender.sendMessage(Component.text("Insufficient arguments! Use /sync <command>.").color(TextColor.color(200,0,0)));
			return true;
		}
		
		StringBuilder builder = new StringBuilder();

		for (int index = 0; index < args.length; index++) {
			builder.append(args[index]);
			
			if (index < args.length - 1) {
				builder.append(" ");
			}
		}

		SyncPlugin.getInstance().getConnectionManager().dispatchCommand(builder.toString());
		
		sender.sendMessage(Component.text("Synced command /" + builder.toString() + " to the Velocity instance!").color(TextColor.color(0,200,0)));

		return true;
	}

}
