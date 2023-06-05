package me.GFelberg.Homes.utils;

import me.GFelberg.Homes.Main;

public class HelpPageUtils {

	public String getHelp_page() {
		return Main.getInstance().getConfig().getString("Help.Page").replace("&", "§");
	}

	public String getHelp_home() {
		return Main.getInstance().getConfig().getString("Help.Home").replace("&", "§");
	}
	
	public String getHelp_sethome() {
		return Main.getInstance().getConfig().getString("Help.Sethome").replace("&", "§");
	}
	
	public String getHelp_reload() {
		return Main.getInstance().getConfig().getString("Help.Reload").replace("&", "§");
	}
}