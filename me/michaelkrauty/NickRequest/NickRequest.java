package me.michaelkrauty.NickRequest;

import java.io.File;
import java.util.logging.Logger;

import me.michaelkrauty.NickRequest.commands.NickExecutor;
import me.michaelkrauty.NickRequest.util.ListStore;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class NickRequest extends JavaPlugin {
	
	public static final Logger log = Logger.getLogger("Minecraft");
	public ListStore nickRequests;
	
	public void onEnable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info("[NickRequest] NickRequest version " + pdfFile.getVersion() + " enabled");
		
		String pluginFolder = this.getDataFolder().getAbsolutePath();
		
		(new File(pluginFolder)).mkdirs();
		
		this.nickRequests = new ListStore(new File(pluginFolder + File.separator + "NickRequests.txt"));
		
		this.nickRequests.load();
		
		this.getCommand("nickrequest").setExecutor(new NickExecutor(this));
	}
	
	@Override
	public void onDisable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info("[NickRequest] NickRequest version " + pdfFile.getVersion() + " disabled");
	}
	

}
