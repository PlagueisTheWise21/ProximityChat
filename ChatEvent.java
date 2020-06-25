package com.github.plagueisthewise21;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;



public class ChatEvent implements Listener{
	
	private Main plugin;

	public ChatEvent(Main main) {
		plugin = main;
	}
	
	int i = 0;
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		i = 0;
		int close = plugin.getConfig().getInt("close");
		int medium = plugin.getConfig().getInt("medium");
		int far = plugin.getConfig().getInt("far");
		
		String message = event.getMessage();
		Player sender = event.getPlayer();
		Location playerLoc = sender.getLocation();
		
		event.setCancelled(true);
		
		StringBuilder builder = new StringBuilder();
		String string = plugin.getConfig().getString("player-tag");
		String string2 = string.replaceAll("&" , "§");
		String tag = string2.replaceAll("%player%", sender.getName());
		builder.append(tag + " ");
		
		for(Player reciever : Bukkit.getOnlinePlayers()) {
			if(reciever.equals(sender)) {
				continue;
			}
			
			Location senderLoc = reciever.getLocation();
			if(playerLoc.distance(senderLoc) < close) {
				i++;
				builder.append(ChatColor.WHITE + message);
				reciever.sendMessage(builder.toString());
			}else if(playerLoc.distance(senderLoc) >= close && playerLoc.distance(senderLoc) < medium ) {
				i++;
				builder.append(ChatColor.GRAY + message);
				reciever.sendMessage(builder.toString());
			}else if(playerLoc.distance(senderLoc) >= far && playerLoc.distance(senderLoc) > far) {
				i++;
				builder.append(ChatColor.DARK_GRAY + message);
				reciever.sendMessage(builder.toString());
			}
		}
		if(i == 0) {
			StringBuilder alone = new StringBuilder();
			String s = plugin.getConfig().getString("no-players-around-message");
			String s1 = s.replaceAll("&" , "§");
			String s2 = s1.replaceAll("%player%", sender.getName());
			alone.append(s2);

			sender.sendMessage(alone.toString());
		}
	}
}
