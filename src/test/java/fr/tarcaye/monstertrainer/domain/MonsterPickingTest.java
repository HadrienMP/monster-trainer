package fr.tarcaye.monstertrainer.domain;

import fr.tarcaye.monstertrainer.domain.position.Coordinate;
import fr.tarcaye.monstertrainer.domain.world.World;
import org.junit.Test;

import static fr.tarcaye.monstertrainer.domain.position.Direction.EAST;
import static fr.tarcaye.monstertrainer.domain.Move.FORWARD;
import static fr.tarcaye.monstertrainer.domain.Move.LEFT;
import static fr.tarcaye.monstertrainer.domain.position.Position.position;
import static org.assertj.core.api.Assertions.assertThat;

public class MonsterPickingTest {

    @Test
    public void cannot_pick_any_monsters_when_none_is_on_the_map() throws Exception {
        World world = World.builder().withSize(2, 1).build();
        Trainer trainer = new Trainer(world, position(0, 0, EAST));

        trainer.move(FORWARD);

        assertThat(trainer.countMonsters()).isEqualTo(0);
    }

    @Test
    public void pick_the_monsters_on_the_path() throws Exception {
        World world = World.builder()
                .withSize(2, 1)
                .withMonstersAt(new Coordinate(1, 0))
                .build();
        Trainer trainer = new Trainer(world, position(0, 0, EAST));

        trainer.move(FORWARD);

        assertThat(trainer.countMonsters()).isEqualTo(1);
    }

    @Test
    public void cannot_pick_the_monsters_outside_the_path() throws Exception {
        World world = World.builder()
                .withSize(2, 2)
                .withMonstersAt(new Coordinate(1, 0), new Coordinate(1, 1), new Coordinate(0, 1))
                .build();
        Trainer trainer = new Trainer(world, position(0, 0, EAST));

        trainer.move(FORWARD);

        assertThat(trainer.countMonsters()).isEqualTo(1);
    }

    @Test
    public void cannot_pick_the_same_monster_twice() throws Exception {
        World world = World.builder()
                .withSize(2, 1)
                .withMonstersAt(new Coordinate(1, 0))
                .build();
        Trainer trainer = new Trainer(world, position(0, 0, EAST));

        trainer.move(FORWARD, LEFT, LEFT, FORWARD, LEFT, LEFT, FORWARD);

        assertThat(trainer.countMonsters()).isEqualTo(1);
    }

    @Test
    public void cannot_pick_a_monster_while_rotating() throws Exception {
        World world = World.builder()
                .withSize(2, 1)
                .withMonstersAt(new Coordinate(1, 0), new Coordinate(1, 0))
                .build();

        Trainer trainer = new Trainer(world, position(0, 0, EAST));

        trainer.move(FORWARD, LEFT, LEFT);

        assertThat(trainer.countMonsters()).isEqualTo(1);
    }

    @Test
    public void picks_the_monster_on_the_start_position() throws Exception {
        World world = World.builder()
                .withSize(1, 1)
                .withMonstersAt(new Coordinate(0, 0))
                .build();

        Trainer trainer = new Trainer(world, position(0, 0, EAST));

        assertThat(trainer.countMonsters()).isEqualTo(1);
    }
}
