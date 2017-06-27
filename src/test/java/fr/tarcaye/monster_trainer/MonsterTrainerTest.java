package fr.tarcaye.monster_trainer;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MonsterTrainerTest {

    @Test
    public void a_trainer_that_does_not_move_stays_at_the_same_place() throws Exception {
        World world = new World(5, 6);
        Trainer sacha = new Trainer("sacha");
        Coordinates startPosition = new Coordinates(0, 0);
        world.place(sacha, startPosition);
        Coordinates endPosition = world.whereIs(sacha);
        assertThat(endPosition).isEqualTo(startPosition);
    }
}
