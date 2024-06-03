package me.GFelberg.Homes.events;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.GFelberg.Homes.data.HomesConfig;
import me.GFelberg.Homes.data.HomesSystem;

public class HomesEvents implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		FileConfiguration customConfig = HomesConfig.getConfig();
		Player p = event.getPlayer();
		UUID uuid = p.getUniqueId();

		if (customConfig.getString("Homes." + uuid.toString()) == null) {
			return;
		} else {
			String world = (String) customConfig.get("Homes." + uuid.toString() + ".World");
			double x = (double) customConfig.get("Homes." + uuid.toString() + ".X");
			double y = (double) customConfig.get("Homes." + uuid.toString() + ".Y");
			double z = (double) customConfig.get("Homes." + uuid.toString() + ".Z");
			float yaw = (float) customConfig.getDouble("Homes." + uuid.toString() + ".Yaw");
			float pitch = (float) customConfig.getDouble("Homes." + uuid.toString() + ".Pitch");
			Location loc = new Location(Bukkit.getWorld(world), x, y, z, (float) yaw, (float) pitch);
			HomesSystem.homes_locations.put(uuid, loc);
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		FileConfiguration customConfig = HomesConfig.getConfig();
		Player p = event.getPlayer();
		UUID uuid = p.getUniqueId();

		if (customConfig.getString("Homes." + uuid.toString()) == null) {
			return;
		} else {
			HomesSystem.homes_locations.remove(uuid);
		}
	}
}