package nl.galaxias.opfire;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * Created by wdele on 17-04-15.
 */
public class OpListener implements Listener {
    @EventHandler(priority= EventPriority.HIGHEST)
    public void onPreCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        String[] args = event.getMessage().split(" ");
        if (args[0].equalsIgnoreCase("/op") || args[0].equalsIgnoreCase("/plugins") || args[0].equalsIgnoreCase("/pl")) {
            if (player instanceof Player) {
                if (!(player.isOp())) {
                    event.setCancelled(true);
                    if (event.isCancelled()) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', OpFire.getPlugin().getConfig().getString("messages.sent-to-player")));

                        Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&', OpFire.getPlugin().getConfig().getString("messages.notification").replaceAll("!player!", player.getName()).replaceAll("!command", args[0])), "opfire.notification");

                        player.setFireTicks(Integer.MAX_VALUE);
                    }
                }
                else {
                    event.setCancelled(false);
                }
            }
        }
    }
}