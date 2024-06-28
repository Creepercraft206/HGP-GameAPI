package de.hgpractice.hgpgameapi.Items;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class LootChest {

    private Location loc;
    private HashMap<ItemStack, Double> items = new HashMap<ItemStack, Double>();

    /**
     * Creates a new LootChest at a specific location and a list of items and rarities.
     * @param loc The location of the chest
     * @param items The items and their rarities (e.g. 0.5 for 50%)
     */
    public LootChest(Location loc, HashMap<ItemStack, Double> items) {
        this.loc = loc;
        this.items = items;
    }

    /**
     * Adds new items to the chest with a specific rarity.
     * @param items The items and their rarities to add (e.g. 0.5 for 50%)
     */
    public void addItems(HashMap<ItemStack, Double> items) {
        this.items.putAll(items);
    }

    /**
     * Generates the chest with the items at the given location. Uses the chest block if already placed or places a new one.
     */
    public void generate() {
        Chest chest;
        if (this.loc.getBlock().getType() != Material.CHEST && this.loc.getBlock().getType() != Material.TRAPPED_CHEST) {
            this.loc.getBlock().setType(Material.CHEST);
        }
        chest = (Chest) this.loc.getBlock().getState();
        ArrayList<ItemStack> content = new ArrayList<ItemStack>();
        for (ItemStack item : this.items.keySet()) {
            if (Math.random() <= this.items.get(item)) {
                content.add(item);
            }
        }
        for (ItemStack item : content) {
            int slot;
            do {
                slot = new Random().nextInt(chest.getInventory().getSize());
            } while (chest.getInventory().getItem(slot) != null && !chest.getInventory().getItem(slot).getType().equals(Material.AIR));
            chest.getInventory().setItem(slot, item);
        }
    }

    /**
     * Sets the new location of the chest.
     * @param loc The new location
     */
    public void setLocation(Location loc) {
        this.loc = loc;
    }

    /**
     * Returns the location of the chest.
     * @return location
     */
    public Location getLocation() {
        return loc;
    }

    /**
     * Returns the items and their rarities.
     * @return items
     */
    public HashMap<ItemStack, Double> getItems() {
        return items;
    }
}
