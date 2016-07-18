package pt.powertrip.gladius.headshop;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pt.powertrip.gladius.headshop.commands.GetHeadCommand;
import pt.powertrip.gladius.headshop.listeners.HeadClickListener;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jaime.
 * 18/07/2016 - 04:55
 */
public class HeadShopPlugin extends JavaPlugin{
	private static Plugin plugin;
	private static PluginManager pluginManager;

	public HeadShopPlugin() {
		HeadShopPlugin.plugin = this;
		HeadShopPlugin.pluginManager = getServer().getPluginManager();
	}

	@Override
	public void onEnable() {
		//saveDefaultConfig();
		List<Listener> listeners = new LinkedList<>(
			Arrays.asList(
				new HeadClickListener(this)
			)
		);
		listeners.stream().
			forEach(listener -> pluginManager.registerEvents(listener, this));

		getCommand("gethead").setExecutor(new GetHeadCommand(this));
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}

	public static HeadShopPlugin getPlugin(){
		return (HeadShopPlugin) plugin;
	}
}
