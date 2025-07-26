package de.hgpractice.hgpgameapi.FileConfiguration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

public class ConfigHandler {

    private final String fileName;
    private final String path;
    private final LinkedHashMap<String, Object> configSettings = new LinkedHashMap<String, Object>();

    /**
     * Create a new Yaml configuration file
     * @param fileName Name of the file without .yml
     * @param path Path to the file (e.g. "plugins//HGP_GameAPI")
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

        FileConfiguration cfg = new YamlConfiguration();

        for (String setting : configSettings.keySet()) {
            cfg.set(setting, configSettings.get(setting));
        }

        if (file.exists()) {
            FileConfiguration existingCfg = YamlConfiguration.loadConfiguration(file);
            for (String setting : configSettings.keySet()) {
                Object existingValue = existingCfg.get(setting);
                if (existingValue != null) {
                    cfg.set(setting, existingValue);
                }
            }
        }

        try {
            if (!file.exists()) file.createNewFile();
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
