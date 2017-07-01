package fr.tarcaye.monstertrainer.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
class Position {

    private final Coordinate coordinate;
    private final Direction direction;

    Position(Coordinate coordinate, Direction direction) {
        this.coordinate = coordinate;
        this.direction = direction;
    }

    static Position position(int x, int y, Direction direction) {
        return new Position(new Coordinate(x, y), direction);
    }

    Position apply(Move move) {
        return new Position(applyToCoordinate(move), direction.apply(move));
    }

    private Coordinate applyToCoordinate(Move move) {
        if (move == Move.FORWARD) {
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
                    return new Coordinate(x, y - 1);
            }
        }

        return coordinate;
    }
}
