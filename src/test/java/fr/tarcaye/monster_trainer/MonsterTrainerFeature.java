package fr.tarcaye.monster_trainer;

import org.junit.Test;

import static fr.tarcaye.monster_trainer.Direction.*;
import static fr.tarcaye.monster_trainer.Direction.NORTH;
import static fr.tarcaye.monster_trainer.Move.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MonsterTrainerFeature {

    @Test
    public void a_good_trainer_knows_its_place() throws Exception {
        World world = new World(5, 6);
        Position start = new Position(new Coordinate(0, 0), NORTH);
        Trainer sacha = new Trainer("sacha", world, start);

        Position end = sacha.position();

        assertThat(end).isEqualTo(start);
    }

    @Test
    public void a_trainer_can_move() throws Exception {
        World world = new World(5, 6);
        Position start = new Position(new Coordinate(0, 0), EAST);
        Trainer sacha = new Trainer("sacha", world, start);

        /*
         * +------------+
         * | > > v      |
         * |   v < E    |
         * |   > > ^    |
         * |            |
         * |            |
         * =------------+
         */
        sacha.move(FORWARD, FORWARD,
                   RIGHT, FORWARD,
                   RIGHT, FORWARD,
                   LEFT, FORWARD,
                   LEFT, FORWARD, FORWARD,
                   LEFT, FORWARD);

        Position endPosition = sacha.position();
        assertThat(endPosition).isEqualTo(new Position(new Coordinate(3, 1), NORTH));
    }

    @Test
    public void a_trainer_cannot_go_oustide_the_borders_of_the_world() throws Exception {
        World world = new World(1, 1);
        Coordinate start = new Coordinate(0, 0);
        Trainer sacha = new Trainer("sacha", world, new Position(start, NORTH));

        /*
         * Path the trainer would travel if it could go outside the borders
         *
         *  v   <
         *    +---+
         *  v | ^ |
         *    +---+
         *  >   >   >
         *
         */
        sacha.move(FORWARD,
                   LEFT, FORWARD,
                   LEFT, FORWARD, FORWARD,
                   LEFT, FORWARD, FORWARD, FORWARD);

        Position endPosition = sacha.position();
        assertThat(endPosition).isEqualTo(new Position(start, EAST));
    }
}
