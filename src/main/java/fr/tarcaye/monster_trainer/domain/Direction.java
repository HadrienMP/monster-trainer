package fr.tarcaye.monster_trainer.domain;

import fr.tarcaye.monster_trainer.common.CircularList;

import java.util.Arrays;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    public static final CircularList<Direction> DIRECTIONS = new CircularList<>(Arrays.asList(values()));

    public Direction apply(Move move) {
        switch (move) {
            case LEFT:
                return DIRECTIONS.before(this);
            case RIGHT:
                return DIRECTIONS.after(this);
            default:
                return this;
        }
    }
}
