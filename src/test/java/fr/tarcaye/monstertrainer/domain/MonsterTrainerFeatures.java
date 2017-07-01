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
        World world = aWorld();
        Position start = givenTrainerIn(world);
        trainerWillBeAt(start, world);
    }

    @Test
    public void a_trainer_can_move() throws Exception {
        World world = aWorld();
        givenTrainerIn(world);

        world.moveTrainer(anItinerary());

        trainerWillBeAt(position(4, 1, NORTH), world);
    }

    @Test
    public void a_trainer_cannot_go_oustide_the_borders_of_the_world() throws Exception {
        World world = new World(1, 1);
        Coordinate start = givenTrainerIn(world).getCoordinate();

        world.moveTrainer(anItinerary());

        trainerWillBeAt(new Position(start, NORTH), world);
    }

    @Test
    public void a_trainer_cannot_go_on_a_mountain() throws Exception {
        // GIVEN
        World world = aWorld();
        givenTrainerIn(world);

        world.placeMountainsAt(new Coordinate(2, 0),
                new Coordinate(2, 1),
                new Coordinate(0, 3),
                new Coordinate(3, 1));

        // WHEN
        world.moveTrainer(anItinerary());

        // THEN
        trainerWillBeAt(position(3, 2, NORTH), world);
    }

    @Test
    public void a_trainer_picks_up_the_monsters_he_meets() throws Exception {
        // GIVEN
        World world = aWorld();
        givenTrainerIn(world);

        world.placeMonstersAt(
                // Picked
                new Coordinate(0, 0),
                new Coordinate(1, 1),
                new Coordinate(1, 1),
                new Coordinate(1, 1),
                new Coordinate(3, 3),

                // Not picked
                new Coordinate(4, 0),
                new Coordinate(2, 2)
        );

        // WHEN
        world.moveTrainer(anItinerary());

        // THEN
        assertThat(world.monstersPickedByTrainer()).isEqualTo(5);
    }


    private static World aWorld() {
        return new World(5, 6);
    }

    /*
        01234
       +-----
      0|>>v
      1| v< E
      2| v  ^
      3| >>>^

     */
    private static Move[] anItinerary() {
        return new Move[]{FORWARD, FORWARD,
                RIGHT, FORWARD,
                RIGHT, FORWARD,
                LEFT, FORWARD, FORWARD,
                LEFT, FORWARD, FORWARD, FORWARD,
                LEFT, FORWARD, FORWARD};
    }

    private static Position givenTrainerIn(World world) {
        Position start = position(0, 0, EAST);
        world.placeTrainerAt(start);
        return start;
    }

    private static void trainerWillBeAt(Position start, World world) {
        Position end = world.whereIsTrainer();
        assertThat(end).isEqualTo(start);
    }

}
