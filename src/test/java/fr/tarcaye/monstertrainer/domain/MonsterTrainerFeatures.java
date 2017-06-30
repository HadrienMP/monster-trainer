package fr.tarcaye.monstertrainer.domain;

import org.junit.Test;

import static fr.tarcaye.monstertrainer.domain.Direction.*;
import static fr.tarcaye.monstertrainer.domain.Direction.NORTH;
import static fr.tarcaye.monstertrainer.domain.Move.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MonsterTrainerFeatures {

    @Test
    public void the_world_knows_where_the_trainer_is() throws Exception {
        World world = new World(5, 6);
        Trainer sacha = new Trainer("sacha");
        Position start = new Position(new Coordinate(0, 0), NORTH);
        world.place(sacha, start);

        Position end = world.whereIs(sacha);

        assertThat(end).isEqualTo(start);
    }

    @Test
    public void a_trainer_can_move() throws Exception {
        World world = new World(5, 6);
        Position start = new Position(new Coordinate(0, 0), EAST);
        Trainer sacha = new Trainer("sacha");
        world.place(sacha, start);

        /*
         * +------------+
         * | > > v      |
         * |   v < E    |
         * |   > > ^    |
         * |            |
         * |            |
         * =------------+
         */
        world.move(sacha, FORWARD, FORWARD,
                   RIGHT, FORWARD,
                   RIGHT, FORWARD,
                   LEFT, FORWARD,
                   LEFT, FORWARD, FORWARD,
                   LEFT, FORWARD);

        Position end = world.whereIs(sacha);
        assertThat(end).isEqualTo(new Position(new Coordinate(3, 1), NORTH));
    }

    @Test
    public void a_trainer_cannot_go_oustide_the_borders_of_the_world() throws Exception {
        World world = new World(1, 1);
        Coordinate start = new Coordinate(0, 0);
        Trainer sacha = new Trainer("sacha");
        world.place(sacha, new Position(start, NORTH));

        /*
         * Path the trainer would travel if it could go outside the borders
         *
         *  v   <
         *    +---+
         *  v | ^ |
         *    +---+
         *  >   >   >  E
         *
         */
        world.move(sacha, FORWARD,
                   LEFT, FORWARD,
                   LEFT, FORWARD, FORWARD,
                   LEFT, FORWARD, FORWARD, FORWARD);

        Position endPosition = world.whereIs(sacha);
        assertThat(endPosition).isEqualTo(new Position(start, EAST));
    }
}
