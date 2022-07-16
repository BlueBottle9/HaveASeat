package bluescreen9.minecraft.bukkit.haveaseat;

import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventListener implements Listener{
					@EventHandler
					public void onPlayerRightClick(PlayerInteractEvent event) {
							if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
									return;
							}	
							Block block = event.getClickedBlock();
							Player player = event.getPlayer();
							if (player.isSneaking()) {
								return;
							}
							if (player.getLocation().distance(block.getLocation()) > Main.Config.getDouble("max-click-range")) {
								return;
							}
							if (!Main.SeatBlocks.contains(block.getType())) {
								return;	
							}
							
							ArmorStand at = player.getWorld().spawn(block.getLocation().add(0.5D, -1D, 0.5D), ArmorStand.class);
							at.setInvisible(true);
							at.setInvulnerable(true);
							at.setGravity(false);
							Main.Seats.add(at);
							at.addPassenger(player);
							((Attributable)at).getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(0D);
					}
}
