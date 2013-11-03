package me.michaelkrauty.NickRequest;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
public class NickRequest extends JavaPlugin implements Listener{
	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e){
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		
		if(commandLabel.equalsIgnoreCase("nickrequest")){
			if(args.length == 1){
				if(sender.hasPermission("nickrequest.nickrequest")){
					getConfig().set(sender.getName(), args[0]);
					sender.sendMessage(ChatColor.GOLD + "You requested that your nickname be changed to " + args[0] + ". ");
					for(Player online : getServer().getOnlinePlayers()){
						if(online.hasPermission("nickrequest.nicklist")){
							online.sendMessage(ChatColor.GOLD + sender.getName() + " has requested their nickname be changed to " + args[0]);
						}
					}
					saveConfig();
					reloadConfig();
				}else{
					sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
					return true;
				}
			}else{
				sender.sendMessage(ChatColor.RED + "Usage: /nickrequest <nickname>");
				return true;
			}
		}
		
		
		
		
		if(commandLabel.equalsIgnoreCase("nicklist")){
			if(args.length == 0){
				if(sender.hasPermission("nickrequest.nicklist")){
					sender.sendMessage(ChatColor.GOLD + "Requested nicknames:");
					for(Player online : getServer().getOnlinePlayers()){
						if(getConfig().contains(online.getName())){
							sender.sendMessage(ChatColor.GOLD + online.getName() + ": " + getConfig().getString(online.getName()));
						}
					}
				}else{
					sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
					return true;
				}
			}else{
				sender.sendMessage(ChatColor.RED + "Usage: /nicklist");
				return true;
			}
		}
		
		
		
		
		if(commandLabel.equalsIgnoreCase("nickdone")){
			if(args.length == 1){
				if(sender.hasPermission("nickrequest.nickdone")){
					Player target = getServer().getPlayer(args[0]);
					if(target instanceof Player){
						getConfig().getConfigurationSection("").set("" + target.getName(), null);
						sender.sendMessage(ChatColor.GOLD + "Removed " + target.getName() + "'s nickname request.");
						saveConfig();
						reloadConfig();
					}else{
						sender.sendMessage(ChatColor.RED + "Couldn't find that player!");
						return true;
					}
				}else{
					sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
					return true;
				}
			}else{
				sender.sendMessage(ChatColor.RED + "Usage: /nickdone <player>");
				return true;
			}
		}
		return true;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		if(player.hasPermission("nickrequest.nicklist")){
			player.sendMessage(ChatColor.GOLD + "Requested nicknames:");
			for(Player online : getServer().getOnlinePlayers()){
				if(getConfig().contains(online.getName())){
					player.sendMessage(ChatColor.GOLD + online.getName() + ": " + getConfig().getString(online.getName()));
				}
			}
		}
	}
}