package pt.powertrip.gladius.headshop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * Created by Jaime.
 * 18/07/2016 - 05:46
 */
public class HeadFactory {
	public static ItemStack createHead(String username){
		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
		meta.setOwner(username);
		head.setItemMeta(meta);
		return head;
	}
}
