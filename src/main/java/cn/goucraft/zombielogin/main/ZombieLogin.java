package cn.goucraft.zombielogin.main;

import cn.goucraft.zombielogin.configuration.ZombieLoginConfig;
import cn.goucraft.zombielogin.utils.ZombieLoginCheck;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;


public final class ZombieLogin extends JavaPlugin {
    private ZombieLoginConfig config;
    private ZombieLoginCheck check=new ZombieLoginCheck();

    @Override
    public void onEnable() {
        config=new ZombieLoginConfig(this);
        config.saveResourceIfNotExists("config.yml");
        config.saveResourceIfNotExists("language.yml");
        config.loadConfig();
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("zombielogin.reload")) {
                config.loadConfig();
                sender.sendMessage("重载成功");
                return true;
            }
            if (args[0].equalsIgnoreCase("show")){
                check.isAllTrue(config);
                return true;
            }
        }
        return false;
    }
}
