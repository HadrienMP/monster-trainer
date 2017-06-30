package fr.tarcaye.monstertrainer.domain;

import lombok.ToString;

@ToString
public class World {
    private final Borders borders;
    private Position trainerPosition;

    public World(int width, int height) {
        this.borders = new Borders(width-1, height-1);
    }

    public void place(Trainer trainer, Position position) {
        this.trainerPosition = position;
    }

    public Position whereIs(Trainer trainer) {
        return trainerPosition;
    }

    public void move(Trainer sacha, Move... moves) {
        for (Move move : moves) {
            Position wantedPosition = trainerPosition.apply(move);
            if (borders.isInside(wantedPosition.getCoordinate())) {
                trainerPosition = wantedPosition;
            }
        }
    }
}
