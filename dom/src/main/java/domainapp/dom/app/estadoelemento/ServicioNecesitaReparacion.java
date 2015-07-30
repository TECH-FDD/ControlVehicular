package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

import org.apache.isis.applib.annotation.Programmatic;

public class ServicioNecesitaReparacion extends ServicioEstado {

	public ServicioNecesitaReparacion() {
		super();
	}

	@Programmatic
	@Override
	public Estado desactivar(Timestamp fecha, Motivo motivo) {
		return null;
	}

	@Override
	@Programmatic
	public Object[] activar(Timestamp fecha, Motivo motivo){
		Object[] arreglo = new Object[2];
		arreglo[0]= null;
		arreglo[1]= "Se necesita confirmación de que el Elemento seleccionado, ha sido reparado, para su Reactivación";
		return arreglo;
	}
}
