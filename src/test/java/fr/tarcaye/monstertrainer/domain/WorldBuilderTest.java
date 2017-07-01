package fr.tarcaye.monstertrainer.domain;

import org.junit.Test;

public class WorldBuilderTest {
    @Test(expected = NullPointerException.class)
    public void cannot_build_a_world_without_borders() throws Exception {
        new WorldBuilder().build();
    }
}