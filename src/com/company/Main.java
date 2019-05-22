package com.company;

public class Main {
    public static boolean contains(final int[] array, final int key) {
        for (final int i : array) {
            if (i == key) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args)
        throws InterruptedException {
        Station station = new Station(); //actually this won't only be the only instance of station... any time another ship is spawned, another station class is spawned. Not sure how i'll deal with that yet.
        while (true)
        {
            station.update();
            station.outInfo();
            Thread.sleep(4000);
        }
    }
}
