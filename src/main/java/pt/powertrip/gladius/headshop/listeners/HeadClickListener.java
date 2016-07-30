package pt.powertrip.gladius.headshop.listeners;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.authlib.GameProfile;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.Skull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.Plugin;
import pt.powertrip.gladius.headshop.HeadFactory;
import pt.powertrip.gladius.headshop.HeadShop;
import pt.powertrip.gladius.headshop.HeadShopPlugin;


/**
 * Created by Jaime.
 * 18/07/2016 - 05:00
 */
public class HeadClickListener implements Listener {
	private HeadShopPlugin plugin;

	public HeadClickListener(Plugin plugin) {
		this.plugin = (HeadShopPlugin) plugin;
	}

	@EventHandler()
	public void onRightClick(PlayerInteractEvent event){
		if(
			event.getAction() == Action.RIGHT_CLICK_BLOCK &&
			event.getHand() == EquipmentSlot.HAND &&
			event.getClickedBlock().getType() == Material.SKULL &&
			((Skull)event.getClickedBlock().getState()).getSkullType().ordinal() == SkullType.PLAYER.ordinal()
		){
			Skull skull = (Skull) event.getClickedBlock().getState();
			GameProfile profile = HeadFactory.getSkullGameProfile(skull);
			HeadShop.givePlayerItemStack( event.getPlayer(), HeadFactory.createHeadFromGameProfile(profile));
		}
	}
}
