package de.hgpractice.hgpgameapi;

import de.hgpractice.hgpgameapi.Player.DamageNerf;
import org.bukkit.plugin.java.JavaPlugin;

public final class HGP_GameAPI extends JavaPlugin {

    private static HGP_GameAPI instance;

    public static HGP_GameAPI getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new DamageNerf(), this);
        System.out.print("\u001b[37m    Enabled\u001b[0m \u001b[31mGameAPI\u001b[0m \u001b[33mV." + getDescription().getVersion() + " by " + getDescription().getAuthors().get(0) + "\u001b[0m");
    }

    @Override
    public void onDisable() {
        System.out.print("\u001b[37m    Disabled\u001b[0m \u001b[31mGameAPI\u001b[0m \u001b[33mV." + getDescription().getVersion() + " by " + getDescription().getAuthors().get(0) + "\u001b[0m");
    }
}
