package dao;

import java.util.List;

public interface IDAO {
    public interface DAO<T>{
        T select(int id);
        List<T> selectAll();
        void insert(T t);
        void update(T t, int id);
        void delete(T t);

    }
}
