package de.hgpractice.hgpgameapi.Player;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class GamePlayer {

    private Player player;

    public enum GamePlayerState {
        LOBBY,
        INGAME,
        SPECTATOR
    }
    private GamePlayerState state;

    private HashMap<Object, Object> playerAttributes = new HashMap<Object, Object>();

    private static ArrayList<GamePlayer> gamePlayers = new ArrayList<GamePlayer>();

    /**
     * Creates a new custom player-object for game modes.
     * It provides different states, based on how can player contributes to the game and custom attributs that can be set.
     * @param player Player object from Bukkit that should be handled.
     */
    public GamePlayer(Player player) {
        this.player = player;
        gamePlayers.add(this);
    }

    /**
     * Set the game-state of the player
     * @param state LOBBY, INGAME or SPECTATOR
     */
    public void setGameState(GamePlayerState state) {
        this.state = state;
    }

    /**
     * Get the game-state of the player
     * @return LOBBY, INGAME or SPECTATOR
     */
    public GamePlayerState getGameState() {
        return this.state;
    }

    /**
     * Get the player object from Bukkit
     * @return Player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Set a custom attribute for the player
     * Can be used e.g. for kills, deaths, etc.
     * @param key Key of the attribute
     * @param value Value of the attribute
     */
    public void setAttribute(Object key, Object value) {
        this.playerAttributes.put(key, value);
    }

    /**
     * Get a custom attribute of the player
     * @param key Key of the attribute
     * @return Value of the attribute
     */
    public Object getAttribute(Object key) {
        return this.playerAttributes.get(key);
    }

    /**
     * Remove the player from the ArrayList of all players. Should be done e.g. when a player leaves the game.
     */
    public void remove() {
        gamePlayers.remove(this);
    }

    /**
     * Get all players that are currently in the game
     * @return ArrayList of GamePlayer objects
     */
    public static ArrayList<GamePlayer> getAllPlayers() {
        return gamePlayers;
    }
}
