package net.bote.allesodernichts.commands;

import net.bote.allesodernichts.AllesOderNichts;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Elias Arndt | bote100
 * Created on 27.04.2019
 */

public class AONCommand implements CommandExecutor {

    private int task = 0;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;

        Player p = ((Player) sender);

        if(!p.hasPermission("system.aon")) {
            p.sendMessage("§cKein Recht!");
            return false;
        }

        if(task != 0) {
            p.sendMessage("§cLäuft bereits!");
            return false;
        }

        AtomicInteger atomicInteger = new AtomicInteger(10);

        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(AllesOderNichts.getInstance(), () -> {

            p.sendMessage("§cBerechne Zahlen...");

            atomicInteger.set(atomicInteger.get() - 1);

            if(atomicInteger.get() == 0) {
                release(p);
                Bukkit.getScheduler().cancelTask(task);
                task = 0;
            }

        }, 0, 20);

        return false;
    }

    private void release(Player p) {
        p.sendMessage("§aErgebnis wurde gefunden!");
        p.sendMessage("");
        p.sendMessage("§7Geld: §e" + ThreadLocalRandom.current().nextInt(100) + "€ §7ING-Geld: §e" + ThreadLocalRandom.current().nextInt(1000000) + "€");
    }

}
