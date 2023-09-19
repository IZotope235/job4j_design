package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0, changed = 0, same = 0;
        Map<Integer, String> previousMap = new HashMap<>();
        for (User user : previous) {
            previousMap.put(user.getId(), user.getName());
        }
        for (User user : current) {
            if (!previousMap.containsKey(user.getId())) {
                added++;
                continue;
            }
            if (previousMap.get(user.getId()).equals(user.getName())) {
                same++;
            }
            if (!previousMap.get(user.getId()).equals(user.getName())) {
                changed++;
            }
        }
        return new Info(added, changed, previous.size() - changed - same);
    }
}
