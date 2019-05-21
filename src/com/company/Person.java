package com.company;



public class Person {
    int HP;
    int maxHP;
    int o2Consume;
    int co2Release;
    BadTraits badTraits;
    GoodTraits goodTraits;
    Gender gender;
    Task[] task = new Task[30];
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


    public Person(BadTraits badTraits, GoodTraits goodTraits, int HP, int maxHP, int o2Consume, int co2Release, int currentRoom, int[] skillLvl) {
        this.badTraits = badTraits;
        this.goodTraits = goodTraits;
        this.HP = HP;
        this.maxHP = maxHP;
        this.o2Consume = o2Consume;
        this.co2Release = co2Release;
        this.currentRoom = currentRoom;
        this.skillLvl = skillLvl;
        jobNextWrite=1;
        task[0].setupAll(Task.TaskTypes.idle, -1, -1, 0);

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
        doWork();
    }
    private void doWork()
    {
        if (getTopTaskType()!=Task.TaskTypes.idle)
        {
            if ((getTopTaskRoom()==currentRoom)||(getTopTaskRoom()==-1))
            {
                task[jobNextWrite-1].doWork(skillLvl[getTopTaskSkillUsed()]);
                if (getTopTaskWorkLeft()<1)
                {
                    removeStackTop();
                }
            }
            else
            {

            }
        }
    }
    private void removeStackTop()
    {
        jobNextWrite=jobNextWrite-1;
    }
    private int getTopTaskRoom() {
        return task[jobNextWrite-1].getRoomLocation();
    }
    private int getTopTaskWorkLeft() {
        return task[jobNextWrite-1].getWorkLeft();
    }
    private Task.TaskTypes getTopTaskType() {
        return task[jobNextWrite-1].getTaskTypes();
    }
    private int getTopTaskSkillUsed() {
        return task[jobNextWrite-1].getSkillUsed();
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

