package fr.tarcaye.monstertrainer.domain.position;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Coordinate oneStep(Direction direction) {
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
