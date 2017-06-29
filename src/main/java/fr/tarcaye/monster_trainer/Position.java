package fr.tarcaye.monster_trainer;

import lombok.EqualsAndHashCode;
import lombok.ToString;

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
        return new Position(coordinate.apply(move, direction), direction.apply(move));
    }
}
