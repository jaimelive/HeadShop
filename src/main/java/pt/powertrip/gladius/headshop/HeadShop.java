package pt.powertrip.gladius.headshop;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Jaime.
 * 18/07/2016 - 06:09
 */
public class HeadShop {
	public static void givePlayerItemStack(Player player, ItemStack itemStack){
		player.getInventory().addItem(itemStack);
	}

	public static void giveOwnHead(Player player){
		givePlayerItemStack(player, HeadFactory.createHead(player.getName()));
	}
}
