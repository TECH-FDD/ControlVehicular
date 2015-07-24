package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Programmatic;

@DomainService(repositoryFor = Inactivo.class)
public class ServicioInactivo extends ServicioEstado {

	public ServicioInactivo() {
		super();
	}

	@Programmatic
	@Override
	public Estado desactivar(Timestamp fecha, Motivo motivo) {
		return null;
	}
}
