package de.hgpractice.hgpgameapi.FileConfiguration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ConfigHandler {

    private String fileName;
    private String path;
    private HashMap<String, Object> configSettings = new HashMap<String, Object>();

    /**
     * Create a new Yaml configuration file
     * @param fileName Name of the file without .yml
     * @param path Path to the file (e.g. "plugins//HGP_GameAPI//")
     */
    public ConfigHandler(String fileName, String path) {
        this.fileName = fileName;
        this.path = path;
    }

    /**
     * Add a setting to the configuration file. Won't be applied directly, but can be through {@link #createConfig()}.
     * @param setting Name of the setting
     * @param value Value of the setting
     */
    public void addSetting(String setting, Object value) {
        this.configSettings.put(setting, value);
    }

    /**
     * Create the configuration file with the settings added through {@link #addSetting(String, Object)}
     */
    public void createConfig() {
        File dir = new File(this.path + "//");
        File file = new File(this.path + "//" + this.fileName + ".yml");
        if (!dir.exists()) dir.mkdir();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        for (String setting : configSettings.keySet()) {
            if (cfg.get(setting) == null) {
                cfg.set(setting, configSettings.get(setting));
            }
        }
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Override a setting in the configuration file
     * @param setting Name of the setting
     * @param value New value of the setting
     */
    public void overrideSetting(String setting, Object value) {
        File file = new File(this.path + "//" + this.fileName + ".yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        cfg.set(setting, value);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a setting from the configuration file
     * @param setting Name of the setting
     * @return Value of the setting
     */
    public Object getSetting(String setting) {
        File file = new File(this.path + "//" + this.fileName + ".yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        return cfg.get(setting);
    }
}
