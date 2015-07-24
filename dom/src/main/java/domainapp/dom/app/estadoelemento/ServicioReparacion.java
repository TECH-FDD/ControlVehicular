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
}
