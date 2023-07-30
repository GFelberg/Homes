package me.GFelberg.Homes;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.GFelberg.Homes.utils.UpdateChecker;
import me.GFelberg.Homes.commands.Home;
import me.GFelberg.Homes.commands.Homes;
import me.GFelberg.Homes.commands.Sethome;
import me.GFelberg.Homes.data.HomesConfig;
import me.GFelberg.Homes.data.HomesSystem;
import me.GFelberg.Homes.events.HomesEvents;
import me.GFelberg.Homes.utils.HomesUtils;

public class Main extends JavaPlugin implements Listener {

	private static Main instance;

	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		loadHomesConfig();
		loadCommands();
		loadVariables();
		loadEvents();
		HomesSystem.loadHomes();
		Bukkit.getConsoleSender().sendMessage("----------------------------");
		Bukkit.getConsoleSender().sendMessage("Homes Plugin Enabled!");
		Bukkit.getConsoleSender().sendMessage("Plugin developed by GFelberg");
		Bukkit.getConsoleSender().sendMessage("----------------------------");

		new UpdateChecker(this, 111630).getVersion(version -> {
			if (this.getDescription().getVersion().equals(version)) {
				getLogger().info("No update available.");
			} else {
				getLogger().info("There is a new update available.");
			}
		});
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

	public void loadEvents() {
		Bukkit.getPluginManager().registerEvents(new HomesEvents(), this);
		Bukkit.getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		FileConfiguration config = this.getConfig();

		if (config.getBoolean("update-check") && p.isOp()
				|| config.getBoolean("update-check") && p.hasPermission("homes.update")) {
			(new UpdateChecker(this, 111630)).getVersion((version) -> {
				if (!this.getDescription().getVersion().equalsIgnoreCase(version)) {
					p.sendMessage("There is a new update available of Back.");
				}
			});
		}
	}
}