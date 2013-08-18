package me.michaelkrauty.NickRequest;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class NickRequest extends JavaPlugin {
	
	public static final Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info("[NickRequest] NickRequest version " + pdfFile.getVersion() + " enabled");
	}
	
	@Override
	public void onDisable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info("[NickRequest] NickRequest version " + pdfFile.getVersion() + " disabled");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		
		if(args.length !=1){
			sender.sendMessage(ChatColor.RED + "Usage: /nickrequest <nickname>");
			return true;
		}
		
		if(commandLabel.equalsIgnoreCase("nickrequest")){
			sender.sendMessage(ChatColor.GOLD + "You requested that your nickname be changed to " + args[0]);
			return true;
		}
		return true;
	}
}
