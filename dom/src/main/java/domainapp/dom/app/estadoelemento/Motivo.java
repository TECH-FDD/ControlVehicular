package domainapp.dom.app.estadoelemento;

import java.util.ArrayList;
import java.util.List;

public enum Motivo {
	//Desactivar.
	INUTILIZBLE ("Inutilizable"), ROTURA ("Rotura"), DESUSO ("Desuso"),

	//Activar
	ALTA("Alta en el Sistema"), REPARADO ("Elemento Reparado"), REACTIVADO("Reactivado");

	private String nombre;

	Motivo(String nombre){
		this.nombre=nombre;
	}

	public String getNombre(){
		return this.nombre;
	}

	public String toString(){
		return nombre;
	}

	public static List<Motivo> listar(final String accion) {
		List<Motivo> lista = new ArrayList<Motivo>();
		if (accion == "desactivar") {
			lista.add(INUTILIZBLE);
			lista.add(DESUSO);
			lista.add(ROTURA);
			return lista;
		} else if (accion == "activar") {
			lista.add(REACTIVADO);
			lista.add(REPARADO);
			lista.add(ALTA);
			return lista;
		}
		return null;
	}
}
