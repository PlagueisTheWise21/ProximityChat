package com.github.plagueisthewise21;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	public void onEnable() {
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		
		Bukkit.getPluginManager().registerEvents(new ChatEvent(this), this);
		this.getCommand("gl").setExecutor(new GlobalChat(this));
		
	}
	
	public void onDisable() {
		
	}

}
