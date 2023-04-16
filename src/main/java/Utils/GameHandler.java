package Utils;

import de.hgpractice.hgpgameapi.HGP_GameAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public class GameHandler {
    
    private final String prefix;

    private final int minplayers;
    private int teleporttime = 15;
    private int starttime = 30;
    private int gametime;

    private final boolean teleportenabled;
    private final boolean graceenabled;

    public ArrayList<Event> events = new ArrayList<Event>();

    private ArrayList<Player> alive = new ArrayList<Player>();

    private enum GameState{
        LOBBY(),
        STARTTIME(),
        TELEPORT(),
        GRACE(),
        GAME(),
        WIN();
    }

    private GameState gameState;
    
    public GameHandler(String pluginprefix, int minplayer, boolean teleport, boolean grace) {
        prefix = pluginprefix;
        minplayers = minplayer;
        gameState = GameState.LOBBY;
        teleportenabled = teleport;
        graceenabled = grace;
    }

    public String getPrefix() {
        return prefix;
    }


    public void startGametimer() {
        if (Bukkit.getOnlinePlayers().size() >= minplayers) {
            gameState = GameState.STARTTIME;
            final BukkitTask runnable = new BukkitRunnable() {
                @Override
                public void run() {
                    if (teleportenabled) {
                        Bukkit.broadcastMessage(prefix + "Teleport startet in §e" + teleporttime + " §7Sekunden.");
                        for (Player all : Bukkit.getOnlinePlayers()){
                            all.playSound(all.getLocation(), Sound.NOTE_BASS_GUITAR,5,5);
                        }
                        teleporttime--;
                    }
                    Bukkit.broadcastMessage(prefix + "Das Spiel startet in §e" + starttime + " §7Sekunden.");
                    starttime--;
                }
            }.runTaskTimer(HGP_GameAPI.getInstance(),0,20);

        }
    }

    public void runEvents() {
        for (Event e : events) {
            if (e.gametime == gametime) {

            }
        }
    }
}
