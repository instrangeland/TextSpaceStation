package com.company;

public class dynamlinks {
    int[] linkStart = new int[15];
    int[] linkEnd = new int[15];
    int nextEntry = 0;
    boolean[] nodeAvailable = new boolean[15];
    public void importLinks(String links)
    {
        String[] parts = links.split(",");
        if (parts.length%2==0) {
            for (int i = 0; i < parts.length/2; i++) {
                try {
                    makelink(Integer.parseInt(parts[2 * i]), Integer.parseInt(parts[2 * i + 1]));
                }
                catch (NumberFormatException ex)
                {
                    throw new IllegalArgumentException("Error: non integer character at index "+Integer.toString(i));
                }
            }
        }
        else
        {
            throw new IllegalArgumentException("Must have an even number csv's!!");
        }
    }

    public void makelink(int linkStart, int linkEnd) {
        this.linkStart[nextEntry] = linkStart;
        this.linkEnd[nextEntry] = linkEnd;
        nextEntry++;
        if (nextEntry + 1 > this.linkStart.length) {
            int[] linkStartNew = new int[nextEntry + 5];
            int[] linkEndNew = new int[nextEntry + 5];
            System.arraycopy(this.linkStart, 0, linkStartNew, 0, nextEntry);
            System.arraycopy(this.linkEnd, 0, linkEndNew, 0, nextEntry);
            this.linkEnd = linkEndNew;
            this.linkStart = linkStartNew;
        }

    }

    public int[] getLinks(int node) {
        int[] links= new int[20];
        int nextWrite = 0;
        for (int i = 0; i < linkEnd.length; i++) {
            if (linkStart[i] == node || linkEnd[i] == node) {
                if (linkStart[i] == node) {
                    links[nextWrite] = linkEnd[i];
                } else {
                    links[nextWrite] = linkStart[i];
                }
                nextWrite++;
                if (nextWrite == links.length) {
                    int[] linkStartNew = new int[nextEntry + 5];
                    System.arraycopy(links, 0, linkStartNew, 0, nextWrite);
                    links = linkStartNew;
                }
            }
        }
        System.out.println();
        System.out.println(nextWrite);
        if (nextWrite + 1 < links.length) {
            int[] linkStartNew = new int[nextEntry];
            System.arraycopy(links, 0, linkStartNew, 0, nextWrite);
            links = linkStartNew;
        }
        return links;
    }

    public boolean hasConnect(int nodeStart, int nodeEnd) {
        for (int i = 0; i < nextEntry; i++) {
            if (((linkStart[i] == nodeStart) && (linkEnd[i] == nodeEnd)) || ((linkEnd[i] == nodeStart) && (linkStart[i] == nodeEnd)))
                return true;
        }
        return false;
    }

}
