package fr.tarcaye.monstertrainer.domain.world;

import fr.tarcaye.monstertrainer.domain.position.Coordinate;
import lombok.ToString;

import java.util.Collection;

@ToString
public class World {

    private final Borders borders;
    private final Collection<Coordinate> mountains;
    private final Collection<Coordinate> monsters;

    private Coordinate previousTrainerPlace;
    private Coordinate currentTrainerPlace;

    World(Borders borders, Collection<Coordinate> mountains, Collection<Coordinate> monsters) {
        this.borders = borders;
        this.mountains = mountains;
        this.monsters = monsters;
    }

    public static WorldBuilder builder() {
        return new WorldBuilder();
    }

    public void moveTrainerTo(Coordinate coordinate) throws IllegalCoordinateException {
        // todo test when trainer is placed outside the borders
        if (isLegal(coordinate)) {
            previousTrainerPlace = currentTrainerPlace;
            currentTrainerPlace = coordinate;
        } else {
            throw new IllegalCoordinateException();
        }
    }

    private boolean isLegal(Coordinate coordinate) {
        return borders.isInside(coordinate) && !mountains.contains(coordinate);
    }

    public boolean hasPickedUpMonster(Coordinate coordinate) {
        return previousTrainerPlace != currentTrainerPlace && monsters.remove(coordinate);
    }
}
