package controle;

import java.util.ArrayList;

import modelo.InsumoModel;

public class InsumoDAO {
    private ArrayList<InsumoModel> insumos = new ArrayList();
	
	public boolean insert(InsumoModel insum) {
		if (insum != null) {
			insumos.add(insum);
			return true;
		}
		return false;
	}
	
	public boolean update(InsumoModel insum, Integer insumoID) {
		if (insum != null) {
			insumos.add(insum);
			return true;
		}
		return false;
	}
	
	public boolean update(Integer insumoID) {
		return true;
	}
	
	public boolean delete(Integer insumoID) {
		return true;
	}
	
	public ArrayList<InsumoModel> listaInsumos() {
		return insumos;
	}

	public int getSelectedRow() {
		return 0;
	}
	
}
