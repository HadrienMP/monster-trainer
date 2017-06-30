package fr.tarcaye.monster_trainer.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Position {

    private final Coordinate coordinate;
    private final Direction direction;

    public Position(Coordinate coordinate, Direction direction) {
        this.coordinate = coordinate;
        this.direction = direction;
    }

    public Position apply(Move move) {
        if (move == Move.FORWARD) {
            return new Position(forward(), direction);
        } else {
            return new Position(coordinate, direction.apply(move));
        }
    }

    private Coordinate forward() {
        int x = coordinate.getX();
        int y = coordinate.getY();
        switch (direction) {
            case EAST:
                return new Coordinate(x + 1, y);
            case SOUTH:
                return new Coordinate(x, y + 1);
            case WEST:
                return new Coordinate(x - 1, y);
            case NORTH:
            default:
                return new Coordinate(x, y - 1);
        }
    }
}
