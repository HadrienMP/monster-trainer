package fr.tarcaye.monster_trainer;

import java.util.ArrayList;
import java.util.List;

public class CircularList<T> extends ArrayList<T> {
    public CircularList(List<T> list) {
        super(list);
    }

    public T before(T t) {
        int j = indexOf(t)-1;
        j = j < 0 ? this.size() - 1 : j;
        return this.get(j);
    }
    public T after(T t) {
        int j = indexOf(t)+1;
        j = j >= this.size() ? 0 : j;
        return this.get(j);
    }
}
