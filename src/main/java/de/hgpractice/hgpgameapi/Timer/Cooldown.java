package de.hgpractice.hgpgameapi.Timer;

public class Cooldown {

    private long time;
    private long startTime;
    private boolean active;

    /**
     * Creates a new Cooldown which is integrated into {@link GameTimer}. The cooldown is not active by default, but can be started with {@link #startCooldown()}.
     * @param time Time of how high the cooldown should be
     */
    public Cooldown(long time) {
        this.time = time;
        this.startTime = time;
        this.active = false;
    }

    /**
     * Starts the cooldown
     */
    public void startCooldown() {
        this.active = true;
    }

    /**
     * Counts down the time of the cooldown. If the time is 0, the cooldown will be set to its start value again.
     * Cooldown can be started with {@link #startCooldown()} again.
     */
    public void countDown() {
        this.time--;
        if (this.time == 0) {
            this.time = this.startTime;
            this.active = false;
        }
    }

    /**
     * Returns true if the cooldown is active
     * @return boolean
     */
    public boolean isActive() {
        return this.active;
    }
}
