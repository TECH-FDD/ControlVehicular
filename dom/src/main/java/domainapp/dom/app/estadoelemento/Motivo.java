/*
 *  SIGAFV is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * SIGAFV is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with SIGAFV.  If not, see <http://www.gnu.org/licenses/>.
 */
    
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
