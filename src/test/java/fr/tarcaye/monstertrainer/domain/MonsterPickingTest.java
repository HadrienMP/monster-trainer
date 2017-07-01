package fr.tarcaye.monstertrainer.domain;

import org.junit.Test;

import static fr.tarcaye.monstertrainer.domain.Direction.EAST;
import static fr.tarcaye.monstertrainer.domain.Move.FORWARD;
import static fr.tarcaye.monstertrainer.domain.Move.LEFT;
import static fr.tarcaye.monstertrainer.domain.Position.position;
import static org.assertj.core.api.Assertions.assertThat;

public class MonsterPickingTest {

    @Test
    public void cannot_pick_any_monsters_when_none_is_on_the_map() throws Exception {
        World world = World.builder().withSize(2, 1).build();
        world.placeTrainerAt(position(0, 0, EAST));

        world.moveTrainer(FORWARD);

        assertThat(world.monstersPickedByTrainer()).isEqualTo(0);
    }

    @Test
    public void pick_the_monsters_on_the_path() throws Exception {
        World world = World.builder()
                .withSize(2, 1)
                .withMonstersAt(new Coordinate(1, 0))
                .build();
        world.placeTrainerAt(position(0, 0, EAST));

        world.moveTrainer(FORWARD);

        assertThat(world.monstersPickedByTrainer()).isEqualTo(1);
    }

    @Test
    public void cannot_pick_the_monsters_outside_the_path() throws Exception {
        World world = World.builder()
                .withSize(2, 2)
                .withMonstersAt(new Coordinate(1, 0), new Coordinate(1, 1), new Coordinate(0, 1))
                .build();
        world.placeTrainerAt(position(0, 0, EAST));

        world.moveTrainer(FORWARD);

        assertThat(world.monstersPickedByTrainer()).isEqualTo(1);
    }

    @Test
    public void cannot_pick_the_same_monster_twice() throws Exception {
        World world = World.builder()
                .withSize(2, 1)
                .withMonstersAt(new Coordinate(1, 0))
                .build();
        world.placeTrainerAt(position(0, 0, EAST));

        world.moveTrainer(FORWARD, LEFT, LEFT, FORWARD, LEFT, LEFT, FORWARD);

        assertThat(world.monstersPickedByTrainer()).isEqualTo(1);
    }

    @Test
    public void cannot_pick_a_monster_while_rotating() throws Exception {
        World world = World.builder()
                .withSize(2, 1)
                .withMonstersAt(new Coordinate(1, 0), new Coordinate(1, 0))
                .build();

        world.placeTrainerAt(position(0, 0, EAST));

        world.moveTrainer(FORWARD, LEFT, LEFT);

        assertThat(world.monstersPickedByTrainer()).isEqualTo(1);
    }

    @Test
    public void picks_the_monster_on_the_start_position() throws Exception {
        World world = World.builder()
                .withSize(1, 1)
                .withMonstersAt(new Coordinate(0, 0))
                .build();

        world.placeTrainerAt(position(0, 0, EAST));

        assertThat(world.monstersPickedByTrainer()).isEqualTo(1);
    }
}
