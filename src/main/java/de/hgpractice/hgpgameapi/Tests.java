package de.hgpractice.hgpgameapi;

import Utils.Event;
import Utils.GameHandler;

public class Tests {

    GameHandler gh;

    public Event test(int a, int b) {
        a = a + b;

        return gh.events.get(1);
    }

    Event e = new Event(1,5,gh);

}
