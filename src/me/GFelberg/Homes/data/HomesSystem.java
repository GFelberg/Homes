package me.GFelberg.Homes.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.GFelberg.Homes.Main;

public class HomesSystem {

	public static Map<UUID, Location> homes_locations = new HashMap<UUID, Location>();
	public static String home_setted, home_message, home_notfound;

	public static void loadVariables() {
		home_setted = Main.getInstance().getConfig().getString("Homes.Setted").replace("&", "§");
		home_message = Main.getInstance().getConfig().getString("Homes.Message").replace("&", "§");
		home_notfound = Main.getInstance().getConfig().getString("Homes.NotFound").replace("&", "§");
	}

	public void sethome(Player player) {
		FileConfiguration customConfig = HomesConfig.getConfig();
		UUID uuid = player.getUniqueId();
		Location loc = player.getLocation();
		customConfig.set("Homes." + uuid.toString() + ".Player", player.getName());
		customConfig.set("Homes." + uuid.toString() + ".World", loc.getWorld().getName());
		customConfig.set("Homes." + uuid.toString() + ".X", loc.getX());
		customConfig.set("Homes." + uuid.toString() + ".Y", loc.getY());
		customConfig.set("Homes." + uuid.toString() + ".Z", loc.getZ());
		customConfig.set("Homes." + uuid.toString() + ".Yaw", loc.getYaw());
		customConfig.set("Homes." + uuid.toString() + ".Pitch", loc.getPitch());
		HomesConfig.saveConfig();
		homes_locations.put(uuid,
				new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch()));
		player.sendMessage(home_setted);
	}

	public void home(Player player) {
		FileConfiguration customConfig = HomesConfig.getConfig();
		UUID uuid = player.getUniqueId();

		if (customConfig.getString("Homes." + uuid.toString()) == null) {
			player.sendMessage(home_notfound);
			return;
		} else {
			Location destination = homes_locations.get(uuid);
			player.teleport(destination);
			player.sendMessage(home_message);
		}
	}

	public static void loadHomes() {

		for (Player players : Bukkit.getOnlinePlayers()) {
			FileConfiguration customConfig = HomesConfig.getConfig();
			UUID uuid = players.getUniqueId();
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
				homes_locations.put(uuid, loc);
			}
		}
	}
}
