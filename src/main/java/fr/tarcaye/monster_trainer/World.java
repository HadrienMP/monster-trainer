package fr.tarcaye.monster_trainer;

public class World {
    private Coordinates at;

    public World(int width, int height) {

    }

    public void place(Trainer trainer, Coordinates at) {

        this.at = at;
    }

    public Coordinates whereIs(Trainer trainer) {
        return at;
    }
}
