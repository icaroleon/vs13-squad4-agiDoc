package service;

import java.util.ArrayList;

public interface IService<T> {
    T get(String id) throws Exception;
    ArrayList<T> getAll();
    T create(T object);
    T update(String id, T object) throws Exception;
    void delete(String id) throws Exception;
}
