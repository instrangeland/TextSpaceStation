package com.company;

public class Crew {
    enum Gender {
        MALE,FEMALE
    }
    enum GoodTraits {
        hardWorker, slowMetabolism, ironLungs, fastRunner, lucky
    }
    enum BadTraits {
        foodMouth, sloth, mouthBreather, slowRunner, unlucky
    }
    int HP; int maxHP; int o2Consume; int co2Release; BadTraits badTraits; GoodTraits goodTraits; Gender gender;
    int currentRoom; int[] jobStack = new int[30]; byte[] skillArray = new byte[10]; int foodCount; int pottyCount; int sleepCount; int jobNextWrite;
    String name;
    public void updateCounts()
    {

    }


}
