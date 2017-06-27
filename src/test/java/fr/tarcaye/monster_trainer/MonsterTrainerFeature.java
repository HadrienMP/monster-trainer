package fr.tarcaye.monster_trainer;

import org.junit.Test;

import static fr.tarcaye.monster_trainer.Move.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MonsterTrainerFeature {

    @Test
    public void non_moving_trainer() throws Exception {
        World world = new World(5, 6);
        Trainer sacha = new Trainer("sacha");
        Coordinates startPosition = new Coordinates(0, 0, Direction.NORTH);
        world.place(sacha, startPosition);

        Coordinates endPosition = world.whereIs(sacha);
        assertThat(endPosition).isEqualTo(startPosition);
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
        Trainer sacha = new Trainer("sacha");
        Coordinates startPosition = new Coordinates(0, 0, Direction.NORTH);
        world.place(sacha, startPosition);

        world.move(sacha, FORWARD, FORWARD, RIGHT, FORWARD, RIGHT, FORWARD, LEFT, FORWARD);

        Coordinates endPosition = world.whereIs(sacha);
        assertThat(endPosition).isEqualTo(new Coordinates(2, 1, Direction.SOUTH));
    }
}
