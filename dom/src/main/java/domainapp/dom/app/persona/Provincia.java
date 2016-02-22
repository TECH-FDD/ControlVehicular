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

import java.util.Arrays;
import java.util.List;

public enum Provincia {
	Neuquen("Neuquen"){
		public List<Ciudad> ciudades() {
            return Arrays.asList(Ciudad.Zapala, Ciudad.Neuquen, Ciudad.Centenario, Ciudad.Cutral_Co, Ciudad.Plottier);
        }
	}, 
	RioNegro("Rio Negro"){
		public List<Ciudad> ciudades() {
            return Arrays.asList(Ciudad.Allen, Ciudad.Cervantes, Ciudad.Cipolletti, Ciudad.General_Roca, Ciudad.Ing_Huergo, Ciudad.Villa_Regina);
        }
	},
	La_Pampa("La Pampa"){
		public List<Ciudad> ciudades() {
            return Arrays.asList(Ciudad.General_Acha, Ciudad.General_Pico, Ciudad.Santa_Rosa);
        }
	}; 
	public abstract List<Ciudad> ciudades();
	
	private String nombre;
	
	Provincia(String nombre){
		this.nombre=nombre;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String toString(){
		return nombre;
	}
}

