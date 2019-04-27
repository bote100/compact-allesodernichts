package net.bote.allesodernichts;

import lombok.Getter;
import net.bote.allesodernichts.commands.AONCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Elias Arndt | bote100
 * Created on 27.04.2019
 */

@Getter
public class AllesOderNichts extends JavaPlugin {

    @Getter
    public static AllesOderNichts instance;

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getServer().getPluginCommand("allesodernichts").setExecutor(new AONCommand());
    }

}
