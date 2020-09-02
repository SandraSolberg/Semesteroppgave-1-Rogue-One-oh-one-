package inf101.v20.rogue101.game;

import java.util.Arrays;

/**
 * Generic bag for å legge til elementer for player
 *
 * @author Sandra Solberg
 */

public class PlayerBag<T> implements IPlayerBag<T> {

    private T[] list;
    private int length;

    public PlayerBag(){
        length = 0;
        list = (T[]) new Object[10];

    }


    @Override
    public void add(T t) {

        list[length] = t;
        length = length + 1;

        if (length == list.length) {
            list = Arrays.copyOf(list, list.length * 2);
        }

    }

    @Override
    public T get(int index) {
        return list[index];
    }

    @Override
    public T remove(int i) {
        T removeObj = list[i];

        for (int x = i; x < length - 1; x++) {
            list[x] = list[x + 1];
        }

        length = length - 1;
        return removeObj;
    }




    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean contains(T t) {
        for(int i = 0; i<list.length; i++){
            if(t == get(i)) {
                return true;
            }
        }
        return false;
    }

}
