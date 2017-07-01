package fr.tarcaye.monstertrainer.domain;

import lombok.ToString;

import java.util.Collection;

@ToString
class World {

    private final Borders borders;
    private final Collection<Coordinate> mountains;
    private final Collection<Coordinate> monsters;

    private Position trainer;
    private int pickedUpMonsters;

    World(Borders borders, Collection<Coordinate> mountains, Collection<Coordinate> monsters) {
        this.borders = borders;
        this.mountains = mountains;
        this.monsters = monsters;
    }

    static WorldBuilder builder() {
        return new WorldBuilder();
    }

    void placeTrainerAt(Position position) {
        trainerTriesToPickUpAMonster(position.getCoordinate());
        this.trainer = position;
    }

    Position whereIsTrainer() {
        return trainer;
    }

    void moveTrainer(Move... moves) {
        for (Move move : moves) {
            Position position = trainer.apply(move);
            Coordinate coordinate = position.getCoordinate();
            if (isLegal(coordinate)) {
                trainerTriesToPickUpAMonster(coordinate);
                trainer = position;
            }
        }
    }

    private void trainerTriesToPickUpAMonster(Coordinate coordinate) {
        if (isTrainerArrivingAt(coordinate) && containsMonster(coordinate)) {
            pickedUpMonsters++;
            monsters.remove(coordinate);
        }
    }

    private boolean isTrainerArrivingAt(Coordinate coordinate) {
        return trainer == null || coordinate != trainer.getCoordinate();
    }

    private boolean isLegal(Coordinate wantedCoordinate) {
        return borders.isInside(wantedCoordinate) && !mountains.contains(wantedCoordinate);
    }

    private boolean containsMonster(Coordinate coordinate) {
        return monsters.contains(coordinate);
    }

    int monstersPickedByTrainer() {
        return pickedUpMonsters;
    }
}
