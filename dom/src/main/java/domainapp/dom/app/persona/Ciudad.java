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
    
package domainapp.dom.app.persona;

import java.util.Collections;
import java.util.List;

public enum Ciudad {
	//Neuquen
	Neuquen ("Nequen"), Plottier("Plottier"), Zapala ("Zapala"), Cutral_Co("Cutral Co"), Centenario("Centenario"),
	//Rio Negro
	General_Roca("General Roca"), Cipolletti("Cipolletti"), Allen("Allen"), Ing_Huergo("Ing. Huergo"), Cervantes("Cervantes"), Villa_Regina("Villa Regina"),
	//La Pampa
	Santa_Rosa("Santa Rosa"), General_Acha("General Acha"), General_Pico("General Pico");
	
	private String nombre;
	
	Ciudad(String nombre){
		this.nombre=nombre;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String toString(){
		return nombre;
	}
	
	public static List<Ciudad> listarPor(final Provincia provincia) {
        return provincia != null? provincia.ciudades(): Collections.<Ciudad>emptyList();
    }
	
	public static String validar(final Provincia provincia, final Ciudad ciudad) {
        if(provincia == null) {
            return "Seleccione una provincia primero.";
        }
        return !provincia.ciudades().contains(ciudad)
                ? "La ciudad seleccionada es invalida para la provincia '" + provincia + "'"
                : null;
    }
}
