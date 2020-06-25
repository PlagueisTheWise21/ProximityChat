package com.github.plagueisthewise21;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class GlobalChat implements CommandExecutor{

	private Main plugin;

	public GlobalChat(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(!(sender instanceof Player)) {
			System.out.println(ChatColor.RED + "You must be a player to use this command");
		}else {

			Player player = (Player)sender;

			StringBuilder builder = new StringBuilder();
			String string = plugin.getConfig().getString("global-tag") + plugin.getConfig().getString("player-tag");
			String string2 = string.replaceAll("&" , "§");
			String tag = string2.replaceAll("%player%", player.getName());
			builder.append(tag);

			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < args.length; i++) {
				if(i > 0) sb.append(" ");
				sb.append(args[i]);
			}
			String globalMessage = sb.toString();
			for(Player p : Bukkit.getOnlinePlayers()) {
				p.sendMessage(tag + ChatColor.WHITE + globalMessage);
			}
		}
		return false;
	}
}
