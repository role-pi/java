package controle;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    List<T> list();
    
    boolean insert(T t);
    
    boolean update(T t, String[] params);
    
    boolean delete(T t);
}