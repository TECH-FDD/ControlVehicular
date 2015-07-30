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

	@Override
	@Programmatic
	public Object[] activar(Timestamp fecha, Motivo motivo){
		Object[] arreglo = new Object[2];
		arreglo[0]= new Activo(new Timestamp(System.currentTimeMillis()), Motivo.REACTIVADO);
		arreglo[1]= "El Elemento, ha sido activado con exito.";
		return arreglo;
	}
}
