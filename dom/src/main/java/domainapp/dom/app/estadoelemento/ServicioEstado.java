package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Programmatic;


@DomainService(repositoryFor = Estado.class)
public class ServicioEstado {

	public ServicioEstado() {
		super();
	}

	/**
	 * Obtener el Servicio correspondiente al Estado actual.
	 *
	 * @param estado
	 * @return servicio
	 */
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
	public Object[] activar(Timestamp fecha, Motivo motivo) {
		return null;
	}

	/**
	 * Verificar si se debe mostrar el boton Desactivar.
	 *
	 * @param estado
	 * @return Confirmacion
	 */
	@Programmatic
	public boolean ocultarDesactivar(Estado estado){
		if (estado instanceof Inactivo ||
				estado instanceof NecesitaReparacion ||
				estado instanceof Baja ||
				estado instanceof Reparacion){
			return true;
		}
		return false;
	}

	/**
	 * Verificar si se debe ocultar el boton Activar.
	 *
	 * @param estado
	 * @return Confirmacion
	 */
	@Programmatic
	public boolean ocultarActivar(Estado estado){
		if (estado instanceof Activo)
			return true;
		return false;
	}
}