package bluescreen9.minecraft.bukkit.haveaseat;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin{

	protected static Plugin HaveASeat;
	protected static FileConfiguration Config;
	protected static ArrayList<ArmorStand> Seats = new ArrayList<ArmorStand>();
	protected static ArrayList<Material> SeatBlocks = new ArrayList<Material>();
					@Override
					public void onEnable() {
							HaveASeat = Main.getPlugin(Main.class);
							saveDefaultConfig();
							reloadConfig();
							Config = getConfig();
							getServer().getPluginManager().registerEvents(new EventListener(), HaveASeat);
							
							for (String str:Config.getStringList("seat-blocks")) {
									SeatBlocks.add(Material.valueOf(str.replaceAll("minecraft:", "").toUpperCase()));
							}
							
							new BukkitRunnable() {
								public void run() {
									for (ArmorStand at:Seats) {
										if (at.getPassengers() == null) {
											at.remove();
										}
										if (at.getPassengers().isEmpty()) {
											at.remove();
										}
								}									
								}
							}.runTaskTimer(HaveASeat, 20L, 20L);
					}
					
					@Override
					public void onDisable() {
							for (ArmorStand at:Seats) {
									if (at.getPassengers() == null) {
										at.remove();
									}
									if (at.getPassengers().isEmpty()) {
										at.remove();
									}
							}
					}
}
