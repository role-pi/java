package controle;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    List<T> list();
    
    int insert(T t);
    
    boolean update(T t);
    
    boolean delete(T t);
}