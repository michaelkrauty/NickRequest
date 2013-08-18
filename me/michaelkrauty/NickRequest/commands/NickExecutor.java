package me.michaelkrauty.NickRequest.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.michaelkrauty.NickRequest.NickRequest;

public class NickExecutor implements CommandExecutor{
	
	public NickRequest plugin;
	
	public NickExecutor(NickRequest plugin){
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (args.length != 1){
			sender.sendMessage(ChatColor.RED + "Usage: /nickrequest <nickname>");
			return true;
		}
				
		plugin.nickRequests.add(sender.getName() + ": " + args[0]);
		plugin.nickRequests.save();
		sender.sendMessage(ChatColor.GOLD + "You requested that your nickname be changed to " + args[0]);
				
		return true;
	}
}
