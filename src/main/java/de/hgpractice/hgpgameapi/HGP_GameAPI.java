package de.hgpractice.hgpgameapi;

import org.bukkit.plugin.java.JavaPlugin;

public final class HGP_GameAPI extends JavaPlugin {

    private static HGP_GameAPI instance;

    public static HGP_GameAPI getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
