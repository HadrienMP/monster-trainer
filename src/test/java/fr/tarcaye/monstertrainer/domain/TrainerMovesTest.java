package fr.tarcaye.monstertrainer.domain;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;

import static fr.tarcaye.monstertrainer.domain.Direction.*;
import static fr.tarcaye.monstertrainer.domain.Move.FORWARD;
import static fr.tarcaye.monstertrainer.domain.Position.position;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class TrainerMovesTest {

    @Test
    @Parameters(method = "againstTheBorders")
    public void a_trainer_cannot_go_outside_the_borders(World world, Position start) throws Exception {
        world.placeTrainerAt(start);
        world.moveTrainer(FORWARD);
        assertThat(world.whereIsTrainer().getCoordinate()).isEqualTo(start.getCoordinate());
    }

    public Collection<Collection<Object>> againstTheBorders() {
        return asList(
                // X
                asList(aWorld(1, 1).build(), position(0,0, EAST)),
                asList(aWorld(2, 1).build(), position(1,0, EAST)),
                asList(aWorld(1, 1).build(), position(0,0, WEST)),

                // Y
                asList(aWorld(1, 1).build(), position(0,0, SOUTH)),
                asList(aWorld(1, 2).build(), position(0,1, SOUTH)),
                asList(aWorld(1, 1).build(), position(0,0, NORTH))
        );
    }

    @Test
    public void a_trainer_cannot_go_over_a_mountain() throws Exception {
        // GIVEN
        World world = aWorld(2, 2)
                .withMountainsAt(
                        new Coordinate(1,0),
                        new Coordinate(0,1),
                        new Coordinate(1,1)
                )
                .build();

        Position startPosition = position(0, 0, EAST);
        world.placeTrainerAt(startPosition);

        // WHEN
        world.moveTrainer(FORWARD);

        // THEN
        Position endPosition = world.whereIsTrainer();
        assertThat(endPosition).isEqualTo(startPosition);
    }

    private WorldBuilder aWorld(int width, int height) {
        return World.builder().withSize(width, height);
    }
}