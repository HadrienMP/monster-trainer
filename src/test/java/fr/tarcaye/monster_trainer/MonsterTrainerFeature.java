package fr.tarcaye.monster_trainer;

import org.junit.Test;

import static fr.tarcaye.monster_trainer.Move.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MonsterTrainerFeature {

    @Test
    public void non_moving_trainer() throws Exception {
        World world = new World(5, 6);
        Position start = new Position(0, 0, Direction.NORTH);
        Trainer sacha = new Trainer("sacha", world, start);

        Position end = sacha.position();

        assertThat(end).isEqualTo(start);
    }

    /**
     * <pre>
     * F  F  RF
     *    LF RF
     *    E
     * </pre>
     * @throws Exception
     */
    @Test
    public void a_moving_trainer() throws Exception {
        World world = new World(5, 6);
        Position start = new Position(0, 0, Direction.NORTH);
        Trainer sacha = new Trainer("sacha", world, start);

        sacha.move(FORWARD, FORWARD, RIGHT, FORWARD, RIGHT, FORWARD, LEFT, FORWARD);

        Position endPosition = sacha.position();
        assertThat(endPosition).isEqualTo(new Position(2, 1, Direction.SOUTH));
    }
}
