package controle;

import java.util.ArrayList;

public interface DAO<T> {
    ArrayList<T> list();
    
    int insert(T t);
    
    boolean update(T t);
    
    boolean delete(T t);
}