package com.company;

public class Crew {

    public Person[] persons;
    int crewSize;
    public Crew(int numbCrew) {
        crewSize = numbCrew;
        persons = new Person[numbCrew];
        int[] baseSkill = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        for (int j = 0; j < numbCrew; j++) {
            persons[j] = new Person(Person.BadTraits.none, Person.GoodTraits.none, 10, 10, 1, 1, 5, baseSkill);
        }
    }
    public void update()
    {
        for (int i=0; i<crewSize; i++)
        {
            persons[i].updateCounts();
        }
    }
    public int getCrewSize()
    {
        return crewSize;
    }
    public int getCurrentRoom( int person )
    {
        return persons[person].getCurrentRoom();
    }
    public int getO2Consume( int person ){
        return persons[person].getO2Consume();
    }
    public int getCo2Release( int person ){
        return persons[person].getCo2Release();
    }

}
