package Utils;

public class Event {

    int gametime;
    int number;
    GameHandler gh;

    public Event(int id, int time, GameHandler gameHandler) {
        gametime = time;
        gh = gameHandler;
        gh.events.add(this);
        number = id;
    }

    public int getGametime() {
        return gametime;
    }
}
