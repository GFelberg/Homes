package me.GFelberg.Homes.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.GFelberg.Homes.Main;

public class HomesUtils {

	public static String prefix;

	public static void loadVariables() {
		prefix = Main.getInstance().getConfig().getString("Homes.Prefix").replace("&", "§");
	}

	public void reloadConfig(Player p) {

		if (!(p.hasPermission("homes.reload"))) {
			p.sendMessage(ChatColor.RED + "You dont have permission to perform this command!");
		} else {
			Main.getInstance().reloadConfig();
			Main.getInstance().loadVariables();
			p.sendMessage(prefix + " " + ChatColor.GREEN + "Plugin reloaded successfully!");
			Bukkit.getConsoleSender().sendMessage("======================================");
			Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Homes Plugin reloaded");
			Bukkit.getConsoleSender().sendMessage("======================================");
		}
	}

	public void helpPage(Player p) {
		HelpPageUtils helpUtils = new HelpPageUtils();

		if (!(p.hasPermission("homes.admin"))) {
			p.sendMessage(ChatColor.WHITE + "-----------------------------------------");
			p.sendMessage(ChatColor.AQUA + "Homes - Help Commands");
			p.sendMessage(ChatColor.YELLOW + "/homes help: " + helpUtils.getHelp_page());
			p.sendMessage(ChatColor.YELLOW + "/home : " + helpUtils.getHelp_home());
			p.sendMessage(ChatColor.YELLOW + "/sethome : " + helpUtils.getHelp_sethome());
			p.sendMessage(ChatColor.WHITE + "-----------------------------------------");
		} else {
			p.sendMessage(ChatColor.WHITE + "-----------------------------------------");
			p.sendMessage(ChatColor.AQUA + "Homes - Help Commands");
			p.sendMessage(ChatColor.YELLOW + "/homes help: " + helpUtils.getHelp_page());
			p.sendMessage(ChatColor.YELLOW + "/home : " + helpUtils.getHelp_home());
			p.sendMessage(ChatColor.YELLOW + "/sethome : " + helpUtils.getHelp_sethome());
			p.sendMessage(ChatColor.YELLOW + "/homes reload : " + helpUtils.getHelp_reload());
			p.sendMessage(ChatColor.WHITE + "-----------------------------------------");
		}
	}
}
