package domainapp.dom.app.estadoalerta;

import java.sql.Timestamp;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Programmatic;

import domainapp.dom.app.alerta.AlertaMatafuego;
import domainapp.dom.app.alerta.AlertaVehiculo;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "Aplazado_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")

@DomainObject(objectType = "APLAZADO")

@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class Aplazado extends EstadoAlerta {

	public Aplazado() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Aplazado(Timestamp fechaCambio) {
		super(fechaCambio);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Aplazado";
	}

	@Override
	@Programmatic
	public void cambiarAlerta(AlertaMatafuego matafuego) {
		if (matafuego.getEstadoAnterior() == "Alerta Amarilla") {
			EstadoAlerta estado = new AlertaNaranja(new Timestamp(System.currentTimeMillis()));
			actualizarAlertaMatafuego(matafuego, estado);
		} else {
			EstadoAlerta estado = new AlertaRoja(new Timestamp(System.currentTimeMillis()));
			actualizarAlertaMatafuego(matafuego, estado);
		}
	}

	@Override
	@Programmatic
	public void cambiarAlerta(AlertaVehiculo vehiculo) {
		if (vehiculo.getEstadoAnterior() == "Alerta Amarilla") {
			EstadoAlerta estado = new AlertaNaranja(new Timestamp(System.currentTimeMillis()));
			actualizarAlertaVehiculo(vehiculo, estado);
		} else {
			EstadoAlerta estado = new AlertaRoja(new Timestamp(System.currentTimeMillis()));
			actualizarAlertaVehiculo(vehiculo, estado);
		}

	}

	@Override
	@Programmatic
	public void aplazarAlertas(AlertaMatafuego matafuego) {
		container.informUser("La alerta ya se encuentra aplazada");

	}

	@Override
	@Programmatic
	public void aplazarAlertas(AlertaVehiculo vehiculo) {
		container.informUser("La alerta ya se encuentra aplazada");

	}
	@Override
	public void finalizarAlertas(AlertaMatafuego matafuego) {
		EstadoAlerta estado = new Finalizada(new Timestamp(System.currentTimeMillis()));
		if(matafuego.getMantenimiento()==null)
			actualizarAlertaMatafuego(matafuego, estado);
		else
			container.warnUser("El Alerta no se puede eliminar, se encuentra asignado a un Mantenimiento");	
	}

	@Override
	public void finalizarAlertas(AlertaVehiculo vehiculo) {
		EstadoAlerta estado = new Finalizada(new Timestamp(System.currentTimeMillis()));
		if(vehiculo.getMantenimiento()==null)
			actualizarAlertaVehiculo(vehiculo, estado);
		else
			container.warnUser("El Alerta no se puede eliminar, se encuentra asignado a un Mantenimiento");
	}

}
