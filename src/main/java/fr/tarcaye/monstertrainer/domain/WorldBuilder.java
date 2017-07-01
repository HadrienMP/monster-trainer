package fr.tarcaye.monstertrainer.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import static java.util.Arrays.asList;

public class WorldBuilder {

    private Borders borders;
    private Collection<Coordinate> mountains = new ArrayList<>();
    private Collection<Coordinate> monsters = new ArrayList<>();

    WorldBuilder withSize(int width, int height) {
        borders = new Borders(width - 1, height - 1);
        return this;
    }

    WorldBuilder withMountainsAt(Coordinate... coordinates) {
        mountains.addAll(asList(coordinates));
        return this;
    }

    WorldBuilder withMonstersAt(Coordinate... coordinates) {
        monsters.addAll(asList(coordinates));
        return this;
    }

    World build() {
        Objects.requireNonNull(borders);
        return new World(borders, mountains, monsters);
    }
}
