package cn.goucraft.zombielogin.configuration;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ZombieLoginConfig {
    private final JavaPlugin plugin;
    private World world;
    private boolean isAlgo_Enable;
    private int baserandom;
    private int inrandom;
    private int derandom;
    private boolean isAlgo_Close_0;
    private boolean isAlgo_Close_100;
    private boolean isTtdt_Enable;
    private boolean isTo_Enable;
    private int t_Change;

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public boolean isAlgo_Enable() {
        return isAlgo_Enable;
    }

    public void setAlgo_Enable(boolean algo_Enable) {
        isAlgo_Enable = algo_Enable;
    }

    public int getBaserandom() {
        return baserandom;
    }

    public void setBaserandom(int baserandom) {
        this.baserandom = baserandom;
    }

    public int getInrandom() {
        return inrandom;
    }

    public void setInrandom(int inrandom) {
        this.inrandom = inrandom;
    }

    public int getDerandom() {
        return derandom;
    }

    public void setDerandom(int derandom) {
        this.derandom = derandom;
    }

    public boolean isAlgo_Close_0() {
        return isAlgo_Close_0;
    }

    public void setAlgo_Close_0(boolean algo_Close_0) {
        isAlgo_Close_0 = algo_Close_0;
    }

    public boolean isAlgo_Close_100() {
        return isAlgo_Close_100;
    }

    public void setAlgo_Close_100(boolean algo_Close_100) {
        isAlgo_Close_100 = algo_Close_100;
    }

    public boolean isTtdt_Enable() {
        return isTtdt_Enable;
    }

    public void setTtdt_Enable(boolean ttdt_Enable) {
        isTtdt_Enable = ttdt_Enable;
    }

    public boolean isTo_Enable() {
        return isTo_Enable;
    }

    public void setTo_Enable(boolean to_Enable) {
        isTo_Enable = to_Enable;
    }

    public int getT_Change() {
        return t_Change;
    }

    public void setT_Change(int t_Change) {
        this.t_Change = t_Change;
    }

    public ZombieLoginConfig(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void saveResourceIfNotExists(String fileName) {
        File file = new File(plugin.getDataFolder(), fileName);
        if (!file.exists()) {
            plugin.saveResource(fileName, false);
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    }

    public void loadConfig(){
        File configFile=new File(plugin.getDataFolder(),"config.yml");
        if (!configFile.exists()){
            plugin.saveResource("config.yml",false);
        }
        FileConfiguration config=YamlConfiguration.loadConfiguration(configFile);
        setWorld(Bukkit.getWorld(config.getString("World")));
        setAlgo_Enable(config.getBoolean("algo.enable"));
        setBaserandom(config.getInt("algo.baserandom"));
        setInrandom(config.getInt("algo.inrandom"));
        setDerandom(config.getInt("algo.derandom"));
        setAlgo_Close_0(config.getBoolean("algo.close_0_base"));
        setAlgo_Close_100(config.getBoolean("algo.close_100_base"));
        setTtdt_Enable(config.getBoolean("ttdt.enable"));
        setTo_Enable(config.getBoolean("ttdt.t_enable"));
        setT_Change(config.getInt("ttdt.t_change"));
    }
}
