package com.company;

public class Person {
    int HP;
    int maxHP;
    int o2Consume;
    int co2Release;
    BadTraits badTraits;
    GoodTraits goodTraits;
    Gender gender;
    int currentRoom;
    int[] jobStack = new int[30];
    byte[] skillArray = new byte[10];
    int foodCount;
    int pottyCount;
    int sleepCount;
    int jobNextWrite;
    int[] jobWorkLeft = new int[30];
    int[] skillLvl = new int[10];
    int[] jobUsesSkill = new int[30];
    String name;
    boolean skipNextFood = false;
    boolean doubleNextFood = false;

    public void updateCounts() {
        if (!skipNextFood) {
            foodCount--;
            if (goodTraits == GoodTraits.slowMetabolism)
                skipNextFood = true;
        }
        if (doubleNextFood)
            foodCount--;
        else if (badTraits == BadTraits.foodMouth)
            doubleNextFood = true;


        pottyCount--;
        sleepCount--;

    }

    enum Gender {
        MALE, FEMALE
    }

    enum GoodTraits {
        hardWorker, slowMetabolism, ironLungs, fastRunner, lucky, fastHealer
    }

    enum BadTraits {
        foodMouth, sloth, mouthBreather, slowRunner, unlucky
    }


}
