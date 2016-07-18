package pt.powertrip.gladius.headshop.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import pt.powertrip.gladius.headshop.HeadFactory;
import pt.powertrip.gladius.headshop.HeadShop;
import pt.powertrip.gladius.headshop.HeadShopPlugin;

/**
 * Created by Jaime.
 * 18/07/2016 - 05:44
 */
public class GetHeadCommand implements CommandExecutor{
	private HeadShopPlugin plugin;

	public GetHeadCommand(Plugin plugin) {
		this.plugin = (HeadShopPlugin) plugin;
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if (!(commandSender instanceof Player)) return false;
		Player player = (Player) commandSender;
		HeadShop.giveOwnHead(player);
		return true;
	}
}
