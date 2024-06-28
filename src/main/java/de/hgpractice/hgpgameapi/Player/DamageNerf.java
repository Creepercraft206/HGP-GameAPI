package de.hgpractice.hgpgameapi.Player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageNerf implements Listener {

    /**
     * Toggle to true to enable customised damage for better PvP experience
     */
    private static boolean damageNerfEnabled = false;

    /**
     * Nerfs the damage of the player depending on the item in hand
     * Credits @HGLabor.de
     */
    @EventHandler
    private void onDmg(EntityDamageByEntityEvent e) {
        if (damageNerfEnabled) {
            if (e.getDamager() instanceof Player) {
                Player p = (Player) e.getDamager();
                String itemname = p.getInventory().getItemInHand().getType().name();
                if (itemname.contains("SWORD") || itemname.contains("AXE")) {
                    e.setDamage(e.getDamage() * 0.65);
                } else if (itemname.contains("SHOVEL") || itemname.contains("PICKAXE")) {
                    e.setDamage(e.getDamage() * 0.2);
                }
            }
        }
    }
}
