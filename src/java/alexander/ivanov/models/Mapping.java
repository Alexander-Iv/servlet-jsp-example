package alexander.ivanov.models;

import alexander.ivanov.models.object.User;

import java.util.List;

public interface Mapping<T> {
    List<T> findAll();
    void add(T obj);
    List<T> get(T obj);
    void del(T obj);
    boolean isExist(T obj);
}
