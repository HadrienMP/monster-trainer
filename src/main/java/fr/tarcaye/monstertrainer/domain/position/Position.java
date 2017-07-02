package fr.tarcaye.monstertrainer.domain.position;

import fr.tarcaye.monstertrainer.domain.Move;
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

    public static Position position(int x, int y, Direction direction) {
        return new Position(new Coordinate(x, y), direction);
    }

    public Position apply(Move move) {
        return new Position(applyToCoordinate(move), direction.apply(move));
    }

    private Coordinate applyToCoordinate(Move move) {
        return move == Move.FORWARD ? coordinate.oneStep(direction) : coordinate;
    }
}
