package fr.tarcaye.monstertrainer.domain;

import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.asList;

@ToString
class World {
    private final Borders borders;
    private Position trainer;
    private Collection<Coordinate> mountains = new ArrayList<>();

    World(int width, int height) {
        this.borders = new Borders(width-1, height-1);
    }

    void placeTrainerAt(Position position) {
        this.trainer = position;
    }

    void placeMountainsAt(Coordinate... coordinates) {
        mountains.addAll(asList(coordinates));
    }

    Position whereIsTrainer() {
        return trainer;
    }

    void moveTrainer(Move... moves) {
        for (Move move : moves) {
            Position wantedPosition = trainer.apply(move);
            if (isLegal(wantedPosition.getCoordinate())) {
                trainer = wantedPosition;
            }
        }
    }

    private boolean isLegal(Coordinate wantedCoordinate) {
        return borders.isInside(wantedCoordinate) && !mountains.contains(wantedCoordinate);
    }

    public void placeMonstersAt(Coordinate... coordinates) {

    }

    public int monstersPickedByTrainer() {
        return 0;
    }
}
