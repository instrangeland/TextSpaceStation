package com.company;

import org.jetbrains.annotations.Contract;

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

    /**
     * @param badTraits   is an enumerated type, see Person.java for names
     * @param goodTraits  is an enumerated type, see Person.java for names
     * @param HP          starting HP of person, probably will start at maxHP
     * @param maxHP       maximum hp
     * @param o2Consume   amount of o2 used per tick
     * @param co2Release  amount of co2 released per tick
     * @param currentRoom prob will start in a single room?
     * @param skillLvl    is 10 int array, maybe won't always be 10. Is amount of work done per tick
     */
    @Contract(pure = true)
    public Person(BadTraits badTraits, GoodTraits goodTraits, int HP, int maxHP, int o2Consume, int co2Release, int currentRoom, int[] skillLvl) {
        this.badTraits = badTraits;
        this.goodTraits = goodTraits;
        this.HP = HP;
        this.maxHP = maxHP;
        this.o2Consume = o2Consume;
        this.co2Release = co2Release;
        this.currentRoom = currentRoom;
        this.skillLvl = skillLvl;
    }


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

    public int getO2Consume() {
        return o2Consume;
    }
    public int getCo2Release() { return co2Release; }
    public int getCurrentRoom()
    {
        return currentRoom;
    }
    enum Gender {
        MALE, FEMALE
    }

    enum GoodTraits {
        none, hardWorker, slowMetabolism, ironLungs, fastRunner, lucky, fastHealer
    }

    enum BadTraits {
        none, foodMouth, sloth, mouthBreather, slowRunner, unlucky
    }



}
