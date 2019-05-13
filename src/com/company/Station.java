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

    String[] roomNames = {"Defense", "Hydroponics", "Hold", "Dock", "Quarters", "Core", "Trade", "Dock",
            "Crew", "Life Support","Engineering","Mining"};
    room[] roomObjList = new room[12];
    Crew[] crew = new Crew[3];
    public Station() {
        this.health = 1000;
        this.maxhealth = 2000;
        this.shield = 500;
        this.maxshield = 1000;
        for (int j = 0; j < roomObjList.length; j++) {
            roomObjList[j] = new room(roomNames[j], false, false);
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

    }
    public void outInfo()
    {

    }
}

class room {
    String name;
    boolean isFire;
    boolean isToxic;

    public room(String name, boolean isFire, boolean isToxic) {
        this.name = name;
        this.isFire = isFire;
        this.isToxic = isToxic;
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

