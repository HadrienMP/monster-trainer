package fr.tarcaye.monstertrainer.domain;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static fr.tarcaye.monstertrainer.domain.Direction.*;
import static fr.tarcaye.monstertrainer.domain.Move.FORWARD;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class PositionTest {
    @Test
    public void equality() throws Exception {
        Position first = new Position(new Coordinate(0, 0), NORTH);
        Position second = new Position(new Coordinate(0, 0), NORTH);
        assertThat(first).isEqualTo(second);
    }

    @Test
    @Parameters({
            "NORTH, LEFT, WEST",
            "NORTH, RIGHT, EAST",
            "EAST, LEFT, NORTH",
            "EAST, RIGHT, SOUTH",
            "WEST, LEFT, SOUTH",
            "WEST, RIGHT, NORTH",
            "SOUTH, LEFT, EAST",
            "SOUTH, RIGHT, WEST",
    })
    public void rotations(Direction origin, Move move, Direction arrival) throws Exception {
        Position start = new Position(new Coordinate(0, 0), origin);

        Position end = start.apply(move);

        Position expected = new Position(new Coordinate(0, 0), arrival);
        assertThat(end).isEqualTo(expected);
    }

    @Test
    @Parameters(method = "legalForwardMoves")
    public void foward(Direction direction, Coordinate start, Coordinate expected)throws Exception {
        Position end = new Position(start, direction).apply(FORWARD);

        assertThat(end).isEqualTo(new Position(expected, direction));
    }

    public List<List<Object>> legalForwardMoves() {
        return Arrays.asList(
                Arrays.asList(EAST, new Coordinate(0, 0), new Coordinate(1, 0)),
                Arrays.asList(SOUTH, new Coordinate(0, 0), new Coordinate(0, 1)),
                Arrays.asList(WEST, new Coordinate(0, 0), new Coordinate(-1, 0)),
                Arrays.asList(NORTH, new Coordinate(0, 0), new Coordinate(0, -1))
        );
    }
}