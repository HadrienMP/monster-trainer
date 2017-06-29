package fr.tarcaye.monster_trainer;

public class Trainer {
    private Position position;

    public Trainer(String name, World world, Position startPosition) {
        this.position = startPosition;
    }

    public Position position() {
        return position;
    }

    public void move(Move... moves) {
        for (Move move : moves) {
            position = position.apply(move);
        }
    }
}
