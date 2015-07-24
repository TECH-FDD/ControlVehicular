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
}
