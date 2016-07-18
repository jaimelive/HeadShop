package pt.powertrip.gladius.headshop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_10_R1.BlockPosition;
import net.minecraft.server.v1_10_R1.TileEntitySkull;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.Skull;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.json.simple.JSONObject;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Properties;
import java.util.UUID;

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

	public static String getSkinUrlFromSkull(Skull skull){
		TileEntitySkull skullTile = (TileEntitySkull) ((CraftWorld)skull.getWorld()).getHandle().getTileEntity(new BlockPosition(skull.getX(), skull.getY(), skull.getZ()));
		if(skullTile==null) return null;
		GameProfile profile = skullTile.getGameProfile();
		if(profile==null)return null;
		Collection<Property> textures = profile.getProperties().get("textures");
		if(textures.size() != 1) return null;
		String texture = new String(Base64.decodeBase64( ((Property)(textures.toArray()[0])).getValue()));
		Gson gson = new GsonBuilder().create();
		JsonElement element = gson.fromJson (texture, JsonElement.class);
		JsonObject jsonObj = element.getAsJsonObject();
		String url = jsonObj.getAsJsonObject("textures").getAsJsonObject("SKIN").get("url").getAsString();
		return url;
	}

	public static ItemStack createTexturedHead(String skinUrl){
		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta meta = (SkullMeta) head.getItemMeta();
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", skinUrl).getBytes());
		profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
		Field profileField = null;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
		head.setItemMeta(meta);
		return head;
	}

}
