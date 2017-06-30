package fr.tarcaye.monstertrainer.domain;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;

import static fr.tarcaye.monstertrainer.domain.Direction.*;
import static fr.tarcaye.monstertrainer.domain.Move.FORWARD;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class WorldTest {

    @Test
    @Parameters(method = "illegalPositions")
    public void a_trainer_cannot_go_outside_the_borders(World world, Position start) throws Exception {
        Trainer sacha = new Trainer("sacha");
        world.place(sacha, start);

        world.move(sacha, FORWARD);

        assertThat(world.whereIs(sacha).getCoordinate()).isEqualTo(start.getCoordinate());
    }

    public Collection<Collection<Object>> illegalPositions() {
        return Arrays.asList(
                // X
                Arrays.asList(new World(1,1), new Position(new Coordinate(0,0), EAST)),
                Arrays.asList(new World(2,1), new Position(new Coordinate(1,0), EAST)),
                Arrays.asList(new World(1,1), new Position(new Coordinate(0,0), WEST)),

                // Y
                Arrays.asList(new World(1,1), new Position(new Coordinate(0,0), SOUTH)),
                Arrays.asList(new World(1,2), new Position(new Coordinate(0,1), SOUTH)),
                Arrays.asList(new World(1,1), new Position(new Coordinate(0,0), NORTH))
        );
    }
}