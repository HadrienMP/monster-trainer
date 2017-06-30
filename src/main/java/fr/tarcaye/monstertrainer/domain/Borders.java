package fr.tarcaye.monstertrainer.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Borders {
    private final int maxX;
    private final int maxY;

    public boolean isInside(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        return 0 <= x && x <= maxX && 0 <= y && y <= maxY;
    }
}
