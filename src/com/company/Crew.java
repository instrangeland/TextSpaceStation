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

    public void update() {

    }

}
