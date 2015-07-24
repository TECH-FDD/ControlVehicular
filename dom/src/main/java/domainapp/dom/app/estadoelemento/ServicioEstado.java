package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Programmatic;

@DomainService(repositoryFor = Estado.class)
public class ServicioEstado {

	private ServicioEstado sInactivo;
	private ServicioEstado sActivo;
	private ServicioEstado sNecesitaRep;
	private ServicioEstado sReparacion;
	private ServicioEstado sBaja;

	public ServicioEstado() {
		super();
	}

	@Programmatic
	public Estado desactivar(Estado estado, Timestamp fecha, Motivo motivo) {
		// Según el estado del Gps, Instancio el servicio correspondiente.
		if (estado instanceof Activo) {
			sActivo = new ServicioActivo();
			Estado e = sActivo.desactivar(fecha, motivo);
			return e;
		} else if (estado instanceof Inactivo) {
			sInactivo = new ServicioInactivo();
			Estado e = sInactivo.desactivar(fecha, motivo);
			return e;
		} else if (estado instanceof NecesitaReparacion) {
			sNecesitaRep = new ServicioNecesitaReparacion();
			Estado e = sNecesitaRep.desactivar(fecha, motivo);
			return e;
		} else if (estado instanceof Reparacion) {
			sReparacion = new ServicioReparacion();
			Estado e = sReparacion.desactivar(fecha, motivo);
			return e;
		} else if (estado instanceof Baja) {
			sBaja = new ServicioBaja();
			Estado e = sBaja.desactivar(fecha, motivo);
			return e;
		}
		return null;
	}

	@Programmatic
	public Estado desactivar(Timestamp fecha, Motivo motivo) {
		return null;
	}
}
