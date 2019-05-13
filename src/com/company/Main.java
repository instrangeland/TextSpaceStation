package com.company;

public class Main {

    public static void main(String[] args) {
        Station station = new Station(); //actually this won't only be the only instance of station... any time another ship is spawned, another station class is spawned. Not sure how i'll deal with that yet.
        while (true)
        {
            station.update();
            station.outInfo();
        }
    }
}
