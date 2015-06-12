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
