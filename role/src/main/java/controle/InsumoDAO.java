package controle;

import java.util.ArrayList;

import modelo.Insumo;

public class InsumoDAO implements DAO<Insumo> {
    @Override
    public ArrayList<Insumo> list() {
    	return new ArrayList<Insumo>();
    }

    public int insert(Insumo insumo) {
        return 0;
    }

    public boolean update(Insumo insumo) {
        return false;
    }

    public boolean delete(Insumo insumo) {
        return false;
    }

    private static InsumoDAO instance = null;

    public InsumoDAO() {
    	
    }
    
    public static InsumoDAO getInstance() {
        if (instance == null) {
            instance = new InsumoDAO();
        }
        return instance;
    }
}
