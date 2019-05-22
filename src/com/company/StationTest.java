package com.company;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertArrayEquals;

@RunWith(Arquillian.class)
public class StationTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Station.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    Station station = new Station();
    @org.junit.Test
    public void getRoomAndDistance() {
        int[] returnz = {1,1};
        assertArrayEquals(station.getRoomAndDistance(0,1),returnz);
    }
}
