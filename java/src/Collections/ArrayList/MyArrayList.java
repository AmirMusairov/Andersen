package Collections.ArrayList;

import java.util.Iterator;

public interface MyArrayList<T> {

    public void add(T value);

    public T get(int index);

    public boolean set(int index, T value);

    public boolean add(int index, T value);

    public int indexOf(T value);

    public int size();

    public T remove(int index);

    public Iterator<T> iterator();
}
