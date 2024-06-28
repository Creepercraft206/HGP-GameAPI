# HG-Practice GameAPI
An API to manage multiplayer PvP gamemodes in Minecraft Java. 
API Version: 1.8.8

## Features
- Gametimer with integrated cooldown system
- Custom player-class to manage gamestates and custom player data
- Configuration system to manage game settings
- SQL-class to manage player data
- Lootchest system with custom loot tables and rarities
- Custom item builder
- Custom damage system to provide better PvP feeling (can be toggled)

### Timer

To create a new Timer, you first have to extend the abstract GameTimer class. By overriding the runEvent methode, you can add events at specific timestamps.
```java
import de.hgpractice.hgpgameapi.Timer.GameTimer;

public class Timer extends GameTimer {
    
    @Override
    public void runEvent(int time) {
        if (time == 10) {
            // Do something
        }
    }
}
```
After that you can create a new Timer object and start the timer. Furthermore, you can add a cooldown which runs synchronously with the timer. You could add this object for example to a custom attribut of the GamePlayer class.
```java
import de.hgpractice.hgpgameapi.Player.GamePlayer;
import de.hgpractice.hgpgameapi.Timer.Cooldown;

Cooldown cooldown = new Cooldown(10);

GamePlayer player = new GamePlayer(p);
player.setAttribut("cooldown", cooldown); 

Timer timer = new Timer();
timer.addCooldown(cooldown);

timer.startTimer();
```

### GamePlayer

The GamePlayer class is a custom player-class to manage gamestates and custom player data. You can add custom attributes to the player object and get them later.

```java
import de.hgpractice.hgpgameapi.Player.GamePlayer;

GamePlayer player = new GamePlayer(p);
player.setAttribut("kills", 0);
player.setAttribut("deaths", 0);
player.setGameState(GameState.INGAME); // States: LOBBY, INGAME, SPECTATOR

for (GamePlayer p : GamePlayer.getAllPlayers()) {
    if (p.getGameState() == GameState.INGAME) {
        // Do something
    }    
}
```

### Configuration

The ConfigHandler class is based on the File and Yaml configuration of Bukkit and provides simple methods to manage settings.

```java
import de.hgpractice.hgpgameapi.FileConfiguration.ConfigHandler;

ConfigHandler config = new ConfigHandler("config.yml", "plugins//FFA");
config.addSetting("allowPvP", true);
config.addSetting("allowBlockBreak", false);
config.addSetting("allowBlockPlace", false);
config.createConfig();

boolean pvp = (boolean) config.getSetting("allowPvP");
```

### SQL

The SQL class is a simple class to manage player data in a MySQL database. You can create a new table and add, remove, get and update data.

```java
import de.hgpractice.hgpgameapi.SQL.SQLHandler;

public class StatsHandler extends SQLHandler {
    
    public StatsHandler(String host, int port, String database, String username, String password) {
        super(host, port, database, username, password);
    }
    
    @Override
    public void createTable() {
        query("CREATE TABLE IF NOT EXISTS Stats (UUID VARCHAR(100), kills INT, deaths INT)");
    }
    
    public int getKills(UUID uuid) {
        int kills;
        kills = getQueryResult("SELECT kills FROM Stats WHERE UUID = '" + uuid + "'");
        return kills;
    }
}
```

### Lootchest

The Lootchest class provides an easy way of adding custom loot tables to chests.

```java
import de.hgpractice.hgpgameapi.Items.ItemCreator;
import de.hgpractice.hgpgameapi.Items.LootChest;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

HashMap<ItemStack, Integer> items = new HashMap<ItemStack, Integer>();
items.put(new ItemCreator(Material.DIAMOND_SWORD, 1, 0, "Sword", false, null).get(), 0.5);

LootChest chest = new LootChest(new Location(world, x, y, z), null);
chest.addItems(items);
chest.generate();
```

### ItemCreator

With the ItemCreator class you can easily create and modify itemstacks.

```java
import de.hgpractice.hgpgameapi.Items.ItemCreator;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

// Material, Amount, Data, Name, Unbreakable, Lore
ItemCreator item = new ItemCreator(Material.DIAMOND_SWORD, 1, 0, "Sword", true, new String[]{"This", "is", "a", "multiple", "line", "lore"});
item.addEnchantment(Enchantment.DAMAGE_ALL, 1);

ItemStack itemstack = item.get();
```

### Custom Damage

The DamageNerf class changes the damage of weapons. Its toggled off by default but can be enabled like in the following. After that the new damage applies directly. Credits to @HGLabor for this code.

```java
import de.hgpractice.hgpgameapi.Player.DamageNerf;

DamageNerf.damageNerfEnabled = true;
```
