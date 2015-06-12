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

