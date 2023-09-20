package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0, changed = 0, same = 0, userId;
        Map<Integer, String> previousMap = new HashMap<>();
        for (User user : previous) {
            previousMap.put(user.getId(), user.getName());
        }
        for (User user : current) {
            userId = user.getId();
            if (!previousMap.containsKey(userId)) {
                added++;
                continue;
            }
            if (previousMap.get(userId).equals(user.getName())) {
                same++;
            } else {
                changed++;
            }
        }
        return new Info(added, changed, previous.size() - changed - same);
    }
}
