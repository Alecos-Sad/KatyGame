package com.example.my_framework.utilits;

import java.util.Random;

public class UtilRandomFW {

    public static int getCasualNumber(int number) {
        Random random = new Random();
        return random.nextInt(number);
    }

    public static int getGap(int minNumber, int maxNumber) {
        int gap;
        gap = (int) ((Math.random() * ++maxNumber) + minNumber);
        return gap;
    }
}
