package fr.tarcaye.monstertrainer.domain;

import org.junit.Test;

import static fr.tarcaye.monstertrainer.domain.Direction.EAST;
import static fr.tarcaye.monstertrainer.domain.Direction.NORTH;
import static fr.tarcaye.monstertrainer.domain.Move.*;
import static fr.tarcaye.monstertrainer.domain.Position.position;
import static org.assertj.core.api.Assertions.assertThat;

public class MonsterTrainerFeatures {

    @Test
    public void the_world_knows_where_the_trainer_is() throws Exception {
        World world = new World(5, 6);
        Position start = givenTrainerIn(world);
        trainerWillBeAt(start, world);
    }

    @Test
    public void a_trainer_can_move() throws Exception {
        World world = new World(5, 6);
        givenTrainerIn(world);

        world.moveTrainer(anItinerary());

        trainerWillBeAt(position(3, 1, NORTH), world);
    }

    @Test
    public void a_trainer_cannot_go_oustide_the_borders_of_the_world() throws Exception {
        World world = new World(1, 1);
        Coordinate start = givenTrainerIn(world).getCoordinate();

        world.moveTrainer(anItinerary());

        trainerWillBeAt(new Position(start, EAST), world);
    }

    @Test
    public void a_trainer_cannot_go_on_a_mountain() throws Exception {
        // GIVEN
        World world = new World(5, 6);
        givenTrainerIn(world);

        world.placeMountainsAt(new Coordinate(2, 0),
                new Coordinate(2, 1),
                new Coordinate(0, 3),
                new Coordinate(3, 1));

        // WHEN

        /*
         *
         * +------------+
         * | > v X      |
         * | v < X X    |
         * | > > > E    |
         * | X          |
         * |            |
         * =------------+
         */
        world.moveTrainer(anItinerary());

        // THEN
        trainerWillBeAt(position(3, 2, NORTH), world);
    }

    /*
        >>v
         v< E
         v  ^
         >>>^
     */
    private Move[] anItinerary() {
        return new Move[]{FORWARD, FORWARD,
                RIGHT, FORWARD,
                RIGHT, FORWARD,
                LEFT, FORWARD, FORWARD,
                LEFT, FORWARD, FORWARD, FORWARD,
                LEFT, FORWARD, FORWARD};
    }

    private Position givenTrainerIn(World world) {
        Position start = position(0, 0, EAST);
        world.placeTrainerAt(start);
        return start;
    }

    private void trainerWillBeAt(Position start, World world) {
        Position end = world.whereIsTrainer();
        assertThat(end).isEqualTo(start);
    }

}
