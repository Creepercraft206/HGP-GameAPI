package de.hgpractice.hgpgameapi;

import de.hgpractice.hgpgameapi.Player.DamageNerf;
import org.bukkit.Bukkit;
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
        Bukkit.getLogger().info(
                "\n§7Enabled §eHGP_GameAPI §7by §6HG-Practice" +
                     "\n§7Version: §e1.0" +
                     "\n§7Author: §eCreeper_craft | Tim" +
                     "\n§7Discord: §e@creepercraft\n"
        );
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(
                "\n§7Disabled §eHGP_GameAPI §7by §6HG-Practice" +
                     "\n§7Version: §e1.0" +
                     "\n§7Author: §eCreeper_craft | Tim" +
                     "\n§7Discord: §e@creepercraft\n"
        );
    }
}
