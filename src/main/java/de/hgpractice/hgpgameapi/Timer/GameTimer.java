package de.hgpractice.hgpgameapi.Timer;

import de.hgpractice.hgpgameapi.HGP_GameAPI;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public abstract class GameTimer {

    private BukkitTask gameTimer;
    private int time;

    private ArrayList<Cooldown> cooldowns = new ArrayList<Cooldown>();

    /**
     * Creates a new GameTimer with basic actions to start, pause and stop a timer.
     */
    public GameTimer() {

    }

    /**
     * Starts the timer. Also runs all events implemented through {@link #runEvent(int)}
     * and counts down all active cooldowns added through {@link #addCooldown(Cooldown)}.
     */
    public void startTimer() {
        this.gameTimer = new BukkitRunnable() {
            @Override
            public void run() {
                runEvent(time);
                if (!cooldowns.isEmpty()) {
                    for (Cooldown cooldown : cooldowns) {
                        if (cooldown.isActive()) cooldown.countDown();
                    }
                }
                time++;
            }
        }.runTaskTimer(HGP_GameAPI.getInstance(),0,20);
    }

    /**
     * Pauses the timer.
     */
    public void pauseTimer() {
        this.gameTimer.cancel();
    }

    /**
     * Stops the timer and resets the time to 0.
     */
    public void stopTimer() {
        this.time = 0;
        this.gameTimer.cancel();
    }

    /**
     * Adds a new cooldown to the timer.
     * @param cooldown The cooldown to add
     */
    public void addCooldown(Cooldown cooldown) {
        this.cooldowns.add(cooldown);
    }

    /**
     * Returns the current time of the timer.
     * @return time
     */
    public int getTime() {
        return this.time;
    }

    /**
     * Runs an event at the given time.<br>
     * {@code if (time == 10) { ... }}
     * @param time The time to run the event at
     */
    public abstract void runEvent(int time);
}
