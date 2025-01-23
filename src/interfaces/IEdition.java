package interfaces;

public interface IEdition<T> {
    boolean delete(int id);
    void view();
    void edit(int id, T item);
    void add(T item);
}
