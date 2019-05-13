package com.company;

public class Station {
    Loyalty loyalty;

    public void setLoyalty(Loyalty loyalty) {
        this.loyalty = loyalty;
    }
    int health;
    int maxhealth;
    int shield;
    int maxshield;
    dynamlinks roomlinks = new dynamlinks();
    String roomListToLink = "1,2,1,6,1,3,2,4,2,5,2,6,3,6,3,7,3,8,4,5,4,9,5,9,6,9,6,10,7,8,7,10,8,10,9,11,10,11,11,12";
    int[] startingCO2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[] gasCapacity = {1200, 1500, 1500, 800, 1200, 900, 800, 800, 1500, 1500, 900, 800};
    int[] startingO2 = {400, 500, 500, 400, 600, 250, 200, 200, 500, 500, 250, 200};

    String[] roomNames = {"Defense", "Hydroponics", "Hold", "Dock", "Quarters", "Core", "Trade", "Dock",
            "Crew", "Life Support","Engineering","Mining"};
    room[] roomObjList = new room[12];
    Crew crew = new Crew(10);
    public Station() {
        this.health = 1000;
        this.maxhealth = 2000;
        this.shield = 500;
        this.maxshield = 1000;
        for (int j = 0; j < roomObjList.length; j++) {
            roomObjList[j] = new room(roomNames[j], false, false, startingO2[j], startingCO2[j], gasCapacity[j]);
        }//defines our rooms for us, (the array 'roomObjList')
        roomlinks.importLinks(roomListToLink);

    }


    enum Loyalty {
        Human, Alien1, Alien2, Pirate
    }
    public void takeDamage(int amount) {
        if (shield < amount) {
            health = health - (amount - shield);
            shield = 0;
        } else {
            shield = shield - amount;
        }
    }
    public void update()
    {
        crew.update();
    }
    public void outInfo()
    {

    }
}

class room {
    String name;
    boolean isFire;
    boolean isToxic;
    int amountO2;
    int amountCO2;
    int gasCapacity;

    public room(String name, boolean isFire, boolean isToxic, int amountO2, int amountCO2, int gasCapacity) {
        this.name = name;
        this.isFire = isFire;
        this.isToxic = isToxic;
        this.amountO2 = amountO2;
        this.amountCO2 = amountCO2;
        this.gasCapacity = gasCapacity;
    }

    public void addCO2(int amountCO2) {
        this.amountCO2 = this.amountCO2 + amountCO2;
    }

    public void setO2(int amountO2) {
        this.amountO2 = amountO2;
    }

    public void addO2(int amountO2) {
        this.amountO2 = this.amountO2 + amountO2;
    }

    public void setCO2(int amountCO2) {
        this.amountCO2 = amountCO2;
    }
    public void setFire(boolean isFire) {
        this.isFire = isFire;
    }

    public void setToxic(boolean isToxic) {
        this.isToxic = isToxic;
    }

    public boolean canConnect(int room1, int room2) {

        return false;
    }
}

