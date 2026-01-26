package org.erasemap.erasemap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Erasemap extends JavaPlugin {

    private final String[] authors = {"Isax"};

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[EraseMap] Plugin activé !");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("erase")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("Cette commande est réservée aux joueurs.");
                return true;
            }

            Player player = (Player) sender;

            if (!player.hasPermission("erasemap.use")) {
                player.sendMessage("§cTu n'as pas la permission.");
                return true;
            }

            int radius = 10;
            int y = player.getLocation().getBlockY();
            int centerX = player.getLocation().getBlockX();
            int centerZ = player.getLocation().getBlockZ();

            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    if (x * x + z * z <= radius * radius) {
                        player.getWorld()
                                .getBlockAt(centerX + x, y, centerZ + z)
                                .setType(Material.AIR);
                    }
                }
            }

            player.sendMessage("§aCouche supprimée dans un rayon de 10 blocs !");
            return true;

        } else if (command.getName().equalsIgnoreCase("info")) {

            sender.sendMessage("§6=== EraseMap Info ===");
            sender.sendMessage("§eVersion: §f" + this.getDescription().getVersion());
            sender.sendMessage("§eAuthors: §f" + String.join(", ", authors));
            return true;
        }

        return false;
    }
}
