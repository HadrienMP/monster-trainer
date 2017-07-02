package fr.tarcaye.monstertrainer.domain;

import fr.tarcaye.monstertrainer.domain.position.Coordinate;
import fr.tarcaye.monstertrainer.domain.position.Position;
import fr.tarcaye.monstertrainer.domain.world.IllegalCoordinateException;
import fr.tarcaye.monstertrainer.domain.world.World;
import fr.tarcaye.monstertrainer.domain.world.WorldBuilder;
import org.junit.Test;

import static fr.tarcaye.monstertrainer.domain.Move.*;
import static fr.tarcaye.monstertrainer.domain.position.Direction.*;
import static fr.tarcaye.monstertrainer.domain.position.Position.position;
import static org.assertj.core.api.Assertions.assertThat;

public class MonsterTrainerFeatures {

    @Test
    public void a_trainer_knows_where_he_is() throws Exception {
        Position start = position(0, 0, EAST);

        Trainer trainer = new Trainer(aWorld().build(), start);

        assertThat(trainer.locate()).isEqualTo(start);
    }

    @Test
    public void a_trainer_can_move() throws Exception {
        Trainer trainer = aTrainerIn(aWorld());

        trainer.move(anItinerary());

        assertThat(trainer.locate()).isEqualTo(position(4, 1, NORTH));
    }

    @Test
    public void a_trainer_cannot_go_oustide_the_borders_of_the_world() throws Exception {
        Trainer trainer = aTrainerIn(aWorld().withSize(1, 1));
        Position start = trainer.locate();

        trainer.move(anItinerary());

        assertThat(trainer.locate()).isEqualTo(new Position(start.getCoordinate(), NORTH));
    }

    @Test
    public void a_trainer_cannot_go_on_a_mountain() throws Exception {
        // GIVEN
        WorldBuilder world = aWorld()
                .withMountainsAt(
                        new Coordinate(2, 0),
                        new Coordinate(2, 1),
                        new Coordinate(0, 3),
                        new Coordinate(3, 1)
                );
        Trainer trainer = aTrainerIn(world);

        // WHEN
        trainer.move(anItinerary());

        // THEN
        assertThat(trainer.locate()).isEqualTo(position(3, 2, NORTH));
    }

    @Test
    public void a_trainer_picks_up_the_monsters_he_encounters() throws Exception {
        // GIVEN
        WorldBuilder world = aWorld()
                .withMonstersAt(
                        // Picked
                        new Coordinate(0, 0),
                        new Coordinate(2, 0),
                        new Coordinate(1, 1),
                        new Coordinate(3, 3),

                        // Not picked
                        new Coordinate(1, 1),
                        new Coordinate(1, 1),
                        new Coordinate(2, 0),
                        new Coordinate(4, 0),
                        new Coordinate(2, 2)
                );

        Trainer trainer = aTrainerIn(world);

        // WHEN
        trainer.move(anItinerary());

        // THEN
        assertThat(trainer.countMonsters()).isEqualTo(4);
    }

    private static WorldBuilder aWorld() {
        return World.builder().withSize(5, 6);
    }


    private Trainer aTrainerIn(WorldBuilder world) throws IllegalCoordinateException {
        return new Trainer(world.build(), position(0, 0, EAST));
    }

    /**
     * 01234
     * +-----
     * 0|>>v
     * 1| v< E
     * 2| v  ^
     * 3| >>>^
     */
    private static Move[] anItinerary() {
        return new Move[]{
                FORWARD, FORWARD,
                RIGHT, FORWARD,
                RIGHT, FORWARD,
                LEFT, FORWARD, FORWARD,
                LEFT, FORWARD, FORWARD, FORWARD,
                LEFT, FORWARD, FORWARD
        };
    }

}
