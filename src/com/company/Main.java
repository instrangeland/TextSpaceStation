package com.company;

public class Main {

    public static void main(String[] args) {
        Station station = new Station(); //this'll be the only instance of station...
        while (true)
        {
            station.update();
            station.outInfo();
        }
    }
}
