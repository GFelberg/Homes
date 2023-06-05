package me.GFelberg.Homes.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.GFelberg.Homes.utils.HomesUtils;

public class Homes implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (command.getName().equalsIgnoreCase("homes")) {

			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "This command can be only made by players!");
				return true;
			}

			if (!(sender.hasPermission("homes.home"))) {
				sender.sendMessage(ChatColor.RED + "You dont have permission to perform this command!");
				return true;
			}

			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED + "Usage: /homes help");
				return true;
			}

			Player p = (Player) sender;
			HomesUtils utils = new HomesUtils();

			if (args.length == 1) {

				if (args[0].equalsIgnoreCase("reload")) {
					utils.reloadConfig(p);
				} else if (args[0].equalsIgnoreCase("help")) {
					utils.helpPage(p);
				}
				return true;
			}
		}
		return true;
	}
}