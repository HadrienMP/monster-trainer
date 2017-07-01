package fr.tarcaye.monstertrainer.domain;

import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;

@ToString
public class World {
    private final Borders borders;
    private Position trainerPosition;
    private Collection<Coordinate> mountains = new ArrayList<>();

    public World(int width, int height) {
        this.borders = new Borders(width-1, height-1);
    }

    public void placeTrainerAt(Position position) {
        this.trainerPosition = position;
    }

    public void placeMountainAt(Coordinate coordinate) {
        mountains.add(coordinate);
    }

    public Position whereIsTrainer() {
        return trainerPosition;
    }

    public void moveTrainer(Move... moves) {
        for (Move move : moves) {
            Position wantedPosition = trainerPosition.apply(move);
            if (isLegal(wantedPosition.getCoordinate())) {
                trainerPosition = wantedPosition;
            }
        }
    }

    private boolean isLegal(Coordinate wantedCoordinate) {
        return borders.isInside(wantedCoordinate) && !mountains.contains(wantedCoordinate);
    }
}
