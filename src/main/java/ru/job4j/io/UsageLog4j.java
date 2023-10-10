package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
       String name = "Peter Arsentev";
        byte age = 33;
        short weight = 85;
        int salary = 150000;
        long numberOfCommits = 4239857394857938457L;
        boolean isProgrammer = true;
        float height = 1.81f;
        double codeLineByDay = 12414423.234234534535d;
        char group = 'A';
        LOG.debug("\nUser info name : {}, age : {}, weight : {}, salary : {}, commits : {}, ",
                name, age, weight, salary, numberOfCommits);
        LOG.debug("\nIs programmer? : {}, height : {}, code line by day : {}, group : {}",
                isProgrammer, height, codeLineByDay, group);

    }
}
