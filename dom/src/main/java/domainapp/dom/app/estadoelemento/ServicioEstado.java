package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Programmatic;

@DomainService(repositoryFor = Estado.class)
public class ServicioEstado {

	public ServicioEstado() {
		super();
	}

	@Programmatic
	public ServicioEstado obtenerServicio(Estado estado) {
		// Según el estado del Gps, Instancio el servicio correspondiente.
		if (estado instanceof Activo) {
			ServicioEstado servicio = new ServicioActivo();
			return servicio;
		} else if (estado instanceof Inactivo) {
			ServicioEstado servicio = new ServicioInactivo();
			return servicio;
		} else if (estado instanceof NecesitaReparacion) {
			ServicioEstado servicio = new ServicioNecesitaReparacion();
			return servicio;
		} else if (estado instanceof Reparacion) {
			ServicioEstado servicio = new ServicioReparacion();
			return servicio;
		} else if (estado instanceof Baja) {
			ServicioEstado servicio = new ServicioBaja();
			return servicio;
		}
		return null;
	}

	@Programmatic
	public Estado desactivar(Timestamp fecha, Motivo motivo) {
		return null;
	}

	@Programmatic
	public Estado activar(Timestamp fecha, Motivo motivo) {
		return null;
	}
}
