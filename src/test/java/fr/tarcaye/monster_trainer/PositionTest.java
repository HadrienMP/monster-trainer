package fr.tarcaye.monster_trainer;

import org.junit.Ignore;
import org.junit.Test;

import static fr.tarcaye.monster_trainer.Move.RIGHT;
import static org.assertj.core.api.Assertions.assertThat;

public class PositionTest {
    @Test
    public void equality() throws Exception {
        Position first = new Position(0, 0, Direction.NORTH);
        Position second = new Position(0, 0, Direction.NORTH);
        assertThat(first).isEqualTo(second);
    }

    @Test
    @Ignore
    public void north_right() throws Exception {
        Position start = new Position(0, 0, Direction.NORTH);

        Position end = start.apply(RIGHT);

        Position expected = new Position(0, 0, Direction.EAST);
        assertThat(end).isEqualTo(expected);
    }
}