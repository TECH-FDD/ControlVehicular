package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

import org.apache.isis.applib.annotation.Programmatic;

public class ServicioReparacion extends ServicioEstado {

	public ServicioReparacion() {
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
		arreglo[1]= "El Gps seleccionado, actualmente se encuentra en estado de Reparación,"
				+ " por lo que no puede ser Reactivado.";
		return arreglo;
	}
}
