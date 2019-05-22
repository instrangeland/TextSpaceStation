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
    public dynamlinks roomlinks = new dynamlinks();
    String roomListToLink = "0,1,0,5,0,2,1,3,1,4,1,5,2,5,2,6,2,7,3,4,3,8,4,8,5,8,5,9,6,7,6,9,7,9,8,10,9,10,10,11";
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
    public int[] getRoomAndDistance(int roomOne, int roomTwo)
    {
        int[] checkedRooms = new int[15];
        int roomsChecked=-1;
        int[] nodes = new int[15];
        int nodesSize = 1;
        int newNodesNextWrite=0;
        int[] returnz = {-1,-1};
        int[] newNodes = new int[15];
        int[] links;
        int[] firstNode = new int[15];
        boolean firstRun = true;
        nodes[0]=roomOne;
        int[] newFirstNodes = new int[15];
        int timesRun=0;
        while (nodesSize>0)
        {
            timesRun++;
            for (int j=0; j<newNodesNextWrite; j++) {
                links=roomlinks.getLinks(nodes[j]);//get links for current node
                for (int k=0; k<links.length; k++)//cycle through links of node
                {
                    if (!Main.contains(checkedRooms,links[k]+1))//have we already checked this??
                    {
                        //TODO: It should also check if the room is dangerous (e.g. fire or depressurized or something)
                        roomsChecked++;
                        checkedRooms[roomsChecked]=links[k]+1;
                        newNodes[newNodesNextWrite]=links[k];

                        if (firstRun=true)
                        {
                            newFirstNodes[newNodesNextWrite]=links[k];
                        }
                        else
                        {
                            newFirstNodes[newNodesNextWrite]=firstNode[j];
                        }
                        newNodesNextWrite++;
                    }
                }
            }
            firstRun=false;
            firstNode=newFirstNodes;
            nodesSize=newNodesNextWrite-1;
            newNodesNextWrite=0;
            nodes=newNodes;
            for (int i=0;i<15; i++)
            {
                newFirstNodes[i]=0;
                newNodes[i]=0;
            }
            for (int j=0; j<nodesSize; j++)
            {
                if (nodes[j]==roomTwo)
                {
                    returnz[0]=firstNode[j];
                    returnz[1]=timesRun;
                    return returnz;
                }
            }
        }
        return returnz;
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

        int[] localRoomLinks;
        int crewSize;

        int toSet;
        crewSize= crew.getCrewSize();
        for (int i=0; i < crewSize; i++) //for loop handles crew breathing
        {
            roomObjList[crew.getCurrentRoom(i)].addCO2(crew.getCo2Release(i));
            roomObjList[crew.getCurrentRoom(i)].addO2(-crew.getO2Consume(i));
            System.out.print("co2:");
            System.out.println(roomObjList[crew.getCurrentRoom(i)].amountCO2);
            System.out.print("o2:");
            System.out.println(roomObjList[crew.getCurrentRoom(i)].amountO2);
        }

        for (int j = 0; j < roomObjList.length; j++) {
            if ( roomObjList[j].isFire)
            {
                if (roomObjList[j].getO2Ratio()<.6)
                {
                    roomObjList[j].setFire(false);
                }
                roomObjList[j].addO2((int)(-.2*(float)roomObjList[j].getAmountO2()));
            }

        }





        for (int j = 0; j < roomObjList.length; j++) { //handles gas flow between rooms: sorta complex
            localRoomLinks = roomlinks.getLinks(j);

            assert (localRoomLinks.length > 0);

            for (int i = 0; i < localRoomLinks.length; i++) {
                assert(j<12);


                if (roomObjList[j].getCO2Ratio() > roomObjList[localRoomLinks[i]].getCO2Ratio()) {
                    toSet = (int) (.5 * (roomObjList[j].getCO2Ratio() - roomObjList[localRoomLinks[i]].getCO2Ratio()) * roomObjList[localRoomLinks[i]].getGasCapacity());
                    roomObjList[j].addCO2(-toSet);
                    roomObjList[localRoomLinks[i]].addCO2(toSet);
                }
                if (roomObjList[j].getO2Ratio() > roomObjList[localRoomLinks[i]].getO2Ratio()) {
                    toSet = (int) (.5 * (roomObjList[j].getO2Ratio() - roomObjList[localRoomLinks[i]].getO2Ratio()) * roomObjList[localRoomLinks[i]].getGasCapacity());
                    roomObjList[j].addO2(-toSet);
                    roomObjList[localRoomLinks[i]].addO2(toSet);
                }
            }
        }

    }
    public void outInfo()
    {

    }
}

class room {
    String name;



    int HP;
    int maxHP;


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
        HP=gasCapacity;
        maxHP=gasCapacity;
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
    public boolean isFire() {
        return isFire;
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
    public int getAmountCO2() {
        return amountCO2;
    }
    public int getAmountO2() {
        return amountO2;
    }
    public int getGasCapacity() {
        return gasCapacity;
    }
    public float getCO2Ratio() {
        return (float) amountCO2 / (float) gasCapacity;
    }
    public float getO2Ratio() {
        return (float) amountO2 / (float) gasCapacity;
    }
    public int getHP() {
        return HP;
    }
    public void setHP(int HP) {
        this.HP = HP;
    }
    public int getMaxHP() {
        return maxHP;
    }
    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }
}

