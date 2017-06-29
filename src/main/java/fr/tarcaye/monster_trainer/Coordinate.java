package fr.tarcaye.monster_trainer;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate apply(Move move, Direction direction) {
        switch (move) {
            case FORWARD:
                return forward(direction);
            default:
                return this;
        }
    }

    private Coordinate forward(Direction direction) {
        switch (direction) {
            case EAST:
                return new Coordinate(x + 1, y);
            case SOUTH:
                return new Coordinate(x, y + 1);
            case WEST:
                return new Coordinate(x - 1, y);
            case NORTH:
                return new Coordinate(x, y - 1);
            default:
                return this;
        }
    }
}
