package fr.tarcaye.monstertrainer.domain;

import fr.tarcaye.monstertrainer.domain.position.Position;
import fr.tarcaye.monstertrainer.domain.world.IllegalCoordinateException;
import fr.tarcaye.monstertrainer.domain.world.World;

public class Trainer {
    private final World world;
    private Position position;
    private int pickedUpMonsters = 0;

    public Trainer(World world, Position position) throws IllegalCoordinateException {
        this.world = world;
        this.position = position;
        world.moveTrainerTo(position.getCoordinate());
        pickMonsterUp();
    }

    void move(Move... moves) {
        for (Move move: moves) {
            move(move);
        }
    }

    private void move(Move move) {
        Position wamtedPosition = position.apply(move);
        try {
            world.moveTrainerTo(wamtedPosition.getCoordinate());
            position = wamtedPosition;
        } catch (IllegalCoordinateException e) {
            // nothing to do
        }
        pickMonsterUp();
    }

    private void pickMonsterUp() {
        pickedUpMonsters += world.hasPickedUpMonster(position.getCoordinate()) ? 1 : 0;
    }

    Position locate() {
        return position;
    }

    int countMonsters() {
        return pickedUpMonsters;
    }
}
