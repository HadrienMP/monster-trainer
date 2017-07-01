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
        return move == Move.FORWARD ? coordinate.oneStep(direction) : coordinate;
    }
}
