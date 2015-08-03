package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Programmatic;

@DomainService(repositoryFor = Activo.class)
public class ServicioActivo extends ServicioEstado{

	public ServicioActivo() {
		super();
	}

	@Override
	@Programmatic
	public Estado desactivar(Timestamp fecha, Motivo motivo) {
		Estado e;
		switch (motivo) {
		case DESUSO:
			e = new Inactivo(new Timestamp(System.currentTimeMillis()),motivo);
			return e;
		case ROTURA:
			e = new NecesitaReparacion(new Timestamp(System.currentTimeMillis()), motivo);
			return e;
		case INUTILIZBLE:
			e = new Baja(new Timestamp(System.currentTimeMillis()),motivo);
			return e;
		}
		return null;
	}

	@Override
	@Programmatic
	public Object[] activar(Timestamp fecha, Motivo motivo){
		Object[] arreglo = new Object[2];
		arreglo[0]= null;
		arreglo[1]= "El Elemento seleccionado, ya se encuentra en estado Activo.";
		return arreglo;
	}
}