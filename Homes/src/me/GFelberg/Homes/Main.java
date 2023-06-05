package me.GFelberg.Homes;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.GFelberg.Homes.commands.Home;
import me.GFelberg.Homes.commands.Homes;
import me.GFelberg.Homes.commands.Sethome;
import me.GFelberg.Homes.data.HomesConfig;
import me.GFelberg.Homes.data.HomesSystem;
import me.GFelberg.Homes.events.HomesEvents;
import me.GFelberg.Homes.utils.HomesUtils;

public class Main extends JavaPlugin {

	private static Main instance;

	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		loadHomesConfig();
		loadCommands();
		loadVariables();
		HomesSystem.loadHomes();
		Bukkit.getPluginManager().registerEvents(new HomesEvents(), this);
		Bukkit.getConsoleSender().sendMessage("----------------------------");
		Bukkit.getConsoleSender().sendMessage("Homes Plugin Enabled!");
		Bukkit.getConsoleSender().sendMessage("Plugin developed by GFelberg");
		Bukkit.getConsoleSender().sendMessage("----------------------------");
	}

	public static Main getInstance() {
		return instance;
	}

	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("----------------------------");
		Bukkit.getConsoleSender().sendMessage("Homes Plugin Disabled!");
		Bukkit.getConsoleSender().sendMessage("Plugin developed by GFelberg");
		Bukkit.getConsoleSender().sendMessage("----------------------------");
	}

	public void loadHomesConfig() {
		HomesConfig.setupConfig();
		HomesConfig.getConfig().options().copyDefaults(true);
		HomesConfig.saveConfig();
	}

	public void loadCommands() {
		getCommand("home").setExecutor(new Home());
		getCommand("homes").setExecutor(new Homes());
		getCommand("sethome").setExecutor(new Sethome());
	}
	
	public void loadVariables() {
		HomesSystem.loadVariables();
		HomesUtils.loadVariables();
	}
}