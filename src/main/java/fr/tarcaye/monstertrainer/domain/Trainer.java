package fr.tarcaye.monstertrainer.domain;

import fr.tarcaye.monstertrainer.domain.position.Position;
import fr.tarcaye.monstertrainer.domain.world.IllegalCoordinateException;
import fr.tarcaye.monstertrainer.domain.world.World;

class Trainer {
    private final World world;
    private Position position;
    private int pickedUpMonsters = 0;

    Trainer(World world, Position position) throws IllegalCoordinateException {
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
        try {
            Position wantedPosition = position.apply(move);
            world.moveTrainerTo(wantedPosition.getCoordinate());
            position = wantedPosition;
            pickMonsterUp();
        } catch (IllegalCoordinateException e) {
            // If the new position is illegal the trainer stays where he is
        }
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
