package controle;

import java.util.ArrayList;

import modelo.Insumo;

public class InsumoDAO {
    private ArrayList<Insumo> insumos = new ArrayList();
	
	public boolean insert(Insumo insum) {
		if (insum != null) {
			insumos.add(insum);
			return true;
		}
		return false;
	}

	public ArrayList<Insumo> listaInsumos() {
		return insumos;
	}
	
	public boolean update(Insumo insum, Integer insumoID) {
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
	
	public boolean removerInsumo(int selectedRow) {
		if (selectedRow >= 0 && selectedRow < insumos.size()) {
			insumos.remove(selectedRow);
			return true;
		}
		return false;
	}
}
