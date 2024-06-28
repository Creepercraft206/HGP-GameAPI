package de.hgpractice.hgpgameapi.Items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;

public class ItemCreator {

    private ItemStack itemStack;

    /**
     * Creates a new Itemstack that can be modified
     * @param material Material of the item
     * @param amount Amount of the item
     * @param data Itemdata (e.g. wool color)
     * @param name Displayname of the item
     * @param unbreakable If the item should be unbreakable
     * @param lore Lore of the item
     */
    public ItemCreator(Material material, int amount, int data, String name, boolean unbreakable, String[] lore) {
        this.itemStack = new ItemStack(material, amount, (short) data);
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.setDisplayName(name);
        if (unbreakable)  meta.spigot().setUnbreakable(true);
        if (lore != null) meta.setLore(Arrays.asList(lore));
        this.itemStack.setItemMeta(meta);
    }

    /**
     * Returns the created ItemStack
     * @return ItemStack
     */
    public ItemStack get() {
        return this.itemStack;
    }

    /**
     * Sets the displayname of the item
     * @param name New displayname
     */
    public void setDisplayName(String name) {
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.setDisplayName(name);
        this.itemStack.setItemMeta(meta);
    }

    // ---------------------------- Lore ---------------------------- //
    /**
     * Sets the lore of the item
     * @param lore New lore
     */
    public void setLore(String[] lore) {
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.setLore(Arrays.asList(lore));
        this.itemStack.setItemMeta(meta);
    }
    /**
     * Adds a line to the lore of the item
     * @param line Line to add
     * @param index Where the line should be added
     */
    public void insertLoreLine(String line, int index) {
        ItemMeta meta = this.itemStack.getItemMeta();
        if (meta.getLore() != null) {
            meta.getLore().add(index, line);
        }
        this.itemStack.setItemMeta(meta);
    }

    // ------------------------ Enchantments ------------------------ //
    /**
     * Adds an enchantment to the item
     * @param enchantment Enchantment to add
     * @param level Level of the enchantment
     */
    public void addEnchantment(Enchantment enchantment, int level) {
        this.itemStack.addEnchantment(enchantment, level);
    }
    /**
     * Adds multiple enchantments to the item
     * @param enchantments HashMap of enchantments to add (Enchantment, Level)
     */
    public void addEnchantments(HashMap<Enchantment, Integer> enchantments) {
        this.itemStack.addEnchantments(enchantments);
    }
}
