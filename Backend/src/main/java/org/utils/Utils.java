package org.utils;

import org.data.entities.Game;

import java.util.LinkedList;
import java.util.List;

public class Utils {
    public static List<Long> extractIds(List<Game> games) {
        LinkedList<Long> f = new LinkedList<>();
        for (Game g : games) {
            f.add(g.getId());
        }
        return f;
    }
}
